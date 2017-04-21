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

/**
 * Servlet implementation class TrLoginServlet
 */
@WebServlet("/CheckLoginServlet")
/**
 * 该Servlet用于在用户登录时，通过ajax异步传输验证该用户输入的信息以及验证码是否正确，并提示
 * @author Administrator
 *
 */
public class CheckLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**通过ajax异步传输实现用户信息的检验并进行提醒
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");                //万能公式-防止出现中文乱码
		response.setContentType("text/html;charset=utf-8");

		String msg=null;     //返回的json对象
		String username = request.getParameter("username");
		String password = MD5Utils.md5(request.getParameter("password"));
		String valistr1 = request.getParameter("valistr");		            //获取输入的获得验证码
		valistr1=valistr1.toLowerCase();    //将用户输入的验证码化成小写字母的形式，验证时可以不区分大小写
		String valistr2 = (String) request.getSession().getAttribute("piccode");   //获得保存在session中的验证码

		UserService service=new UserServiceImpl();      //实例化一个服务对象，完成用户登录
		User user=new User();
		user.setName(username);
		user.setPassword(password);
		if(!service.login(user)){
			msg="{msg:'用户名或密码错误！',stat:0}";   //stat用于区分用户信息的对错进行不同颜色的提示
		}
		else if(service.login(user)){
			msg="{msg:'用户名和密码正确!',stat:1}";
		}

		//			if(valistr1==null)
		//				msg="{msg:'请输入验证码！',stat:2}";
		if(valistr2 == null || !valistr1.equals(valistr2)) {     //比对验证码是否输入正确，完成登录，不正确则提示
			msg="{msg:'验证码输入错误！',stat:2}";
		}
		response.getWriter().write(msg);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
