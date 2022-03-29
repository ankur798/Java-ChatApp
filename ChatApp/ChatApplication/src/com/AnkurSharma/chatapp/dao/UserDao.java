package com.AnkurSharma.chatapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.AnkurSharma.chatapp.dto.UserDTO;



	
public class UserDao {
	CommonDAO commonDAO = CommonDAO.getInstance();
		public String doLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException {
			System.out.println(userDTO.getUserid()+" "+userDTO.getPassword());
			Connection con=null;
			
			PreparedStatement pstmt=null;
			ResultSet rs=null;	
			
			try {
			con=commonDAO.createConnection();
			pstmt = con.prepareStatement("select userid from users where userid=? and password=?");
			pstmt.setString(1, userDTO.getUserid());
			pstmt.setString(2, new String(userDTO.getPassword()));
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				return "Welcome "+ userDTO.getUserid();
			}
			else
			{
				return "INVALID USERID OR PASSWORD";
			}
			}
			finally {
				if(rs!=null) {
					rs.close();
				}
				if(pstmt!=null) {
					pstmt.close();
				}
				
				if(con!=null) {
				con.close();
				}
			}

		}
		
		public String register(UserDTO userDTO) throws ClassNotFoundException, SQLException {
			Connection con = null;
			//Statement stmt = null;
			PreparedStatement pstmt=null;
			 //ResultSet rs=null;
			try {
			 con = commonDAO.createConnection();
			 //stmt = con.createStatement(); 
			 
			 pstmt = con.prepareStatement("insert into users (userid,password) values(?,?)");
				pstmt.setString(1, userDTO.getUserid());
				pstmt.setString(2, new String(userDTO.getPassword()));
				boolean isAdded=false;
				isAdded=pstmt.execute();
			  
			if(!isAdded) 
			{
				return "Record Added ";
			}
			else {
				return "Record Not Added ";
			}
			}
			finally {
			if(pstmt!=null) {	
			pstmt.close();
			}
			if(con!=null) {
			con.close();
			}
			}
		}
		
		
}




