package com.ebay.nest.analyzer;

import java.util.ArrayList;
import java.util.List;

import cascading.tuple.Fields;

import com.ebay.nest.Data;
import com.ebay.nest.Pair;
import com.ebay.nest.primitive.BYTE;
import com.ebay.nest.primitive.CHAR;
import com.ebay.nest.primitive.DATETIME;
import com.ebay.nest.primitive.NUMERIC;

public class JoinInfo {

	private List<Pair<ExprInfo, Fields>> rightJoinList = new ArrayList<Pair<ExprInfo, Fields>>();
	private List<Pair<ExprInfo, Fields>> leftJoinList = new ArrayList<Pair<ExprInfo, Fields>>();

	private static int joinCount = 0;

	public JoinInfo() {

	}

	public static JoinInfo merge(JoinInfo j1, JoinInfo j2) {
		JoinInfo res = new JoinInfo();

		for (Pair<ExprInfo, Fields> p : j1.getRightJoinList()) {

			res.getRightJoinList().add(p);
		}
		for (Pair<ExprInfo, Fields> p : j2.getRightJoinList()) {

			res.getRightJoinList().add(p);
		}
		for (Pair<ExprInfo, Fields> p : j1.getleftJoinList()) {

			res.getleftJoinList().add(p);
		}
		for (Pair<ExprInfo, Fields> p : j2.getleftJoinList()) {

			res.getleftJoinList().add(p);
		}
		return res;

	}

	public List<Pair<ExprInfo, Fields>> getRightJoinList() {
		return rightJoinList;
	}

	public List<Pair<ExprInfo, Fields>> getleftJoinList() {
		return leftJoinList;
	}

	public synchronized void addJoinList(ExprInfo leftexprInfo, ExprInfo rightExprInfo) throws SemanticException {

		Class leftType = leftexprInfo.getReturnType();
		Class rightType = rightExprInfo.getReturnType();
		Class resType;
		if (NUMERIC.class.isAssignableFrom(leftType) && NUMERIC.class.isAssignableFrom(rightType)) {

			resType = NUMERIC.class;
		} else if (CHAR.class.isAssignableFrom(leftType) && CHAR.class.isAssignableFrom(rightType)) {
			resType = CHAR.class;
		} else if (DATETIME.class.isAssignableFrom(leftType) && DATETIME.class.isAssignableFrom(rightType)) {
			resType = DATETIME.class;
		} else if (BYTE.class.isAssignableFrom(leftType) && BYTE.class.isAssignableFrom(rightType)) {
			resType = BYTE.class;
		} else {
			resType = Data.class;
		}

		Fields leftFields = new Fields("LEXPR_" + joinCount, resType);

		leftJoinList.add(new Pair(leftexprInfo, leftFields));

		Fields rightFields = new Fields("REXPR_" + joinCount, resType);

		rightJoinList.add(new Pair(rightExprInfo, rightFields));

		joinCount++;
	}
}
