package com.wemeet.service;

import com.wemeet.domain.User;

public interface UserService {

	/**
	 * ע���û�
	 * @param user ��װ���û����ݵ�userbean
	 */
	void regist(User user);
	
	/**
	 * �û���¼������dao����ķ������������ֵΪtrue��ʾ���Ե�½����֮�û���Ϣ����ȷ
	 * @param 
	 * @param 
	 * @return
	 */
	boolean login(User user);

	/**
	 * �����û������ж��Ƿ����ע��
	 * @param username,�û���
	 * @return
	 */
	boolean hasName(String username);
}

