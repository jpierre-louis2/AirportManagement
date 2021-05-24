package com.ss.may.jb.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.ss.may.jb.entities.Booking;

public class BookingDAO extends BaseDAO<Booking>{
	//Supports Database Connection
	public BookingDAO(Connection conn) {
			super(conn);
		}
	
	//Returns an ArrayList of Bookings based on the Select Statement sent
	public ArrayList<Booking> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		while(rs.next()) {
			int id = rs.getInt("id");
			int activity = rs.getInt("is_active");
			String code = rs.getString("confirmation_code");
			Booking booking = new Booking(id, activity, code);
			bookings.add(booking);
		}
		return bookings;
	}
	//Returns the Booking specified by Booking Id
	public Booking getBooking(int id) throws ClassNotFoundException, SQLException{
		ArrayList<Booking> bookings = read("SELECT id, is_active, confirmation_code FROM booking WHERE id=?", new Object[] {id});
		return bookings.get(0);
	}
	//Returns an ArrayList of all Bookings in Booking Table
	public ArrayList<Booking>getAllBookings() throws ClassNotFoundException, SQLException{
		ArrayList<Booking> bookings = read("SELECT id, is_active, confirmation_code FROM booking", null);
		return bookings;
	}
	//Inserts a new Booking into database
	public void addBooking(Booking booking) throws ClassNotFoundException, SQLException{
		save("INSERT INTO booking (id, is_active, confirmation_code) VALUES (?, ?, ?)", new Object[] {booking.getBookingId(), booking.getIsActive(), booking.getConfirmationCode()});
	}
	//Updates the information for an Booking specified by Booking Id
	public void updateBooking(Booking booking) throws ClassNotFoundException, SQLException{
		save("UPDATE booking SET is_active=?, confirmation_code=? WHERE id=?", new Object[] {booking.getIsActive(), booking.getConfirmationCode(), booking.getBookingId()});
	}
	//Deletes the Booking specified by Booking Id
	public void deleteBooking(int id) throws ClassNotFoundException, SQLException{
		save("DELETE FROM booking WHERE id=?", new Object[] {id});
	}	
}
