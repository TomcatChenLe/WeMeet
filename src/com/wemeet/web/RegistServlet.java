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
 * 该Servlet用于用户的登录校验，如果输入信息无误并实现重定向
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
		
		request.setCharacterEncoding("utf-8");            //防止出现中文乱码
		response.setContentType("text/html;charset=utf-8");
		
		try{
			String username=request.getParameter("username");        //获取form表单中的用户名和密码
			String password=request.getParameter("password");
			
				UserService service=new UserServiceImpl();           //实例化一个服务对象，完成登录
				User user=new User();
				user.setName(username);
				user.setPassword(MD5Utils.md5(password));
				service.regist(user);                 //实现注册
				response.setHeader("Refresh", "1;url=index.html");   //注册成功，请求重定向到主页面
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
