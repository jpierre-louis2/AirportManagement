package com.ss.may.jb.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.may.jb.entities.FlightClassSeating;

public class FlightClassSeatingDAO extends BaseDAO<FlightClassSeating>{

	public FlightClassSeatingDAO(Connection conn) {
		super(conn);
	}

//Returns an ArrayList of FlightClassSeatings based on the Select Statement sent
public ArrayList<FlightClassSeating> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
	ArrayList<FlightClassSeating> flightClassSeatings = new ArrayList<FlightClassSeating>();
	while(rs.next()) {
		int id = rs.getInt("flight_id");
		int economy = rs.getInt("economy_total");
		int business = rs.getInt("business_total");
		int first = rs.getInt("first_total");
		FlightClassSeating flightClassSeating = new FlightClassSeating(id, economy, business, first);
		flightClassSeatings.add(flightClassSeating);
	}
	return flightClassSeatings;
}
//Returns the FlightClassSeating specified by Flight Id
public FlightClassSeating getFlightClassSeating(int id) throws ClassNotFoundException, SQLException{
	ArrayList<FlightClassSeating> classes = read("SELECT flight_id, economy_total, business_total, first_total FROM flight_class_seating WHERE flight_id=?", new Object[] {id});
	return classes.get(0);
}
//Returns an ArrayList of all FlightClassSeatings in Table
public ArrayList<FlightClassSeating>getAllFlightClassSeatings() throws ClassNotFoundException, SQLException{
	ArrayList<FlightClassSeating> flightClassSeatings = read("SELECT flight_id, economy_total, business_total, first_total FROM flight_class_seating WHERE flight_id!=0", null);
	return flightClassSeatings;
}
//Inserts a new FlightClassSeating into database
public void addFlightClassSeating(FlightClassSeating flightClassSeating) throws ClassNotFoundException, SQLException{
	save("INSERT INTO flight_class_seating (flight_id, economy_total, business_total, first_total) VALUES (?, ?, ?, ?)",
			new Object[] {flightClassSeating.getFlightId(), flightClassSeating.getEconomyTotal(), flightClassSeating.getBusinessTotal(), flightClassSeating.getFirstTotal()});
}
//Updates the information for an FlightClassSeating specified by Flight Id
public void updateFlightClassSeating(FlightClassSeating flightClassSeating) throws ClassNotFoundException, SQLException{
	save("UPDATE flight_class_seating SET economy_total=?, business_total=?, first_total=? WHERE flight_id=?",
			new Object[] {flightClassSeating.getEconomyTotal(), flightClassSeating.getBusinessTotal(), flightClassSeating.getFirstTotal(), flightClassSeating.getFlightId()});
}
//Deletes the FlightClassSeating specified by Flight Id
public void deleteFlightClassSeating(int id) throws ClassNotFoundException, SQLException{
	save("DELETE FROM flight_class_seating WHERE flight_id=?", new Object[] {id});
}	
}
