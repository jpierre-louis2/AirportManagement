package com.ss.may.jb.DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.may.jb.entities.Airplane;

public class AirplaneDAO extends BaseDAO<Airplane> {
	//Supports Database Connection
	public AirplaneDAO(Connection conn) {
			super(conn);
		}
	
	//Returns an ArrayList of Airplanes based on the Select Statement sent
	public ArrayList<Airplane> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		ArrayList<Airplane> planes = new ArrayList<Airplane>();
		while(rs.next()) {
			int id = rs.getInt("id");
			int type = rs.getInt("type_id");
			Airplane plane = new Airplane(id, type);
			planes.add(plane);
		}
		return planes;
	}
	//Returns the Airplane specified by Airplane Id
	public Airplane getAirplane(int id) throws ClassNotFoundException, SQLException{
		ArrayList<Airplane> planes = read("SELECT id, type_id FROM airplane WHERE id=?", new Object[] {id});
		return planes.get(0);
	}
	//Returns an ArrayList of all Airplanes inTable
	public ArrayList<Airplane>getAllAirplanes() throws ClassNotFoundException, SQLException{
		ArrayList<Airplane> planes = read("SELECT id, type_id FROM airplane WHERE id!=0", null);
		return planes;
	}
	//Inserts a new Airplane into database
	public void addAirplane(Airplane plane) throws ClassNotFoundException, SQLException{
		save("INSERT INTO airplane (id, type_id) VALUES (?, ?)", new Object[] {plane.getAirplaneId(), plane.getTypeId()});
	}
	//Updates the information for an Airplane specified by Airplane Id
	public void updateAirplane(Airplane plane) throws ClassNotFoundException, SQLException{
		save("UPDATE airplane SET type_id=? WHERE id=?", new Object[] {plane.getTypeId(), plane.getAirplaneId()});
	}
	//Deletes the Airplane specified by Airplane Id
	public void deleteAirplane(int id) throws ClassNotFoundException, SQLException{
		save("DELETE FROM airplane WHERE id=?", new Object[] {id});
	}

}
