package com.teamAirlines.flightManagementSystem.dao;

import java.util.List;

import com.teamAirlines.flightManagementSystem.bean.Passenger;

public interface PassengerDao {
	public void save(Passenger passenger);
	public List<Passenger> findByTicketNumber(Long ticketNumber);
	public void deletePassenger(Passenger p);
	public List<Passenger> showAllPassengerDetails();
}
