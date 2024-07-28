package com.teamAirlines.flightManagementSystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.teamAirlines.flightManagementSystem.bean.Passenger;

@Repository
@Service
public class PassengerDaoImpl implements PassengerDao {

	@Autowired
	private  PassengerRepository repository;
	
	@Override
	public void save(Passenger passenger) {
		repository.save(passenger);
	}
	
	@Override
	public List<Passenger> findByTicketNumber(Long ticketNumber) {
		return repository.findByTicketNumber(ticketNumber);
	}
	
	@Override
	public void deletePassenger(Passenger p) {
		repository.delete(p);
		
	}

	@Override
	public List<Passenger> showAllPassengerDetails() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
}
