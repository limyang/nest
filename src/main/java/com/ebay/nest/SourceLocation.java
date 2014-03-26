package com.ebay.nest;

import com.ebay.nest.parser.ASTNode;

public class SourceLocation {
	private int line = -1;
	private int offset = -1;

	private ASTNode node;

	public SourceLocation() {
	}

	public SourceLocation(int line, int offset) {
		this.line = line;
		this.offset = offset;
	}

	public SourceLocation(ASTNode tree) {
		this.line = tree.getLine();
		this.offset = tree.getCharPositionInLine();
		this.node = tree;
	}

	public SourceLocation(SourceLocation location) {
		this.line = location.line;
		this.offset = location.offset;
		this.node = location.node;
	}

	public int line() {
		return line;
	}

	public int offset() {
		return offset;
	}

	public ASTNode node() {
		return node;
	}

	public String toString() {
		if (line == -1)
			return "";

		StringBuilder sb = new StringBuilder();
		sb.append("<");
		sb.append("line " + line + ", column " + offset + "> ");

		return sb.toString();
	}

}
