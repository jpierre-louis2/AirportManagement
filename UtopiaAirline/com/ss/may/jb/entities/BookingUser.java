package com.ss.may.jb.entities;

public class BookingUser {

	private int bookingId;
	private int userId;
	
	public BookingUser(int bId, int uId) {
		setBookingId(bId);
		setUserId(uId);
	}
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
