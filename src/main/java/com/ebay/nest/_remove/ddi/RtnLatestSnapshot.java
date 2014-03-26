package com.ebay.nest._remove.ddi;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 
import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
 
public class RtnLatestSnapshot {
    private String user;
    private String password;
    private static final String ip = "ares-cli.vip.ebay.com";
    private Connection conn;
    private static final int TIME_OUT = 1000 * 5 * 60;
 
    public RtnLatestSnapshot(String user, String password) {
       this.user = user;
       this.password = password;
    }
 
    private boolean login() throws IOException {
       conn = new Connection(ip);
       conn.connect();
       return conn.authenticateWithPassword(user, password);
    }
 
    private String processStream(InputStream in) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(in));
       StringBuffer buffer = new StringBuffer();
       String line = "";
       try {
           while ((line = br.readLine()) != null) {
              buffer.append(line);
           }
       } catch (IOException e) {
           System.out.println(e.toString());
       } finally {
           br.close();
       }
       return buffer.toString();
    }
 
    public String getLastestSnapshot(String base, String table)
           throws IOException {
       InputStream stdOut = null;
       String outStr = "";
       int ret = -1;
       if (login()) {
           Session session = conn.openSession();
           session.execCommand("hadoop fs -lsr " + base + "/" + table
                  + "/snapshot | grep '^d'");
           stdOut = new StreamGobbler(session.getStdout());
           outStr = processStream(stdOut);
           session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);
           ret = session.getExitStatus();
           if (ret == 0) {
              String[] outToken = outStr.split(" ");
              return outToken[outToken.length - 1];
           }
       }
       return null;
    }
}