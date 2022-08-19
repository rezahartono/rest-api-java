package com.restapi.common;

import java.sql.*;

public class CommonGeneric {
	
	private static CommonGeneric _CommonGeneric;
	
	public static CommonGeneric getInstance() {
		_CommonGeneric = new CommonGeneric();
		
		return _CommonGeneric;
	}
	
	public static Statement dbConnection() {
		Statement result = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/jwt-demo", "root", "");
			
			 result = db.createStatement();
//			
//			result = stmt.executeQuery(query);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return result;
	}
	
}
