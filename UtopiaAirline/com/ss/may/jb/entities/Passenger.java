package com.ss.may.jb.entities;
import java.util.Date;

public class Passenger {
	
	private int passengerId;
	private int bookingId;
	private String givenName;
	private String familyName;
	private Date dob;
	private String gender;
	private String address;
	
	public Passenger(int pId, int bId, String gName, String fName, Date dob, String gender, String add) {
		setPassengerId(pId);
		setBookingId(bId);
		setGivenName(gName);
		setFamilyName(fName);
		setDob(dob);
		setGender(gender);
		setAddress(add);
	}
	
	public int getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
