//package com.ebay.nest.testdriven;
//
//import in.masr.utils.FilenameU;
//import in.masr.utils.StringU;
//
//import java.io.File;
//import java.io.FilenameFilter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import junit.framework.TestCase;
//
//import org.apache.commons.configuration.PropertiesConfiguration;
//import org.apache.commons.io.FileUtils;
//
//import com.ebay.nest.NestEngine;
//import com.ebay.nest.ddi.Audit;
//public class CQLTest extends TestCase {
//	private static final String DELIMETER = Character.toString((char) 127);
//	private static final String BATCH_USER = "coolmore";
//	private static final String AUDIT_DATE_VALUE = "2013-08-20";
//	private static final String UOW_FROM = "20130819000000";
//	private static final String UOW_TO = "20130820000000";
//	private static final String REAL_SNAPSHOT_DIR = FilenameU.join("snapshot",
//			"2013", "08", "20", "00");
//
//	private static final String EXPECT_SNAPSHOT_PATH = FilenameU.join(
//			"snapshot", "2013", "08", "20", "08", "result.dat");
//
//	private String LINE_SEPRATOR = "\n";
//
//	private String etlid = null;
//
//	private static PropertiesConfiguration config;
//
//	public CQLTest(String etlid) {
//		super("testEtlid");
//		this.etlid = etlid;
//	}
//
//	public CQLTest() {
//
//	}
//
//	public void setUp() {
//	}
//
//	public void testEtlid() {
//		if (etlid == null) {
//			ok();
//			return;
//		}
//		try {
//			config = Home.properties("config.ini");
//			String table = etlid.split("\\.")[1];
//			String[] fileNames = getCqlNamesOfEtlID(etlid);
//			if (fileNames.length == 0) {
//				System.err.println("Cannot find cql file for " + etlid);
//				fail();
//				return;
//			}
//			System.out.println("Start to run ETL ID : " + etlid);
//			System.out.println("Deleteing output files.");
//			FileUtils.deleteQuietly(new File(FilenameU.join(
//					config.getString("local_data_dir"), table,
//					REAL_SNAPSHOT_DIR)));
//			for (String fileName : fileNames) {
//				if (fileName == null) {
//					continue;
//				}
//				System.out.println("Start to run " + fileName);
//				NestEngine
//						.main(new String[] { "-etlid", etlid, "-cql", fileName,
//								"-uow_from", UOW_FROM, "-uow_to", UOW_TO,
//								"-data_dir",
//								config.getString("local_data_dir"), "-cql_dir",
//								config.getString("local_cql_dir"), "-test" });
//				System.out.println("Finish running " + fileName);
//			}
//			System.out.println("Finish running ETLID : " + etlid);
//			String expectResultPath = getExpectPath(etlid);
//			String realResultDir = getRealResultDir(etlid);
//			System.out.println("Expect result path : " + expectResultPath);
//			System.out.println("Real result dir : " + realResultDir);
//			if (!new File(expectResultPath).exists()) {
//				System.out.println("Expect Result Path  not exist !!!");
//				fail();
//			}
//			String expectResult = FileUtils.readFileToString(
//					new File(expectResultPath)).trim();
//
//			String realResult = getResultContent(realResultDir);
//			expectResult = StringU.sort(expectResult);
//			realResult = StringU.sort(realResult);
//			expectResult = transformAudit(expectResult, table);
//			realResult = transformAudit(realResult, table);
//			FileUtils.writeStringToFile(new File(expectResultPath + ".sort"),
//					expectResult);
//			FileUtils.writeStringToFile(
//					new File(FilenameU.join(realResultDir, "result.dat.sort")),
//					realResult);
//
//			if (!expectResult.equals(realResult)) {
//				System.out.println("Test failed......");
//				fail();
//				return;
//			}
//			System.out.println("Test successfully......");
//			ok();
//		} catch (Exception ex) {
//			System.err.println(ex.getMessage());
//			ex.printStackTrace();
//			fail();
//		}
//	}
//
//	private String getResultContent(String realResultDir) throws IOException {
//		File dir = new File(realResultDir);
//		String[] filenames = dir.list(new FilenameFilter() {
//			@Override
//			public boolean accept(File dir, String name) {
//				return name.matches("part-\\d+$");
//			}
//		});
//		if (filenames.length == 0) {
//			System.out.println("Real Result Path  not found !!!");
//			fail();
//			return null;
//		}
//		String content = "";
//		for (String filename : filenames) {
//			content += FileUtils.readFileToString(new File(FilenameU.join(
//					realResultDir, filename)));
//		}
//		return content;
//	}
//
//	private String transformAudit(String content, String table) {
//		table = table.toUpperCase();
//		String creDateName = Audit.getCreateDateFieldName(table);
//		String creUserName = Audit.getCreateUserFieldName(table);
//		String updDateName = Audit.getUpdateDateFieldName(table);
//		String updUserName = Audit.getUpdateUserFieldName(table);
//		int creDatePos = TeraMetadataU.getFieldPosition(table, creDateName);
//		if (creDatePos != -1) {
//			content = replaceUnEmptyGrid(content, creDatePos, AUDIT_DATE_VALUE);
//		}
//		int creUserPos = TeraMetadataU.getFieldPosition(table, creUserName);
//		if (creUserPos != -1) {
//			content = replaceUnEmptyGrid(content, creUserPos, BATCH_USER);
//		}
//		int updDatePos = TeraMetadataU.getFieldPosition(table, updDateName);
//		if (updDatePos != -1) {
//			content = replaceUnEmptyGrid(content, updDatePos, AUDIT_DATE_VALUE);
//		}
//		int updUserPos = TeraMetadataU.getFieldPosition(table, updUserName);
//		if (updUserPos != -1) {
//			content = replaceUnEmptyGrid(content, updUserPos, BATCH_USER);
//		}
//		return content;
//	}
//
//	private String replaceUnEmptyGrid(String content, int pos, String value) {
//		String lines[] = content.split(LINE_SEPRATOR);
//		int lineCount = lines.length;
//		String newLines[] = new String[lineCount];
//		int i = 0;
//		for (String line : lines) {
//			String[] grids = line.split(DELIMETER, -1);
//			if (grids[pos].isEmpty()) {
//				newLines[i++] = line;
//			} else {
//				grids[pos] = value;
//				newLines[i++] = StringU.join(grids, DELIMETER);
//			}
//		}
//		String newContent = StringU.join(newLines, LINE_SEPRATOR);
//		return newContent;
//	}
//
//	private String getExpectPath(String etlid) {
//		String table = etlid.split("\\.")[1];
//		String expectResultPath = FilenameU
//				.join(config.getString("local_data_dir"), table,
//						EXPECT_SNAPSHOT_PATH);
//		return expectResultPath;
//	}
//
//	private String getRealResultDir(String etlid) {
//		String table = etlid.split("\\.")[1];
//		String expectResultPath = FilenameU.join(
//				config.getString("local_data_dir"), table, REAL_SNAPSHOT_DIR);
//		return expectResultPath;
//	}
//
//	private String[] getCqlNamesOfEtlID(String etlid) {
//		File dir = new File(config.getString("local_cql_dir"));
//		List<String> list = new ArrayList<String>();
//		for (String fileName : dir.list()) {
//			Pattern pattern = Pattern.compile(etlid
//					+ "\\.(.*?\\.)?(\\d+)\\.cql");
//			Matcher matcher = pattern.matcher(fileName);
//			if (matcher.find()) {
//				String seq = matcher.group(2);
//				int i = Integer.parseInt(seq);
//				list.add(fileName);
//			}
//
//		}
//		return list.toArray(new String[0]);
//
//	}
//
//	private void ok() {
//		assertTrue(true);
//	}
//}
