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

import com.ebay.nest.primitive.CHAR;

@SerializationToken(tokens = { 145 }, classNames = { "com.ebay.nest.primitive.CHAR" })
public class CharSerialization extends Configured implements Serialization<CHAR> {

	public static class CharDeserializer implements Deserializer<CHAR> {
		private DataInputStream in;

		public void open(InputStream in) throws IOException {
			if (in instanceof DataInputStream)
				this.in = (DataInputStream) in;
			else
				this.in = new DataInputStream(in);
		}

		public CHAR deserialize(CHAR existing) throws IOException {
			String s = in.readUTF();
			int length = in.readInt();
			boolean isCase = in.readBoolean();
			boolean isFixed = in.readBoolean();

			return new CHAR(s, length, isCase, isFixed);
		}

		public void close() throws IOException {
			in.close();
		}
	}

	public static class CharSerializer implements Serializer<CHAR> {
		private DataOutputStream out;

		public void open(OutputStream out) throws IOException {
			if (out instanceof DataOutputStream)
				this.out = (DataOutputStream) out;
			else
				this.out = new DataOutputStream(out);
		}

		public void serialize(CHAR o) throws IOException {
			out.writeUTF(o.getString());
			out.writeInt(o.getAttribute().getLength());
			out.writeBoolean(o.getAttribute().isCaseSensitive());
			out.writeBoolean(o.getAttribute().isFixed());
		}

		public void close() throws IOException {
			out.close();
		}
	}

	public boolean accept(Class<?> c) {
		return CHAR.class == c;
	}

	public Deserializer<CHAR> getDeserializer(Class<CHAR> arg0) {
		return new CharDeserializer();
	}

	public Serializer<CHAR> getSerializer(Class<CHAR> arg0) {
		return new CharSerializer();
	}

}
