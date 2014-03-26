package com.ebay.nest.plan;

import com.ebay.nest.expr.ExprDesc;

public class FilterDesc extends AbstractOperatorDesc {

	private static final long serialVersionUID = 1L;
	private ExprDesc predicate;
	private boolean isSortedFilter;

	public FilterDesc() {
	}

	public FilterDesc(final ExprDesc predicate) {
		this.predicate = predicate;
	}

	public ExprDesc getPredicate() {
		return predicate;
	}

	public void setPredicate(final ExprDesc predicate) {
		this.predicate = predicate;
	}

	public boolean isSortedFilter() {
		return isSortedFilter;
	}

	public void setSortedFilter(boolean isSortedFilter) {
		this.isSortedFilter = isSortedFilter;
	}

	public Object clone() {
		FilterDesc filterDesc = new FilterDesc(getPredicate().clone());
		filterDesc.setSortedFilter(isSortedFilter());
		return filterDesc;
	}
}