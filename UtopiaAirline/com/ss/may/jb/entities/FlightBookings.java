package com.ss.may.jb.entities;

public class FlightBookings {

	private int flightId;
	private int bookingId;
	
	public FlightBookings(int fId, int bId) {
		setFlightId(fId);
		setBookingId(bId);
	}
	
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
}
