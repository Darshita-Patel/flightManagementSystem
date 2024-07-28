package com.teamAirlines.flightManagementSystem.dao;

import java.util.List;

import com.teamAirlines.flightManagementSystem.bean.Ticket;

public interface TicketDao {
	public void save(Ticket ticket);
	public Long findLastTicketNumber();
	public Ticket findByTicketNumber(Long ticketNumber);
	public void cancelTicket(Long ticketNumber);
	public List<Ticket> showAllTickets();
}
