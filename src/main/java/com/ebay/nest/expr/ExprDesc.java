package com.ebay.nest.expr;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ebay.nest.NestLogger;
import com.ebay.nest.NestProgressable;
import com.ebay.nest.TypeInfo;
import com.ebay.nest.plan.Element;
import com.ebay.nest.plan.Node;

public abstract class ExprDesc extends Element<ExprVisitor> implements Cloneable, Node {

	private static final long serialVersionUID = -5335411058551246308L;
	private static final Log log = LogFactory.getLog(ExprDesc.class);

	private static ThreadLocal<NestProgressable> reporter = new ThreadLocal<NestProgressable>();
	protected static NestLogger nestLogger;

	protected Class resultType;

	TypeInfo typeInfo;

	protected Set<String> cols;

	public boolean supportsMultipleOutputs() {
		return false;
	}

	public abstract boolean visit(ExprVisitor v);

	public void setResType(Class type) {
		this.resultType = type;
	}

	public Class getResType() {
		return this.resultType;
	}

	public static NestProgressable getReporter() {
		return ExprDesc.reporter.get();
	}

	public ExprDesc clone() throws CloneNotSupportedException {
		String s = "This expression operator does not implement clone.";
		log.error(s);
		throw new CloneNotSupportedException(s);
	}

	public abstract List<ExprDesc> getChildren();

	public boolean containUDF() {
		if (this instanceof UDFExpr) {
			return true;
		}

		List<ExprDesc> l = getChildren();
		if (l != null) {
			for (ExprDesc e : l) {
				if (e.containUDF()) {
					return true;
				}
			}
		}

		return false;
	}

}
