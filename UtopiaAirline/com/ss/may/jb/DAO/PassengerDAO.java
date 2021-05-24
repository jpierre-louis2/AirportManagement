package com.ss.may.jb.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.ss.may.jb.entities.Passenger;

public class PassengerDAO extends BaseDAO<Passenger>{
	//Supports Database Connection
	public PassengerDAO(Connection conn) {
			super(conn);
		}
	
	//Returns an ArrayList of Passengers based on the Select Statement sent
	public ArrayList<Passenger> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		ArrayList<Passenger> passengers = new ArrayList<Passenger>();
		while(rs.next()) {
			int id = rs.getInt("id");
			int bId = rs.getInt("booking_id");
			String gName = rs.getString("given_name");
			String fName = rs.getString("family_name");
			Date dob = rs.getDate("dob");
			String gender = rs.getString("gender");
			String address = rs.getString("address");
			Passenger passenger = new Passenger(id, bId, gName, fName, dob, gender, address);
			passengers.add(passenger);
		}
		return passengers;
	}
	//Returns the Passenger specified by Passenger Id
	public Passenger getPassenger(int id) throws ClassNotFoundException, SQLException{
		ArrayList<Passenger> passengers = read("SELECT id, booking_id, given_name, family_name, dob, gender, address FROM passenger WHERE id=?", new Object[] {id});
		return passengers.get(0);
	}
	//Returns an ArrayList of all Passengers in Passenger Table
	public ArrayList<Passenger>getAllPassengers() throws ClassNotFoundException, SQLException{
		ArrayList<Passenger> passengers = read("SELECT id, booking_id, given_name, family_name, dob, gender, address FROM passenger WHERE id!=0", null);
		return passengers;
	}
	//Inserts a new Passenger into database
	public void addPassenger(Passenger passenger) throws ClassNotFoundException, SQLException{
		save("INSERT INTO passenger (id, booking_id, given_name, family_name, dob, gender, address) VALUES (?, ?, ?, ?, ?, ?, ?)",
				new Object[] {passenger.getPassengerId(), passenger.getBookingId(), passenger.getGivenName(), passenger.getFamilyName(), passenger.getDob(), passenger.getGender(), passenger.getAddress()});
	}
	//Updates the information for an Passenger specified by Passenger Id
	public void updatePassenger(Passenger passenger) throws ClassNotFoundException, SQLException{
		save("UPDATE passenger SET booking_id=?, given_name=?, family_name=?, dob=?, gender=?, address=? WHERE id=?",
				new Object[] {passenger.getBookingId(), passenger.getGivenName(), passenger.getFamilyName(), passenger.getDob(), passenger.getGender(), passenger.getAddress(), passenger.getPassengerId()});
	}
	//Deletes the Passenger specified by Passenger Id
	public void deletePassenger(int id) throws ClassNotFoundException, SQLException{
		save("DELETE FROM passenger WHERE id=?", new Object[] {id});
	}
}
