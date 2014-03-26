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
import com.ebay.nest.primitive.BYTEINT;

@SerializationToken(tokens = { 129 }, classNames = { "com.ebay.nest.primitive.BYTEINT" })
public class ByteIntSerialization extends Configured implements Serialization<BYTEINT> {

	public static class ByteIntDeserializer implements Deserializer<BYTEINT> {
		private DataInputStream in;

		public void open(InputStream in) throws IOException {
			if (in instanceof DataInputStream)
				this.in = (DataInputStream) in;
			else
				this.in = new DataInputStream(in);
		}

		public BYTEINT deserialize(BYTEINT existing) throws IOException {
			byte l = in.readByte();

			return new BYTEINT(l);
		}

		public void close() throws IOException {
			in.close();
		}
	}

	public static class ByteIntSerializer implements Serializer<BYTEINT> {
		private DataOutputStream out;

		public void open(OutputStream out) throws IOException {
			if (out instanceof DataOutputStream)
				this.out = (DataOutputStream) out;
			else
				this.out = new DataOutputStream(out);
		}

		public void serialize(BYTEINT o) throws IOException {
			byte value = o.byteValue();

			out.writeByte(value);
		}

		public void close() throws IOException {
			out.close();
		}
	}

	public boolean accept(Class<?> c) {
		return BYTEINT.class == c;
	}

	public Deserializer<BYTEINT> getDeserializer(Class<BYTEINT> arg0) {
		return new ByteIntDeserializer();
	}

	public Serializer<BYTEINT> getSerializer(Class<BYTEINT> arg0) {
		return new ByteIntSerializer();
	}

}