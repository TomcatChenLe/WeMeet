package com.wemeet.dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wemeet.domain.User;

public class UserDaoImpl implements UserDao{
	public String userName="root";           //�洢��¼���ݿ���û���
	public String userPas="1234";            //�洢���ݿ��û�����
	public String url="jdbc:mysql://localhost:3306/test";    //�洢���ݿ��url
	public String className="com.mysql.jdbc.Driver";           //�洢���ݿ�������·��
	public Connection con=null;
	public Statement stmt=null;                    //������
	public java.sql.PreparedStatement pstmt=null;
	public ResultSet rs=null;	
	
	public void loadDrive(){              	//�������ݿ�����
		try{
			Class.forName(className);     
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public void getCon(){                       //�������ݿ�
		loadDrive();
		try{
			con=(Connection) DriverManager.getConnection(url,userName,userPas);       //��ȡ���ݿ������
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	public void close(){              //�ر����ݿ⣬ȷ���ر���ȫ
		try{
			if(rs!=null)
				try{
					rs.close();
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					rs=null;
				}
			if(pstmt!=null)
				try{
					pstmt.close();
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					pstmt=null;
				}
			if(con!=null)
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					con=null;
				}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public boolean findUserByName(String username){           //�����û����ж��Ƿ��½�����������ֵΪtrue����Ե�½����֮������
		int result=0;
		getCon();
		try{
			String sql="select *from usertest where username='"+username+"'";
			pstmt=con.prepareStatement(sql);
			//pstmt.setString(1, name);
			rs = pstmt .executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			while(rs.next())         //������ڽ�����������һ����ʾ�Ѿ��и��û�����
				result++;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		if(result>0)                            //�������ֵ��false�t�������]��
			return false;
		else
			return true;
	}
	
	public void addUser(User user){             //����û��������룬ʵ���û���ע��
		getCon();
		try{
			pstmt=con.prepareStatement("insert into usertest (username,password) "
					+ "values (?,?)"); 
			pstmt.setString(1,user.getName());
			pstmt.setString(2, user.getPassword());
			pstmt.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
	}

	public boolean getLogin(String name,String password){         //�����û��������������ݿ�ĶԱ�ʵ���û���¼���������ֵΪtrue���ʾ���Ե�¼
		int result=0;
		getCon();
		try	{String sql="select *from usertest where username='"+name+"' and password='"+password+"'";
		pstmt=con.prepareStatement(sql);
		rs = pstmt .executeQuery();	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try {
			while(rs.next())
				result++;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		if(result>0)      //�������ֵ��true�t���Ե�¼
			return true;
		else
			return false;
	}
}


