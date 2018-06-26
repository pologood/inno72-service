package com.inno72.service;

import java.util.List;

import com.inno72.model.UserInfo;

public interface UserInfoService {
	
	public List<UserInfo> findAll();
	
	public UserInfo findByKey(UserInfo userInfo);

}
