package com.ss.may.jb.DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.ss.may.jb.entities.FlightBookings;

public class FlightBookingsDAO extends BaseDAO<FlightBookings>{
	//Supports Database Connection
	public FlightBookingsDAO(Connection conn) {
			super(conn);
		}
	
	//Returns an ArrayList of FlightBookings based on the Select Statement sent
	public ArrayList<FlightBookings> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		ArrayList<FlightBookings> bookings = new ArrayList<FlightBookings>();
		while(rs.next()) {
			int fId = rs.getInt("flight_id");
			int bId = rs.getInt("booking_id");
			FlightBookings booking = new FlightBookings(fId, bId);
			bookings.add(booking);
		}
		return bookings;
	}
	//Returns the FlightBooking specified by Flight Id
	public FlightBookings getFlightBookingsByBooking(int id) throws ClassNotFoundException, SQLException{
		ArrayList<FlightBookings> bookings = read("SELECT flight_id, booking_id FROM flight_bookings WHERE booking_id=?", new Object[] {id});
		return bookings.get(0);
	}
	//Returns an ArrayList of all FlightBookings in Table
	public ArrayList<FlightBookings>getAllFlightBookings() throws ClassNotFoundException, SQLException{
		ArrayList<FlightBookings> bookings = read("SELECT flight_id, booking_id FROM flight_bookings", null);
		return bookings;
	}
	//Inserts a new FlightBooking into database
	public void addFlightBookings(FlightBookings booking) throws ClassNotFoundException, SQLException{
		save("INSERT INTO flight_bookings (flight_id, booking_id) VALUES (?, ?)", new Object[] {booking.getFlightId(), booking.getBookingId()});
	}
	/*
	public void updateFlightBookings(FlightBookings booking) throws ClassNotFoundException, SQLException{
		save("UPDATE airport SET city=? WHERE iata_id=?", new Object[] {airport.getCity(), airport.getAirportId()});
	}
	*/
	//Deletes the FlightBooking specified by Flight Id
	public void deleteFlightBookingsByBooking(int id) throws ClassNotFoundException, SQLException{
		save("DELETE FROM flight_bookings WHERE booking_id=?", new Object[] {id});
	}	
	//Deletes the FlightBooking specified by Flight Id
	public void deleteFlightBookingsByFlight(int id) throws ClassNotFoundException, SQLException{
		save("DELETE FROM flight_bookings WHERE flight_id=?", new Object[] {id});
	}
	//Deletes the FlightBooking specified by Flight Id
	public void deleteFlightBookings(int bookingId, int flightId) throws ClassNotFoundException, SQLException{
		save("DELETE FROM flight_bookings WHERE flight_id=? AND booking_id=?", new Object[] {bookingId, flightId});
	}
}
