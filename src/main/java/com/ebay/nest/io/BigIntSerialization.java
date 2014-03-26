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

import com.ebay.nest.primitive.BIGINT;

@SerializationToken(tokens = {132}, classNames = {"com.ebay.nest.primitive.BIGINT"})
public class BigIntSerialization extends Configured implements Serialization<BIGINT> {

	public static class BigIntDeserializer implements Deserializer<BIGINT> {
		private DataInputStream in;

		public void open(InputStream in) throws IOException {
			if (in instanceof DataInputStream)
				this.in = (DataInputStream) in;
			else
				this.in = new DataInputStream(in);
		}

		public BIGINT deserialize(BIGINT existing) throws IOException {
			long l = in.readLong();

			return new BIGINT(l);
		}

		public void close() throws IOException {
			in.close();
		}
	}

	public static class BigIntSerializer implements Serializer<BIGINT> {
		private DataOutputStream out;

		public void open(OutputStream out) throws IOException {
			if (out instanceof DataOutputStream)
				this.out = (DataOutputStream) out;
			else
				this.out = new DataOutputStream(out);
		}

		public void serialize(BIGINT o) throws IOException {
			long value = o.longValue();

			out.writeLong(value);
		}

		public void close() throws IOException {
			out.close();
		}
	}

	public boolean accept(Class<?> c) {
		return BIGINT.class == c;
	}

	public Deserializer<BIGINT> getDeserializer(Class<BIGINT> arg0) {
		return new BigIntDeserializer();
	}

	public Serializer<BIGINT> getSerializer(Class<BIGINT> arg0) {
		return new BigIntSerializer();
	}

}
