package com.ebay.nest.analyzer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ebay.nest.NestContext;
import com.ebay.nest.NestException;
import com.ebay.nest.parser.ASTNode;

public abstract class Analyzer {
	protected final Log LOG;

	protected NestContext ctx;

	StatementType statementType;

	public Analyzer(StatementType type) throws NestException {
		try {
			this.statementType = type;
			this.LOG = LogFactory.getLog(this.getClass().getName());

		} catch (Exception e) {
			throw new NestException(e);
		}
	}

	public void analyze(ASTNode ast, NestContext ctx) throws NestException {
		this.ctx = ctx;
		ctx.nextId();
		reset();
		analyzeInternal(ast);
	}

	protected void reset() {

	}

	public abstract void analyzeInternal(ASTNode ast) throws NestException;

}
