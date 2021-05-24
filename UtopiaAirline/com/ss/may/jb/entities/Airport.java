package com.ss.may.jb.entities;

public class Airport {
	
	private String airportId;
	private String city;
	
	public Airport(String id, String city) {
		setAirportId(id);
		setCity(city);
	}
	
	public String getAirportId() {
		return airportId;
	}
	public void setAirportId(String airportId) {
		this.airportId = airportId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

}
