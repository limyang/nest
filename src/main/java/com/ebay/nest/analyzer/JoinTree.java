package com.ebay.nest.analyzer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ebay.nest.expr.ExprDesc;
import com.ebay.nest.parser.ASTNode;

public class JoinTree implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;

	private String[] rightAliases;
	private String[] leftAliases;
	private JoinType joinType;
	private ASTNode joinCondNode;

	private JoinTree leftJoinTree = null;

	private JoinTree rightJoinTree = null;

	private ExprDesc joinExpr = null;

	private List<ExprDesc> leftFilter = new ArrayList<ExprDesc>();

	private List<ExprDesc> rightFilter = new ArrayList<ExprDesc>();

	private List<ExprDesc> leftJoinKey = new ArrayList<ExprDesc>();

	private List<ExprDesc> rightJoinKey = new ArrayList<ExprDesc>();

	private List<ExprDesc> postJoin = new ArrayList<ExprDesc>();
	private boolean noOuterJoin;
	private boolean noSemiJoin;
	private JoinTree joinSrc;
	private String[] baseSrc;
	private ArrayList<ArrayList<ASTNode>> filtersForPushing;

	public void setJoinType(JoinType joinType) {
		this.joinType = joinType;
	}

	public JoinType getJoinType() {
		return this.joinType;
	}

	public String getJoinId() {
		return this.id;
	}

	public void setJoinExpr(ExprDesc condExpr) {
		this.joinExpr = condExpr;
	}

	public ExprDesc getJoinExpr() {
		return this.joinExpr;
	}

	public ASTNode getJoinCondNode() {
		return joinCondNode;
	}

	public void setJoinCondNode(ASTNode ast) {
		this.joinCondNode = ast;
	}

	public List<ExprDesc> getAllcondExpr() {
		List<ExprDesc> lists = new ArrayList<ExprDesc>();

		if (leftJoinTree != null) {
			List<ExprDesc> leftLists = leftJoinTree.getAllcondExpr();
			lists.addAll(leftLists);
		}

		if (rightJoinTree != null) {
			List<ExprDesc> rightLists = leftJoinTree.getAllcondExpr();
			lists.addAll(rightLists);
		}
		lists.add(joinExpr);
		return lists;
	}

	public void addLeftFilter(ExprDesc eo) {
		leftFilter.add(eo);
	}

	public void addRightFilter(ExprDesc eo) {
		rightFilter.add(eo);
	}

	public void addLeftkey(ExprDesc eo) {
		leftJoinKey.add(eo);
	}

	public void addRightkey(ExprDesc eo) {
		rightJoinKey.add(eo);
	}

	public void addPostJoinCond(ExprDesc eo) {
		postJoin.add(eo);
	}

	public List<ExprDesc> getPostJoinCond() {
		return postJoin;
	}

	public List<ExprDesc> getLeftFilter() {
		return leftFilter;
	}

	public List<ExprDesc> getRightFilter() {
		return rightFilter;
	}

	public List<ExprDesc> getLeftJoinKey() {
		return leftJoinKey;
	}

	public List<ExprDesc> getRightJoinKey() {
		return rightJoinKey;
	}

	public void setId(String id) {
		this.id = id;
	}

	public JoinTree getLeftJoin() {
		return leftJoinTree;
	}

	public void setLeftJoin(JoinTree leftJoin) {
		this.leftJoinTree = leftJoin;
	}

	public JoinTree getRightJoin() {
		return rightJoinTree;
	}

	public void setRightJoin(JoinTree rightJoin) {
		this.rightJoinTree = rightJoin;
	}

	public String[] getRightAliases() {
		return rightAliases;
	}

	public void setRightAliases(String[] rightAliases) {
		this.rightAliases = rightAliases;
	}

	public String[] getLeftAliases() {
		return leftAliases;
	}

	public void setLeftAliases(String[] leftAliases) {
		this.leftAliases = leftAliases;
	}

	public boolean getNoOuterJoin() {
		return noOuterJoin;
	}

	public void setNoOuterJoin(boolean noOuterJoin) {
		this.noOuterJoin = noOuterJoin;
	}

	public boolean getNoSemiJoin() {
		return noSemiJoin;
	}

	public void setNoSemiJoin(boolean semi) {
		noSemiJoin = semi;
	}

	public JoinTree getJoinSrc() {
		return joinSrc;
	}

	public void setJoinSrc(JoinTree joinSrc) {
		this.joinSrc = joinSrc;
	}

	public String[] getBaseSrc() {
		return baseSrc;
	}

	public void setBaseSrc(String[] baseSrc) {
		this.baseSrc = baseSrc;
	}

	public ArrayList<ArrayList<ASTNode>> getFiltersForPushing() {
		return filtersForPushing;
	}

	public void setFiltersForPushing(ArrayList<ArrayList<ASTNode>> filters) {
		this.filtersForPushing = filters;
	}

}
