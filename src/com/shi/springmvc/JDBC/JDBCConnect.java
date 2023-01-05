package com.shi.springmvc.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnect{
	//���ݿ��ַ
	private static String dbUrl="jdbc:mysql://47.104.129.243:3306/tuiwen";
	//�û���
	private static String dbUserName="root";
	//����
	private static String dbPassword="password";
	//��������
	private static String jdbcName = "com.mysql.jdbc.Driver";
	
	public Connection getConnection(){
		try {
			Class.forName(jdbcName);
			System.out.println("���������ɹ���");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("��������ʧ�ܣ�");
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
			System.out.println("��ȡ���ݿ����ӳɹ���");
			System.out.println("�������ݿ������");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("��ȡ���ݿ�����ʧ�ܣ�");
		}
		return con;
		
	}
	
	public void closeConnection(Connection tConnection){
		try {
			tConnection.close();
			System.out.println("���ݿ�رճɹ���");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("���ݿ�ر�ʧ�ܣ�");
		}
	}
	
	public static void main(String[] args){
		JDBCConnect tJDBCConnect = new JDBCConnect();
		Connection tConnection =  tJDBCConnect.getConnection();
		tJDBCConnect.closeConnection(tConnection);
		
	}
}