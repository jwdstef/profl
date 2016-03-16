package com.radixdigit.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.radixdigit.utils.Constants;
/**
 * 
 * 针对于於Mybatis通用的數據分頁模型
 *	Page
 * @param <T>
 */
public class Page <T>{
	/**
	 * 总页数
	 */
	@SuppressWarnings("unused")
	private Integer countPage;
	/**
	 * 当前页
	 */
	private Integer currPage = 3;
	/**
	 * 每页显示记录数
	 */
	private Integer pageSize=Constants.length;
	/**
	 * 开始位置
	 */
	private Integer startIndex=0;
	/**
	 * 记录的数据
	 */
	private List<T> entryList;
	/**
	 * 总记录数
	 */
	private Integer countRecord;
	/**
	 * oracle结束索引
	 */
	private Integer endIndex=15;
	/**
	 * sql参数
	 */
	 private Map<String, Object> params = new HashMap<String, Object>();//
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	/*
	 * 泛型
	 */
	private T t;
	
	public T getT() {
		return t;
	}
	/**
	 * 获取总记录数
	 * @return
	 */
	public Integer getCountRecord() {
		return countRecord;
	}
	
	public void setCountRecord(Integer countRecord) {
		this.countRecord = countRecord;
	}

	public void setT(T t) {
		this.t = t;
	}
	/**
	 * MySQL数据库的构造函数
	 * @param currPage
	 * @param pageSize
	 */
	public Page(Integer currPage, Integer pageSize) {
		this.currPage = currPage;
		this.pageSize = pageSize;
		this.startIndex = (currPage-1)*pageSize;
	}
	public Integer getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}

	/**
	 * Oracle数据库的构造函数
	 * @param currPage
	 * @param pageSize
	 * @param startIndex
	 */
	public Page(Integer currPage, Integer pageSize,Integer startIndex) {
		this.currPage = currPage;
		this.pageSize = pageSize;
		this.startIndex = startIndex;
		this.endIndex = startIndex+pageSize;
	}
	
	public Page() {}

	public List<T> getEntryList() {
		return entryList;
	}

	public Integer getCountPage() {
		return countPage = ((countRecord%pageSize)!=0) ? countRecord/pageSize+1 : countRecord/pageSize;
	}

	public void setCountPage(Integer countPage) {
		this.countPage = countPage;
	}

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
		this.startIndex = ((currPage<=0?1:currPage)-1)*pageSize;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public void setEntryList(List<T> entryList) {
		this.entryList = entryList;
	}
}
