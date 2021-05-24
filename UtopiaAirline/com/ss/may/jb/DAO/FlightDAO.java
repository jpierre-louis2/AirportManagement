package com.ss.may.jb.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.ss.may.jb.entities.Flight;

public class FlightDAO extends BaseDAO<Flight>{
	//Supports Database Connection
	public FlightDAO(Connection conn) {
			super(conn);
	}
	
	//Returns an ArrayList of Flights based on the Select Statement sent
	public ArrayList<Flight> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		ArrayList<Flight> flights = new ArrayList<Flight>();
		while(rs.next()) {
			int id = rs.getInt("id");
			int rId = rs.getInt("route_id");
			int aId = rs.getInt("airplane_id");
			Date depart = rs.getDate("departure_time");
			int reserved = rs.getInt("reserved_seats");
			float price = rs.getFloat("seat_price");
			Flight flight = new Flight(id, rId, aId, depart, reserved, price);
			flights.add(flight);
		}
		return flights;
	}
	//Returns the Flight specified by Flight Id
	public Flight getFlight(int id) throws ClassNotFoundException, SQLException{
		ArrayList<Flight> flights = read("SELECT id, route_id, airplane_id, departure_time, reserved_seats, seat_price FROM flight WHERE id=?", new Object[] {id});
		return flights.get(0);
	}
	//Returns an ArrayList of all Flights in Table
	public ArrayList<Flight>getAllFlights() throws ClassNotFoundException, SQLException{
		ArrayList<Flight> flights = read("SELECT id, route_id, airplane_id, departure_time, reserved_seats, seat_price FROM flight WHERE id!=0", null);
		return flights;
	}
	//Returns an ArrayList of all Flights with more than 1 seat available in Table
	public ArrayList<Flight>getAllAvailableFlights() throws ClassNotFoundException, SQLException{
		ArrayList<Flight> flights = read("SELECT id, route_id, airplane_id, departure_time, reserved_seats, seat_price FROM flight WHERE reserved_seats!=0", null);
		return flights;
	}
	//Inserts a new Flight into database
	public void addFlight(Flight flight) throws ClassNotFoundException, SQLException{
		save("INSERT INTO flight (id, route_id, airplane_id, departure_time, reserved_seats, seat_price) VALUES (?, ?, ?, ?, ?, ?)",
				new Object[] {flight.getFlightId(), flight.getRouteId(), flight.getAirplaneId(), flight.getDepart(), flight.getReservedSeats(), flight.getSeatPrice()});
	}
	//Updates the information for an Flight specified by Flight Id
	public void updateFlight(Flight flight) throws ClassNotFoundException, SQLException{
		save("UPDATE flight SET route_id=?, airplane_id=?, departure_time=?, reserved_seats=?, seat_price=? WHERE id=?", 
				new Object[] {flight.getRouteId(), flight.getAirplaneId(), flight.getDepart(), flight.getReservedSeats(), flight.getSeatPrice(), flight.getFlightId()});
	}
	//Deletes the Flight specified by Flight Id
	public void deleteFlight(int id) throws ClassNotFoundException, SQLException{
		save("DELETE FROM flight WHERE id=?", new Object[] {id});
	}	
}
