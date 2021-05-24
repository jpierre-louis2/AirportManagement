package com.ss.may.jb.entities;

public class BookingAgent {

	private int bookingId;
	private int agentId;
	
	public BookingAgent(int bId, int aId) {
		setBookingId(bId);
		setAgentId(aId);
	}
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getAgentId() {
		return agentId;
	}
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}
	
}
