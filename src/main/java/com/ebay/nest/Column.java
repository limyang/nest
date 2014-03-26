package com.ebay.nest;

public class Column implements java.io.Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	private String name;
	private TypeInfo type;
	private String comment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TypeInfo getType() {
		return type;
	}

	public void setType(TypeInfo type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Column() {
	}

	public Column(String name, TypeInfo type, String comment) {
		this();
		this.name = name;
		this.type = type;
		this.comment = comment;
	}

	public Column(String name, TypeInfo type) {
		this();
		this.name = name;
		this.type = type;
	}

	public Column(Column other) {
		if (other.isSetName()) {
			this.name = other.name;
		}
		if (other.isSetType()) {
			this.type = other.type;
		}
		if (other.isSetComment()) {
			this.comment = other.comment;
		}
	}

	public Column deepCopy() {
		return new Column(this);
	}

	public boolean isSetComment() {
		return this.comment != null;
	}

	public boolean isSetName() {
		return this.name != null;
	}

	public boolean isSetType() {
		return this.type != null;
	}

	public void clear() {
		this.name = null;
		this.type = null;
		this.comment = null;
	}

}
