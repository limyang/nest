package com.ebay.nest.analyzer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ebay.nest.parser.ASTNode;

public class RowResolver implements Serializable {
	private static final long serialVersionUID = 1L;
	private RowSchema rowSchema;
	private HashMap<String, LinkedHashMap<String, ColumnInfo>> rslvMap;

	private HashMap<String, String[]> invRslvMap;
	private Map<String, ASTNode> expressionMap;

	private boolean isExprResolver;

	private static final Log LOG = LogFactory.getLog(RowResolver.class.getName());

	public RowResolver() {
		rowSchema = new RowSchema();
		rslvMap = new HashMap<String, LinkedHashMap<String, ColumnInfo>>();
		invRslvMap = new HashMap<String, String[]>();
		expressionMap = new HashMap<String, ASTNode>();
		isExprResolver = false;
	}

	public void putExpression(ASTNode node, ColumnInfo colInfo) {
		String treeAsString = node.toStringTree();
		expressionMap.put(treeAsString, node);
		put("", treeAsString, colInfo);
	}

	public ColumnInfo getExpression(ASTNode node) throws SemanticException {
		return get("", node.toStringTree());
	}

	public ASTNode getExpressionSource(ASTNode node) {
		return expressionMap.get(node.toStringTree());
	}

	public void put(String tab_alias, String col_alias, ColumnInfo colInfo) {
		if (tab_alias != null) {
			tab_alias = tab_alias.toLowerCase();
		}
		col_alias = col_alias.toLowerCase();
		if (rowSchema.getSignature() == null) {
			rowSchema.setSignature(new ArrayList<ColumnInfo>());
		}

		rowSchema.getSignature().add(colInfo);

		LinkedHashMap<String, ColumnInfo> f_map = rslvMap.get(tab_alias);
		if (f_map == null) {
			f_map = new LinkedHashMap<String, ColumnInfo>();
			rslvMap.put(tab_alias, f_map);
		}
		f_map.put(col_alias, colInfo);

		String[] qualifiedAlias = new String[2];
		qualifiedAlias[0] = tab_alias;
		qualifiedAlias[1] = col_alias;
		invRslvMap.put(colInfo.getInternalName(), qualifiedAlias);
	}

	public boolean hasTableAlias(String tab_alias) {
		return rslvMap.get(tab_alias.toLowerCase()) != null;
	}

	public ColumnInfo get(String tab_alias, String col_alias) throws SemanticException {
		col_alias = col_alias.toLowerCase();
		ColumnInfo ret = null;

		if (tab_alias != null) {
			tab_alias = tab_alias.toLowerCase();
			HashMap<String, ColumnInfo> f_map = rslvMap.get(tab_alias);
			if (f_map == null) {
				return null;
			}
			ret = f_map.get(col_alias);
		} else {
			boolean found = false;
			for (LinkedHashMap<String, ColumnInfo> cmap : rslvMap.values()) {
				for (Map.Entry<String, ColumnInfo> cmapEnt : cmap.entrySet()) {
					if (col_alias.equalsIgnoreCase(cmapEnt.getKey())) {
						if (found) {
							throw new SemanticException("Column " + col_alias
									+ " Found in more than One Tables/Subqueries");
						}
						found = true;
						ret = cmapEnt.getValue();
					}
				}
			}
		}

		return ret;
	}

	/**
	 * check if column name is already exist in RR
	 */
	public void checkColumn(String tableAlias, String columnAlias) throws SemanticException {
		ColumnInfo prev = get(null, columnAlias);
		if (prev != null && (tableAlias == null || !tableAlias.equalsIgnoreCase(prev.getTabAlias()))) {
			throw new SemanticException(ErrorMsg.DUPLICAT_COLUMN_NAME.getMsg(columnAlias));
		}
	}

	public ArrayList<ColumnInfo> getColumnInfos() {
		return rowSchema.getSignature();
	}

