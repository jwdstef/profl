package com.radixdigit.utils;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonUtil {
	
	
	/**
	 *  根据list对相集合转化jsonarray
	 * @param list
	 * @return
	 */
	public static JSONArray jsonArrayConvert(@SuppressWarnings("rawtypes") List list) {
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray;

	}

	/**
	 * 带过滤功能的list对相集合转化jsonarray
	 * @param list
	 * @param jsonConfig
	 * @return
	 */
	public static JSONArray jsonArrayConvert(@SuppressWarnings("rawtypes") List list, JsonConfig jsonConfig) {
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		return jsonArray;
	}

	/**
	 * 单个对象转化json
	 * @param object
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Object jsonObjConvert(Object object) {
		JSONObject jsonObject = new JSONObject();

		return jsonObject.fromObject(object);
	}

	/**
	 *  带过滤功能单个对象转化json
	 * @param object
	 * @param jsonConfig
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Object jsonObjConvert(Object object, JsonConfig jsonConfig) {
		JSONObject jsonObject = new JSONObject();

		return jsonObject.fromObject(object, jsonConfig);
	}

	/**
	 * 根据过滤属性数组获取json过滤对象
	 * @param strings
	 * @return
	 */
	public static JsonConfig jsonConfigConvert(String... strings) {
		JsonConfig jsonConfig = new JsonConfig();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < strings.length; i++) {
			list.add(strings[i]);
		}

		Object[] objs = list.toArray();
		String str[] = new String[objs.length];
		for (int i = 0; i < objs.length; i++) {
			str[i] = (String) objs[i];
		}
		jsonConfig.setExcludes(str);
		System.out.println(jsonConfig);
		return jsonConfig;

	}

	/**
	 * 根据过滤單個属性获取json过滤对象
	 * @param objectName
	 * @return
	 */

	public static JsonConfig jsonConfigConvert(String objectName) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { objectName });
		return jsonConfig;
	}
	
	
	


}
