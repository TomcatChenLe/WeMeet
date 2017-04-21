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
 * ��Servlet�����û���ע�ᣬע��ɹ�ʱ����ض�����ҳ�Ĳ���
 * @author Administrator
 *
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}
/**
 * ��Servletֻ���ڼ����û��ĵ�¼��Ϣ��ȷʱ�Ƿ��ض�������Ϣ����ʱ���ڴ�ҳ
 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");                //���ܹ�ʽ-��ֹ������������
		response.setContentType("text/html;charset=utf-8");

		String valistr1 = request.getParameter("valistr");		            //��ȡ����Ļ����֤��
		String valistr2 = (String) request.getSession().getAttribute("piccode");   //��ñ�����session�е���֤��

		if(valistr1 == null || valistr2 == null || !valistr1.equals(valistr2)){      //�ȶ���֤���Ƿ�������ȷ����ɵ�¼������ȷ����ʾ
			response.sendRedirect("login.html");
		}else{           
			String username = request.getParameter("username");
			String password = MD5Utils.md5(request.getParameter("password"));
			UserService service=new UserServiceImpl();      //ʵ����һ�������������û���¼
			User user=new User();
			user.setName(username);
			user.setPassword(password);

			if(service.login(user)){          //�������ֵΪtrue��ʾ�û�����������ȷ����ɵ�¼���ض�����ҳ
				//.��¼�û��ض�����ҳ�����û���Ϣ������session��
				request.getSession().setAttribute("user", user);
				if("true".equals(request.getParameter("remname"))){     //���ѡ���˼�ס�û��������룬������һ��Cookie���󣬼�ס�û�������ɼ�ס�û�����������ʮ��
					//Cookie�����Ǻ��֣���ҪURLEncoder.encode����ת��
					Cookie remnameC = new Cookie("remname",URLEncoder.encode(user.getName(),"utf8"));
					remnameC.setPath(request.getContextPath());
					remnameC.setMaxAge(3600*24*30);
					response.addCookie(remnameC);
				}else{                           //�����ѡ���ס�û������򲻼�ס�û���������
					Cookie remnameC = new Cookie("remname","");
					remnameC.setPath("/");
					remnameC.setMaxAge(0);     //����Cookie�����������
					response.addCookie(remnameC);
				}

				response.sendRedirect("index.html");    //�û���Ϣ��ȷ�ض���...........
				return;
			}
			else  {      //�û������벻��ȷ�����ڵ�¼ҳ��
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
