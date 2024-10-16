package com.dharshan.GMP;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class UserAuthentication {
	AdminMenu am = new AdminMenu();
	void loginUser(int uid,String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerymanagement", "root", "");
			PreparedStatement stmt = con.prepareStatement("select * from users where UID = ? AND PASSWORD = ?");
			stmt.setInt(1, uid);
			stmt.setString(2, password);
			ResultSet rs  = stmt.executeQuery();
			if(rs.next()) {
				System.out.println("Logined As "+rs.getString("Role"));
				if(rs.getString("Role").equalsIgnoreCase("admin")) {
					am.disp_adminmenu();
				}
				else {
					System.out.println("Wait Until Employee Menu Is added!");
				}
			}
			else {
				System.out.println("Incorrect Password or UID");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}