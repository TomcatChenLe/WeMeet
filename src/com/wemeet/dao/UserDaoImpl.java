package com.wemeet.dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wemeet.domain.User;

public class UserDaoImpl implements UserDao{
	public String userName="root";           //存储登录数据库的用户名
	public String userPas="1234";            //存储数据库用户密码
	public String url="jdbc:mysql://localhost:3306/test";    //存储数据库的url
	public String className="com.mysql.jdbc.Driver";           //存储数据库驱动累路径
	public Connection con=null;
	public Statement stmt=null;                    //语句对象
	public java.sql.PreparedStatement pstmt=null;
	public ResultSet rs=null;	
	
	public void loadDrive(){              	//加载数据库驱动
		try{
			Class.forName(className);     
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public void getCon(){                       //连接数据库
		loadDrive();
		try{
			con=(Connection) DriverManager.getConnection(url,userName,userPas);       //获取数据库的链接
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	public void close(){              //关闭数据库，确保关闭完全
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

	public boolean findUserByName(String username){           //根据用户名判断是否登陆过，如果返回值为true则可以登陆，反之不可以
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
			while(rs.next())         //如果存在结果集，结果加一，表示已经有该用户存在
				result++;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		if(result>0)                            //如果返回值是false則不可以註冊
			return false;
		else
			return true;
	}
	
	public void addUser(User user){             //添加用户名和密码，实现用户的注册
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

	public boolean getLogin(String name,String password){         //根据用户名和密码与数据库的对比实现用户登录，如果返回值为true则表示可以登录
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
		if(result>0)      //如果返回值是true則可以登录
			return true;
		else
			return false;
	}
}


