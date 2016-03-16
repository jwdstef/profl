package com.radixdigit.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radixdigit.entity.NetTree;
import com.radixdigit.mapper.FlPowerNetMapper;
import com.radixdigit.services.FlPowerNetService;

@Service
public class FlPowerNetServiceImpl implements FlPowerNetService{

	@Autowired
	private FlPowerNetMapper flPowerNetMapper;
	
	@Override
	public List<NetTree> selectLines() {
		return flPowerNetMapper.selectLines();
	}

	@Override
	public List<NetTree> selectTowers(NetTree netTree) {
		return flPowerNetMapper.selectTowers(netTree);
	}

	@Override
	public List<NetTree> selectPoints(NetTree netTree) {
		return flPowerNetMapper.selectPoints(netTree);
	}

}
