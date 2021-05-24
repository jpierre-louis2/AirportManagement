package com.ss.may.jb.DAL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import com.ss.may.jb.DAO.PassengerDAO;
import com.ss.may.jb.entities.Passenger;

public class PassengerDAL {
	
	ConnectionUtil u = new ConnectionUtil();
	
	//Prints out all Passengers in Database
	public void printAllPassengers() {
		FlightDAL fl = new FlightDAL();
		ArrayList<Passenger> passengers = getAllPassengers();
		int count = 1;
		for(Passenger passenger : passengers) {
			System.out.println(count + ". " + "Passenger Id: " + passenger.getPassengerId() 
				+ " Name: " + passenger.getGivenName() + " " + passenger.getFamilyName()
				+ " Date of Birth: " + passenger.getDob() + " Gender: " + passenger.getGender() + " Address: " + passenger.getAddress());
			fl.printBookingInfo(passenger.getBookingId());
			++count;
		}
	}
	
	//Returns an ArrayList of all Passengers
	public ArrayList<Passenger> getAllPassengers(){
		ArrayList<Passenger> passengers = new ArrayList<Passenger>();
		try(Connection conn = u.getConnection()){
			PassengerDAO pass = new PassengerDAO(conn);
			passengers = pass.getAllPassengers();
			return passengers;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Returns the id for a Passenger that is to be inserted
	private int findNewPassengerId(){
		ArrayList<Passenger> passengers = getAllPassengers();
		if (passengers.size() == 0) {
			return 1;
		}
		int highest = passengers.get(0).getPassengerId();
		for(Passenger passenger: passengers) {
			if (passenger.getPassengerId() > highest)
				highest = passenger.getPassengerId();
		}
		
		return highest + 1;
	}
	
	//Inserts a new Passenger into database
	public String addPassenger(int bId, String gName, String fName, Date dob, String gender, String address) throws SQLException{
		int id = findNewPassengerId();
		Passenger passenger = new Passenger(id, bId, gName, fName, dob, gender, address);
		Connection conn = null;
		try {
			conn = u.getConnection();
			PassengerDAO pass = new PassengerDAO(conn);
			pass.addPassenger(passenger);
			conn.commit();
			return "Passenger Successfully Added!";
		} catch (Exception e) {
			//e.printStackTrace();
			conn.rollback();
			return "Passenger Could not be Added!";
		}
		finally {
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	//Updates a current Passenger specified by Passenger Id
	public String updatePassenger(int pId, int bId, String gName, String fName, Date dob, String gender, String address) throws SQLException{
		Passenger passenger = new Passenger(pId, bId, gName, fName, dob, gender, address);
		Connection conn = null;
		try {
			conn = u.getConnection();
			PassengerDAO pass = new PassengerDAO(conn);
			pass.updatePassenger(passenger);
			conn.commit();
			return "Passenger Successfully Updated!";
		} catch (Exception e) {
			//e.printStackTrace();
			conn.rollback();
			return "Passenger Could not be Updated!";
		}
		finally {
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	//Deletes a Passenger from Database
	public String deletePassenger(int id) throws SQLException{
		Connection conn = null;
		try {
			conn = u.getConnection();
			PassengerDAO pass = new PassengerDAO(conn);
			pass.deletePassenger(id);
			conn.commit();
			return "Passenger Successfully Deleted!";
		} catch (Exception e) {
			//e.printStackTrace();
			conn.rollback();
			return "Passenger Could not be Deleted!";
		}
		finally {
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
}
