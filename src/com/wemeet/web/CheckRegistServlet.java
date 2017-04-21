package com.wemeet.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wemeet.service.UserService;
import com.wemeet.service.UserServiceImpl;

/**
 * Servlet implementation class CheckRegistServlet
 */
@WebServlet("/CheckRegistServlet")
/**
 * 该Servlet用于ajax异步传输提用户注册时该用户名是否可用
 * @author Administrator
 *
 */
public class CheckRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckRegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");                //万能公式-防止出现中文乱码
		response.setContentType("text/html;charset=utf-8");
		
		UserService service =new UserServiceImpl();
		String username=request.getParameter("username");
		String msg=null;
		if(!service.hasName(username)){
			msg="{msg:'用户名已经存在！',stat:1}";   //stst用于页面显示效果
		}
		else
			msg="{msg:'用户名不存在！可以注册！',stat:0}";
		response.getWriter().write(msg);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
