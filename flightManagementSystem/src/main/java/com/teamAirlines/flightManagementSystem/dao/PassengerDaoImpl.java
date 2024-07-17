package com.teamAirlines.flightManagementSystem.dao;

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

}
