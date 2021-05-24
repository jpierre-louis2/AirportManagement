package com.ss.may.jb.entities;

public class BookingGuest {

	private int bookingId;
	private String email;
	private String phone;
	
	
	public BookingGuest(int id, String email, String phone) {
		setBookingId(id);
		setEmail(email);
		setPhone(phone);
	}
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
