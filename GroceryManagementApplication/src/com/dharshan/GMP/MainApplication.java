package com.dharshan.GMP;
public class MainApplication {
	public static void main(String[] args) {
		Menu menu = new Menu();
		UserTypeMenu utm = new UserTypeMenu();
		utm.disp_usermenu();
		menu.disp_menu();
	}
}