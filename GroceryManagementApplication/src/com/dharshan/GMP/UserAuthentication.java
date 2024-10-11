package com.dharshan.GMP;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class UserAuthentication {
	void addNewUser(String role, String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerymanagement", "root", "");
			PreparedStatement stmt = con.prepareStatement("insert into users(Role,USERNAME,PASSWORD) values (?,?,?)");
			stmt.setString(1, role);
			stmt.setString(2, username);
			stmt.setString(3, password);
			int i = stmt.executeUpdate();
			if (i > 0) {
				System.out.println("New Profile Added!");
				ResultSet rs = stmt.getGeneratedKeys();
				while(rs.next()) {
					System.out.println("Your User Id : "+rs.getInt(1));
				}
			}
			else {
				System.out.println("New Profile Addition Failed");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	void loginUser(int uid,String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerymanagement", "root", "");
			PreparedStatement stmt = con.prepareStatement("select Role from users where UID = ? and PASSWORD = ?");
			stmt.setInt(1, uid);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
			String role = rs.getString("Role");
			if (role.equalsIgnoreCase("admin")) {
				System.out.println("Logined as a"+role);
			}
			else if(role.equalsIgnoreCase("employee")) {
				System.out.println("Logined as a"+role);
			}
			else {
				System.out.println("Invlaid Username or Password!");
			}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}