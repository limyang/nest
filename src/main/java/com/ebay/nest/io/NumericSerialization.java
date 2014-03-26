package com.ebay.nest.io;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.serializer.Deserializer;
import org.apache.hadoop.io.serializer.Serialization;
import org.apache.hadoop.io.serializer.Serializer;

import cascading.tuple.hadoop.SerializationToken;

import com.ebay.nest.primitive.BIGINT;
import com.ebay.nest.primitive.BYTEINT;
import com.ebay.nest.primitive.DECIMAL;
import com.ebay.nest.primitive.FLOAT;
import com.ebay.nest.primitive.INTEGER;
import com.ebay.nest.primitive.NUMERIC;
import com.ebay.nest.primitive.SMALLINT;

@SerializationToken(tokens = { 128 }, classNames = { "com.ebay.nest.primitive.INumeric" })
public class NumericSerialization extends Configured implements Serialization {

	public boolean accept(Class c) {
		return NUMERIC.class.isAssignableFrom(c);
	}

	public Deserializer getDeserializer(Class c) {
		if (c == BYTEINT.class) {
			return new ByteIntSerialization.ByteIntDeserializer();
		} else if (c == SMALLINT.class) {
			return new SmallIntSerialization.SmallIntDeserializer();
		} else if (c == INTEGER.class) {
			return new IntegerSerialization.IntegerDeserializer();
		} else if (c == BIGINT.class) {
			return new BigIntSerialization.BigIntDeserializer();
		} else if (c == DECIMAL.class) {
			return new DecimalSerialization.DecimalDeserializer();
		} else if (c == FLOAT.class) {
			return new FloatSerialization.FloatDeserializer();
		}

		return null;
	}

	public Serializer getSerializer(Class c) {
		if (c == BYTEINT.class) {
			return new ByteIntSerialization.ByteIntSerializer();
		} else if (c == SMALLINT.class) {
			return new SmallIntSerialization.SmallIntSerializer();
		} else if (c == INTEGER.class) {
			return new IntegerSerialization.IntegerSerializer();
		} else if (c == BIGINT.class) {
			return new BigIntSerialization.BigIntSerializer();
		} else if (c == DECIMAL.class) {
			return new DecimalSerialization.DecimalSerializer();
		} else if (c == FLOAT.class) {
			return new FloatSerialization.FloatSerializer();
		}

		return null;
	}

}
