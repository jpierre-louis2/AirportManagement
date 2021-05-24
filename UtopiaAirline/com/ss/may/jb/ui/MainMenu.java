package com.ss.may.jb.ui;

public class MainMenu {

	public static void main(String[] args) {
		
		int input = -1;
		InputFunctions helper = new InputFunctions();
		EmployeeMenu employee = new EmployeeMenu();
		TravellerMenu traveler = new TravellerMenu();
		AdministratorMenu admin = new AdministratorMenu();
		
		System.out.println("Welcome to the Utopia Airlines Management System! Which category of user are you?");
		
		//Main Menu Prompt
		while(input != 0) {
			System.out.println("\n1. Employee \n2. Administrator \n3. Traveler");
			System.out.println("Enter 0 to quit");
			input = helper.getInput(3, 0);
			
			switch(input) {
				case 1:
					employee.emp1();
					break;
				case 2:
					admin.mainMenu();
					break;
				case 3:
					traveler.trav1();
					break;
			}
			
		}
		
		System.out.println("Goodbye, now Exiting!");
	}

}
