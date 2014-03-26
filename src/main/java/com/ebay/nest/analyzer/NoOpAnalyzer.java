package com.ebay.nest.analyzer;

import com.ebay.nest.NestException;
import com.ebay.nest.parser.ASTNode;

public class NoOpAnalyzer extends Analyzer {

	public NoOpAnalyzer(StatementType type) throws NestException {
		super(type);
	}

	@Override
	public void analyzeInternal(ASTNode ast) throws SemanticException {
		// Do Nothing
	}

}
