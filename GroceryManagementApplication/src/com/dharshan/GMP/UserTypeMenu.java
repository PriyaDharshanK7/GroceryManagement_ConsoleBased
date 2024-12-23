package com.dharshan.GMP;
import java.util.Scanner;
public class UserTypeMenu {
	UserAuthentication ua = new UserAuthentication();
	Operations operation = new Operations();
	AdminMenu am = new AdminMenu();
	EmployeeMenu em = new EmployeeMenu();
	Scanner kb =  new Scanner(System.in);
	String role;
	String username;
	String password;
	void disp_usermenu() {
		System.out.println("\t\t__ WELCOME TO MY APPLICTAION __ \n\t 1.NEW USER \n\t 2.EXISTING USER ");
		int choice = kb.nextInt();
		kb.nextLine();
		switch(choice) {
		case 1:
			System.out.println("Welcome New User!");
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
			if(role.equalsIgnoreCase("admin")) {
				am.disp_adminmenu();
			}
			if(role.equalsIgnoreCase("employee"))
				em.empMenu_disp();
			break;
		case 2:
			System.out.println("Welcome...");
			System.out.println("Enter your User Id : ");
			int uid = kb.nextInt();
			kb.nextLine();
			System.out.println("Enter Your Password : ");
			password = kb.nextLine();
			ua.loginUser(uid, password);
			break;
		default:
			System.out.println("Oops.., Something went wrong!");
			break;
		}
	} 
}