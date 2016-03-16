package com.radixdigit.mapper;

import java.util.List;

import com.radixdigit.base.BaseSqlMapper;
import com.radixdigit.entity.FlUserUser;

public interface FlUserUserMapper extends BaseSqlMapper<FlUserUser, Object>{
	
	List<FlUserUser> listPageUser(FlUserUser user);
	
	FlUserUser login(FlUserUser user);
}