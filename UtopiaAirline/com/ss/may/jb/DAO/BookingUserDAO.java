package com.ss.may.jb.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.ss.may.jb.entities.BookingUser;

public class BookingUserDAO extends BaseDAO<BookingUser>{

	//Supports Database Connection
	public BookingUserDAO(Connection conn) {
			super(conn);
		}
	
	//Returns an ArrayList of BookingUsers based on the Select Statement sent
	public ArrayList<BookingUser> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		ArrayList<BookingUser> users = new ArrayList<BookingUser>();
		while(rs.next()) {
			int bId = rs.getInt("booking_id");
			int uId = rs.getInt("user_id");
			BookingUser user = new BookingUser(bId, uId);
			users.add(user);
		}
		return users;
	}
	//Returns the BookingUser specified by Booking Id
	public BookingUser getBookingUser(int bookingId) throws ClassNotFoundException, SQLException{
		ArrayList<BookingUser> users = read("SELECT booking_id, user_id FROM booking_user WHERE booking_id=?", new Object[] {bookingId});
		return users.get(0);
	}
	//Returns the BookingUser specified by User Id
	public ArrayList<BookingUser> getUserBookings(int userId) throws ClassNotFoundException, SQLException{
		ArrayList<BookingUser> users = read("SELECT booking_id, user_id FROM booking_user WHERE user_id=?", new Object[] {userId});
		return users;
	}
	//Returns an ArrayList of all BookingUsers in Table
	public ArrayList<BookingUser>getAllBookingUsers() throws ClassNotFoundException, SQLException{
		ArrayList<BookingUser> users = read("SELECT booking_id, user_id FROM booking_user WHERE booking_id!=0", null);
		return users;
	}
	//Inserts a new BookingUser into database
	public void addBookingUser(BookingUser user) throws ClassNotFoundException, SQLException{
		save("INSERT INTO booking_user (booking_id, user_id) VALUES (?, ?)", new Object[] {user.getBookingId(), user.getUserId()});
	}
	//Updates the information for an BookingUser specified by Booking Id
	public void updateBookingUser(BookingUser user) throws ClassNotFoundException, SQLException{
		save("UPDATE booking_user SET user_id=? WHERE booking_id=?", new Object[] {user.getUserId(), user.getBookingId()});
	}
	//Deletes the BookingUser specified by Booking Id
	public void deleteBookingUserByBooking(int id) throws ClassNotFoundException, SQLException{
		save("DELETE FROM booking_user WHERE booking_id=?", new Object[] {id});
	}
	//Deletes the BookingUser specified by Booking and User Id
	public void deleteBookingUser(int bookingId, int userId) throws ClassNotFoundException, SQLException{
		save("DELETE FROM booking_user WHERE booking_id=? AND user_id=?", new Object[] {bookingId, userId});
	}
}
