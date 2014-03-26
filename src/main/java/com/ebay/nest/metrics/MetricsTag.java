package com.ebay.nest.metrics;

import com.google.common.base.Objects;
import static com.google.common.base.Preconditions.*;

public class MetricsTag implements MetricsInfo {
	private final MetricsInfo info;
	private final String value;

	public MetricsTag(MetricsInfo info, String value) {
		this.info = checkNotNull(info, "tag info");
		this.value = value;
	}

	public String name() {
		return info.name();
	}

	public String description() {
		return info.description();
	}

	public MetricsInfo info() {
		return info;
	}

	public String value() {
		return value;
	}

	public boolean equals(Object obj) {
		if (obj instanceof MetricsTag) {
			final MetricsTag other = (MetricsTag) obj;
			return Objects.equal(info, other.info()) && Objects.equal(value, other.value());
		}
		return false;
	}

	public int hashCode() {
		return Objects.hashCode(info, value);
	}

	public String toString() {
		return Objects.toStringHelper(this).add("info", info).add("value", value()).toString();
	}
}