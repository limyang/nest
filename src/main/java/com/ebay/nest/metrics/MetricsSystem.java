package com.ebay.nest.metrics;

public abstract class MetricsSystem implements MetricsSystemMXBean {

	public abstract MetricsSystem init(String prefix);

	public abstract <T> T register(String name, String desc, T source);

	public <T> T register(T source) {
		return register(null, null, source);
	}

	public abstract MetricsSource getSource(String name);



	public abstract void register(Callback callback);

	public abstract void publishMetricsNow();

	public abstract boolean shutdown();

	public interface Callback {

		void preStart();

		void postStart();

		void preStop();

		void postStop();
	}

	public static abstract class AbstractCallback implements Callback {

		public void preStart() {
		}

		public void postStart() {
		}

		public void preStop() {
		}

		public void postStop() {
		}
	}
}
