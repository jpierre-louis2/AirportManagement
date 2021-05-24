package com.ss.may.jb.entities;

import java.util.Date;

public class Flight {
	
	private int flightId;
	private int routeId;
	private int airplaneId;
	private Date depart;
	private int reservedSeats;
	private float seatPrice;
	
	public Flight(int fId, int rId, int aId, Date d, int rSeats, float sPrice) {
		setFlightId(fId);
		setRouteId(rId);
		setAirplaneId(aId);
		setDepart(d);
		setReservedSeats(rSeats);
		setSeatPrice(sPrice);
	}
	
	
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public int getRouteId() {
		return routeId;
	}
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
	public int getAirplaneId() {
		return airplaneId;
	}
	public void setAirplaneId(int airplaneId) {
		this.airplaneId = airplaneId;
	}
	public Date getDepart() {
		return depart;
	}
	public void setDepart(Date depart) {
		this.depart = depart;
	}
	public int getReservedSeats() {
		return reservedSeats;
	}
	public void setReservedSeats(int reservedSeats) {
		this.reservedSeats = reservedSeats;
	}
	public float getSeatPrice() {
		return seatPrice;
	}
	public void setSeatPrice(float seatPrice) {
		this.seatPrice = seatPrice;
	}
}
