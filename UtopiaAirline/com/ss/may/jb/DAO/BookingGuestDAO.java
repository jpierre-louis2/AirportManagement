package com.ss.may.jb.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.ss.may.jb.entities.BookingGuest;

public class BookingGuestDAO extends BaseDAO<BookingGuest>{
	
	//Supports Database Connection
	public BookingGuestDAO(Connection conn) {
			super(conn);
		}
	
	//Returns an ArrayList of BookingGuests based on the Select Statement sent
	public ArrayList<BookingGuest> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		ArrayList<BookingGuest> guests = new ArrayList<BookingGuest>();
		while(rs.next()) {
			int id = rs.getInt("booking_id");
			String email = rs.getString("contact_email");
			String phone = rs.getString("contact_phone");
			BookingGuest guest = new BookingGuest(id, email, phone);
			guests.add(guest);
		}
		return guests;
	}
	//Returns the BookingGuest specified by Booking Id
	public BookingGuest getBookingGuest(int id) throws ClassNotFoundException, SQLException{
		ArrayList<BookingGuest> guests = read("SELECT booking_id, contact_email, contact_phone FROM booking_guest WHERE booking_id=?", new Object[] {id});
		return guests.get(0);
	}
	//Returns an ArrayList of all BookingGuests in Table
	public ArrayList<BookingGuest>getAllBookingGuests() throws ClassNotFoundException, SQLException{
		ArrayList<BookingGuest> guests = read("SELECT booking_id, contact_email, contact_phone FROM booking_guest WHERE booking_id=!0", null);
		return guests;
	}
	//Inserts a new BookingGuest into database
	public void addBookingGuest(BookingGuest guest) throws ClassNotFoundException, SQLException{
		save("INSERT INTO booking_guest (booking_id, contact_email, contact_phone) VALUES (?, ?, ?)", new Object[] {guest.getBookingId(), guest.getEmail(), guest.getPhone()});
	}
	//Updates the information for an BookingGuest specified by Booking Id
	public void updateBookingGuest(BookingGuest guest) throws ClassNotFoundException, SQLException{
		save("UPDATE booking_guest SET contact_email=?, contact_phone=? WHERE booking_id=?", new Object[] {guest.getEmail(), guest.getPhone(), guest.getBookingId()});
	}
	//Deletes the BookingGuest specified by Booking Id
	public void deleteBookingGuest(int id) throws ClassNotFoundException, SQLException{
		save("DELETE FROM booking_guest WHERE booking_id=?", new Object[] {id});
	}	
}
