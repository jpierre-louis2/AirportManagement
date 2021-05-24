package com.ss.may.jb.DAL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import com.ss.may.jb.DAO.AirportDAO;
import com.ss.may.jb.entities.Airport;

public class AirportDAL {
	
	ConnectionUtil u = new ConnectionUtil();
	
	//Prints all Airports in Database
	public void printAllAirports() {
		ArrayList<Airport> airports = getAllAirports();
		int count = 1;
		for(Airport airport : airports) {
			System.out.println(count + ". " + airport.getAirportId() + ", " + airport.getCity());
			++count;
		}
	}

	//Returns the City associated with the Airport Code
	public String getAssociatedCity(String code) {
		try(Connection conn = u.getConnection()){
			AirportDAO air = new AirportDAO(conn);
			Airport airport = air.getAirport(code);
			return airport.getCity();
		}catch(Exception e) {
			e.printStackTrace();
			return "Unable to get City!";
		}
	}
	
	//Returns an ArrayList of all Airports
	public ArrayList<Airport> getAllAirports(){
		ArrayList<Airport> airports = new ArrayList<Airport>();
		try(Connection conn = u.getConnection()){
			AirportDAO air = new AirportDAO(conn);
			airports = air.getAllAirports();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return airports;
	}
	
	//Inserts a new Airport into Database
	public String addAirport(String id, String city) throws SQLException{
		Airport airport = new Airport(id, city);
		Connection conn = null;
		try {
			conn = u.getConnection();
			AirportDAO air = new AirportDAO(conn);
			air.addAirport(airport);
			conn.commit();
			return "Airport Successfully Added!";
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Airport Could not be Added!";
		}
		finally {
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	//Updates an existing Airport based on AirportId
	public String updateAirport(String id, String city) throws SQLException{
		Airport airport = new Airport(id, city);
		Connection conn = null;
		try {
			conn = u.getConnection();
			AirportDAO air = new AirportDAO(conn);
			air.updateAirport(airport);
			conn.commit();
			return "Airport Successfully Added!";
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Airport Could not be Added!";
		}
		finally {
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	//Removes an airport from Database based on Airport Id
	public String deleteAirport(String id) throws SQLException{
		Connection conn = null;
		try {
			conn = u.getConnection();
			AirportDAO air = new AirportDAO(conn);
			air.deleteAirport(id);
			conn.commit();
			return "Airport Successfully Deleted!";
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Airport Could not be Deleted!";
		}
		finally {
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
}
