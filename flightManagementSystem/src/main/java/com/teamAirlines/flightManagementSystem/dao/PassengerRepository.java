package com.teamAirlines.flightManagementSystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teamAirlines.flightManagementSystem.bean.Passenger;
import com.teamAirlines.flightManagementSystem.bean.TicketPassengerEmbed;

public interface PassengerRepository extends JpaRepository<Passenger,TicketPassengerEmbed>{
	@Query("select p from Passenger p where p.embeddedId.ticketNumber = ?1")
	public List<Passenger> findByTicketNumber(Long ticketNumber);
}
