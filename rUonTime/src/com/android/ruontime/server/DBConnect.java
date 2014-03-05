package com.android.ruontime.server;

import java.sql.*;
import java.util.ArrayList;

import com.android.ruontime.model.User;

public class DBConnect {

	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public DBConnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://192.168.56.1:3306/webservice","RuonTime","ruontime");
			st = con.createStatement();
		}catch(Exception ex){
			System.out.println("Error : " + ex);
		}
	}
	
	public void getData(){
		try{
			String query = "select * from users";
			rs=st.executeQuery(query);
			System.out.println("Results");
			while(rs.next()){
				String name = rs.getString("username");
				String password = rs.getString("password");
				System.out.println("Name "+name);
				System.out.println("password" + password);
			}
		}catch(Exception ex){
			System.out.println("Error : " + ex);
			
		}
	}
	
	public void addUser(){
			String query = "INSERT INTO `users` (`user_id`, `username`, `password`, `FirstName`, `LastName`) VALUES (NULL, 'Anassssstest', 'test', '', '')";
			try {
				st.executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Name ");
			}
	}
	
	public ArrayList<User> getUsers(){
		ArrayList<User> users= new ArrayList<User>();
		try{
			String query = "select * from users";
			rs=st.executeQuery(query);
			while(rs.next()){
				users.add(new User(rs.getString("username"),rs.getString("password")));
			}
		}catch(Exception ex){
			System.out.println("Error : " + ex);
		}
		return users;
	}
	
	public Boolean checkUser(String username){

		Boolean check=false;
		DBConnect connect = new DBConnect();
		ArrayList<User> users= connect.getUsers();
		for (User user : users){
			if (user.getUsername().equals(username)){
				check=true;
				break;
			}
		}
		return check;
	}
	
	public Boolean checkPassword(String username, String password){
		Boolean check=false;
		try{
			String query = "select password from users where username = '"+username+"'";
			rs=st.executeQuery(query);
			while(rs.next()){
				String pass = rs.getString("password");
				if (pass.equals(password)){
					check=true;
				}
			}
		}catch(Exception ex){
			System.out.println("Error : " + ex);
		}	
		return check;
	}
}
