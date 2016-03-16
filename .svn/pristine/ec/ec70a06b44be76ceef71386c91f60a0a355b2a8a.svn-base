/*
 * @Copyright: Copyright 北京网擎科技有限公司 (c) 2012
 * @修改记录： 1、2012-5-2 下午02:48:00，user创建。
 */
package com.radixdigit.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Desc：字符串操作工具类</br>
 * @Filename:StringUtils.java</br>
 * @Author:CJ</br>
 * @Date:2012下午02:48:00</br>
 */
@SuppressWarnings("unchecked")
public class StringUtils {
	public static final String SEPRATOR_COMMA = ",";

	/**
	 * String数组转换成List数组
	 * 
	 * @param str
	 *            字符数组
	 * @return 转换后的list数组
	 */
	public static List<String> StringArrayToList(String[] str) {
		List<String> strList = null;
		if (null != str && str.length > 0) {
			strList = new ArrayList<String>();
			for (String string : str) {
				strList.add(string);
			}
		}
		return strList;
	}

	/**
	 * 字符数组转换成字符串
	 * 
	 * @param str
	 *            字符数组
	 * @return 拼接后的字符串
	 */
	public static String StringArrayToString(String[] str) {
		StringBuffer connectStr = null;
		if (null != str && str.length > 0) {
			connectStr = new StringBuffer();
			for (String string : str) {
				connectStr.append(string);
			}
		}
		return connectStr.toString();
	}

	/**
	 * 转换字符串数组为指定分隔符的字符串
	 * 
	 * @param strList
	 *            字符串数组
	 * @param sepatator
	 *            分隔符
	 * @return
	 */
	public static String listToStringBySeparator(List<String> strList,
			String sepatator) {
		if (null == strList || strList.size() == 0) {
			return null;
		}
		if (null == sepatator || sepatator.trim().length() == 0) {
			sepatator = ",";
		}
		StringBuffer sb = new StringBuffer();
		for (String str : strList) {
			sb.append(str).append(sepatator);
		}
		return sb.toString().substring(0, sb.toString().length() - 1);
	}

	/**
	 * @param s
	 *            字符串
	 * @return false:空 true:不为空
	 */
	public static boolean isNotEmpty(String s) {
		return s == null || s.trim().length() == 0 ? false : true;
	}

	public static boolean isEmpty(String s) {
		return s == null || s.trim().length() == 0 ? true : false;
	}

	/**
	 * 求两数值型字符串之差. author quegh
	 * 
	 * @param str1
	 *            大数
	 * @param str2
	 *            小数
	 * @return 两数减之差
	 */
	public static int strByMult(final String str1, final String str2) {
		String str11 = "";
		String str22 = "";
		if (null == str1 || "".equals(str1)) {
			str11 = "0";
		} else {
			str11 = str1;
		}
		if (null == str2 || "".equals(str2)) {
			str22 = "0";
		} else {
			str22 = str2;
		}
		// 去零操作,例:00590,去零后变为590
		String strMax = str11.replaceFirst("^0*", "");
		String strMin = str22.replaceFirst("^0*", "");
		BigInteger bigIntMax = new BigInteger(strMax);
		BigInteger bigIntMin = new BigInteger(strMin);
		BigInteger bigIntOne = new BigInteger("1");
		// return Integer.parseInt(strMax) - Integer.parseInt(strMin) + 1;
		return bigIntMax.subtract(bigIntMin).add(bigIntOne).intValue();
	}

	/**
	 * 大写字母转换成小写字母，仅限于10以内，如一转换成1
	 * 
	 * @author quegh
	 * @param str
	 *            大写字母。
	 * @return 小写字母
	 */
	public static String bigToSmallSingle(String str) {
		String value = "";
		if (str.length() == 1) {
			if ("一".equals(str)) {
				value = "1";
			} else if ("二".equals(str)) {
				value = "2";
			} else if ("三".equals(str)) {
				value = "3";
			} else if ("四".equals(str)) {
				value = "4";
			} else if ("五".equals(str)) {
				value = "5";
			} else if ("六".equals(str)) {
				value = "6";
			} else if ("七".equals(str)) {
				value = "7";
			} else if ("八".equals(str)) {
				value = "8";
			} else if ("九".equals(str)) {
				value = "9";
			}
		}
		return value;

	}

	/**
	 * 大写字母转换成小写字母，仅限于100以内，如二十四转换成24,八转换成8.
	 * 
	 * @author quegh
	 * @param str
	 *            大写字母。
	 * @return 小写字母
	 */
	public static String bigToSmall(String str) {
		String value = "";
		// 1-9
		if (str.length() == 1 && !"十".equals(str)) {
			value = bigToSmallSingle(str);
		} else if (str.indexOf('十') >= 0) {
			// 10
			if (str.length() == 1) {
				value = "10";
			} else if (str.length() == 2) {
				// 20,30,40,50,60,70,80,90
				if (str.charAt(1) == '十') {
					value = bigToSmallSingle(String.valueOf(str.charAt(0)))
							+ "0";
				}
				// 11-19
				else {
					value = "1"
							+ bigToSmallSingle(String.valueOf(str.charAt(1)));
				}
			}
			// 21-29,31-39,41-49,51-59,61-69,71-79,81-89,91-99
			else if (str.length() == 3) {
				value = bigToSmallSingle(String.valueOf(str.charAt(0)))
						+ bigToSmallSingle(String.valueOf(str.charAt(2)));
			}
		}
		return value;
	}

