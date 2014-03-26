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

import com.ebay.nest.primitive.SMALLINT;

@SerializationToken(tokens = {130}, classNames = {"com.ebay.nest.primitive.SMALLINT"})
public class SmallIntSerialization extends Configured implements Serialization<SMALLINT> {

	public static class SmallIntDeserializer implements Deserializer<SMALLINT> {
		private DataInputStream in;

		public void open(InputStream in) throws IOException {
			if (in instanceof DataInputStream)
				this.in = (DataInputStream) in;
			else
				this.in = new DataInputStream(in);
		}

		public SMALLINT deserialize(SMALLINT existing) throws IOException {
			short s = in.readShort();
			return new SMALLINT(s);
		}

		public void close() throws IOException {
			in.close();
		}
	}

	public static class SmallIntSerializer implements Serializer<SMALLINT> {
		private DataOutputStream out;

		public void open(OutputStream out) throws IOException {
			if (out instanceof DataOutputStream)
				this.out = (DataOutputStream) out;
			else
				this.out = new DataOutputStream(out);
		}

		public void serialize(SMALLINT o) throws IOException {
			out.writeShort(o.shortValue());
		}

		public void close() throws IOException {
			out.close();
		}
	}

	public boolean accept(Class<?> c) {
		return SMALLINT.class == c;
	}

	public Deserializer<SMALLINT> getDeserializer(Class<SMALLINT> arg0) {
		return new SmallIntDeserializer();
	}

	public Serializer<SMALLINT> getSerializer(Class<SMALLINT> arg0) {
		return new SmallIntSerializer();
	}

}