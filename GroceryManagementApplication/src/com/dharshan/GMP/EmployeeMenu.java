package com.dharshan.GMP;
import java.util.Scanner;
public class EmployeeMenu {
	void empMenu_disp() {
		Operations op = new Operations();
		int ch,id,quantity;
		Scanner kb = new Scanner(System.in);
		System.out.println("Welcome... You Logged in as Employee");
		while(true) {
			System.out.println("\t\t EMPLOYEE MENU \n \t1.Add Stock \n \t2.Remove Stock \n \t3.View Stocks \n \t4.Log Out");
			ch = kb.nextInt();
			kb.nextLine();
			switch(ch) {
			case 1:
				System.out.println("Enter Product Id : ");
				id = kb.nextInt();
				System.out.println("Enter Quantity :");
				quantity = kb.nextInt();
				op.addStock(id, quantity);
				break;
			case 2:
				System.out.println("Enter Product Id : ");
				id = kb.nextInt();
				System.out.println("Enter Quantity to be deducted !");
				quantity = kb.nextInt();
				op.deductStock(id, quantity);
				break;
			case 3:
				op.viewItems();
				break;
			case 4:
				System.out.println("Loging Out");
				kb.close();
				System.exit(0);
				break;
			default:
				System.out.println("Please Enter Valid Choice [1 - 4]");
			}
		}
	}
}