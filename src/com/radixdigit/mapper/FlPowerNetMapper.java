package com.radixdigit.mapper;

import java.util.List;

import com.radixdigit.entity.NetTree;

public interface FlPowerNetMapper {

	public List<NetTree> selectLines();
	
	public List<NetTree> selectTowers(NetTree netTree);
	
	public List<NetTree> selectPoints(NetTree netTree);
}
