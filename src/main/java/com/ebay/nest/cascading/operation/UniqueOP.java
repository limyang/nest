package com.ebay.nest.cascading.operation;

import java.util.LinkedHashMap;
import java.util.Map;

import cascading.flow.FlowProcess;
import cascading.operation.BaseOperation;
import cascading.operation.Filter;
import cascading.operation.FilterCall;
import cascading.operation.OperationCall;
import cascading.operation.buffer.FirstNBuffer;
import cascading.pipe.Each;
import cascading.pipe.Every;
import cascading.pipe.GroupBy;
import cascading.pipe.Pipe;
import cascading.pipe.SubAssembly;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;
import cascading.tuple.Tuples;

public class UniqueOP extends SubAssembly {

	private static final long serialVersionUID = 3488967940167360715L;

	public enum Include {
		ALL, NO_NULLS
	}

	public static class FilterPartialDuplicates extends BaseOperation<LinkedHashMap<Tuple, Object>> implements
			Filter<LinkedHashMap<Tuple, Object>> {

		private static final long serialVersionUID = 2343482432630029331L;
		private int threshold = 10000;
		private Include include = Include.ALL;

		public FilterPartialDuplicates() {
		}

		public FilterPartialDuplicates(int threshold) {
			this.threshold = threshold;
		}

		public FilterPartialDuplicates(Include include, int threshold) {
			this.threshold = threshold;
			this.include = include == null ? this.include : include;
		}

		public void prepare(FlowProcess flowProcess, OperationCall<LinkedHashMap<Tuple, Object>> operationCall) {
			operationCall.setContext(new LinkedHashMap<Tuple, Object>(threshold, 0.75f, true) {

				private static final long serialVersionUID = 2363292656015123139L;

				@Override
				protected boolean removeEldestEntry(Map.Entry eldest) {
					return size() > threshold;
				}
			});
		}

		public boolean isRemove(FlowProcess flowProcess, FilterCall<LinkedHashMap<Tuple, Object>> filterCall) {
			Tuple args = filterCall.getArguments().getTuple();

			switch (include) {
			case ALL:
				break;

			case NO_NULLS:
				if (Tuples.frequency(args, null) == args.size())
					return true;

				break;
			}

			if (filterCall.getContext().containsKey(args))
				return true;

			filterCall.getContext().put(filterCall.getArguments().getTupleCopy(), null);

			return false;
		}

		public void cleanup(FlowProcess flowProcess, OperationCall<LinkedHashMap<Tuple, Object>> operationCall) {
			operationCall.setContext(null);
		}

		public boolean equals(Object object) {
			if (this == object)
				return true;
			if (!(object instanceof FilterPartialDuplicates))
				return false;
			if (!super.equals(object))
				return false;

			FilterPartialDuplicates that = (FilterPartialDuplicates) object;

			if (threshold != that.threshold)
				return false;

			return true;
		}

		public int hashCode() {
			int result = super.hashCode();
			result = 31 * result + threshold;
			return result;
		}
	}

	public UniqueOP(Pipe pipe, Fields uniqueFields) {
		this(null, pipe, uniqueFields);
	}

	public UniqueOP(Pipe pipe, Fields uniqueFields, Include include) {
		this(null, pipe, uniqueFields, include);
	}

	public UniqueOP(Pipe pipe, Fields uniqueFields, int threshold) {
		this(null, pipe, uniqueFields, threshold);
	}

	public UniqueOP(Pipe pipe, Fields uniqueFields, Include include, int threshold) {
		this(null, pipe, uniqueFields, include, threshold);
	}

	public UniqueOP(String name, Pipe pipe, Fields uniqueFields) {
		this(name, pipe, uniqueFields, 10000);
	}

	public UniqueOP(String name, Pipe pipe, Fields uniqueFields, Include include) {
		this(name, pipe, uniqueFields, include, 10000);
	}

	public UniqueOP(String name, Pipe pipe, Fields uniqueFields, int threshold) {
		this(name, Pipe.pipes(pipe), uniqueFields, threshold);
	}

	public UniqueOP(String name, Pipe pipe, Fields uniqueFields, Include include, int threshold) {
		this(name, Pipe.pipes(pipe), uniqueFields, include, threshold);
	}

	public UniqueOP(Pipe[] pipes, Fields uniqueFields) {
		this(null, pipes, uniqueFields, 10000);
	}

	public UniqueOP(Pipe[] pipes, Fields uniqueFields, Include include) {
		this(null, pipes, uniqueFields, include, 10000);
	}

	public UniqueOP(Pipe[] pipes, Fields uniqueFields, int threshold) {
		this(null, pipes, uniqueFields, threshold);
	}

	public UniqueOP(Pipe[] pipes, Fields uniqueFields, Include include, int threshold) {
		this(null, pipes, uniqueFields, include, threshold);
	}

	public UniqueOP(String name, Pipe[] pipes, Fields uniqueFields) {
		this(name, pipes, uniqueFields, 10000);
	}

	public UniqueOP(String name, Pipe[] pipes, Fields uniqueFields, Include include) {
		this(name, pipes, uniqueFields, include, 10000);
	}

	public UniqueOP(String name, Pipe[] pipes, Fields uniqueFields, int threshold) {
		this(name, pipes, uniqueFields, null, threshold);
	}

	public UniqueOP(String name, Pipe[] pipes, Fields uniqueFields, Include include, int threshold) {
		super(pipes);

		Pipe[] filters = new Pipe[pipes.length];
		FilterPartialDuplicates partialDuplicates = new FilterPartialDuplicates(include, threshold);

		for (int i = 0; i < filters.length; i++)
			filters[i] = new Each(pipes[i], uniqueFields, partialDuplicates);

		Pipe pipe = new GroupBy(name, filters, uniqueFields);
		pipe = new Every(pipe, Fields.ALL, new FirstNBuffer(), Fields.RESULTS);

		setTails(pipe);
	}
}
