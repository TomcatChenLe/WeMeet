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
 * ��Servlet�������û���¼ʱ��ͨ��ajax�첽������֤���û��������Ϣ�Լ���֤���Ƿ���ȷ������ʾ
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

	/**ͨ��ajax�첽����ʵ���û���Ϣ�ļ��鲢��������
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");                //���ܹ�ʽ-��ֹ������������
		response.setContentType("text/html;charset=utf-8");

		String msg=null;     //���ص�json����
		String username = request.getParameter("username");
		String password = MD5Utils.md5(request.getParameter("password"));
		String valistr1 = request.getParameter("valistr");		            //��ȡ����Ļ����֤��
		valistr1=valistr1.toLowerCase();    //���û��������֤�뻯��Сд��ĸ����ʽ����֤ʱ���Բ����ִ�Сд
		String valistr2 = (String) request.getSession().getAttribute("piccode");   //��ñ�����session�е���֤��

		UserService service=new UserServiceImpl();      //ʵ����һ�������������û���¼
		User user=new User();
		user.setName(username);
		user.setPassword(password);
		if(!service.login(user)){
			msg="{msg:'�û������������',stat:0}";   //stat���������û���Ϣ�ĶԴ���в�ͬ��ɫ����ʾ
		}
		else if(service.login(user)){
			msg="{msg:'�û�����������ȷ!',stat:1}";
		}

		//			if(valistr1==null)
		//				msg="{msg:'��������֤�룡',stat:2}";
		if(valistr2 == null || !valistr1.equals(valistr2)) {     //�ȶ���֤���Ƿ�������ȷ����ɵ�¼������ȷ����ʾ
			msg="{msg:'��֤���������',stat:2}";
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
