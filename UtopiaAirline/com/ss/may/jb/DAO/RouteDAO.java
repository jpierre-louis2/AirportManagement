package com.ss.may.jb.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.ss.may.jb.entities.Route;

public class RouteDAO extends BaseDAO<Route>{
	
	public RouteDAO(Connection conn) {
			super(conn);
		}
	
	//Returns an ArrayList of Routes based on the Select Statement sent
	public ArrayList<Route> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		ArrayList<Route> routes = new ArrayList<Route>();
		while(rs.next()) {
			int id = rs.getInt("id");
			String origin = rs.getString("origin_id");
			String des = rs.getString("destination_id");
			Route route = new Route(id, origin, des);
			routes.add(route);
		}
		return routes;
	}
	//Returns the Route specified by Route Id
	public Route getRoute(int id) throws ClassNotFoundException, SQLException{
		ArrayList<Route> routes = read("SELECT id, origin_id, destination_id FROM route WHERE id=?", new Object[] {id});
		return routes.get(0);
	}
	//Returns an ArrayList of all Routes in Route Table
	public ArrayList<Route>getAllRoutes() throws ClassNotFoundException, SQLException{
		ArrayList<Route> routes = read("SELECT id, origin_id, destination_id FROM route WHERE id!=0", null);
		return routes;
	}
	//Inserts a new Route into database
	public void addRoute(Route route) throws ClassNotFoundException, SQLException{
		save("INSERT INTO route (id, origin_id, destination_id) VALUES (?, ?, ?)", new Object[] {route.getRouteId(), route.getOriginId(), route.getDestinationId()});
	}
	//Updates the information for an Route specified by Route Id
	public void updateRoute(Route route) throws ClassNotFoundException, SQLException{
		save("UPDATE route SET origin_id=?, destination_id=? WHERE id=?", new Object[] {route.getOriginId(), route.getDestinationId(), route.getRouteId()});
	}
	//Deletes the Route specified by Route Id
	public void deleteRoute(int id) throws ClassNotFoundException, SQLException{
		save("DELETE FROM route WHERE id=?", new Object[] {id});
	}	
}
