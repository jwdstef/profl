package com.radixdigit.services;

import java.util.List;
import java.util.Map;

import com.radixdigit.entity.FlData;
import com.radixdigit.entity.FlGlobalInfo;

public interface FlDataService {

	/**
	 * 折线图
	 * @param map
	 * @return
	 */
	public List<FlData> selectLineData(Map<String, Object> map);
	
	/**
	 * 变形量折线图
	 * @param map
	 * @return
	 */
	public List<FlData> selectDefLineData(Map<String, Object> map);
	
	/**
	 * 查询站心坐标最新一条数据
	 * @param map
	 * @return
	 */
	public FlData selectAddData(Map<String, Object> map);
	
	/**
	 * 查询站心坐标第一条数据
	 * @param map
	 * @return
	 */
	public FlData firstData(Map<String, Object> map);
	
	/**
	 * 查询变形量最新一条数据
	 * @param map
	 * @return
	 */
	public FlData selectDefAddData(Map<String, Object> map);
	
	/**
	 * 查询变形均值和变形精度
	 * @param map
	 * @return
	 */
	public FlData selectChangeData(Map<String, Object> map);

	/**
	 * 得到全局数据(开始计数时间)
	 */
	public FlGlobalInfo globalData(Map<String, Object> map);
	
	/**
	 * 查询当前记录时间
	 * @param map
	 * @return
	 */
	public FlData timeNow(Map<String, Object> map);
	
	public FlData startTimeNow(Map<String, Object> map);
	
	public FlGlobalInfo selectPointState(Map<String, Object> map);
	
	public void updateFlData(Map<String, Object> map);
	/**
	 * 时间
	 * @param map
	 * @return
	 */
	public FlData selectTimeData(Map<String, Object> map);

}
