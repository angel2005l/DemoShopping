package com.edu.util;

public class StrUtil {
	public static boolean isBlank(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		return false;
	}

	public static boolean notBlank(String str) {
		if (str == null || "".equals(str)) {
			return false;
		}
		return true;
	}
}
