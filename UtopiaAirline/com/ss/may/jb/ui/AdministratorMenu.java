package com.ss.may.jb.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.ss.may.jb.DAL.*;
import com.ss.may.jb.entities.*;

public class AdministratorMenu {

	InputFunctions helper = new InputFunctions();
	FlightDAL fl = new FlightDAL();
	UserDAL us = new UserDAL();
	PassengerDAL pas = new PassengerDAL();
	AirportDAL air = new AirportDAL();
	
	//Admin Main Menu Prompt
	public void mainMenu() {
		
		int input = 0;
		
		while(input != 6) {
			System.out.println("\nAdministrator Menu:");
			System.out.println("1. Add/Update/Delete/Read Flights");
			System.out.println("2. Add/Update/Delete/Read Passengers and their Bookings");
			System.out.println("3. Add/Update/Delete/Read Airports");
			System.out.println("4. Add/Update/Delete/Read Booking Users");
			System.out.println("5. Add/Update/Delete/Read Employees");
			System.out.println("6. Quit to Previous");
			input = helper.getInput(6, 1);
			
			switch(input) {
				case 1:
					flightMenu();
					break;
				case 2:
					travelerMenu();
					break;
				case 3:
					airportsMenu();
					break;
				case 4:
					userMenu();
					break;
				case 5:
					employeeMenu();
					break;
			}
			
		}
		
	}
	
	//Flight Menu Prompt
	public void flightMenu() {
		int input = 0;
		while(input != 5) {
			System.out.println("\nFlight Menu");
			System.out.println("1. Add a Flight \n2. Update a Flight \n3. Delete a Flight \n4. Read Flights \n5. Quit");
			input = helper.getInput(5, 1);
			
			switch(input) {
			case 1:
				addFlight();
				break;
			case 2:
				updateFlight();
				break;
			case 3:
				deleteFlight();
				break;
			case 4:
				viewFlights();
				break;
			}
		}
	}
	
	//Returns a Route ID based on selected airports
	private int findRouteId() {
		ArrayList<Airport> airports = air.getAllAirports();
		int input;
		int max = airports.size();
		Airport departure;
		Airport arrival;
		
		System.out.println("\nPlease choose a new Origin Airport: ");
		air.printAllAirports();
		input = helper.getInput(max, 1);
		departure = airports.get(input - 1);
		
		System.out.println("\nPlease choose a new Destination Airport: ");
		air.printAllAirports();
		
		input = helper.getInput(max, 1);
		arrival = airports.get(input - 1);
		
		int routeId = fl.newRouteCheck(departure, arrival);
		
		if(fl.airportCheck(departure, arrival)) {
			return 0;
		}
		
		return routeId;
	}
	
