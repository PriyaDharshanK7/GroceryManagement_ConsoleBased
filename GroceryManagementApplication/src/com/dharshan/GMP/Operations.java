package com.dharshan.GMP;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;
public class Operations {
	Scanner kb =  new Scanner(System.in);
	int c = 0 ;
	// Method to View Items
	void viewItems() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerymanagement","root","");
			PreparedStatement stmt = con.prepareStatement("select * from product");
			ResultSet rs = stmt.executeQuery();
			if(!rs.isBeforeFirst()) { // Check With DB and return if No DataSet is Found
				System.out.println("No Record Founded!");
		   }
			else {
				while(rs.next()) {
					c = c + 1;
					System.out.println("Record "+c);
					System.out.println("\tId : "+rs.getInt("PID"));
					System.out.println("\tNAME : "+rs.getString("PNAME"));
					System.out.println("\tCATEGORY : "+rs.getString("Category"));
					System.out.println("\tQUANTITY : "+rs.getInt("Quantity"));
					System.out.println("--------------------------------------");
				}
			}
		}
		catch (Exception e) {}
	}
	// Method to Add New Item
	void addNewItem(int id, String name, String category, int quantity) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerymanagement","root","");
			PreparedStatement stmt = con.prepareStatement("insert into product values (?,?,?,?)");
			stmt.setInt(1, id);
			stmt.setString(2, name);
			stmt.setString(3, category);
			stmt.setInt(4, quantity);
			int i = stmt.executeUpdate();
			if (i > 0) {
				System.out.println("Producted Added Successfully");
			}
			else {
				System.out.println("Producted Addition Failed");
			}
		}
		catch (Exception e) {}
	}
	// Method To Edit Existing Item
	void editItem(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerymanagement","root","");
			System.out.println("\t What To Change! \t\n\t 1.Product Name \n\t 2.Category \n\t 3.Quantity");
			int choice = kb.nextInt();
			switch(choice) {
			case 1:
				System.out.println("Product Name to be changed : ");
				String pn = kb.next();
				kb.nextLine();
				PreparedStatement stmt = con.prepareStatement("update product SET pname = ? WHERE PID = ? ");
				stmt.setString(1, pn);
				stmt.setInt(2, id);
				int i = stmt.executeUpdate();
				if(i > 0) {
					System.out.println("Product Name Updated!");
				}
				else {
					System.out.println("Failed!");
				}
				stmt.close();
				break;
			case 2:
				System.out.println("Enter Category to be Changed : ");
				String pc = kb.next();
				kb.nextLine();
				PreparedStatement stmt1 = con.prepareStatement("update product SET category =  ? WHERE PID = ? ");
				stmt1.setString(1, pc);
				stmt1.setInt(2, id);
				i = stmt1.executeUpdate();
				if(i > 0) {
					System.out.println("Category Updated!");
				}
				else {
					System.out.println("Failed!");
				}
				stmt1.close();
				break;
			case 3:
				System.out.println("Enter Qunatity to be Changed : ");
				String pq = kb.next();
				kb.nextLine();
				PreparedStatement stmt2 = con.prepareStatement("update product SET quantity =  ? WHERE PID = ? ");
				stmt2.setString(1, pq);
				stmt2.setInt(2, id);
				i = stmt2.executeUpdate();
				if(i > 0) {
					System.out.println("Quantity Updated!");
				}
				else {
					System.out.println("Failed!");
				}
				stmt2.close();
				break;
			default:
				System.out.println("Enter Valid Integer Between 1 - 3");
				break;
			}
		}
		catch (Exception e) {}
	}
	//Method to Deleted Item
	void deleteItem(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerymanagement","root","");
			PreparedStatement stmt = con.prepareStatement("delete from product where pid = ?");
			stmt.setInt(1, id);
			int i = stmt.executeUpdate();
			if(i > 0) {
				System.out.println("1 Row Deleted!");
			}
			else {
				System.out.println("Deletion Failed!");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Method to Add Quantity Stock
	void addStock(int id, int quantity) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerymanagement", "root", "");
			con.setAutoCommit(false);
			PreparedStatement stmt = con.prepareStatement("update product SET quantity = quantity + ?  where pid = ?");
			stmt.setInt(1, quantity);
			stmt.setInt(2, id);
			int i = stmt.executeUpdate();
			if (i > 0 ) {
				con.commit();
				System.out.println("Stock Added Success!");
			}
			else {
				con.rollback();
				System.out.println("Failed!");
			}
		}
		catch (Exception e) {}
	}
	void deductStock(int id, int quantity) {
		if(quantity < 0) {
			System.out.println("Opertaion Cannot be done due to null value in field");
		}
		else {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerymanagement", "root", "");
			con.setAutoCommit(false);
			PreparedStatement stmt = con.prepareStatement("update product SET quantity = quantity - ?  where pid = ?");
			stmt.setInt(1, quantity);
			stmt.setInt(2, id);
			int i = stmt.executeUpdate();
			if (i > 0 ) {
				con.commit();
				System.out.println("Stock Deducted Success!");
			}
			else {
				con.rollback();
				System.out.println("Failed!");
			}
		}
		catch (Exception e) {}
	}}
	void addNewUser(String role, String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerymanagement", "root", "");
			PreparedStatement stmt = con.prepareStatement("insert into users(Role,USERNAME,PASSWORD) values (?,?,?) ",Statement.RETURN_GENERATED_KEYS);
			String hsp = BCrypt.hashpw(password, BCrypt.gensalt());
			stmt.setString(1, role);
			stmt.setString(2, username);
			stmt.setString(3, hsp);
			int i = stmt.executeUpdate();
			if (i > 0) {
				System.out.println("New Profile Added!");
				ResultSet rs = stmt.getGeneratedKeys();
				while(rs.next()) {
					System.out.println("Your User Id : "+rs.getInt(1));
				}
			}
			else {
				System.out.println("New Profile Creation Failed");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	void deleteUser(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerymanagement", "root", "");
			PreparedStatement stmt = con.prepareStatement("delete from users where UID = ?");
			stmt.setInt(1, id);
			int i = stmt.executeUpdate();
			if (i > 0) {
				System.out.println("Deleted User!");
			}
			else {
				System.out.println("User Not Exists!");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
