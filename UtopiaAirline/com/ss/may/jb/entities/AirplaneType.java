package com.ss.may.jb.entities;

public class AirplaneType {

	private int airplaneTypeId;
	private int maxCapacity;
	
	public AirplaneType(int id, int max) {
		setAirplaneTypeId(id);
		setMaxCapacity(max);
	}
	
	public int getAirplaneTypeId() {
		return airplaneTypeId;
	}
	public void setAirplaneTypeId(int airplaneTypeId) {
		this.airplaneTypeId = airplaneTypeId;
	}
	public int getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	
}
