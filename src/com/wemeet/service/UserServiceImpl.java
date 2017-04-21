package com.wemeet.service;

import com.wemeet.dao.UserDao;
import com.wemeet.dao.UserDaoImpl;
import com.wemeet.domain.User;

public class UserServiceImpl implements UserService{
	private UserDao dao = new UserDaoImpl();
	
	public boolean hasName(String username){
		return dao.findUserByName(username);
	}
	
	public void regist(User user) {
		try{
			if(dao.findUserByName(user.getName())){     //返回值为true，表示可以注册
				user.setPassword(user.getPassword());
				dao.addUser(user);
			}else{
				throw new RuntimeException("用户名已经存在!!");    //反之抛出一个异常，表示用户名已存在，**-----前端待解决
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean login(User user){
		return dao.getLogin(user.getName(),user.getPassword());
	}

}

