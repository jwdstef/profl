package com.radixdigit.utils;

import java.util.Random;
/**
 * 数字工具类
 * @author Administrator
 *
 */
public class NumberCommon {
	/**
	 * String转换long
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static Long convertStrForLong(String str) throws Exception {
		return Long.parseLong(str);
	}

	/**
	 * String转换int
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static int convertStrForInt(String str) throws Exception {
		return Integer.parseInt(str);
	}
	/**
	 * 自动生成n位数的随机数
	 * @param n
	 * @return
	 */
	public static String random(int n) {
		if (n < 1 || n > 10) {
			throw new IllegalArgumentException("cannot random " + n
					+ " bit number");
		}
		Random ran = new Random();
		if (n == 1) {
			return String.valueOf(ran.nextInt(10));
		}
		int bitField = 0;
		char[] chs = new char[n];
		for (int i = 0; i < n; i++) {
			while (true) {
				int k = ran.nextInt(10);
				if ((bitField & (1 << k)) == 0) {
					bitField |= 1 << k;
					chs[i] = (char) (k + '0');
					break;
				}
			}
		}
		return new String(chs);
	}
}
