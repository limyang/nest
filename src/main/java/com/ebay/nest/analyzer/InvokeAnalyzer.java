package com.ebay.nest.analyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.tree.Tree;

import com.ebay.nest.NestException;
import com.ebay.nest._remove.method.Method;
import com.ebay.nest._remove.method.MethodFactory;
import com.ebay.nest.parser.ASTNode;
import com.ebay.nest.utils.ParserUtil;

public class InvokeAnalyzer extends Analyzer {

	public InvokeAnalyzer(StatementType type) throws NestException {
		super(type);

	}

	private Object getParamValueObject(Tree paramValueNode) {
		if (ParserUtil.isLeafType(paramValueNode.getType())) {
			// value is plain string
			String value = paramValueNode.getText();
			return value;
		} else {
			List<Object> list = new ArrayList<Object>();
			// value is array
			ASTNode paramArrayNode = (ASTNode) paramValueNode;
			for (int j = 0; j < paramArrayNode.getChildCount(); j++) {
				Tree paramArrayEleNode = paramArrayNode.getChild(j);
				Object paramArrayEleObject = getParamValueObject(paramArrayEleNode);
				list.add(paramArrayEleObject);
			}
			return list;
		}
	}

	@Override
	public void analyzeInternal(ASTNode statementNode) throws SemanticException {
		try {
			ASTNode invokeNode = (ASTNode) statementNode.getChild(0);
			String methodName = invokeNode.getChild(0).getText();

			if (invokeNode.getChildCount() == 1) {
				Method method = MethodFactory.getMethod(methodName, ctx,
						null);
				method.invoke();
			} else {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				ASTNode paramsNode = (ASTNode) invokeNode.getChild(1);
				for (int i = 0; i < paramsNode.getChildCount(); i++) {
					ASTNode paramNode = (ASTNode) paramsNode.getChild(i);
					String key = paramNode.getChild(0).getText();
					Object valueObj = getParamValueObject(paramNode.getChild(1));
					paramMap.put(key, valueObj);
				}
				Method method = MethodFactory.getMethod(methodName, ctx,
						paramMap);
				method.invoke();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SemanticException(e);
		}

	}
}
