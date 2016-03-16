package com.radixdigit.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.radixdigit.entity.FlUserUser;

@Service
public interface FlUserUserService {
	List<FlUserUser> listPageUser(FlUserUser user);
	void save(FlUserUser user);
	public FlUserUser login(FlUserUser user);
}
