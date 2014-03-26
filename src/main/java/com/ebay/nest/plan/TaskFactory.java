package com.ebay.nest.plan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ebay.nest.NestConf;
import com.ebay.nest2.plan.MapredWork;
import com.ebay.nest2.exec.MapRedTask;
import com.ebay.nest2.exec.Task;

public final class TaskFactory {

	public static final class TaskTuple<T extends Serializable> {
		public Class<T> workClass;
		public Class<? extends Task<T>> taskClass;

		public TaskTuple(Class<T> workClass, Class<? extends Task<T>> taskClass) {
			this.workClass = workClass;
			this.taskClass = taskClass;
		}
	}

	public static ArrayList<TaskTuple<? extends Serializable>> taskvec;
	static {
		taskvec = new ArrayList<TaskTuple<? extends Serializable>>();

		taskvec.add(new TaskTuple<MapredWork>(MapredWork.class, MapRedTask.class));

	}

	private static ThreadLocal<Integer> tid = new ThreadLocal<Integer>() {
		protected synchronized Integer initialValue() {
			return Integer.valueOf(0);
		}
	};

	public static int getAndIncrementId() {
		int curValue = tid.get().intValue();
		tid.set(new Integer(curValue + 1));
		return curValue;
	}

	public static void resetId() {
		tid.set(Integer.valueOf(0));
	}

	public static <T extends Serializable> Task<T> get(Class<T> workClass, NestConf conf) {

		for (TaskTuple<? extends Serializable> t : taskvec) {
			if (t.workClass == workClass) {
				try {
					Task<T> ret = (Task<T>) t.taskClass.newInstance();
					ret.setId("Stage-" + Integer.toString(getAndIncrementId()));
					return ret;
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}

		throw new RuntimeException("No task for work class " + workClass.getName());
	}

	public static <T extends Serializable> Task<T> get(T work, NestConf conf, Task<? extends Serializable>... tasklist) {
		Task<T> ret = get((Class<T>) work.getClass(), conf);
		ret.setWork(work);
		if (tasklist.length == 0) {
			return (ret);
		}

		ArrayList<Task<? extends Serializable>> clist = new ArrayList<Task<? extends Serializable>>();
		for (Task<? extends Serializable> tsk : tasklist) {
			clist.add(tsk);
		}
		ret.setChildTasks(clist);
		return (ret);
	}

	public static <T extends Serializable> Task<T> getAndMakeChild(T work, NestConf conf,
			Task<? extends Serializable>... tasklist) {
		Task<T> ret = get((Class<T>) work.getClass(), conf);
		ret.setWork(work);
		if (tasklist.length == 0) {
			return (ret);
		}

		makeChild(ret, tasklist);

		return (ret);
	}

	public static void makeChild(Task<?> ret, Task<? extends Serializable>... tasklist) {
		for (Task<? extends Serializable> tsk : tasklist) {
			List<Task<? extends Serializable>> children = tsk.getChildTasks();
			if (children == null) {
				children = new ArrayList<Task<? extends Serializable>>();
			}
			children.add(ret);
			tsk.setChildTasks(children);
		}
	}

	private TaskFactory() {
	}

}
