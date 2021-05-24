package com.ss.may.jb.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.may.jb.entities.BookingPayment;

public class BookingPaymentDAO extends BaseDAO<BookingPayment>{

	//Supports Database Connection
	public BookingPaymentDAO(Connection conn) {
			super(conn);
		}
	
	//Returns an ArrayList of BookingPayments based on the Select Statement sent
	public ArrayList<BookingPayment> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		ArrayList<BookingPayment> payments = new ArrayList<BookingPayment>();
		while(rs.next()) {
			int bId = rs.getInt("booking_id");
			String sId = rs.getString("stripe_id");
			int refund = rs.getInt("refunded");
			BookingPayment payment = new BookingPayment(bId, sId, refund);
			payments.add(payment);
		}
		return payments;
	}
	//Returns the BookingPayment specified by Booking Id
	public BookingPayment getBookingPayment(int id) throws ClassNotFoundException, SQLException{
		ArrayList<BookingPayment> payments = read("SELECT booking_id, stripe_id, refunded FROM booking_payment WHERE booking_id=?", new Object[] {id});
		return payments.get(0);
	}
	//Returns an ArrayList of all BookingPayments in Table
	public ArrayList<BookingPayment>getAllBookingPayments() throws ClassNotFoundException, SQLException{
		ArrayList<BookingPayment> payments = read("SELECT booking_id, stripe_id, refunded FROM booking_payment WHERE booking_id!=0", null);
		return payments;
	}
	//Inserts a new BookingPayment into database
	public void addBookingPayment(BookingPayment payment) throws ClassNotFoundException, SQLException{
		save("INSERT INTO booking_payment (booking_id, stripe_id, refunded) VALUES (?, ?, ?)", new Object[] {payment.getBookingId(), payment.getStripeId(), payment.getRefunded()});
	}
	//Updates the information for an BookingPayment specified by Booking Id
	public void updateBookingPayment(BookingPayment payment) throws ClassNotFoundException, SQLException{
		save("UPDATE booking_payment SET stripe_id=?, refunded=? WHERE booking_id=?", new Object[] {payment.getStripeId(), payment.getRefunded(), payment.getBookingId()});
	}
	//Deletes the BookingPayment specified by Booking Id
	public void deleteBookingPayment(int id) throws ClassNotFoundException, SQLException{
		save("DELETE FROM booking_payment WHERE booking_id=?", new Object[] {id});
	}	
}
