package com.ss.may.jb.DAL;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import com.ss.may.jb.DAO.*;
import com.ss.may.jb.entities.*;

public class FlightDAL {
	
	ConnectionUtil u = new ConnectionUtil();
	
	
	//Prints the associated cities for flights
	public void printAssociatedCities(Route route) {
		try(Connection conn = u.getConnection()){
			AirportDAO air = new AirportDAO(conn);
			Airport oAirport = air.getAirport(route.getOriginId());
			Airport dAirport = air.getAirport(route.getDestinationId());
			System.out.println(oAirport.getAirportId() + ", "  + oAirport.getCity() + " ---> " + dAirport.getAirportId() + ", "+ dAirport.getCity());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Prints all Flights
	public void printAllFlights(){
		ArrayList<Flight> flights = getFlights();
		
		for(Flight flight : flights) {
			try(Connection conn = u.getConnection()){
				RouteDAO ro = new RouteDAO(conn);
				Route route = ro.getRoute(flight.getRouteId());
				printFlightDetails(flight);
				printAssociatedCities(route);
				System.out.println("Airplane Id: " + flight.getAirplaneId() + " Departure Time: " + flight.getDepart() + " Seat Price: " + flight.getSeatPrice());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//Returns an ArrayList of all flights
	public ArrayList<Flight> getAllFlights(){
		ArrayList<Flight> flights = getFlights();
		int count = 1;
		
		for(Flight flight : flights) {
			try(Connection conn = u.getConnection()){
				RouteDAO ro = new RouteDAO(conn);
				Route route = ro.getRoute(flight.getRouteId());
				System.out.print(count + ". ");
				printAssociatedCities(route);
				++count;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return flights;
	}
	
	//Returns an ArrayList of all Available Flights
	public ArrayList<Flight> getAllAvailableFlights(){
		ArrayList<Flight> flights = getAvailableFlights();
		int count = 1;
		
		for(Flight flight : flights) {
			try(Connection conn = u.getConnection()){
				RouteDAO ro = new RouteDAO(conn);
				Route route = ro.getRoute(flight.getRouteId());
				System.out.print(count + ". ");
				printAssociatedCities(route);
				++count;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return flights;
	}
	
	//Returns Routes associated with a Flight
	public Route getAssociatedRoute(Flight flight) {
		try(Connection conn = u.getConnection()){
			RouteDAO ro = new RouteDAO(conn);
			Route route = ro.getRoute(flight.getRouteId());
			return route;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Could not find associated Route!");
			return null;
		}
	}
	
	//Prints Details related to a Flight
	public void printFlightDetails(Flight flight){
		AirportDAL air = new AirportDAL();
		try(Connection conn = u.getConnection()){
			RouteDAO ro = new RouteDAO(conn);
			Route route = ro.getRoute(flight.getRouteId());
			String dAirport = air.getAssociatedCity(route.getDestinationId());
			String oAirport = air.getAssociatedCity(route.getOriginId());
			FlightClassSeating seats = getClassSeating(flight.getFlightId());
			
			System.out.println("\nYou have chosen to view the Flight with Flight Id: " + flight.getFlightId()
			+ " and Departure Airport: " + route.getOriginId() + " and Arrival Airport: " + route.getDestinationId());
			
			System.out.println("Departure Airport: " + route.getOriginId() + ", "+ oAirport);
			System.out.println("Arrival Airport: " + route.getDestinationId() + ", "+ dAirport);
			System.out.println("Departure Date: " + flight.getDepart());
			System.out.println("Reserved Seats: " + flight.getReservedSeats());
			System.out.println("Economy Class: " + seats.getEconomyTotal() + "\nBusiness Class: " + seats.getBusinessTotal() + "\nFirst Class: " + seats.getFirstTotal());
		}catch(Exception e) {
			//e.printStackTrace();
		}
	}
	
	//Returns an ArrayList of all Routes
	public ArrayList<Route> getRoutes(){
		ArrayList<Route> routes = new ArrayList<Route>();
		try(Connection conn = u.getConnection()){
			RouteDAO ro = new RouteDAO(conn);
			routes = ro.getAllRoutes();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return routes;
	}
	
	//Returns a Flight Specified by Id
	public Flight getFlight(int id){
		try(Connection conn = u.getConnection()){
			FlightDAO fl = new FlightDAO(conn);
			Flight flight = fl.getFlight(id);
			return flight;
		}catch(Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	//Returns an ArrayList of all flights
	public ArrayList<Flight> getFlights(){
		ArrayList<Flight> flights = new ArrayList<Flight>();
		try(Connection conn = u.getConnection()){
			FlightDAO fl = new FlightDAO(conn);
			flights = fl.getAllFlights();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flights;
	}

	//Returns a list of all flights with more than 1 reserve seat available
	public ArrayList<Flight> getAvailableFlights(){
		ArrayList<Flight> flights = new ArrayList<Flight>();
		try(Connection conn = u.getConnection()){
			FlightDAO fl = new FlightDAO(conn);
			flights = fl.getAllAvailableFlights();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flights;
	}
	
	//Returns a Flight ID for a specified Booking
	public int getFlightIdForBooking(int bookingId) {
		FlightBookings booking = null;
		try(Connection conn = u.getConnection()){
			FlightBookingsDAO book = new FlightBookingsDAO(conn);
			booking = book.getFlightBookingsByBooking(bookingId);
			return booking.getFlightId();
		}catch(Exception e) {
			//e.printStackTrace();
			System.out.println("No Booking Currently Available for Passenger!\n");
			return 0;
		}
		
	}
	
	//Prints information for a specific Booking
	public void printBookingInfo(int bookingId) {
		System.out.print("Booking Information: ");
		Booking booking = getBooking(bookingId);
		int flightId = getFlightIdForBooking(bookingId);
		if (flightId == 0) {
			return;
		}
		Flight flight = getFlight(flightId);
		String type = getSeatTypeString(bookingId);
		
		System.out.println("Booking Id: " + booking.getBookingId() + " Confirmation Code: " + booking.getConfirmationCode() + " Seat Type: " + type);
		printFlightDetails(flight);
		System.out.println();
	}
	
	
	//Checks to see if an Airplane has the same departure and arrival airport
	//Returns true if they do
	public boolean airportCheck(Airport d, Airport a) {
		if (d.getAirportId() == a.getAirportId()) {
			return true;
		}
		return false;
	}
	
	
	//Checks if Route already exits
	public int newRouteCheck(Airport d, Airport a) {
		ArrayList<Route> routes = getRoutes();
		for(Route route : routes) {
			if(route.getOriginId().equalsIgnoreCase(d.getAirportId()) && route.getDestinationId().equalsIgnoreCase(a.getAirportId())) {
				System.out.println("Route Exits!");
				return route.getRouteId();
			}
		}
		try {
			System.out.println("Adding new Route!");
			int newId = findNewRouteId();
			addRoute(d.getAirportId(), a.getAirportId());
			return newId;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	//Returns an ID to be used by a new Flight
	private int findNewFlightId(){
		ArrayList<Flight> flights = getAllFlights(); 
		if (flights.size() == 0) {
			return 1;
		}
		int highest = flights.get(0).getRouteId();
		for(Flight flight : flights) {
			if (flight.getFlightId() > highest)
				highest = flight.getFlightId();
		}
		
		return highest + 1;
	}
	
	//Returns an ID to be used by a new Route
	private int findNewRouteId(){
		ArrayList<Route> routes = getRoutes(); 
		if (routes.size() == 0) {
			return 1;
		}
		int highest = routes.get(0).getRouteId();
		for(Route route : routes) {
			if (route.getRouteId() > highest)
				highest = route.getRouteId();
		}
		
		return highest + 1;
	}
	
	//Adds/Inserts a new Route
	public String addRoute(String oId, String dId) throws SQLException{
		int id = findNewRouteId();
		Route route = new Route(id, oId, dId);
		Connection conn = null;
		try {
			conn = u.getConnection();
			RouteDAO ro = new RouteDAO(conn);
			ro.addRoute(route);
			conn.commit();
			return "Route Successfully Added!";
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Route Could not be Added!";
		}
		finally {
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	
	//Adds/Inserts a new Flight
	public String addFlight(int rId, int aId, Date depart, int eco, int bus, int first, float price) throws SQLException{
		int flightId = findNewFlightId();
		int reserved = eco + bus + first;
		FlightClassSeating seating = new FlightClassSeating(flightId, eco, bus, first);
		Flight flight = new Flight(flightId, rId, aId, depart, reserved, price);
		Connection conn = null;
		try {
			conn = u.getConnection();
			FlightDAO fl = new FlightDAO(conn);
			FlightClassSeatingDAO fc = new FlightClassSeatingDAO(conn);
			fl.addFlight(flight);
			fc.addFlightClassSeating(seating);
			conn.commit();
			printFlightDetails(flight);
			return "\nFlight Successfully Added!";
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "\nFlight Could not be Added!";
		}
		finally {
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	//Updates information related to a Flight
	public String updateFlight(int fId, int rId, int aId, Date depart, int eco, int bus, int first, float price) throws SQLException{
		int reserved = eco + bus + first;
		FlightClassSeating seating = new FlightClassSeating(fId, eco, bus, first);
		Flight flight = new Flight(fId, rId, aId, depart, reserved, price);
		Connection conn = null;
		try {
			conn = u.getConnection();
			FlightDAO fl = new FlightDAO(conn);
			FlightClassSeatingDAO fc = new FlightClassSeatingDAO(conn);
			fl.updateFlight(flight);
			fc.updateFlightClassSeating(seating);
			conn.commit();
			printFlightDetails(flight);
			return "\nFlight Successfully Added!";
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "\nFlight Could not be Added!";
		}
		finally {
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	//Deletes a Flight
	public String deleteFlight(int id) throws SQLException{
		Connection conn = null;
		try {
			conn = u.getConnection();
			FlightDAO fl = new FlightDAO(conn);
			FlightClassSeatingDAO fc = new FlightClassSeatingDAO(conn);
			fc.deleteFlightClassSeating(id);
			fl.deleteFlight(id);
			conn.commit();
			return "\nFlight Successfully Deleted!";
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "\nFlight Could not be Deleted!";
		}
		finally {
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	
	//Updates details Related to a Flight
	public String updateFlightDetails(Airport d, Airport a, Date date, Flight flight) throws SQLException{
		int routeId = newRouteCheck(d, a);
		if(airportCheck(d, a)) {
			return "\nError same Airport Detected, Flight not updated!";
		}
		else if(routeId == 0) {
			return "\nError could not create new Route!";
		}
		
		flight.setRouteId(routeId);
		flight.setDepart(date);
		Connection conn = null;
		try {
			conn = u.getConnection();
			FlightDAO fl = new FlightDAO(conn);
			fl.updateFlight(flight);
			conn.commit();
			printFlightDetails(flight);
			return "\nFlight Successfully Updated!";
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "\nFlight Could not be Updated!";
		}
		finally {
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	//Updates Flight a Flight Class Seating and Reserved Seating
	public String updateFlightSeats(int totalSeats, Flight flight, FlightClassSeating seats) throws SQLException{
		flight.setReservedSeats(totalSeats);
		Connection conn = null;
		try {
			conn = u.getConnection();
			FlightDAO fl = new FlightDAO(conn);
			FlightClassSeatingDAO flc = new FlightClassSeatingDAO(conn);
			fl.updateFlight(flight);
			flc.updateFlightClassSeating(seats);
			conn.commit();
			printFlightDetails(flight);
			return "\nFlight Successfully Updated!";
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "\nFlight Could not be Updated!";
		}
		finally {
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	//Returns a FlightClassSeating based on Flight ID
	public FlightClassSeating getClassSeating(int flightId) {	
		try(Connection conn = u.getConnection()){
			FlightClassSeatingDAO seat = new FlightClassSeatingDAO(conn);
			FlightClassSeating seating = seat.getFlightClassSeating(flightId);
			return seating;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Returns an ArrayList of all Bookings
	public ArrayList<Booking> getAllBookings(){
		try(Connection conn = u.getConnection()){
			BookingDAO bg = new BookingDAO(conn);
			ArrayList<Booking> bookings = bg.getAllBookings();
			return bookings;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Returns a Booking for specified ID
	public Booking getBooking(int id){
		try(Connection conn = u.getConnection()){
			BookingDAO bg = new BookingDAO(conn);
			Booking booking = bg.getBooking(id);
			return booking;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Returns an ArrayList of all BookingGuests
	//Function not needed, table not in use
	public ArrayList<BookingGuest> getAllGuests(){
		try(Connection conn = u.getConnection()){
			BookingGuestDAO bg = new BookingGuestDAO(conn);
			ArrayList<BookingGuest> guests = bg.getAllBookingGuests();
			return guests;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Returns an ID to be used by a new Booking
	private int findNewBookingId(){
		ArrayList<Booking> bookings = getAllBookings(); 
		if (bookings.size() == 0) {
			return 1;
		}
		int highest = bookings.get(0).getBookingId();
		for(Booking booking : bookings) {
			if (booking.getBookingId() > highest)
				highest = booking.getBookingId();
		}
		
		return highest + 1;
	}
	
	
	//Generates a new Booking for a Flight Booking
	public Booking generateBooking() {
		int id = findNewBookingId();
	    byte[] array = new byte[6]; // length is bounded by 6
	    new Random().nextBytes(array);
	    String generatedString = new String(array, Charset.forName("UTF-8"));
	    Booking booking = new Booking(id, 1, generatedString);
	    return booking;
	}
	
	//Decreases Seating for 1 Seating Type
	public FlightClassSeating decreaseClassSeating(FlightClassSeating seating, int type) {
		int total;
		
		switch(type) {
			case 1:
				total = seating.getFirstTotal()-1;
				seating.setFirstTotal(total);
				break;
			case 2:
				total = seating.getBusinessTotal()-1;
				seating.setBusinessTotal(total);
				break;
			case 3:
				total = seating.getEconomyTotal()-1;
				seating.setEconomyTotal(total);
				break;
		}
		return seating;
	}
	//Increases Seating for 1 Seating type
	public FlightClassSeating increaseClassSeating(FlightClassSeating seating, int type) {
		int total;
		switch(type) {
			case 1:
				total = seating.getFirstTotal()+1;
				seating.setFirstTotal(total);
				break;
			case 2:
				total = seating.getBusinessTotal()+1;
				seating.setBusinessTotal(total);
				break;
			case 3:
				total = seating.getEconomyTotal()+1;
				seating.setEconomyTotal(total);
				break;
		}
		return seating;
	}
	//Increased Reserved Flight Seating
	public Flight increaseFlightSeating(Flight flight) {
		int total = flight.getReservedSeats()+1;
		flight.setReservedSeats(total);
		return flight;
	}
	
	//Reduces Reserved Flight Seating
	public Flight decreaseFlightSeating(Flight flight) {
		int total = flight.getReservedSeats()-1;
		flight.setReservedSeats(total);
		return flight;
	}
	
	//Returns an ArrayList of flights associated with a User
	public ArrayList<Flight> getAssociatedFlights(int userId) {
		int count = 1;
		try(Connection conn = u.getConnection()){
			BookingUserDAO bg = new BookingUserDAO(conn);
			FlightBookingsDAO fb = new FlightBookingsDAO(conn);
			FlightDAO fl = new FlightDAO(conn);
			RouteDAO ro = new RouteDAO(conn);
			
			
			ArrayList<BookingUser> usersB = bg.getUserBookings(userId);
			ArrayList<FlightBookings> bookings = new ArrayList<FlightBookings>();
			for(BookingUser user : usersB) {
				FlightBookings booking = fb.getFlightBookingsByBooking(user.getBookingId());
				bookings.add(booking);
			}
			System.out.println("\n"+ bookings.size() + " Bookings Found!");
			ArrayList<Flight> flights = new ArrayList<Flight>();
			for(FlightBookings booking : bookings) {
				Flight flight = fl.getFlight(booking.getFlightId());
				Route route = ro.getRoute(flight.getRouteId());
				System.out.print(count + ". (Departure Date: " + flight.getDepart() + ") ");
				printAssociatedCities(route);
				flights.add(flight);
				++count;
			}
			return flights;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Compares for Booking ID as it relates to a User and returns the booking ID based on UserID and their FlightID
	public int getUserBookingId(int flightId, int userId) {
		try(Connection conn = u.getConnection()){
			BookingUserDAO bu = new BookingUserDAO(conn);
			FlightBookingsDAO fb = new FlightBookingsDAO(conn);
			ArrayList<BookingUser> userBookings = bu.getUserBookings(userId);
			ArrayList<FlightBookings> fBookings = fb.getAllFlightBookings();
			for (BookingUser user : userBookings) {
				for(FlightBookings flight : fBookings) {
					if (user.getBookingId() == flight.getBookingId() && flight.getFlightId() == flightId) {
						return user.getBookingId();
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}	
		return 0;
	}
	
	//Returns a SeatType String based on the type given
	public String setSeatType(int type) {
		switch(type) {
			case 3:
				return "Economy";
			case 2:
				return "Business";
			case 1:
				return "First";
		}
		return null;
	}
	
	//Returns an integer related to the Seat type based on Booking
	public int getSeatType(int bookingId) {
		int type = 0;
		try(Connection conn = u.getConnection()){
			BookingClassDAO bc = new BookingClassDAO(conn);
			BookingClass booking = bc.getBookingClass(bookingId);
		
			switch(booking.getBookingClass()) {
				case "Economy":
					type = 3;
					break;
				case "Business":
					type = 2;
					break;
				case "First":
					type = 1;
					break;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return type;
	}
	
	//Returns a String of the Seat Type for a Booking (First, Economy, Business)
	public String getSeatTypeString(int bookingId) {
		try(Connection conn = u.getConnection()){
			BookingClassDAO bc = new BookingClassDAO(conn);
			BookingClass booking = bc.getBookingClass(bookingId);
			return booking.getBookingClass();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Creates/Inserts a new Booking for a user & associated data
	public String createNewBooking(int flightId, int userId, int type) throws SQLException{
		//Creates new Booking
		Booking booking = generateBooking();
		//Crates a new flight booking
		FlightBookings fBooking = new FlightBookings(flightId, booking.getBookingId());
		//Adds booking to associated userId
		BookingUser bUser = new BookingUser(booking.getBookingId(), userId);
		FlightClassSeating seats = getClassSeating(flightId);
		Flight flight = getFlight(flightId);
		String seatType = setSeatType(type);
		BookingClass bClass = new BookingClass(booking.getBookingId(), seatType);
		
		Connection conn = null;
		try {
			conn = u.getConnection();
			BookingDAO bo = new BookingDAO(conn);
			FlightBookingsDAO fb = new FlightBookingsDAO(conn);
			BookingUserDAO bu = new BookingUserDAO(conn);
			FlightDAO fl = new FlightDAO(conn);
			FlightClassSeatingDAO flc = new FlightClassSeatingDAO(conn);
			BookingClassDAO bc = new BookingClassDAO(conn);
			//Create a new Booking
			//Add the Booking to Flight bookings
			//Add the Booking to associated userId
			//Reduce Economy Seat and Seat count by 1 for flight
			bo.addBooking(booking);
			fb.addFlightBookings(fBooking);
			bu.addBookingUser(bUser);
			Flight flight2 = decreaseFlightSeating(flight);
			fl.updateFlight(flight2);
			FlightClassSeating seats2 = decreaseClassSeating(seats, type);
			flc.updateFlightClassSeating(seats2);
			bc.addBookingClass(bClass);
			conn.commit();
			printFlightDetails(flight);
			return "\nFlight Successfully Booked!";
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "\nFlight Could not be Booked!";
		}
		finally {
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	
	//Processes a Booking Cancellation
	public String cancelBooking(int flightId, int userId) throws SQLException{
		FlightClassSeating seats = getClassSeating(flightId);
		Flight flight = getFlight(flightId);
		
		Connection conn = null;
		try {
			conn = u.getConnection();
			BookingDAO bo = new BookingDAO(conn);
			FlightBookingsDAO fb = new FlightBookingsDAO(conn);
			BookingUserDAO bu = new BookingUserDAO(conn);
			FlightDAO fl = new FlightDAO(conn);
			FlightClassSeatingDAO flc = new FlightClassSeatingDAO(conn);
			BookingClassDAO bc = new BookingClassDAO(conn);
			
			int bookingId = getUserBookingId(flightId, userId);
			int seatType = getSeatType(bookingId);
			fb.deleteFlightBookings(bookingId, flightId);
			bu.deleteBookingUser(bookingId, userId);
			bc.deleteBookingClass(bookingId);
			bo.deleteBooking(bookingId);
			Flight flight2 = increaseFlightSeating(flight);
			fl.updateFlight(flight2);
			FlightClassSeating seats2 = increaseClassSeating(seats, seatType);
			flc.updateFlightClassSeating(seats2);
			
			conn.commit();
			printFlightDetails(flight);
			System.out.println("$" + flight.getSeatPrice() + " Will be Refunded after processing!"); //-How should the refund processing be Conducted & How are we handling Seating
			
			return "\nBooking Successfully Cancelled!";
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "\nBooking Could not be Cancelled!";
		}
		finally {
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
}
