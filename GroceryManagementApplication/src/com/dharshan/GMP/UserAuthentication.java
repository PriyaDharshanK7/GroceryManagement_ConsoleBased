package com.dharshan.GMP;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;
public class UserAuthentication {
	AdminMenu am = new AdminMenu();
	EmployeeMenu em = new EmployeeMenu();
	void loginUser(int uid,String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerymanagement", "root", "");
			PreparedStatement stmt = con.prepareStatement("select PASSWORD, Role from users where UID = ? ");
			stmt.setInt(1, uid);
			ResultSet rs  = stmt.executeQuery();
			if(rs.next()) {
				String storedPass = rs.getString("PASSWORD");
				if(BCrypt.checkpw(password, storedPass)) {
					System.out.println("Logined As "+rs.getString("Role"));
					if(rs.getString("Role").equalsIgnoreCase("admin")) {
						am.disp_adminmenu();
					}
					else {
						em.empMenu_disp();
					}
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