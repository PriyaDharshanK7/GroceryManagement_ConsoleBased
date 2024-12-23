package com.dharshan.GMP;
import java.util.Scanner;
public class AdminMenu {
	Scanner kb = new Scanner(System.in);
	int id, quantity;
	String name;
	String category;
	String role;
	String username;
	String password;
	Operations operation = new Operations();
	void disp_adminmenu() {
		System.out.println("WelCome User you are logined as Admin !\n ");
		while(true) {
			System.out.println("\t\t --M E N U-- \n \t 1.Add New User\n\t 2.Remove User\n\t 3.Add New Product\n\t 4.View Products\n\t 5.Edit Products\n\t 6.Remove Products\n\t 7.View Users\n\t 8.Exit");
			int choice = kb.nextInt();
			kb.nextLine();
			switch(choice) {
			case 1:// Add new user!
				System.out.println("Enter Role[Admin / Employee] :");
				role = kb.nextLine();
				System.out.println("Enter Username : ");
				username = kb.nextLine();
				System.out.println("Enter Password : ");
				password = kb.nextLine();
				if(role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("employee")) {
					operation.addNewUser(role, username, password);
				}
				else {
					System.out.println("Please Enter Valid User Role[Admin / Employee]");
				}
				break;
			case 2://Remove user!
				System.out.println("Enter User ID to be deleted : ");
				id = kb.nextInt();
				operation.deleteUser(id);
				break;
			case 3:// Add Product!
				System.out.println("Enter Product Id to be added : ");
				id = kb.nextInt();
				System.out.println("Enter Product Name to be added : ");
				name = kb.next();
				System.out.println("Enter Product Category to be added : ");
				category = kb.next();
				System.out.println("Enter Product Quantity to be added : ");
				quantity = kb.nextInt();
				operation.addNewItem(id, name, category, quantity);
				break;
			case 4:// View Products
				operation.viewItems();
				break;
			case 5:// Edit Products
				System.out.println("Enter Product ID to be Edited : ");
				id = kb.nextInt();
				operation.editItem(id);
				break;
			case 6:// Remove Product
				System.out.println("Enter Product ID to be Deleted : ");
				id = kb.nextInt();
				operation.deleteItem(id);
				break;
			case 7:
				//System.out.println("View Users!");
				operation.viewUsers();
				break;
			case 8: // To Exit
				System.out.println("LoggingOut");
				kb.close();
				System.exit(0);
				break;
			default:
				System.out.println("Dear Admin, Please Enter Valid Choice");
			}
		}
	}
}