package com.ebay.nest.utils.format;

import java.text.Format;

interface FieldDelegate {

	public void formatted(Format.Field attr, Object value, int start, int end, StringBuffer buffer);

	public void formatted(int fieldID, Format.Field attr, Object value, int start, int end, StringBuffer buffer);
}
