package com.ebay.nest.utils.format;

import java.text.FieldPosition;
import java.text.Format;

class DontCareFieldPosition extends FieldPosition {
	static final FieldPosition INSTANCE = new DontCareFieldPosition();

	private final FieldDelegate noDelegate = new FieldDelegate() {
		public void formatted(Format.Field attr, Object value, int start, int end, StringBuffer buffer) {
		}

		public void formatted(int fieldID, Format.Field attr, Object value, int start, int end, StringBuffer buffer) {
		}
	};

	private DontCareFieldPosition() {
		super(0);
	}

	public FieldDelegate getFieldDelegate() {
		return noDelegate;
	}
}