	public List<String> getReferenceableColumnAliases(String tableAlias, int max) {
		int count = 0;
		Set<String> columnNames = new LinkedHashSet<String>();

		int tables = rslvMap.size();

		Map<String, ColumnInfo> mapping = rslvMap.get(tableAlias);
		if (mapping != null) {
			for (Map.Entry<String, ColumnInfo> entry : mapping.entrySet()) {
				if (max > 0 && count >= max) {
					break;
				}
				ColumnInfo columnInfo = entry.getValue();
				if (!columnInfo.isHiddenVirtualCol()) {
					columnNames.add(entry.getKey());
					count++;
				}
			}
		} else {
			for (ColumnInfo columnInfo : getColumnInfos()) {
				if (max > 0 && count >= max) {
					break;
				}
				if (!columnInfo.isHiddenVirtualCol()) {
					String[] inverse = !isExprResolver ? reverseLookup(columnInfo.getInternalName()) : null;
					if (inverse != null) {
						columnNames.add(inverse[0] == null || tables <= 1 ? inverse[1] : inverse[0] + "." + inverse[1]);
					} else {
						columnNames.add(columnInfo.getAlias());
					}
					count++;
				}
			}
		}
		return new ArrayList<String>(columnNames);
	}

	public HashMap<String, ColumnInfo> getFieldMap(String tabAlias) {
		if (tabAlias == null) {
			return rslvMap.get(null);
		} else {
			return rslvMap.get(tabAlias.toLowerCase());
		}
	}

	public int getPosition(String internalName) {
		int pos = -1;

		for (ColumnInfo var : rowSchema.getSignature()) {
			++pos;
			if (var.getInternalName().equals(internalName)) {
				return pos;
			}
		}

		return -1;
	}

	public Set<String> getTableNames() {
		return rslvMap.keySet();
	}

	public String[] reverseLookup(String internalName) {
		return invRslvMap.get(internalName);
	}

	public void setIsExprResolver(boolean isExprResolver) {
		this.isExprResolver = isExprResolver;
	}

	public boolean getIsExprResolver() {
		return isExprResolver;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Map.Entry<String, LinkedHashMap<String, ColumnInfo>> e : rslvMap.entrySet()) {
			String tab = e.getKey();
			sb.append(tab + "{");
			HashMap<String, ColumnInfo> f_map = e.getValue();
			if (f_map != null) {
				for (Map.Entry<String, ColumnInfo> entry : f_map.entrySet()) {
					sb.append("(" + entry.getKey() + "," + entry.getValue().toString() + ")");
				}
			}
			sb.append("} ");
		}
		return sb.toString();
	}

	public RowSchema getRowSchema() {
		return rowSchema;
	}

	public HashMap<String, LinkedHashMap<String, ColumnInfo>> getRslvMap() {
		return rslvMap;
	}

	public HashMap<String, String[]> getInvRslvMap() {
		return invRslvMap;
	}

	public Map<String, ASTNode> getExpressionMap() {
		return expressionMap;
	}

	public void setExprResolver(boolean isExprResolver) {
		this.isExprResolver = isExprResolver;
	}

	public void setRowSchema(RowSchema rowSchema) {
		this.rowSchema = rowSchema;
	}

	public void setRslvMap(HashMap<String, LinkedHashMap<String, ColumnInfo>> rslvMap) {
		this.rslvMap = rslvMap;
	}

	public void setInvRslvMap(HashMap<String, String[]> invRslvMap) {
		this.invRslvMap = invRslvMap;
	}

	public void setExpressionMap(Map<String, ASTNode> expressionMap) {
		this.expressionMap = expressionMap;
	}

	public String[] toColumnDesc() {
		StringBuilder cols = new StringBuilder();
		StringBuilder colTypes = new StringBuilder();

		for (ColumnInfo colInfo : getColumnInfos()) {
			if (cols.length() > 0) {
				cols.append(',');
				colTypes.append(':');
			}
			cols.append(colInfo.getInternalName());
			colTypes.append(colInfo.getType().getTypeName());
		}
		return new String[] { cols.toString(), colTypes.toString() };
	}
}
