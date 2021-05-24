package com.ss.may.jb.entities;

public class Booking {
	
	private int bookingId;
	private int isActive;
	private String confirmationCode;
	
	public Booking(int id, int active, String confirm) {
		setBookingId(id);
		setIsActive(active);
		setConfirmationCode(confirm);
	}
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public String getConfirmationCode() {
		return confirmationCode;
	}
	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

}
