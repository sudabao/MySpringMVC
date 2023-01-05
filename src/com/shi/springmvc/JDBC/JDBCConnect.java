package com.shi.springmvc.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnect{
	//数据库地址
	private static String dbUrl="jdbc:mysql://47.104.129.243:3306/tuiwen";
	//用户名
	private static String dbUserName="root";
	//密码
	private static String dbPassword="password";
	//驱动名称
	private static String jdbcName = "com.mysql.jdbc.Driver";
	
	public Connection getConnection(){
		try {
			Class.forName(jdbcName);
			System.out.println("加载驱动成功！");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("加载驱动失败！");
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
			System.out.println("获取数据库连接成功！");
			System.out.println("进行数据库操作！");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("获取数据库连接失败！");
		}
		return con;
		
	}
	
	public void closeConnection(Connection tConnection){
		try {
			tConnection.close();
			System.out.println("数据库关闭成功！");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据库关闭失败！");
		}
	}
	
	public static void main(String[] args){
		JDBCConnect tJDBCConnect = new JDBCConnect();
		Connection tConnection =  tJDBCConnect.getConnection();
		tJDBCConnect.closeConnection(tConnection);
		
	}
}