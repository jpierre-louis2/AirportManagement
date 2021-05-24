package com.ss.may.jb.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.ss.may.jb.entities.BookingAgent;

public class BookingAgentDAO extends BaseDAO<BookingAgent>{
	//Supports Database Connection
	public BookingAgentDAO(Connection conn) {
			super(conn);
		}
	
	//Returns an ArrayList of BookingAgents based on the Select Statement sent
	public ArrayList<BookingAgent> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		ArrayList<BookingAgent> bookingAgents = new ArrayList<BookingAgent>();
		while(rs.next()) {
			int bId = rs.getInt("booking_id");
			int aId = rs.getInt("agent_id");
			BookingAgent bookingAgent = new BookingAgent(bId, aId);
			bookingAgents.add(bookingAgent);
		}
		return bookingAgents;
	}
	//Returns the BookingAgent specified by Booking Id
	public BookingAgent getBookingAgent(int id) throws ClassNotFoundException, SQLException{
		ArrayList<BookingAgent> bookingAgents = read("SELECT booking_id, agent_id FROM booking_agent WHERE booking_id=?", new Object[] {id});
		return bookingAgents.get(0);
	}
	//Returns an ArrayList of all BookingAgents in Booking Table
	public ArrayList<BookingAgent>getAllBookingAgents() throws ClassNotFoundException, SQLException{
		ArrayList<BookingAgent> bookingAgents = read("SELECT booking_id, agent_id FROM booking_agent WHERE booking_id=0", null);
		return bookingAgents;
	}
	//Inserts a new BookingAgent into database
	public void addBookingAgent(BookingAgent bookingAgent) throws ClassNotFoundException, SQLException{
		save("INSERT INTO booking_agent (booking_id, agent_id) VALUES (?, ?)", new Object[] {bookingAgent.getBookingId(), bookingAgent.getAgentId()});
	}
	//Updates the information for an BookingAgent specified by Booking Id
	public void updateBookingAgent(BookingAgent bookingAgent) throws ClassNotFoundException, SQLException{
		save("UPDATE booking_agent SET agent_id=? WHERE booking_id=?", new Object[] {bookingAgent.getAgentId(), bookingAgent.getBookingId()});
	}
	//Deletes the BookingAgent specified by Booking Id
	public void deleteBookingAgent(int id) throws ClassNotFoundException, SQLException{
		save("DELETE FROM booking_agent WHERE booking_id=?", new Object[] {id});
	}	
}
