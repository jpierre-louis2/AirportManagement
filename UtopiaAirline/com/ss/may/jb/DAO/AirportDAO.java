package com.ss.may.jb.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.ss.may.jb.entities.Airport;

public class AirportDAO extends BaseDAO<Airport>{
	//Supports Database Connection
	public AirportDAO(Connection conn) {
			super(conn);
		}
	
	//Returns an ArrayList of Airports based on the Select Statement sent
	public ArrayList<Airport> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		ArrayList<Airport> airports = new ArrayList<Airport>();
		while(rs.next()) {
			String id = rs.getString("iata_id");
			String city = rs.getString("city");
			Airport airport = new Airport(id, city);
			airports.add(airport);
		}
		return airports;
	}
	//Returns the Airport specified by Airport Id
	public Airport getAirport(String id) throws ClassNotFoundException, SQLException{
		ArrayList<Airport> airports = read("SELECT iata_id, city FROM airport WHERE iata_id=?", new Object[] {id});
		return airports.get(0);
	}
	//Returns an ArrayList of all Airports in Table
	public ArrayList<Airport>getAllAirports() throws ClassNotFoundException, SQLException{
		ArrayList<Airport> airports = read("SELECT iata_id, city FROM airport WHERE iata_id=0", null);
		return airports;
	}
	//Inserts a new Airport into database
	public void addAirport(Airport airport) throws ClassNotFoundException, SQLException{
		save("INSERT INTO airport (iata_id, city) VALUES (?, ?)", new Object[] {airport.getAirportId(), airport.getCity()});
	}
	//Updates the information for an Airport specified by Airport Id
	public void updateAirport(Airport airport) throws ClassNotFoundException, SQLException{
		save("UPDATE airport SET city=? WHERE iata_id=?", new Object[] {airport.getCity(), airport.getAirportId()});
	}
	//Deletes the Airport specified by Airport Id
	public void deleteAirport(String id) throws ClassNotFoundException, SQLException{
		save("DELETE FROM airport WHERE iata_id=?", new Object[] {id});
	}	
}
