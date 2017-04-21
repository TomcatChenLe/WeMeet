package com.wemeet.dao;
import com.wemeet.domain.User;

public interface UserDao {
	boolean findUserByName(String username);
	
	/**
	 * 添加用户
	 * @param user 封装了用户信息的bean
	 * @param conn 
	 */
	void addUser(User user);
	/**
	 * 根据用户名，查找密码，实现登录
	 * @param name   用户名
	 * @param password   如果返回值为true则表示可以登陆，反之提示密码不对
	 * @return
	 */
	public boolean getLogin(String name,String password);
}