	/**
	 * 字符串乱码转换
	 * 
	 * @author zhanglun
	 * @param str
	 * @return
	 */
	public static String encodeStr(String str) {
		String s = null;
		try {
			s = new String(str.getBytes("iso-8859-1"), "utf-8").toString();
		} catch (UnsupportedEncodingException e) {
			s = null;
			e.printStackTrace();
		}

		return s;
	}

	/**
	 * List<Map<String,String>>结构的字符串 转换成 List<Map<String,String>>
	 * 
	 * @author SHANJIANLONG
	 * @param string
	 * @return
	 */
	public static List<Map> convertStringToListMap(String string) {
		List<Map> list = new ArrayList<Map>();
		string = string.replace("[", "");
		string = string.replace("]", "");
		if (string.indexOf("{") != -1) {
			String arr[] = string.split("},");
			for (int i = 0; i < arr.length; i++) {
				Map map = new HashMap();
				String str = arr[i];
				str = str.replace("{", "");
				str = str.replace("}", "");
				/******************** 断定每个value后边,后边跟一个空格，但不知道是否一定是这样 ********************************/
				// String arrnext[]=str.split(", ");
				// for(int j=0;j<arrnext.length;j++){
				// String arrnextnext[]=arrnext[j].split("=");
				// map.put(arrnextnext[0].trim(), arrnextnext[1].trim());
				// }

				/****************** 根据=拆分 *************************/
				/*-------拆分时候注意 :如果 1,2,3, 拆分后的长度为 3  如果 1,2,3,4 拆分后的长度为4,以下用来处理这种情况  start--------*/
				String arrnext[] = null;
				if (str.lastIndexOf("=") == str.length() - 1) {
					String a[] = str.split("=");
					arrnext = new String[a.length + 1];
					int k = 0;
					for (; k < a.length; k++) {
						arrnext[k] = a[k];
					}
					arrnext[k] = "";
				} else {
					arrnext = str.split("=");
				}
				/*--------拆分时候注意 :如果 1,2,3, 拆分后的长度为 3  如果 1,2,3,4 拆分后的长度为4 end-------*/
				String key = arrnext[0];
				int j = 1;
				for (; j < arrnext.length - 1; j++) {

					int first = arrnext[j].lastIndexOf(",");
					String strvalue = arrnext[j].substring(0, first);
					String strkey = arrnext[j].substring(first + 1);
					map.put(key.trim(), strvalue.trim());
					key = strkey;
				}
				map.put(key.trim(), arrnext[j].trim());
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * 将用户产品权限 map 转成 String 用于hql语句
	 */
	public static String covertMapToString(
			Map<Integer, Map<String, Map<Integer, String>>> mappro) {

		Iterator<Integer> it = null;
		if (mappro != null) {
			it = mappro.keySet().iterator();
		}
		StringBuffer sb = new StringBuffer();
		sb.append("(");
		sb.append("'',");
		if (it != null) {
			while (it.hasNext()) {
				Map<String, Map<Integer, String>> map = mappro.get(it.next());
				Iterator<String> it1 = map.keySet().iterator();
				while (it1.hasNext()) {
					Map<Integer, String> map1 = map.get(it1.next());
					Iterator<Integer> it2 = map1.keySet().iterator();

					while (it2.hasNext()) {
						String prodName = map1.get(it2.next());
						sb.append("'");
						sb.append(prodName.trim());
						sb.append("'");
						sb.append(",");
					}
				}
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");

		// return sb.toString();
		// 由于产品权限没有添加 暂时写死
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(" ('铲车','装载机','坦克') ");
		return stringBuffer.toString();

	}

	/**
	 * 将日期字符串格式化
	 * 
	 * @param dateStr
	 *            日期字符串,形如20120922
	 * @return 2012-09-22
	 */
	public static Date formatString2Date(String dateStr) {
		if (null == dateStr || dateStr.length() == 0) {
			return new Date();
		}
		String year = dateStr.substring(0, 4);
		String month = dateStr.substring(4, 6);
		String day = dateStr.substring(6, 8);
		StringBuffer dateString = new StringBuffer("");
		dateString.append(year);
		dateString.append("-");
		dateString.append(month);
		dateString.append("-");
		dateString.append(day);
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(year), Integer.parseInt(month), Integer
				.parseInt(day));
		return c.getTime();
	}

	public static void main(String args[]) {
		System.out.println(formatString2Date("20120922"));
	}

	/**
	 * 判斷字符是否為null,true設置空字符,false str
	 * 
	 * @param str
	 * @return
	 */
	public static String isNull(String str) {
		return null == str ? str = "" : str;
	}
}
