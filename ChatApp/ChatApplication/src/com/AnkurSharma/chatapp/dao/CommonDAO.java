package com.AnkurSharma.chatapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CommonDAO {
	
	private static CommonDAO commonDAO;
	
	private CommonDAO()
	{
		
	}
	public static CommonDAO getInstance()
	{
		if(commonDAO == null)
		{
			commonDAO=new CommonDAO();
		}
		return commonDAO;
	}
	
	public Connection createConnection() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/chatapp","root","tiger");
		return con;
	}

}
