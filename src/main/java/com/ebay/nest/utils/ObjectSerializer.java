package com.ebay.nest.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

import org.apache.commons.codec.binary.Base64;

public class ObjectSerializer {

	public static String serialize(Serializable obj) throws IOException {
		if (obj == null)
			return "";
		try {
			ByteArrayOutputStream serialObj = new ByteArrayOutputStream();
			Deflater def = new Deflater(Deflater.BEST_COMPRESSION);
			ObjectOutputStream objStream = new ObjectOutputStream(new DeflaterOutputStream(serialObj, def));
			objStream.writeObject(obj);
			objStream.close();
			return encodeBytes(serialObj.toByteArray());
		} catch (Exception e) {
			throw new IOException("Serialization error: " + e.getMessage(), e);
		}
	}

	public static Object deserialize(String str) throws IOException {
		if (str == null || str.length() == 0)
			return null;
		try {
			ByteArrayInputStream serialObj = new ByteArrayInputStream(decodeBytes(str));
			ObjectInputStream objStream = new ObjectInputStream(new InflaterInputStream(serialObj));
			return objStream.readObject();
		} catch (Exception e) {
			throw new IOException("Deserialization error: " + e.getMessage(), e);
		}
	}

	public static String encodeBytes(byte[] bytes) throws UnsupportedEncodingException {
		return bytes == null ? null : new String(Base64.encodeBase64(bytes), Charset.forName("UTF-8"));
	}

	public static byte[] decodeBytes(String str) throws UnsupportedEncodingException {
		return Base64.decodeBase64(str.getBytes(Charset.forName("UTF-8")));
	}
}
