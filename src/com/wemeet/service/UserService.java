package com.wemeet.service;

import com.wemeet.domain.User;

public interface UserService {

	/**
	 * 注册用户
	 * @param user 封装了用户数据的userbean
	 */
	void regist(User user);
	
	/**
	 * 用户登录，调用dao层里的方法，如果返回值为true表示可以登陆，反之用户信息不正确
	 * @param 
	 * @param 
	 * @return
	 */
	boolean login(User user);

	/**
	 * 根据用户名，判断是否可以注册
	 * @param username,用户名
	 * @return
	 */
	boolean hasName(String username);
}

