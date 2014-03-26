package com.ebay.nest.cascading.operation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cascading.flow.FlowProcess;
import cascading.operation.BaseOperation;
import cascading.operation.Function;
import cascading.operation.FunctionCall;
import cascading.tuple.Fields;
import cascading.tuple.TupleEntry;

import com.ebay.nest.Data;
import com.ebay.nest.metastore.TableMetadata;
import com.ebay.nest.primitive.BIGINT;
import com.ebay.nest.primitive.BYTE;
import com.ebay.nest.primitive.BYTEINT;
import com.ebay.nest.primitive.CHAR;
import com.ebay.nest.primitive.DATE;
import com.ebay.nest.primitive.DECIMAL;
import com.ebay.nest.primitive.FLOAT;
import com.ebay.nest.primitive.INTEGER;
import com.ebay.nest.primitive.SMALLINT;
import com.ebay.nest.primitive.TIME;
import com.ebay.nest.primitive.TIMESTAMP;

public class CastTypeOP extends BaseOperation implements Function {

	private static final long serialVersionUID = -5605997709031258692L;
	private final String[] names;
	private final byte[] types;
	private final int[][] params;
	private final TableMetadata meta;
	static final private Log LOG = LogFactory.getLog(CastTypeOP.class.getName());

	public CastTypeOP(Fields declaredFields, TableMetadata meta) {
		super(declaredFields);
		if (meta == null) {
			throw new IllegalArgumentException("expected meta should not be null");
		}
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < declaredFields.size(); i++) {
			list.add((String) declaredFields.get(i));

		}
		this.names = list.toArray(new String[list.size()]);
		this.params = new int[names.length][];
		this.types = new byte[names.length];
		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			String type = meta.getFieldType(name);
			if (type.startsWith("byteint")) {
				this.types[i] = Data.BYTEINT;
			} else if (type.startsWith("smallint")) {
				this.types[i] = Data.SMALLINT;
			} else if (type.startsWith("integer")) {
				this.types[i] = Data.INTEGER;
			} else if (type.startsWith("bigint")) {
				this.types[i] = Data.BIGINT;
			} else if (type.startsWith("float")) {
				this.types[i] = Data.FLOAT;
			} else if (type.startsWith("decimal")) {
				this.types[i] = Data.DECIMAL;
				int[] param = getParamFromType(type);
				this.params[i] = new int[2];
				int scale = 0;
				int pre = 12;
				if (param.length == 1) {
					pre = param[0];
				} else if (param.length == 2) {
					scale = param[1];
					pre = param[0];
				}
				this.params[i][0] = pre;
				params[i][1] = scale;

			} else if (type.startsWith("byte")) {
				this.types[i] = Data.BYTE;
				int[] param = getParamFromType(type);
				int length = 1;
				if (param.length == 1) {
					length = param[0];
				}
				params[i] = new int[1];
				params[i][0] = length;

			} else if (type.startsWith("varbyte")) {
				this.types[i] = Data.VARBYTE;
				int[] param = getParamFromType(type);
				int length = 1;
				if (param.length == 1) {
					length = param[0];
				}
				params[i] = new int[1];
				params[i][0] = length;

			} else if (type.startsWith("blob")) {
				this.types[i] = Data.BLOB;
				throw new RuntimeException("Currently we did not handle BLOB data");
			} else if (type.startsWith("char")) {
				this.types[i] = Data.CHAR;
				int[] param = getParamFromType(type);
				int length = 1;
				if (param.length == 1) {
					length = param[0];
				}
				params[i] = new int[1];
				params[i][0] = length;

			} else if (type.startsWith("varchar")) {
				this.types[i] = Data.VARCHAR;
				int[] param = getParamFromType(type);
				int length = 6400;
				if (param.length == 1) {
					length = param[0];
				}
				params[i] = new int[1];
				params[i][0] = length;

			} else if (type.startsWith("clob")) {
				this.types[i] = Data.CLOB;
				throw new RuntimeException("Currently we did not handle CLOB data");
			} else if (type.startsWith("date")) {
				this.types[i] = Data.DATE;
			} else if (type.startsWith("timestamp")) {
				this.types[i] = Data.TIMESTAMP;
			} else if (type.startsWith("time")) {
				this.types[i] = Data.TIME;
			} else if (type.startsWith("interval")) {
				this.types[i] = Data.INTERVAL;
				throw new RuntimeException("Currently we did not handle INTERVAL data");
			} else if (type.startsWith("period")) {
				this.types[i] = Data.PEROID;
				throw new RuntimeException("Currently we did not handle PERIOD data");
			} else {
				this.types[i] = Data.UNKNOWN;
				throw new RuntimeException("Unknown data");
			}
		}

		this.meta = meta;
	}

	private int[] toIntArray(Integer[] array) {
		if (array == null) {
			return null;

		} else if (array.length == 0) {
			return new int[] {};
		}

		final int[] result = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = array[i].intValue();

		}
		return result;

	}

	protected TupleEntry process(TupleEntry entry) {

		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			byte type = this.types[i];
			Object o = entry.getObject(name);
			if (o == null) {
				continue;
			}
			String value = o.toString();
			if ("".equals(value)) {
				entry.setObject(name, null); // empty string is null
				continue;
			}

			switch (type) {
			case Data.BYTEINT:
				BYTEINT byteint = BYTEINT.valueOf(value);
				entry.setObject(name, byteint);
				break;
			case Data.SMALLINT:
				SMALLINT smallint = SMALLINT.valueOf(value);
				entry.setObject(name, smallint);
				break;
			case Data.INTEGER:
				INTEGER integer = INTEGER.valueOf(value);
				entry.setObject(name, integer);
				break;
			case Data.BIGINT:
				BIGINT bigint = BIGINT.valueOf(value);
				entry.setObject(name, bigint);
				break;
			case Data.FLOAT:
				FLOAT f = FLOAT.valueOf(value);
				entry.setObject(name, f);
				break;
			case Data.DECIMAL:
				DECIMAL d = DECIMAL.valueOf(value, params[i][1]);
				entry.setObject(name, d);
				break;
			case Data.CHAR:
				boolean isCase = meta.isCaseSensitive(name);
				CHAR string = new CHAR(value, params[i][0], isCase, true);
				entry.setObject(name, string);
				break;
			case Data.VARCHAR:
				isCase = meta.isCaseSensitive(name);
				string = new CHAR(value, params[i][0], isCase, false);
				entry.setObject(name, string);
				break;
			case Data.CLOB:
				entry.setObject(name, null);
				break;
			case Data.BYTE:
				byte[] bytes = value.getBytes();
				BYTE by = new BYTE(bytes, params[i][0], true);
				entry.setObject(name, by);
				break;
			case Data.VARBYTE:
				bytes = value.getBytes();
				by = new BYTE(bytes, params[i][0], false);
				entry.setObject(name, by);
				break;
			case Data.BLOB:
				entry.setObject(name, null);
				break;
			case Data.DATE:
				String format = meta.getFieldFormat(name);
				DATE date = DATE.valueOf(value, format);
				entry.setObject(name, date);
				break;
			case Data.TIME:
				format = meta.getFieldFormat(name);
				TIME time = TIME.valueOf(value, format);
				entry.setObject(name, time);
				break;
			case Data.TIMESTAMP:
				format = meta.getFieldFormat(name);
				TIMESTAMP ts = TIMESTAMP.valueOf(value, format);
				entry.setObject(name, ts);
				break;
			case Data.PEROID:
				entry.setObject(name, null);
				break;
			case Data.INTERVAL:
				entry.setObject(name, null);
				break;
			default:
				entry.setObject(name, null);
				break;
			}

		}
		return entry;
	}

	public void operate(FlowProcess flowProcess, FunctionCall functionCall) {
		TupleEntry entry = functionCall.getArguments();
		entry = new TupleEntry(entry);
		entry = process(entry);
		functionCall.getOutputCollector().add(entry);

	}

	private int[] getParamFromType(String s) {
		List<Integer> list = new ArrayList<Integer>();
		if (s.indexOf("(") != -1) {
			s = s.substring(s.indexOf("(") + 1);
			if (s.indexOf(')') != -1) {
				try {
					s = s.substring(0, s.indexOf(')'));
					String[] sp = s.split(",");
					for (String l : sp) {
						l = l.trim().toLowerCase();
						if (l.endsWith("k")) {
							l = l.substring(0, l.length() - 1);
							Integer value = Integer.valueOf(l);
							value = value * 1024;
							list.add(value);
						} else if (l.endsWith("m")) {
							l = l.substring(0, l.length() - 1);
							Integer value = Integer.valueOf(l);
							value = value * 1024 * 1024;
							list.add(value);
						} else {
							Integer value = Integer.valueOf(l);
							list.add(value);
						}

					}
				} catch (NumberFormatException e) {

				}
			}
		}
		return toIntArray(list.toArray(new Integer[list.size()]));
	}

}
