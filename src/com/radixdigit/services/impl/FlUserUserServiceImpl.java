package com.radixdigit.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radixdigit.entity.FlUserUser;
import com.radixdigit.mapper.FlUserUserMapper;
import com.radixdigit.services.FlUserUserService;

@Service
public class FlUserUserServiceImpl implements FlUserUserService{
	
	@Autowired
	private FlUserUserMapper flUserUserMapper;
	
	@Override
	public List<FlUserUser> listPageUser(FlUserUser user) {
		// TODO Auto-generated method stub
		return flUserUserMapper.listPageUser(user);
	}
	@Override
	public void save(FlUserUser user) {
		flUserUserMapper.insert(user);
	}
	@Override
	public FlUserUser login(FlUserUser user){
		return flUserUserMapper.login(user);
	}

}
