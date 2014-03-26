package com.ebay.nest;

import java.io.Serializable;

public class Pair<L, R> implements Serializable {

	private static final long serialVersionUID = -3028418298648303775L;

	public final L left;

	public final R right;

	public static <L, R> Pair<L, R> of(L left, R right) {
		return new Pair<L, R>(left, right);
	}

	public Pair(L left, R right) {
		super();
		this.left = left;
		this.right = right;
	}

	public L getLeft() {
		return left;
	}

	public R getRight() {
		return right;
	}

	public R setValue(R value) {
		throw new UnsupportedOperationException();
	}

	public String toString() {
		return "[" + left.toString() + "," + right.toString() + "]";
	}

	public int hashCode() {
		return (((this.left == null ? 1 : this.left.hashCode()) * 17) + (this.right == null ? 1 : this.right.hashCode()) * 19);
	}

	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}

		if (!(other instanceof Pair)) {
			return false;
		}

		Pair otherPair = (Pair) other;

		if (this.left == null) {
			if (otherPair.left != null) {
				return false;
			} else {
				return true;
			}
		}

		if (this.right == null) {
			if (otherPair.right != null) {
				return false;
			} else {
				return true;
			}
		}

		if (this.left.equals(otherPair.left) && this.right.equals(otherPair.right)) {
			return true;
		} else {
			return false;
		}
	}

}
