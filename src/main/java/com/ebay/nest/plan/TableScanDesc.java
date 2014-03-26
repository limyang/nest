package com.ebay.nest.plan;

import java.util.List;
import java.util.Map;

import com.ebay.nest.expr.ExprDesc;

public class TableScanDesc extends AbstractOperatorDesc {
	private static final long serialVersionUID = 1L;

	private String alias;

	private String statsAggKeyPrefix;

	private List<String> partColumns;

	private int rowLimit = -1;

	private boolean gatherStats;
	private boolean statsReliable;
	private int maxStatsKeyPrefixLength = -1;

	private ExprDesc filterExpr;

	public static final String FILTER_EXPR_CONF_STR = "hive.io.filter.expr.serialized";

	public static final String FILTER_TEXT_CONF_STR = "hive.io.filter.text";

	private Map<String, Integer> bucketFileNameMapping;

	public TableScanDesc() {
	}

	public TableScanDesc(final String alias) {
		this.alias = alias;
	}

	public Object clone() {
		return new TableScanDesc(getAlias());
	}

	public String getAlias() {
		return alias;
	}

	public ExprDesc getFilterExpr() {
		return filterExpr;
	}

	public void setFilterExpr(ExprDesc filterExpr) {
		this.filterExpr = filterExpr;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public void setPartColumns(List<String> partColumns) {
		this.partColumns = partColumns;
	}

	public List<String> getPartColumns() {
		return partColumns;
	}

	public void setGatherStats(boolean gatherStats) {
		this.gatherStats = gatherStats;
	}

	public boolean isGatherStats() {
		return gatherStats;
	}

	public void setStatsAggPrefix(String k) {
		statsAggKeyPrefix = k;
	}

	public String getStatsAggPrefix() {
		return statsAggKeyPrefix;
	}

	public boolean isStatsReliable() {
		return statsReliable;
	}

	public void setStatsReliable(boolean statsReliable) {
		this.statsReliable = statsReliable;
	}

	public int getMaxStatsKeyPrefixLength() {
		return maxStatsKeyPrefixLength;
	}

	public void setMaxStatsKeyPrefixLength(int maxStatsKeyPrefixLength) {
		this.maxStatsKeyPrefixLength = maxStatsKeyPrefixLength;
	}

	public void setRowLimit(int rowLimit) {
		this.rowLimit = rowLimit;
	}

	public int getRowLimit() {
		return rowLimit;
	}

	public Integer getRowLimitExplain() {
		return rowLimit >= 0 ? rowLimit : null;
	}

	public Map<String, Integer> getBucketFileNameMapping() {
		return bucketFileNameMapping;
	}

	public void setBucketFileNameMapping(Map<String, Integer> bucketFileNameMapping) {
		this.bucketFileNameMapping = bucketFileNameMapping;
	}
}
