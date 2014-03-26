package com.ebay.nest.analyzer;

import com.ebay.nest.NestException;
import com.ebay.nest.parser.ASTNode;
import com.ebay.nest.parser.SQLParser;

public class RootAnalyzer extends Analyzer {

	public RootAnalyzer(StatementType type) throws NestException {
		super(type);
	}

	public void analyzeInternal(ASTNode rootTree) throws NestException {
		if (rootTree == null || rootTree.getChildCount() == 0) {
			return;
		}
		if (rootTree.getToken().getType() != SQLParser.TOK_ROOT) {
			throw new SemanticException(ErrorMsg.ROOT_ERROR.getMsg(rootTree));
		}
		int child_count = rootTree.getChildCount();
		Analyzer analyzer;
		for (int child_pos = 0; child_pos < child_count; ++child_pos) {
			ASTNode child = (ASTNode) rootTree.getChild(child_pos);

			switch (child.getToken().getType()) {
			case SQLParser.TOK_CREATE_VIEW_STATEMENT:
				analyzer = new QueryAnalyzer(StatementType.CREATE_VIEW);
				analyzer.analyze(child, ctx);
				break;
			case SQLParser.TOK_OVERWRITE_TARGET_STATEMENT:
				analyzer = new QueryAnalyzer(StatementType.OVERWRITE_TARGET);
				analyzer.analyze(child, ctx);
				break;
			case SQLParser.TOK_DEFINE_SOURCE_STATEMENT:
				analyzer = new TapAnalyzer(StatementType.DEFINE_SOURCE);
				analyzer.analyze(child, ctx);
				break;
			case SQLParser.TOK_DEFINE_TARGET_STATEMENT:
				analyzer = new TapAnalyzer(StatementType.DEFINE_TARGET);
				analyzer.analyze(child, ctx);
				break;
			case SQLParser.TOK_INVOKE_STATEMENT:
				analyzer = new InvokeAnalyzer(StatementType.INVOKE);
				analyzer.analyze(child, ctx);
				break;
			case SQLParser.TOK_UPDATE_STATEMENT:
				analyzer = new QueryAnalyzer(StatementType.UPDATE);
				analyzer.analyze(child, ctx);
				break;
			case SQLParser.TOK_DELETE_STATEMENT:
				analyzer = new QueryAnalyzer(StatementType.DELETE);
				analyzer.analyze(child, ctx);
				break;
			case SQLParser.TOK_INSERT_STATEMENT:
				analyzer = new QueryAnalyzer(StatementType.INSERT);
				analyzer.analyze(child, ctx);
				break;
			default:
				analyzer = new NoOpAnalyzer(StatementType.NOOP);
				analyzer.analyze(child, ctx);
				break;
			}
		}

	}

}
