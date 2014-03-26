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

import com.ebay.nest.primitive.TIME;

@SerializationToken(tokens = { 162 }, classNames = { "com.ebay.nest.primitive.TIME" })
public class TimeSerialization extends Configured implements Serialization<TIME> {

	public static class TimeDeserializer implements Deserializer<TIME> {
		private DataInputStream in;

		public void open(InputStream in) throws IOException {
			if (in instanceof DataInputStream)
				this.in = (DataInputStream) in;
			else
				this.in = new DataInputStream(in);
		}

		public TIME deserialize(TIME existing) throws IOException {
			long time = in.readLong();

			return new TIME(time);
		}

		public void close() throws IOException {
			in.close();
		}
	}

	public static class TimeSerializer implements Serializer<TIME> {
		private DataOutputStream out;

		public void open(OutputStream out) throws IOException {
			if (out instanceof DataOutputStream)
				this.out = (DataOutputStream) out;
			else
				this.out = new DataOutputStream(out);
		}

		public void serialize(TIME o) throws IOException {
			out.writeLong(o.getTime());
		}

		public void close() throws IOException {
			out.close();
		}
	}

	public boolean accept(Class<?> c) {
		return TIME.class == c;
	}

	public Deserializer<TIME> getDeserializer(Class<TIME> arg0) {
		return new TimeDeserializer();
	}

	public Serializer<TIME> getSerializer(Class<TIME> arg0) {
		return new TimeSerializer();
	}
}
