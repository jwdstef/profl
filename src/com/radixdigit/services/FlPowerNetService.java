package com.radixdigit.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.radixdigit.entity.NetTree;

public interface FlPowerNetService {

public List<NetTree> selectLines();
	
	public List<NetTree> selectTowers(NetTree netTree);
	
	public List<NetTree> selectPoints(NetTree netTree);
	
}
