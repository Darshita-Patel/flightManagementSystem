package com.teamAirlines.flightManagementSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teamAirlines.flightManagementSystem.bean.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{
	@Query("select max(ticketNumber) from Ticket")
	public Long findLastTicketNumber();
	
	@Query("select t from Ticket t where t.ticketNumber=?1")
	public Ticket findByTicketNumber(Long ticketNumber);
	
}
