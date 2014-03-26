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

import com.ebay.nest.primitive.BYTE;

@SerializationToken(tokens = { 209 }, classNames = { "com.ebay.nest.primitive.BYTE" })
public class ByteSerialization extends Configured implements Serialization<BYTE> {

	public static class ByteDeserializer implements Deserializer<BYTE> {
		private DataInputStream in;

		public void open(InputStream in) throws IOException {
			if (in instanceof DataInputStream)
				this.in = (DataInputStream) in;
			else
				this.in = new DataInputStream(in);
		}

		public BYTE deserialize(BYTE existing) throws IOException {
			int length = in.readInt();
			boolean isFixed = in.readBoolean();
			int l = in.readInt();
			byte[] valueBytes = new byte[l];
			in.readFully(valueBytes);
			return new BYTE(valueBytes, length, isFixed);
		}

		public void close() throws IOException {
			in.close();
		}
	}

	public static class ByteSerializer implements Serializer<BYTE> {
		private DataOutputStream out;

		public void open(OutputStream out) throws IOException {
			if (out instanceof DataOutputStream)
				this.out = (DataOutputStream) out;
			else
				this.out = new DataOutputStream(out);
		}

		public void serialize(BYTE o) throws IOException {
			out.writeInt(o.getAttribute().getLength());
			out.writeBoolean(o.getAttribute().isFixed());
			out.writeInt(o.getBytes().length);
			out.write(o.getBytes());
		}

		public void close() throws IOException {
			out.close();
		}
	}

	public boolean accept(Class<?> c) {
		return BYTE.class == c;
	}

	public Deserializer<BYTE> getDeserializer(Class<BYTE> arg0) {
		return new ByteDeserializer();
	}

	public Serializer<BYTE> getSerializer(Class<BYTE> arg0) {
		return new ByteSerializer();
	}

}