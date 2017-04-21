package com.wemeet.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wemeet.domain.User;
import com.wemeet.service.UserService;
import com.wemeet.service.UserServiceImpl;

import com.wemeet.util.MD5Utils;
/**
 * Servlet implementation class LoginServletS
 */
@WebServlet("/LoginServlet")
/**
 * 该Servlet用于用户的注册，注册成功时完成重定向到首页的操作
 * @author Administrator
 *
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}
/**
 * 该Servlet只用于检验用户的登录信息正确时是否重定向与信息错误时留在此页
 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");                //万能公式-防止出现中文乱码
		response.setContentType("text/html;charset=utf-8");

		String valistr1 = request.getParameter("valistr");		            //获取输入的获得验证码
		String valistr2 = (String) request.getSession().getAttribute("piccode");   //获得保存在session中的验证码

		if(valistr1 == null || valistr2 == null || !valistr1.equals(valistr2)){      //比对验证码是否输入正确，完成登录，不正确则提示
			response.sendRedirect("login.html");
		}else{           
			String username = request.getParameter("username");
			String password = MD5Utils.md5(request.getParameter("password"));
			UserService service=new UserServiceImpl();      //实例化一个服务对象，完成用户登录
			User user=new User();
			user.setName(username);
			user.setPassword(password);

			if(service.login(user)){          //如果返回值为true表示用户名和密码正确，完成登录，重定向到首页
				//.登录用户重定向到主页，将用户信息保存在session中
				request.getSession().setAttribute("user", user);
				if("true".equals(request.getParameter("remname"))){     //如果选择了记住用户名和密码，则生成一个Cookie对象，记住用户名，完成记住用户名和密码三十天
					//Cookie不能是汉字，需要URLEncoder.encode进行转换
					Cookie remnameC = new Cookie("remname",URLEncoder.encode(user.getName(),"utf8"));
					remnameC.setPath(request.getContextPath());
					remnameC.setMaxAge(3600*24*30);
					response.addCookie(remnameC);
				}else{                           //如果不选择记住用户名，则不记住用户名和密码
					Cookie remnameC = new Cookie("remname","");
					remnameC.setPath("/");
					remnameC.setMaxAge(0);     //设置Cookie的最长生命周期
					response.addCookie(remnameC);
				}

				response.sendRedirect("index.html");    //用户信息正确重定向到...........
				return;
			}
			else  {      //用户名密码不正确则留在登录页面
				response.sendRedirect("login.html");
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
