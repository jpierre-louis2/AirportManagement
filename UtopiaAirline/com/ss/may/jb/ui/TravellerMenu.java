package com.ss.may.jb.ui;

import java.util.ArrayList;

import com.ss.may.jb.DAL.FlightDAL;
import com.ss.may.jb.DAL.UserDAL;
import com.ss.may.jb.entities.*;

public class TravellerMenu {
	InputFunctions helper = new InputFunctions();
	FlightDAL fl = new FlightDAL();
	UserDAL us = new UserDAL();
	private int membershipNumber;

	//Continuously Prompts user for user Id, until given an active ID or quit
	public int validateMembership() {
		int input = -1;
		while(input != 0) {
			System.out.print("Please Enter your Membership Number (Enter 0 to quit): ");
			input = helper.getInput();	
			User user = us.getUser(input);
			if(user != null) {
				return input;
			}
			System.out.println("Invalid Membership Number! Please try again!");
		}
		return 0;
	}
	
	//After a correct Validation, first Menu Prompt
	public void trav1() {
		int membership = validateMembership();
		if (membership == 0) {
			return;
		}
		else {
			this.membershipNumber = membership;
			us.printUserDetails(membership);
		}
		
		int input = 0;
		while (input != 3) {
			System.out.println("\nHow would you like to proceed? \n1. Book a Ticket \n2. Cancel a Trip \n3. Quit to previous");
			input = helper.getInput(3, 1);	
			if(input == 1) {
				bookTrip1();
			}
			else if(input == 2) {
				cancelTrip();
			}
			
		}
	}
	
	//User Trip Booking, first Menu
	public void bookTrip1() {
		int input = 0;
		int max = -1;
		
		System.out.println("\nAvailable Flights: ");
		ArrayList<Flight> flights = fl.getAllAvailableFlights();
		max = flights.size() + 1;
		System.out.println(max + ". To Quit to previous");
		input = helper.getInput(max, 1);	
		
		if(input != max) {
			Flight target = flights.get(input - 1);
			bookTrip2(target);
		}
		
	}
	
	//User Trip Booking, second Menu
	public void bookTrip2(Flight flight) {
		int input = 1;
		
		while (input == 1) {
			System.out.println("Pick the Seat you want to book a ticket for");
			System.out.println("\n1. View Flight Details \n2. First \n3. Business \n4. Economy \n5. Quit");
			input = helper.getInput(5, 1);
			
			if (input == 1) {
				fl.printFlightDetails(flight);
			}
			else if(input != 5 && input != 1) {
				try {
					System.out.println(fl.createNewBooking(flight.getFlightId(), membershipNumber, input-1));
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public void cancelTrip() {
		//List all of the user's flights
		ArrayList<Flight> flights = fl.getAssociatedFlights(membershipNumber);
		int max = flights.size() + 1;
		System.out.println(max + ". To Quit to previous");
		System.out.println("\nWhich would you like to cancel?");
		int input = helper.getInput(max, 1);	
		if(input != max) {
			Flight target = flights.get(input - 1);
			//int bookingId = fl.getUserBookingId(target.getFlightId(), membershipNumber);
			//int seatType = fl.getSeatType(bookingId);
			try {
				fl.cancelBooking(target.getFlightId(), membershipNumber);
			}catch(Exception e) {
				e.printStackTrace();
			}
			//System.out.println("Booking Id: " + bookingId + " Seat Type: " + seatType);
		}
		
		//Prompts them for which one to cancel
			//Add Seat type to flight & flight class back
			//Delete the booking
	}
	

}
