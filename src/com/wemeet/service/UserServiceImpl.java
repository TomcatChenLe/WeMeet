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
			if(dao.findUserByName(user.getName())){     //����ֵΪtrue����ʾ����ע��
				user.setPassword(user.getPassword());
				dao.addUser(user);
			}else{
				throw new RuntimeException("�û����Ѿ�����!!");    //��֮�׳�һ���쳣����ʾ�û����Ѵ��ڣ�**-----ǰ�˴����
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean login(User user){
		return dao.getLogin(user.getName(),user.getPassword());
	}

}

