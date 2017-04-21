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
 * ��Servlet����ajax�첽�������û�ע��ʱ���û����Ƿ����
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

		request.setCharacterEncoding("utf-8");                //���ܹ�ʽ-��ֹ������������
		response.setContentType("text/html;charset=utf-8");
		
		UserService service =new UserServiceImpl();
		String username=request.getParameter("username");
		String msg=null;
		if(!service.hasName(username)){
			msg="{msg:'�û����Ѿ����ڣ�',stat:1}";   //stst����ҳ����ʾЧ��
		}
		else
			msg="{msg:'�û��������ڣ�����ע�ᣡ',stat:0}";
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
