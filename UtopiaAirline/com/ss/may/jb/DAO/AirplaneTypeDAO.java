package com.ss.may.jb.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.ss.may.jb.entities.AirplaneType;

public class AirplaneTypeDAO extends BaseDAO<AirplaneType>{
	
	//Supports Database Connection
	public AirplaneTypeDAO(Connection conn) {
			super(conn);
		}
	//Returns an ArrayList of AirplaneTypes based on the Select Statement sent
	public ArrayList<AirplaneType> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		ArrayList<AirplaneType> types = new ArrayList<AirplaneType>();
		while(rs.next()) {
			int id = rs.getInt("id");
			int max = rs.getInt("max_capacity");
			AirplaneType type = new AirplaneType(id, max);
			types.add(type);
		}
		return types;
	}
	//Returns the AirplaneTypes specified by AirplaneType Id
	public AirplaneType getAirplaneType(int id) throws ClassNotFoundException, SQLException{
		ArrayList<AirplaneType> types = read("SELECT id, max_capacity FROM airplane_type WHERE id=?", new Object[] {id});
		return types.get(0);
	}
	//Returns an ArrayList of all AirplaneTypes in Table
	public ArrayList<AirplaneType>getAllAirplaneTypes() throws ClassNotFoundException, SQLException{
		ArrayList<AirplaneType> types = read("SELECT id, max_capacity FROM airplane_type WHERE id!=0", null);
		return types;
	}
	//Inserts a new AirplaneTypes into database
	public void addAirplaneType(AirplaneType type) throws ClassNotFoundException, SQLException{
		save("INSERT INTO airplane_type (id, max_capacity) VALUES (?, ?)", new Object[] {type.getAirplaneTypeId(), type.getMaxCapacity()});
	}
	//Updates the information for an AirplaneTypes specified by AirplaneType Id
	public void updateAirplaneType(AirplaneType type) throws ClassNotFoundException, SQLException{
		save("UPDATE airplane_type SET max_capacity=? WHERE id=?", new Object[] {type.getAirplaneTypeId(), type.getMaxCapacity()});
	}
	//Deletes the AirplaneTypes specified by AirplaneType Id
	public void deleteAirplaneType(int id) throws ClassNotFoundException, SQLException{
		save("DELETE FROM airplane_type WHERE id=?", new Object[] {id});
	}


}
