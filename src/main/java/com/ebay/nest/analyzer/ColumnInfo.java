package com.ebay.nest.analyzer;

import java.io.Serializable;

import com.ebay.nest.TypeInfo_remove;

public class ColumnInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String internalName;

	private String alias = null;

	private boolean isSkewedCol;

	private String tabAlias;

	private boolean isVirtualCol;

	private TypeInfo_remove typeInfo;

	private boolean isHiddenVirtualCol;

	public ColumnInfo() {
	}

	public ColumnInfo(String internalName, TypeInfo_remove type, String tabAlias, boolean isVirtualCol) {
		this(internalName, type, tabAlias, isVirtualCol, false);
	}

	public ColumnInfo(String internalName, TypeInfo_remove typeInfo, String tabAlias, boolean isVirtualCol,
			boolean isHiddenVirtualCol) {
		this.internalName = internalName;
		this.typeInfo = typeInfo;
		this.tabAlias = tabAlias;
		this.isVirtualCol = isVirtualCol;
		this.isHiddenVirtualCol = isHiddenVirtualCol;
	}

	public ColumnInfo(ColumnInfo columnInfo) {
		this.internalName = columnInfo.getInternalName();
		this.alias = columnInfo.getAlias();
		this.isSkewedCol = columnInfo.isSkewedCol();
		this.tabAlias = columnInfo.getTabAlias();
		this.isVirtualCol = columnInfo.getIsVirtualCol();
		this.isHiddenVirtualCol = columnInfo.isHiddenVirtualCol();
		this.setType(columnInfo.getType());
	}

	public TypeInfo_remove getType() {
		return typeInfo;
	}

	public String getInternalName() {
		return internalName;
	}

	public void setType(TypeInfo_remove typeInfo) {
		this.typeInfo = typeInfo;
	}

	public void setInternalName(String internalName) {
		this.internalName = internalName;
	}

	public String getTabAlias() {
		return tabAlias;
	}

	public boolean getIsVirtualCol() {
		return isVirtualCol;
	}

	public boolean isHiddenVirtualCol() {
		return isHiddenVirtualCol;
	}

	public String toString() {
		return internalName + ": " + typeInfo.getTypeName();
	}

	public void setAlias(String col_alias) {
		alias = col_alias;
	}

	public String getAlias() {
		return alias;
	}

	public void setTabAlias(String tabAlias) {
		this.tabAlias = tabAlias;
	}

	public void setVirtualCol(boolean isVirtualCol) {
		this.isVirtualCol = isVirtualCol;
	}

	public void setHiddenVirtualCol(boolean isHiddenVirtualCol) {
		this.isHiddenVirtualCol = isHiddenVirtualCol;
	}

	public boolean isSkewedCol() {
		return isSkewedCol;
	}

	public void setSkewedCol(boolean isSkewedCol) {
		this.isSkewedCol = isSkewedCol;
	}

	private boolean checkEquals(Object obj1, Object obj2) {
		return obj1 == null ? obj2 == null : obj1.equals(obj2);
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof ColumnInfo) || (obj == null)) {
			return false;
		}

		ColumnInfo dest = (ColumnInfo) obj;
		if ((!checkEquals(internalName, dest.getInternalName())) || (!checkEquals(alias, dest.getAlias()))
				|| (!checkEquals(getType(), dest.getType())) || (isSkewedCol != dest.isSkewedCol())
				|| (isVirtualCol != dest.getIsVirtualCol()) || (isHiddenVirtualCol != dest.isHiddenVirtualCol())) {
			return false;
		}

		return true;
	}
}
