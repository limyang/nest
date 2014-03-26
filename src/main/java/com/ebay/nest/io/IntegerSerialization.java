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

import com.ebay.nest.primitive.INTEGER;

@SerializationToken(tokens = { 131 }, classNames = { "com.ebay.nest.primitive.INTEGER" })
public class IntegerSerialization extends Configured implements Serialization<INTEGER> {

	public static class IntegerDeserializer implements Deserializer<INTEGER> {
		private DataInputStream in;

		public void open(InputStream in) throws IOException {
			if (in instanceof DataInputStream)
				this.in = (DataInputStream) in;
			else
				this.in = new DataInputStream(in);
		}

		public INTEGER deserialize(INTEGER existing) throws IOException {
			int i = in.readInt();
			return new INTEGER(i);
		}

		public void close() throws IOException {
			in.close();
		}
	}

	public static class IntegerSerializer implements Serializer<INTEGER> {
		private DataOutputStream out;

		public void open(OutputStream out) throws IOException {
			if (out instanceof DataOutputStream)
				this.out = (DataOutputStream) out;
			else
				this.out = new DataOutputStream(out);
		}

		public void serialize(INTEGER o) throws IOException {
			out.writeInt(o.intValue());
		}

		public void close() throws IOException {
			out.close();
		}
	}

	public boolean accept(Class<?> c) {
		return INTEGER.class == c;
	}

	public Deserializer<INTEGER> getDeserializer(Class<INTEGER> arg0) {
		return new IntegerDeserializer();
	}

	public Serializer<INTEGER> getSerializer(Class<INTEGER> arg0) {
		return new IntegerSerializer();
	}

}