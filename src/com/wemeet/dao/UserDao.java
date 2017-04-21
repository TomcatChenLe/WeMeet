package com.wemeet.dao;
import com.wemeet.domain.User;

public interface UserDao {
	boolean findUserByName(String username);
	
	/**
	 * ����û�
	 * @param user ��װ���û���Ϣ��bean
	 * @param conn 
	 */
	void addUser(User user);
	/**
	 * �����û������������룬ʵ�ֵ�¼
	 * @param name   �û���
	 * @param password   �������ֵΪtrue���ʾ���Ե�½����֮��ʾ���벻��
	 * @return
	 */
	public boolean getLogin(String name,String password);
}

