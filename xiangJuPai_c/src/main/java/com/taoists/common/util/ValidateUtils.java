package com.taoists.common.util;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Jun Liu
 * @email 732839131@qq.com
 * @date 2014-5-26 上午2:31:58
 */
public class ValidateUtils {
	/**
	 * 判断对象是否为空（包括集合、数组没值,空串）
	 * 
	 * @param object
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public static boolean isNotEmpty(Object object) {
		if (object == null) {
			return false;
		}
		if (object instanceof String) {
			return !"".equals(object.toString().trim());
		}
		if (object instanceof List) {
			return !(((List) object).size() == 0);
		}
		if (object instanceof Object[]) {
			return !(((Object[]) object).length == 0);
		}
		if (object instanceof Map) {
			return !((Map) object).isEmpty();
		}
		return true;
	}

	/**
	 * 判断对象不为空（包括集合没值）
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(Object object) {
		return !isNotEmpty(object);
	}

	/**
	 * 队列比较
	 * 
	 * @param <T>
	 * @param a
	 * @param b
	 * @return
	 */
	public static <T extends Comparable<T>> boolean compare(List<T> a, List<T> b) {
		if (a.size() != b.size())
			return false;
		Collections.sort(a);
		Collections.sort(b);
		for (int i = 0; i < a.size(); i++) {
			if (!a.get(i).equals(b.get(i)))
				return false;
		}
		return true;
	}
}
