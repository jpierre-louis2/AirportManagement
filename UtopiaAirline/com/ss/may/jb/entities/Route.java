package com.ss.may.jb.entities;

public class Route {

	private int routeId;
	private String originId;
	private String destinationId;
	
	public Route(int rId, String oId, String dId) {
		setRouteId(rId);
		setOriginId(oId);
		setDestinationId(dId);
	}
	
	public int getRouteId() {
		return routeId;
	}
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
	public String getOriginId() {
		return originId;
	}
	public void setOriginId(String originId) {
		this.originId = originId;
	}
	public String getDestinationId() {
		return destinationId;
	}
	public void setDestinationId(String destinationId) {
		this.destinationId = destinationId;
	}
	
}
