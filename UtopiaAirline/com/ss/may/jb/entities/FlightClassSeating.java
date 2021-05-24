package com.ss.may.jb.entities;

public class FlightClassSeating {

	private int flightId;
	private int economyTotal;
	private int businessTotal;
	private int firstTotal;
	
	public FlightClassSeating(int id, int economy, int business, int first) {
		setFlightId(id);
		setEconomyTotal(economy);
		setBusinessTotal(business);
		setFirstTotal(first);
	}
	
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public int getEconomyTotal() {
		return economyTotal;
	}
	public void setEconomyTotal(int economyTotal) {
		this.economyTotal = economyTotal;
	}
	public int getBusinessTotal() {
		return businessTotal;
	}
	public void setBusinessTotal(int businessTotal) {
		this.businessTotal = businessTotal;
	}
	public int getFirstTotal() {
		return firstTotal;
	}
	public void setFirstTotal(int firstTotal) {
		this.firstTotal = firstTotal;
	}
	
	
}
