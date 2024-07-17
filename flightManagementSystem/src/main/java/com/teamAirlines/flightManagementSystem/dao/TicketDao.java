package com.teamAirlines.flightManagementSystem.dao;

import com.teamAirlines.flightManagementSystem.bean.Ticket;

public interface TicketDao {
	public void save(Ticket ticket);
	public Long findLastTicketNumber();
}
