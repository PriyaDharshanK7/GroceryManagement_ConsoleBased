package com.dharshan.GMP;
import java.util.Scanner;
public class Menu {
	Operations opertaion = new Operations();
	void disp_menu() {
		Scanner kb = new Scanner(System.in);
		int choice, id, quantity;
		String name;
		String category;
		System.out.println("Welcome to My Grocery Management Application...");
		while(true) {
			try {
				System.out.println("\t\t--M E N U--\t\t\n\t 1. View Products\n \t 2. Add New Product\n \t 3. Edit Existing Product\n \t 4. Delete Product\n \t 5. Add Stock(Quantity)\n \t 6. Deduct Stock(Quantity)\n \t 7. Log Out");
				choice = kb.nextInt();
				switch(choice) {
				case 1:
					opertaion.viewItems();
					break;
				case 2:
					System.out.println("Enter Product Id to be added : ");
					id = kb.nextInt();
					System.out.println("Enter Product Name to be added : ");
					name = kb.next();
					System.out.println("Enter Product Category to be added : ");
					category = kb.next();
					System.out.println("Enter Product Quantity to be added : ");
					quantity = kb.nextInt();
					opertaion.addNewItem(id, name, category, quantity);
					break;
				case 3:
					System.out.println("Enter ID Of Product to be changed :");
					id = kb.nextInt();
					opertaion.editItem(id);
					break;
				case 4:
					System.out.println("Enter Product ID to be Deleted : ");
					id = kb.nextInt();
					opertaion.deleteItem(id);
					break;
				case 5:
					System.out.println("Id of Stock to be added : ");
					id = kb.nextInt();
					System.out.println("Quantity of Stock to be added: ");
					quantity = kb.nextInt();
					opertaion.addStock(id, quantity);
					break;
				case 6:
					System.out.println("Id of stock to be deducted : ");
					id = kb.nextInt();
					System.out.println("Quantity of Stock to be detected : ");
					quantity = kb.nextInt();
					opertaion.deductStock(id, quantity);
					break;
				case 7:
					System.out.println("Exiting... In A While");
					kb.close();
					System.exit(0);
					break;
				default:
					System.out.println("Please Provide correct choice between 1 - 5");
				}
			} catch (Exception e) {
				System.out.println("Please Enter Correct Value");
				kb.next();
			}
		}
	}
}