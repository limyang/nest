package com.ebay.nest.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.serializer.Deserializer;
import org.apache.hadoop.io.serializer.Serialization;
import org.apache.hadoop.io.serializer.Serializer;

import cascading.tuple.hadoop.SerializationToken;

import com.ebay.nest.primitive.DECIMAL;

@SerializationToken(tokens = { 133 }, classNames = { "com.ebay.nest.primitive.DECIMAL" })
public class DecimalSerialization extends Configured implements Serialization<DECIMAL> {

	public static class DecimalDeserializer implements Deserializer<DECIMAL> {
		private DataInputStream in;

		public void open(InputStream in) throws IOException {
			if (in instanceof DataInputStream)
				this.in = (DataInputStream) in;
			else
				this.in = new DataInputStream(in);
		}

		public DECIMAL deserialize(DECIMAL existing) throws IOException {
			int len = in.readInt();
			byte[] valueBytes = new byte[len];

			in.readFully(valueBytes);
			BigInteger value = new BigInteger(valueBytes);
			BigDecimal bd = new BigDecimal(value, in.readInt());

			return new DECIMAL(bd);
		}

		public void close() throws IOException {
			in.close();
		}
	}

	public static class DecimalSerializer implements Serializer<DECIMAL> {
		private DataOutputStream out;

		public void open(OutputStream out) throws IOException {
			if (out instanceof DataOutputStream)
				this.out = (DataOutputStream) out;
			else
				this.out = new DataOutputStream(out);
		}

		public void serialize(DECIMAL o) throws IOException {
			BigInteger value = o.decimalValue().unscaledValue();
			byte[] valueBytes = value.toByteArray();

			out.writeInt(valueBytes.length);
			out.write(valueBytes);
			out.writeInt(o.getAttribute().getScale());
		}

		public void close() throws IOException {
			out.close();
		}
	}

	public boolean accept(Class<?> c) {
		return DECIMAL.class == c;
	}

	public Deserializer<DECIMAL> getDeserializer(Class<DECIMAL> arg0) {
		return new DecimalDeserializer();
	}

	public Serializer<DECIMAL> getSerializer(Class<DECIMAL> arg0) {
		return new DecimalSerializer();
	}

}
