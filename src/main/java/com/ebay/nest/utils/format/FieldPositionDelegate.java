package com.ebay.nest.utils.format;

import java.text.FieldPosition;
import java.text.Format;

public class FieldPositionDelegate implements FieldDelegate {

	private boolean encounteredField;
	private FieldPosition pos;
	
	public FieldPositionDelegate(FieldPosition pos){
		this.pos=pos;
	}
	
	

	public void formatted(Format.Field attr, Object value, int start, int end, StringBuffer buffer) {
		if (!encounteredField && matchesField(attr)) {
			pos.setBeginIndex(start);
			pos.setEndIndex(end);
			encounteredField = (start != end);
		}
	}

	public void formatted(int fieldID, Format.Field attr, Object value, int start, int end, StringBuffer buffer) {
		if (!encounteredField && matchesField(attr, fieldID)) {
			pos.setBeginIndex(start);
			pos.setEndIndex(end);
			encounteredField = (start != end);
		}
	}

	private boolean matchesField(Format.Field attribute) {
		if (pos.getFieldAttribute() != null) {
			return pos.getFieldAttribute().equals(attribute);
		}
		return false;
	}
	
    private boolean matchesField(Format.Field attribute, int field) {
        if (pos.getFieldAttribute() != null) {
            return pos.getFieldAttribute().equals(attribute);
        }
        return (field == pos.getField());
    }


}
