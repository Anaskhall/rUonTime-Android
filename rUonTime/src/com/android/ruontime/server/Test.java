package com.android.ruontime.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.android.ruontime.model.User;

public class Test {

	
	public static void main(String[] args){
		//System.out.println("Test");

		DBConnect connect = new DBConnect();
		ArrayList<User> users= connect.getUsers();
		if(connect.checkPassword("fuck","fuck")){
			System.out.println("true");
		}
		else{
			System.out.println("false");
		}
		/*for (User user:users){
			System.out.println(user.getUsername());
			System.out.println(user.getPwHash());
		}*/
		//connect.getData();
	//	connect.addUser();
	}
}
