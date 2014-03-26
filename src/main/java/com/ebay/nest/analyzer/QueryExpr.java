package com.ebay.nest.analyzer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ebay.nest.parser.ASTNode;

public class QueryExpr {

	private static final Log LOG = LogFactory.getLog(QueryExpr.class);

	public static enum Opcode {
		NULLOP, UNION, INTERSECT, DIFF
	};

	private Opcode opcode;
	private QueryExpr qbexpr1;
	private QueryExpr qbexpr2;
	private QueryInfo qb;
	private String alias;
	private ASTNode ast;
	
	public ASTNode getNode(){
		return ast;
	}
	public void setNode(ASTNode node){
		ast=node;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public QueryExpr(String alias) {
		this.alias = alias;
	}

	public QueryExpr(QueryInfo qb) {
		opcode = Opcode.NULLOP;
		this.qb = qb;
	}

	public QueryExpr(Opcode opcode, QueryExpr qbexpr1, QueryExpr qbexpr2) {
		this.opcode = opcode;
		this.qbexpr1 = qbexpr1;
		this.qbexpr2 = qbexpr2;
	}

	public void setQB(QueryInfo qb) {
		this.qb = qb;
	}

	public void setOpcode(Opcode opcode) {
		this.opcode = opcode;
	}

	public void setQBExpr1(QueryExpr qbexpr) {
		qbexpr1 = qbexpr;
	}

	public void setQBExpr2(QueryExpr qbexpr) {
		qbexpr2 = qbexpr;
	}

	public QueryInfo getQB() {
		return qb;
	}

	public Opcode getOpcode() {
		return opcode;
	}

	public QueryExpr getQBExpr1() {
		return qbexpr1;
	}

	public QueryExpr getQBExpr2() {
		return qbexpr2;
	}

	public void print(String msg) {
		if (opcode == Opcode.NULLOP) {
			LOG.info(msg + "start qb = " + qb);
			qb.print(msg + " ");
			LOG.info(msg + "end qb = " + qb);
		} else {
			LOG.info(msg + "start qbexpr1 = " + qbexpr1);
			qbexpr1.print(msg + " ");
			LOG.info(msg + "end qbexpr1 = " + qbexpr1);
			LOG.info(msg + "start qbexpr2 = " + qbexpr2);
			qbexpr2.print(msg + " ");
			LOG.info(msg + "end qbexpr2 = " + qbexpr2);
		}
	}

}
