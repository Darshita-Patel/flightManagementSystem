package com.teamAirlines.flightManagementSystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.teamAirlines.flightManagementSystem.bean.Ticket;

@Repository
@Service
public class TicketDaoImpl implements TicketDao {
	
	@Autowired
	private TicketRepository repository;
	
	@Override
	public void save(Ticket ticket) {
		repository.save(ticket);
	}

	@Override
	public Long findLastTicketNumber() {
		Long val = repository.findLastTicketNumber();
		if(val==null)
			val = 1000001L;
		else
			val = val + 1;
		return val;
	}

}
