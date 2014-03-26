package com.ebay.nest.shell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;

import sun.misc.Signal;
import sun.misc.SignalHandler;

import com.ebay.nest.shell.SessionState.LogHelper;

public class ShellDriver {
	private final LogHelper console;
	private Configuration conf;

	public ShellDriver() {
		SessionState ss = SessionState.get();
		conf = (ss != null) ? ss.getConf() : new Configuration();
		Log LOG = LogFactory.getLog("ShellDriver");
		console = new LogHelper(LOG);
	}

	public static void main(String[] args) throws Exception {
		int ret = new ShellDriver().run(args);
		System.exit(ret);
	}

	public int run(String[] args) throws Exception {

		return 0;
	}

	private String[] tokenizeCmd(String cmd) {
		return cmd.split("\\s+");
	}

	private String getFirstCmd(String cmd, int length) {
		return cmd.substring(length).trim();
	}

	public int processCmd(String cmd) {
		SessionState ss = SessionState.get();
		ss.err.flush();
		String cmd_trimmed = cmd.trim();
		String[] tokens = tokenizeCmd(cmd_trimmed);
		int ret = 0;

		if (cmd_trimmed.toLowerCase().equals("quit") || cmd_trimmed.toLowerCase().equals("exit")) {

			ss.close();
			System.exit(0);

		} else if (tokens[0].equalsIgnoreCase("source")) {
			String cmd_1 = getFirstCmd(cmd_trimmed, tokens[0].length());

			File sourceFile = new File(cmd_1);
			if (!sourceFile.isFile()) {
				console.printError("File: " + cmd_1 + " is not a file.");
				ret = 1;
			} else {
				try {
					this.processFile(cmd_1);
				} catch (IOException e) {
					console.printError("Failed processing file " + cmd_1 + " " + e.getLocalizedMessage(),
							org.apache.hadoop.util.StringUtils.stringifyException(e));
					ret = 1;
				}
			}
		} else if (tokens[0].toLowerCase().equals("list")) {

			SessionState.ResourceType t;
			if (tokens.length < 2 || (t = SessionState.find_resource_type(tokens[1])) == null) {
				console.printError("Usage: list [" + StringUtils.join(SessionState.ResourceType.values(), "|")
						+ "] [<value> [<value>]*]");
				ret = 1;
			} else {
				List<String> filter = null;
				if (tokens.length >= 3) {
					System.arraycopy(tokens, 2, tokens, 0, tokens.length - 2);
					filter = Arrays.asList(tokens);
				}
				Set<String> s = ss.list_resource(t, filter);
				if (s != null && !s.isEmpty()) {
					ss.out.println(StringUtils.join(s, "\n"));
				}
			}
		} else {
			try {
				ret = processLocalCmd(cmd, ss);
			} catch (Exception e) {
				console.printError("Failed processing command " + tokens[0] + " " + e.getLocalizedMessage(),
						org.apache.hadoop.util.StringUtils.stringifyException(e));
				ret = 1;
			}
		}

		return ret;
	}

	public int processLine(String line, boolean allowInterupting) {
		SignalHandler oldSignal = null;
		Signal interupSignal = null;

		if (allowInterupting) {
			interupSignal = new Signal("INT");
			oldSignal = Signal.handle(interupSignal, new SignalHandler() {
				private final Thread cliThread = Thread.currentThread();
				private boolean interruptRequested;

				@Override
				public void handle(Signal signal) {
					boolean initialRequest = !interruptRequested;
					interruptRequested = true;

					if (!initialRequest) {
						console.printInfo("Exiting the JVM");
						System.exit(127);
					}

					console.printInfo("Interrupting... Be patient, this might take some time.");
					console.printInfo("Press Ctrl+C again to kill JVM");

					this.cliThread.interrupt();
				}
			});
		}

		try {
			int lastRet = 0, ret = 0;

			String command = "";
			for (String oneCmd : line.split(";")) {

				if (StringUtils.endsWith(oneCmd, "\\")) {
					command += StringUtils.chop(oneCmd) + ";";
					continue;
				} else {
					command += oneCmd;
				}
				if (StringUtils.isBlank(command)) {
					continue;
				}

				ret = processCmd(command);

				command = "";
				lastRet = ret;
			}
			return lastRet;
		} finally {
			if (oldSignal != null && interupSignal != null) {
				Signal.handle(interupSignal, oldSignal);
			}
		}
	}

	public int processReader(BufferedReader r) throws IOException {
		String line;
		StringBuilder qsb = new StringBuilder();

		while ((line = r.readLine()) != null) {
			if (!line.startsWith("--")) {
				qsb.append(line + "\n");
			}
		}

		return (processLine(qsb.toString()));
	}

	public int processLine(String line) {
		return processLine(line, false);
	}

	public int processFile(String fileName) throws IOException {
		FileReader fileReader = null;
		BufferedReader bufferReader = null;
		int rc = 0;
		try {
			fileReader = new FileReader(fileName);
			bufferReader = new BufferedReader(fileReader);
			rc = processReader(bufferReader);
			bufferReader.close();
			bufferReader = null;
		} finally {
			IOUtils.closeStream(bufferReader);
		}
		return rc;
	}

	int processLocalCmd(String cmd, SessionState ss) {
		int tryCount = 0;
		boolean needRetry;
		int ret = 0;

		do {
			try {
				needRetry = false;

				Proc qp = new Proc();
				PrintStream out = ss.out;
				long start = System.currentTimeMillis();
				if (ss.getIsVerbose()) {
					out.println(cmd);
				}
				ret = qp.run(cmd).getResponseCode();
				if (ret != 0) {
					qp.close();
					return ret;
				}

				ArrayList<String> res = new ArrayList<String>();

				int counter = 0;
				try {
					while (qp.getResults(res)) {
						for (String r : res) {
							out.println(r);
						}
						counter += res.size();
						res.clear();
						if (out.checkError()) {
							break;
						}
					}
				} catch (IOException e) {
					console.printError("Failed with exception " + e.getClass().getName() + ":" + e.getMessage(), "\n"
							+ org.apache.hadoop.util.StringUtils.stringifyException(e));
					ret = 1;
				}

				int cret = qp.close();
				if (ret == 0) {
					ret = cret;
				}

				long end = System.currentTimeMillis();
				double timeTaken = (end - start) / 1000.0;
				console.printInfo("Time taken: " + timeTaken + " seconds"
						+ (counter == 0 ? "" : ", Fetched: " + counter + " row(s)"));

			} catch (Exception e) {
				console.printInfo("Retry query with a different approach...");
				tryCount++;
				if (tryCount > 3) {
					needRetry = false;
				} else
					needRetry = true;
			}
		} while (needRetry);

		return ret;
	}

}
