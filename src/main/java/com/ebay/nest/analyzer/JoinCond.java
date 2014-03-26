package com.ebay.nest.analyzer;

public class JoinCond {
	private int left;
	private int right;
	private JoinType joinType;
	private boolean preserved;

	public JoinCond() {
	}

	public JoinCond(int left, int right, JoinType joinType) {
		this.left = left;
		this.right = right;
		this.joinType = joinType;
	}

	public JoinCond(boolean p) {
		joinType = JoinType.UNIQUE;
		preserved = p;
	}

	public boolean getPreserved() {
		return preserved;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(final int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(final int right) {
		this.right = right;
	}

	public JoinType getJoinType() {
		return joinType;
	}

	public void setJoinType(final JoinType joinType) {
		this.joinType = joinType;
	}

}
