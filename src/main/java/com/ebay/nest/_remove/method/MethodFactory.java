package com.ebay.nest._remove.method;

import java.util.Map;

import com.ebay.nest.NestContext;

public class MethodFactory {
	public static Method getMethod(String name, NestContext context,
			Map<String, Object> paramMap) {
		if (name.startsWith("ddi_")) {
			if (name.equals("ddi_stg_direct")) {
				// return new DirectMethod(context, paramMap);
			} else if (name.equals("ddi_stg_upsert")) {
				// return new DDIStgUpsertMethod(context, paramMap);
			} else if (name.equals("ddi_stg_del_ins")) {
				// return new DDIStgDelInsMethod(context, paramMap);
			}
		}
		if (name.equals("dedup")) {
			// return new DedupMethod();
		} else if (name.equals("transform")) {
			// return new TransformMethod();
		} else if (name.equals("split_datetime")) {
			// return new SplitDatetimeMethod();
		} else if (name.equals("retain")) {
			// return new RetainMethod();
		} else if (name.equals("upsert")) {
			// return new UpsertMethod();
		} else if (name.equals("del_ins")) {
			// return new DelInsMethod();
		}
		return null;
	}
}
