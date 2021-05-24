package com.ss.may.jb.entities;

public class BookingClass {

	private int bookingId;
	private String bookingClass;
	
	public BookingClass(int id, String bClass) {
		setBookingId(id);
		setBookingClass(bClass);
	}
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getBookingClass() {
		return bookingClass;
	}
	public void setBookingClass(String bookingClass) {
		this.bookingClass = bookingClass;
	}
	
}
