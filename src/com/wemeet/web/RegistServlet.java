package com.wemeet.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wemeet.domain.User;
import com.wemeet.service.UserService;
import com.wemeet.service.UserServiceImpl;

import com.wemeet.util.MD5Utils;

@WebServlet("/RegistServlet")
/**
 * ��Servlet�����û��ĵ�¼У�飬���������Ϣ����ʵ���ض���
 * @author Administrator
 *
 */
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");            //��ֹ������������
		response.setContentType("text/html;charset=utf-8");
		
		try{
			String username=request.getParameter("username");        //��ȡform���е��û���������
			String password=request.getParameter("password");
			
				UserService service=new UserServiceImpl();           //ʵ����һ�����������ɵ�¼
				User user=new User();
				user.setName(username);
				user.setPassword(MD5Utils.md5(password));
				service.regist(user);                 //ʵ��ע��
				response.setHeader("Refresh", "1;url=index.html");   //ע��ɹ��������ض�����ҳ��
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);                      
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
