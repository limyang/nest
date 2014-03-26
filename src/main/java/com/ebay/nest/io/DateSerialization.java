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

import com.ebay.nest.primitive.DATE;

@SerializationToken(tokens = { 161 }, classNames = { "com.ebay.nest.primitive.DATE" })
public class DateSerialization extends Configured implements Serialization<DATE> {

	public static class DateDeserializer implements Deserializer<DATE> {
		private DataInputStream in;

		public void open(InputStream in) throws IOException {
			if (in instanceof DataInputStream)
				this.in = (DataInputStream) in;
			else
				this.in = new DataInputStream(in);
		}

		public DATE deserialize(DATE existing) throws IOException {
			long time = in.readLong();

			return new DATE(time);
		}

		public void close() throws IOException {
			in.close();
		}
	}

	public static class DateSerializer implements Serializer<DATE> {
		private DataOutputStream out;

		public void open(OutputStream out) throws IOException {
			if (out instanceof DataOutputStream)
				this.out = (DataOutputStream) out;
			else
				this.out = new DataOutputStream(out);
		}

		public void serialize(DATE o) throws IOException {
			out.writeLong(o.getTime());
		}

		public void close() throws IOException {
			out.close();
		}
	}

	public boolean accept(Class<?> c) {
		return DATE.class == c;
	}

	public Deserializer<DATE> getDeserializer(Class<DATE> arg0) {
		return new DateDeserializer();
	}

	public Serializer<DATE> getSerializer(Class<DATE> arg0) {
		return new DateSerializer();
	}

}
