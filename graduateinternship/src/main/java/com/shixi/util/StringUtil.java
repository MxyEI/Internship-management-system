package com.shixi.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: String工具类
 * @author: hw
 * @date: 2018年3月28日 下午5:10:03
 */
public class StringUtil {

	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(String str) {
		if ((str != null) && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @Description: 格式化str, %str%
	 * @author: hw
	 * @date: 2018年3月28日 下午5:12:42
	 */
	public static String formatLike(String str) {
		if (isNotEmpty(str)) {
			return "%" + str + "%";
		} else {
			return null;
		}
	}

	/**
	 * @Description: 格式化ids
	 * @param ids
	 *            1,2,3
	 * @author: hw
	 * @date: 2018年3月30日 下午1:48:51
	 */
	public static List<Integer> formatIdsToList(String ids) {
		List<Integer> list = new ArrayList<Integer>();
		String[] idss = ids.split(",");
		for (String id : idss) {
			list.add(Integer.valueOf(id));
		}
		return list;
	}
}
