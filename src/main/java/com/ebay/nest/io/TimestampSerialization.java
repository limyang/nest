package com.ebay.nest.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.serializer.Deserializer;
import org.apache.hadoop.io.serializer.Serialization;
import org.apache.hadoop.io.serializer.Serializer;

import cascading.tuple.hadoop.SerializationToken;

import com.ebay.nest.primitive.TIMESTAMP;

@SerializationToken(tokens = { 163 }, classNames = { "com.ebay.nest.primitive.TIMESTAMP" })
public class TimestampSerialization extends Configured implements Serialization<TIMESTAMP> {

	public static class TimestampDeserializer implements Deserializer<TIMESTAMP> {
		private DataInputStream in;

		public void open(InputStream in) throws IOException {
			if (in instanceof DataInputStream)
				this.in = (DataInputStream) in;
			else
				this.in = new DataInputStream(in);
		}

		public TIMESTAMP deserialize(TIMESTAMP existing) throws IOException {
			long time = in.readLong();

			return new TIMESTAMP(time);
		}

		public void close() throws IOException {
			in.close();
		}
	}

	public static class TimestampSerializer implements Serializer<TIMESTAMP> {
		private DataOutputStream out;

		public void open(OutputStream out) throws IOException {
			if (out instanceof DataOutputStream)
				this.out = (DataOutputStream) out;
			else
				this.out = new DataOutputStream(out);
		}

		public void serialize(TIMESTAMP o) throws IOException {
			out.writeLong(o.getTime());
		}

		public void close() throws IOException {
			out.close();
		}
	}

	public boolean accept(Class<?> c) {
		return TIMESTAMP.class == c;
	}

	public Deserializer<TIMESTAMP> getDeserializer(Class<TIMESTAMP> arg0) {
		return new TimestampDeserializer();
	}

	public Serializer<TIMESTAMP> getSerializer(Class<TIMESTAMP> arg0) {
		return new TimestampSerializer();
	}
}