	public void addFlight() {
		int routeId = findRouteId();
		if(routeId == 0) {
			System.out.println("\nError could not create new Route!");
			return;
		}
		
		System.out.print("What is the associated Airplane Id: ");
		int aId = helper.getInput();
		
		Date date = null;
		System.out.println("\nPlease enter a new Departure Date or 'Quit' to Exit");
		System.out.println("Must be in mm/dd/yyyy format");
		System.out.print("New Departure Date: ");
		String sDate = helper.getStringInput();
		try {
			date = new SimpleDateFormat("MM/dd/yyyy").parse(sDate);
		} catch (Exception e) {
			System.out.println("Error: Incorrect input format!");
		}
		
		System.out.print("How many Economy Seats are there? ");
		int economy = helper.getInput();
		System.out.print("How many Business Seats are there? ");
		int business = helper.getInput();
		System.out.print("How many First Class Seats are there? ");
		int first = helper.getInput();
		
		System.out.print("What is the pricing for a seat: ");
		float price = helper.getFloatInput();
		
		try {
			System.out.println(fl.addFlight(routeId, aId, date, economy, business, first, price));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void updateFlight() {
		System.out.println("\nWhich Flight would you like to update: ");
		ArrayList<Flight> flights = fl.getAllFlights();
		int max = flights.size() + 1;
		System.out.println(max + ". To Quit to previous");
		int input = helper.getInput(max, 1);	
		if(input == max) {
			return;
		}
		Flight target = flights.get(input - 1);
		
		int routeId = findRouteId();
		if(routeId == 0) {
			System.out.println("\nError could not create new Route!");
			return;
		}
		
		System.out.print("What is the associated Airplane Id: ");
		int aId = helper.getInput();
		
		Date date = null;
		System.out.println("\nPlease enter a new Departure Date or 'Quit' to Exit");
		System.out.println("Must be in mm/dd/yyyy format");
		System.out.print("New Departure Date: ");
		String sDate = helper.getStringInput();
		try {
			date = new SimpleDateFormat("MM/dd/yyyy").parse(sDate);
		} catch (Exception e) {
			System.out.println("Error: Incorrect input format!");
		}
		
		System.out.print("How many Economy Seats are there? ");
		int economy = helper.getInput();
		System.out.print("How many Business Seats are there? ");
		int business = helper.getInput();
		System.out.print("How many First Class Seats are there? ");
		int first = helper.getInput();
		
		System.out.print("What is the pricing for a seat: ");
		float price = helper.getFloatInput();
		
		try {
			System.out.println(fl.updateFlight(target.getFlightId(), routeId, aId, date, economy, business, first, price));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void deleteFlight(){
		System.out.println("\nWhich Flight would you like to update: ");
		ArrayList<Flight> flights = fl.getAllFlights();
		int max = flights.size() + 1;
		System.out.println(max + ". To Quit to previous");
		int input = helper.getInput(max, 1);	
		if(input == max) {
			return;
		}
		Flight target = flights.get(input - 1);
		
		try {
			System.out.println(fl.deleteFlight(target.getFlightId()));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void viewFlights() {
		fl.printAllFlights();
	}
		
	//Airport Menu Prompt
	public void airportsMenu() {
		int input = 0;
		while(input != 5) {
			System.out.println("\nAirport Menu");
			System.out.println("1. Add an Airport \n2. Update an Airport \n3. Delete an Airport \n4. Read Airports \n5. Quit");
			input = helper.getInput(5, 1);
			
			switch(input) {
			case 1:
				addAirport();
				break;
			case 2:
				updateAirport();
				break;
			case 3:
				deleteAirport();
				break;
			case 4:
				viewAirports();
				break;
			}
		}

	}
	public void addAirport() {
		System.out.print("What is the 3 Letter ID for your new Airport?: ");
		String id = helper.getStringInput();
		if(id.length() != 3) {
			System.out.println("Error! Must be 3 Letters in Length!");
				return;
		}
		System.out.print("What city is it located in? (Type 'Quit' to Exit): ");
		String city = helper.getStringInput();
		if(helper.validateQuit(city)) {
			return;
		}
		
		try {
			System.out.println(air.addAirport(id, city));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void updateAirport() {
		ArrayList<Airport> airports = air.getAllAirports();
		int max = airports.size() + 1;
		System.out.println("\nPlease choose an Airport to update: ");
		air.printAllAirports();
		System.out.println(max + ". To Exit");
		int input = helper.getInput(max, 1);
		if(input == max) {
			return;
		}
		Airport target = airports.get(input - 1);
		
		System.out.print("What city is it located in? (Type 'Quit' to Exit): ");
		String city = helper.getStringInput();
		if(helper.validateQuit(city)) {
			return;
		}
		
		try {
			System.out.println(air.updateAirport(target.getAirportId(), city));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void deleteAirport(){
		ArrayList<Airport> airports = air.getAllAirports();
		int max = airports.size() + 1;
		System.out.println("\nPlease choose an Airport to Delete: ");
		air.printAllAirports();
		System.out.println(max + ". To Exit");
		int input = helper.getInput(max, 1);
		if(input == max) {
			return;
		}
		Airport target = airports.get(input - 1);
		
		try {
			System.out.println(air.deleteAirport(target.getAirportId()));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void viewAirports() {
		System.out.println("\nAirports: ");
		air.printAllAirports();
	}
	
	//Passengers Menu Prompt
	public void travelerMenu() {
		int input = 0;
		while(input != 5) {
			System.out.println("\nPassenger Menu");
			System.out.println("1. Add a Passenger \n2. Update a Passenger \n3. Delete a Passenger \n4. Read Passengers & Ticket Info \n5. Quit");
			input = helper.getInput(5, 1);
			
			switch(input) {
			case 1:
				addPassenger();
				break;
			case 2:
				updatePassenger();
				break;
			case 3:
				deletePassenger();
				break;
			case 4:
				viewPassengers();
				break;
			}	
		}
	}
	public void addPassenger() {
		Scanner scan = new Scanner(System.in);
		System.out.print("What is the Booking Id for the Passenger (Must have existing Booking!): ");
		int bId = helper.getInput();
		System.out.print("What is the Given Name for the new Passenger (Type 'Quit' to Exit): ");
		String gName = helper.getStringInput();
		if(helper.validateQuit(gName)) {
			return;
		}
		System.out.print("What is the Family Name for the new Passenger (Type 'Quit' to Exit): ");
		String fName = helper.getStringInput();
		if(helper.validateQuit(fName)) {
			return;
		}
		System.out.print("What is the Gender of the New Passenger (Type 'Quit' to Exit): ");
		String gender = helper.getStringInput();
		if(helper.validateQuit(gender)) {
			return;
		}
		System.out.print("What is the Address for the new Passenger (Type 'Quit' to Exit): ");
		String address = helper.getStringInput();
		if(helper.validateQuit(address)) {
			return;
		}
		
		System.out.println("\nPlease enter a Date of Birth");
		System.out.println("Must be in mm/dd/yyyy format");
		System.out.print("Date of Birth: ");
		String sDate = scan.nextLine();
		
		Date date = null;
		try {
			date = new SimpleDateFormat("MM/dd/yyyy").parse(sDate);
		} catch (Exception e) {
			System.out.println("Error: Incorrect input format!");
		}
		
		try {
			System.out.println(pas.addPassenger(bId, gName, fName, date, gender, address));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void updatePassenger() {
		ArrayList<Passenger> passengers = pas.getAllPassengers();
		int max = passengers.size() + 1;
		System.out.println("\nPlease choose a Passenger to update: ");
		pas.printAllPassengers();
		System.out.println("\n" + max + ". To Exit");
		int input = helper.getInput(max, 1);
		if(input == max) {
			return;
		}
		Passenger target = passengers.get(input - 1);
		
		
		Scanner scan = new Scanner(System.in);
		System.out.print("What is the new Booking Id for the Passenger (Must have existing Booking!): ");
		int bId = helper.getInput();
		System.out.print("What is the new Given Name for the Passenger (Type 'Quit' to Exit): ");
		String gName = helper.getStringInput();
		if(helper.validateQuit(gName)) {
			return;
		}
		System.out.print("What is the new Family Name for the Passenger (Type 'Quit' to Exit): ");
		String fName = helper.getStringInput();
		if(helper.validateQuit(fName)) {
			return;
		}
		System.out.print("What is the new Gender of the Passenger (Type 'Quit' to Exit): ");
		String gender = helper.getStringInput();
		if(helper.validateQuit(gender)) {
			return;
		}
		System.out.print("What is the new Address for the Passenger (Type 'Quit' to Exit): ");
		String address = helper.getStringInput();
		if(helper.validateQuit(address)) {
			return;
		}
		
		System.out.println("\nPlease enter a Date of Birth");
		System.out.println("Must be in mm/dd/yyyy format");
		System.out.print("Date of Birth: ");
		String sDate = scan.nextLine();
		
		Date date = null;
		try {
			date = new SimpleDateFormat("MM/dd/yyyy").parse(sDate);
		} catch (Exception e) {
			System.out.println("Error: Incorrect input format!");
		}
		
		try {
			System.out.println(pas.updatePassenger(target.getPassengerId(), bId, gName, fName, date, gender, address));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void deletePassenger(){
		ArrayList<Passenger> passengers = pas.getAllPassengers();
		int max = passengers.size() + 1;
		System.out.println("\nPlease choose a Passenger to Delete: ");
		pas.printAllPassengers();
		System.out.println("\n" + max + ". To Exit");
		int input = helper.getInput(max, 1);
		if(input == max) {
			return;
		}
		Passenger target = passengers.get(input - 1);
		
		try {
			System.out.println(pas.deletePassenger(target.getPassengerId()));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void viewPassengers() {
		pas.printAllPassengers();
	}
	
	//User Menu Prompt
	public void userMenu() {
		int input = 0;
		while(input != 5) {
			System.out.println("\nUser Menu");
			System.out.println("1. Add a User \n2. Update a User \n3. Delete a User \n4. Read Booking Users (Not Employees) \n5. Quit");
			input = helper.getInput(5, 1);
			
			switch(input) {
			case 1:
				addUser();
				break;
			case 2:
				updateUser();
				break;
			case 3:
				deleteUser();
				break;
			case 4:
				viewUsers();
				break;
			}
		}
	}
	public void addUser() {
		System.out.print("What is the Given Name for the new User (Type 'Quit' to Exit): ");
		String gName = helper.getStringInput();
		if(helper.validateQuit(gName)) {
			return;
		}
		System.out.print("What is the Family Name for the new User (Type 'Quit' to Exit): ");
		String fName = helper.getStringInput();
		if(helper.validateQuit(fName)) {
			return;
		}
		System.out.print("What is the Username for the new User (Type 'Quit' to Exit): ");
		String username = helper.getStringInput();
		if(helper.validateQuit(username)) {
			return;
		}
		System.out.print("What is the Email for the new User (Type 'Quit' to Exit): ");
		String email = helper.getStringInput();
		if(helper.validateQuit(email)) {
			return;
		}
		System.out.print("What is the Password for the new User (Type 'Quit' to Exit): ");
		String password = helper.getStringInput();
		if(helper.validateQuit(password)) {
			return;
		}
		System.out.print("What is the Phone Number for the new User (Type 'Quit' to Exit): ");
		String phone = helper.getStringInput();
		if(helper.validateQuit(phone)) {
			return;
		}
		try {
			System.out.println(us.addNormalUser(gName, fName, username, email, password, phone));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void updateUser() {
		ArrayList<User> users = us.getBookingUsers();
		int max = users.size() + 1;
		System.out.println("\nPlease choose a User to update: ");
		us.printNormalUsers();
		System.out.println("\n" + max + ". To Exit");
		int input = helper.getInput(max, 1);
		if(input == max) {
			return;
		}
		User target = users.get(input - 1);
		
		System.out.print("What is the new Given Name for the User (Type 'Quit' to Exit): ");
		String gName = helper.getStringInput();
		if(helper.validateQuit(gName)) {
			return;
		}
		System.out.print("What is the new Family Name for the User (Type 'Quit' to Exit): ");
		String fName = helper.getStringInput();
		if(helper.validateQuit(fName)) {
			return;
		}
		System.out.print("What is the new Username for the User (Type 'Quit' to Exit): ");
		String username = helper.getStringInput();
		if(helper.validateQuit(username)) {
			return;
		}
		System.out.print("What is the new Email for the User (Type 'Quit' to Exit): ");
		String email = helper.getStringInput();
		if(helper.validateQuit(email)) {
			return;
		}
		System.out.print("What is the new Password for the User (Type 'Quit' to Exit): ");
		String password = helper.getStringInput();
		if(helper.validateQuit(password)) {
			return;
		}
		System.out.print("What is the new Phone Number for the User (Type 'Quit' to Exit): ");
		String phone = helper.getStringInput();
		if(helper.validateQuit(phone)) {
			return;
		}
		
		try {
			System.out.println(us.updateUser(target.getUserId(), target.getRoleId(), gName, fName, username, email, password, phone));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void deleteUser(){
		ArrayList<User> users = us.getBookingUsers();
		int max = users.size() + 1;
		System.out.println("\nPlease choose a User to Delete: ");
		us.printNormalUsers();
		System.out.println("\n" + max + ". To Exit");
		int input = helper.getInput(max, 1);
		if(input == max) {
			return;
		}
		User target = users.get(input - 1);
		
		try {
			System.out.println(us.deleteUser(target.getUserId()));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void viewUsers() {
		us.printNormalUsers();
	}
	
	
	public void employeeMenu() {
		int input = 0;
		while(input != 5) {
			System.out.println("\nEmployee Menu");
			System.out.println("1. Add an Employee \n2. Update an Employee \n3. Delete an Employee \n4. Read Employees \n5. Quit");
			input = helper.getInput(5, 1);
			
			
			switch(input) {
			case 1:
				addEmployee();
				break;
			case 2:
				updateEmployee();
				break;
			case 3:
				deleteEmployee();
				break;
			case 4:
				viewEmployees();
				break;
			}
		}
	}
	public void addEmployee() {
		System.out.print("What is the Given Name for the new Employee (Type 'Quit' to Exit): ");
		String gName = helper.getStringInput();
		if(helper.validateQuit(gName)) {
			return;
		}
		System.out.print("What is the Family Name for the new Employee (Type 'Quit' to Exit): ");
		String fName = helper.getStringInput();
		if(helper.validateQuit(fName)) {
			return;
		}
		System.out.print("What is the Username for the new Employee (Type 'Quit' to Exit): ");
		String username = helper.getStringInput();
		if(helper.validateQuit(username)) {
			return;
		}
		System.out.print("What is the Email for the new Employee (Type 'Quit' to Exit): ");
		String email = helper.getStringInput();
		if(helper.validateQuit(email)) {
			return;
		}
		System.out.print("What is the Password for the new Employee (Type 'Quit' to Exit): ");
		String password = helper.getStringInput();
		if(helper.validateQuit(password)) {
			return;
		}
		System.out.print("What is the Phone Number for the new Employee (Type 'Quit' to Exit): ");
		String phone = helper.getStringInput();
		if(helper.validateQuit(phone)) {
			return;
		}
		try {
			System.out.println(us.addEmployee(gName, fName, username, email, password, phone));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void updateEmployee() {
		ArrayList<User> employees = us.getBookingEmployees();
		int max = employees.size() + 1;
		System.out.println("\nPlease choose an Employee to update: ");
		us.printEmployeeUsers();
		System.out.println("\n" + max + ". To Exit");
		int input = helper.getInput(max, 1);
		if(input == max) {
			return;
		}
		User target = employees.get(input - 1);
		
		System.out.print("What is the new Given Name for the Employee (Type 'Quit' to Exit): ");
		String gName = helper.getStringInput();
		if(helper.validateQuit(gName)) {
			return;
		}
		System.out.print("What is the new Family Name for the Employee (Type 'Quit' to Exit): ");
		String fName = helper.getStringInput();
		if(helper.validateQuit(fName)) {
			return;
		}
		System.out.print("What is the new Username for the Employee (Type 'Quit' to Exit): ");
		String username = helper.getStringInput();
		if(helper.validateQuit(username)) {
			return;
		}
		System.out.print("What is the new Email for the Employee (Type 'Quit' to Exit): ");
		String email = helper.getStringInput();
		if(helper.validateQuit(email)) {
			return;
		}
		System.out.print("What is the new Password for the Employee (Type 'Quit' to Exit): ");
		String password = helper.getStringInput();
		if(helper.validateQuit(password)) {
			return;
		}
		System.out.print("What is the new Phone Number for the Employee (Type 'Quit' to Exit): ");
		String phone = helper.getStringInput();
		if(helper.validateQuit(phone)) {
			return;
		}
		
		try {
			System.out.println(us.updateUser(target.getUserId(), target.getRoleId(), gName, fName, username, email, password, phone));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void deleteEmployee(){
		ArrayList<User> employees = us.getBookingEmployees();
		int max = employees.size() + 1;
		System.out.println("\nPlease choose an Employee to Delete: ");
		us.printEmployeeUsers();
		System.out.println("\n" + max + ". To Exit");
		int input = helper.getInput(max, 1);
		if(input == max) {
			return;
		}
		User target = employees.get(input - 1);
		
		try {
			System.out.println(us.deleteUser(target.getUserId()));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void viewEmployees() {
		us.printEmployeeUsers();
	}
	
}
