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

import com.ebay.nest.primitive.DECIMAL;
import com.ebay.nest.primitive.FLOAT;

@SerializationToken(tokens = { 134 }, classNames = { "com.ebay.nest.primitive.FLOAT" })
public class FloatSerialization extends Configured implements Serialization<FLOAT> {

	public static class FloatDeserializer implements Deserializer<FLOAT> {
		private DataInputStream in;

		public void open(InputStream in) throws IOException {
			if (in instanceof DataInputStream)
				this.in = (DataInputStream) in;
			else
				this.in = new DataInputStream(in);
		}

		public FLOAT deserialize(FLOAT existing) throws IOException {
			double f = in.readDouble();
			return new FLOAT(f);
		}

		public void close() throws IOException {
			in.close();
		}
	}

	public static class FloatSerializer implements Serializer<FLOAT> {
		private DataOutputStream out;

		public void open(OutputStream out) throws IOException {
			if (out instanceof DataOutputStream)
				this.out = (DataOutputStream) out;
			else
				this.out = new DataOutputStream(out);
		}

		public void serialize(FLOAT o) throws IOException {
			out.writeDouble(o.floatValue());
		}

		public void close() throws IOException {
			out.close();
		}
	}

	public boolean accept(Class<?> c) {
		return FLOAT.class == c;
	}

	public Deserializer<FLOAT> getDeserializer(Class<FLOAT> arg0) {
		return new FloatDeserializer();
	}

	public Serializer<FLOAT> getSerializer(Class<FLOAT> arg0) {
		return new FloatSerializer();
	}

}
