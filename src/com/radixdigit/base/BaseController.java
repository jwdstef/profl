package com.radixdigit.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.radixdigit.entity.files.Files;

/**
 * 控制器基类
 * 
 * @author chegnjun
 * 
 * @param <T>
 */
public class BaseController<T> {
	/**
	 * 服务器容器对象
	 */
	private static ThreadLocal<HttpServletRequest> request_threadLocal = new ThreadLocal<HttpServletRequest>();

	private static ThreadLocal<HttpServletResponse> reponse_threadLocal = new ThreadLocal<HttpServletResponse>();

	public static void setRequest(HttpServletRequest request) {
		request_threadLocal.set(request);
	}

	public static HttpServletRequest getRequest() {
		return request_threadLocal.get();
	}

	public static void removeRequest() {
		request_threadLocal.remove();
	}

	public static void setResponse(HttpServletResponse response) {
		reponse_threadLocal.set(response);
	}

	public static HttpServletResponse getResponse() {
		return reponse_threadLocal.get();
	}

	public static void removeResponse() {
		reponse_threadLocal.remove();
	}

	/**
	 * 分页模型
	 */
	protected Page<T> page = new Page<T>();
	/**
	 * 上传文件信息总接收器
	 * 
	 * 根Key为表单name属性，二级集合为Files詳情集合
	 */
	protected Map<String, List<Files>> map;

	/**
	 * 文件详情获取
	 * 
	 * @param map
	 * @param fileFildName
	 * @return
	 */
	public List<Files> getFileDetail(Map<String, List<Files>> map,
			String fileFildName) {
		Set<String> keys = map.keySet();
		List<Files> fileList = new ArrayList<Files>();
		List<Files> files = new ArrayList<Files>();
		for (String key : keys) {
			if (key.contains(fileFildName)) {

				files = map.get(key);
				fileList.add(files.get(0));
			}
		}
		return fileList;

	}

}
