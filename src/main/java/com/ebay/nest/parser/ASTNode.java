package com.ebay.nest.parser;

import java.io.Serializable;
import java.util.ArrayList;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import com.ebay.nest.utils.ParserUtil;

public class ASTNode extends CommonTree implements Node, Serializable {
	private static final long serialVersionUID = 1L;

	private ASTNodeOrigin origin;

	public ASTNode() {
	}

	public ASTNode(Token t) {
		super(t);
	}

	public ASTNode(ASTNode node) {
		super(node);
		this.origin = node.origin;
	}

	@Override
	public Tree dupNode() {
		return new ASTNode(this);
	}

	public ArrayList<Node> getChildren() {
		if (super.getChildCount() == 0) {
			return new ArrayList<Node>();
		}

		ArrayList<Node> ret_vec = new ArrayList<Node>();
		for (int i = 0; i < super.getChildCount(); ++i) {
			ret_vec.add((Node) super.getChild(i));
		}

		return ret_vec;
	}

	public String getName() {
		return (Integer.valueOf(super.getToken().getType())).toString();
	}

	public ASTNodeOrigin getOrigin() {
		return origin;
	}

	public void setOrigin(ASTNodeOrigin origin) {
		this.origin = origin;
	}

	public String dump() {
		StringBuilder sb = new StringBuilder();

		sb.append('(');
		sb.append(toString());
		ArrayList<Node> children = getChildren();
		if (children != null) {
			for (Node node : getChildren()) {
				if (node instanceof ASTNode) {
					sb.append(((ASTNode) node).dump());
				} else {
					sb.append("NON-ASTNODE!!");
				}
			}
		}
		sb.append(')');
		return sb.toString();
	}

	public String prettyDump() {

		return prettyDump(0);
	}

	public String prettyDump(int level) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < level; i++) {
			sb.append("  ");
		}
		if (ParserUtil.isLeafType(getType())) {
			return sb.append(toString() + "\n").toString();
		}

		sb.append("<" + toString() + ">\n");
		for (Node node : getChildren()) {
			if (node instanceof ASTNode) {
				sb.append(((ASTNode) node).prettyDump(level + 1));
			} else {
				sb.append("NON-ASTNODE!!");
			}
		}

		for (int i = 0; i < level; i++) {
			sb.append("  ");
		}
		sb.append("</" + toString() + ">\n");
		return sb.toString();
	}

}
