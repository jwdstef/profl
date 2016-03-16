package com.radixdigit.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radixdigit.entity.FlData;
import com.radixdigit.entity.FlGlobalInfo;
import com.radixdigit.mapper.FlDataMapper;
import com.radixdigit.services.FlDataService;

@Service
public class FlDataServiceImpl implements FlDataService{

	@Autowired
	private FlDataMapper flDataMapper;
	
	@Override
	public List<FlData> selectLineData(Map<String, Object> map) {
		return flDataMapper.selectLineData(map);
	}

	@Override
	public List<FlData> selectDefLineData(Map<String, Object> map){
		return flDataMapper.selectDefLineData(map);
	}
	
	@Override
	public FlData selectAddData(Map<String, Object> map) {
		return flDataMapper.selectAddData(map);
	}
	
	@Override
	public FlData firstData(Map<String, Object> map){
		return flDataMapper.firstData(map);
	}
	
	@Override
	public FlData selectDefAddData(Map<String, Object> map){
		return flDataMapper.selectDefAddData(map);
	}
	
	@Override
	public FlData selectChangeData(Map<String, Object> map){
		return flDataMapper.selectChangeData(map);
	}
	
	@Override
	public FlGlobalInfo globalData(Map<String, Object> map){
		return flDataMapper.globalData(map);
	}
	
	@Override
	public FlGlobalInfo selectPointState(Map<String, Object> map){
		return flDataMapper.selectPointState(map);
	}
	
	@Override
	public FlData timeNow(Map<String, Object> map){
		return flDataMapper.timeNow(map);
	}
	
	@Override
	public FlData startTimeNow(Map<String, Object> map){
		return flDataMapper.startTimeNow(map);
	}
	
	@Override
	public void updateFlData(Map<String, Object> map){
		flDataMapper.updateFlData(map);
	}

	@Override
	public FlData selectTimeData(Map<String, Object> map){
		return flDataMapper.selectTimeData(map);
	}
}
