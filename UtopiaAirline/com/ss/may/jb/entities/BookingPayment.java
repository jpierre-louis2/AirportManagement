package com.ss.may.jb.entities;

public class BookingPayment {

	private int bookingId;
	private String stripeId;
	private int refunded;
	
	public BookingPayment(int bId, String sId, int ref) {
		setBookingId(bId);
		setStripeId(sId);
		setRefunded(ref);
	}
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getStripeId() {
		return stripeId;
	}
	public void setStripeId(String stripeId) {
		this.stripeId = stripeId;
	}
	public int getRefunded() {
		return refunded;
	}
	public void setRefunded(int refunded) {
		this.refunded = refunded;
	}
}
