package com.ss.may.jb.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.may.jb.entities.BookingClass;

public class BookingClassDAO extends BaseDAO<BookingClass>{

	public BookingClassDAO(Connection conn) {
		super(conn);
	}

//Returns an ArrayList of BookingClasss based on the Select Statement sent
public ArrayList<BookingClass> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
	ArrayList<BookingClass> bookingClasses = new ArrayList<BookingClass>();
	while(rs.next()) {
		int id = rs.getInt("booking_id");
		String bClass = rs.getString("class");
		BookingClass bookingClass = new BookingClass(id, bClass);
		bookingClasses.add(bookingClass);
	}
	return bookingClasses;
}
//Returns the BookingClass specified by Booking Id
public BookingClass getBookingClass(int id) throws ClassNotFoundException, SQLException{
	ArrayList<BookingClass> classes = read("SELECT booking_id, class FROM booking_class WHERE booking_id=?", new Object[] {id});
	return classes.get(0);
}
//Returns an ArrayList of all BookingClasss in Table
public ArrayList<BookingClass>getAllBookingClasses() throws ClassNotFoundException, SQLException{
	ArrayList<BookingClass> bookingClasses = read("SELECT booking_id, class FROM booking_class WHERE booking_id!=0", null);
	return bookingClasses;
}
//Inserts a new BookingClass into database
public void addBookingClass(BookingClass bookingClass) throws ClassNotFoundException, SQLException{
	save("INSERT INTO booking_class (booking_id, class) VALUES (?, ?)", new Object[] {bookingClass.getBookingId(), bookingClass.getBookingClass()});
}
//Updates the information for an BookingClass specified by Booking Id
public void updateBookingClass(BookingClass bookingClass) throws ClassNotFoundException, SQLException{
	save("UPDATE booking_class SET class=? WHERE booking_id=?", new Object[] {bookingClass.getBookingClass(), bookingClass.getBookingId()});
}
//Deletes the BookingClass specified by Booking Id
public void deleteBookingClass(int id) throws ClassNotFoundException, SQLException{
	save("DELETE FROM booking_class WHERE booking_id=?", new Object[] {id});
}	
	
	
}
