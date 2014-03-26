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

import com.ebay.nest.primitive.BOOLEAN;

@SerializationToken(tokens = { 225 }, classNames = { "com.ebay.nest.primitive.BOOLEAN" })
public class BooleanSerialization extends Configured implements Serialization<BOOLEAN> {

	public static class BooleanDeserializer implements Deserializer<BOOLEAN> {
		private DataInputStream in;

		public void open(InputStream in) throws IOException {
			if (in instanceof DataInputStream)
				this.in = (DataInputStream) in;
			else
				this.in = new DataInputStream(in);
		}

		public BOOLEAN deserialize(BOOLEAN existing) throws IOException {
			boolean l = in.readBoolean();
			if (l) {
				return BOOLEAN.TRUE;
			}

			return BOOLEAN.FALSE;
		}

		public void close() throws IOException {
			in.close();
		}
	}

	public static class BooleanSerializer implements Serializer<BOOLEAN> {
		private DataOutputStream out;

		public void open(OutputStream out) throws IOException {
			if (out instanceof DataOutputStream)
				this.out = (DataOutputStream) out;
			else
				this.out = new DataOutputStream(out);
		}

		public void serialize(BOOLEAN o) throws IOException {
			boolean value = o.getBoolean();

			out.writeBoolean(value);
		}

		public void close() throws IOException {
			out.close();
		}
	}

	public boolean accept(Class<?> c) {
		return BOOLEAN.class == c;
	}

	public Deserializer<BOOLEAN> getDeserializer(Class<BOOLEAN> arg0) {
		return new BooleanDeserializer();
	}

	public Serializer<BOOLEAN> getSerializer(Class<BOOLEAN> arg0) {
		return new BooleanSerializer();
	}
}