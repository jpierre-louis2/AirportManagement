package com.ss.may.jb.entities;

public class Airplane {

	private int airplaneId;
	private int typeId;
	
	public Airplane(int id, int type) {
		setAirplaneId(id);
		setTypeId(type);
	}

	public int getAirplaneId() {
		return airplaneId;
	}

	public void setAirplaneId(int airplaneId) {
		this.airplaneId = airplaneId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

}
