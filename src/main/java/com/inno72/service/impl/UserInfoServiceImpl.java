package com.inno72.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inno72.mapper.UserInfoMapper;
import com.inno72.model.UserInfo;
import com.inno72.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Override
	public List<UserInfo> findAll() {
		return userInfoMapper.selectAll();
	}

	@Override
	public UserInfo findByKey(UserInfo userInfo) {
		return userInfoMapper.selectByPrimaryKey(userInfo);
	}

}
