package com.ss.may.jb.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.ss.may.jb.DAL.FlightDAL;
import com.ss.may.jb.DAL.AirportDAL;
import com.ss.may.jb.entities.*;

public class EmployeeMenu {
	
	InputFunctions helper = new InputFunctions();
	FlightDAL fl = new FlightDAL();
	AirportDAL air = new AirportDAL();
	
	//First Employee Menu
	public void emp1() {
		int input = 0;
		while (input != 2) {
			System.out.println("\nHow would you like to proceed? \n1. Enter Flights you Manage \n2. Quit to previous");
			input = helper.getInput(2, 1);	
			if(input == 1) {
				emp2();
			}
			
		}
	}
	
	//Second Employee Menu
	public void emp2() {
		System.out.println("\nAvailable Flights: ");
		ArrayList<Flight> flights = fl.getAllFlights();
		int max = flights.size() + 1;
		System.out.println(max + ". To Quit to previous");
		int input = helper.getInput(max, 1);	
		
		if(input != max) {
			Flight target = flights.get(input - 1);
			emp3(target);
		}
	}
	
	//Third Employee Menu
	public void emp3(Flight flight) {
		int input = 0;
		while (input != 4) {
			System.out.println("\nHow would you like to proceed?");
			System.out.println("\n1. View more details about Flight \n2. Update the details of the flight");
			System.out.println("3. Add Seats to Flight \n4. Quit to previous");
			input = helper.getInput(4, 1);	
			
			switch(input) {
			case 1:
				//View Details
				fl.printFlightDetails(flight);
				break;
			case 2:
				updateFlight(flight);
				break;
			case 3:
				addSeats(flight);
				break;
			}
			
		}
	}
	
	//Updates information regarding a flight (Not Seating)
	public void updateFlight(Flight flight) {
		Route route = fl.getAssociatedRoute(flight);
		ArrayList<Airport> airports = air.getAllAirports();
		int input;
		int max = airports.size() + 1;
		Airport departure;
		Airport arrival;
		Date date = null;
		
		System.out.println("\nYou have chosen to update the Flight with Flight Id: " + flight.getFlightId()
		+ " and Departure Airport: " + route.getOriginId() + " and Arrival Airport: " + route.getDestinationId());
		
		System.out.println("\nPlease choose a new Origin Airport: ");
		air.printAllAirports();
		System.out.println(max + ". To Exit");
		input = helper.getInput(max, 1);
		if(input == max) {
			return;
		}
		else {
			departure = airports.get(input - 1);
		}
		
		System.out.println("\nPlease choose a new Destination Airport: ");
		air.printAllAirports();
		System.out.println(max + ". To Exit");
		input = helper.getInput(max, 1);
		if(input == max) {
			return;
		}
		else {
			arrival = airports.get(input - 1);
		}
		
		
		System.out.println("\nPlease enter a new Departure Date or 'Quit' to Exit");
		System.out.println("Must be in mm/dd/yyyy format");
		System.out.print("New Departure Date: ");
		String sDate = helper.getStringInput();
		try {
			date = new SimpleDateFormat("MM/dd/yyyy").parse(sDate);
		} catch (Exception e) {
			System.out.println("Error: Incorrect input format!");
		}
		
		try {
			System.out.println(fl.updateFlightDetails(departure, arrival, date, flight));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Updates seating related to a flight
	public void addSeats(Flight flight) {
		
		int input = 0;
		int total;
		FlightClassSeating seats = fl.getClassSeating(flight.getFlightId());
		System.out.println("Free Amount of Seats: " + flight.getReservedSeats());
		System.out.println("\nYou have chosen to update the Flight Seating");
		System.out.println("Pick the Seat Class you want to add seats of to your flight");
		System.out.println("\n1. First \n2. Business \n3. Economy \n4. Quit");
		input = helper.getInput(4, 1);
		
		switch(input) {
			case 1:
				System.out.println("Existing Number of First Class Seats: " + seats.getFirstTotal());
				System.out.print("Enter new number of Seats: ");
				total = helper.getInput();
				seats.setFirstTotal(total);
				break;
			case 2:
				System.out.println("Existing Number of Business Class Seats: " + seats.getEconomyTotal());
				System.out.print("Enter new number of Seats: ");
				total = helper.getInput();
				seats.setBusinessTotal(total);
				break;
			case 3:
				System.out.println("Existing Number of Economy Class Seats: " + seats.getEconomyTotal());
				System.out.print("Enter new number of Seats: ");
				total = helper.getInput();
				seats.setEconomyTotal(total);
				break;
		}
		
		total = seats.getBusinessTotal() + seats.getEconomyTotal() + seats.getFirstTotal();
		try {
			System.out.println(fl.updateFlightSeats(total, flight, seats));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
