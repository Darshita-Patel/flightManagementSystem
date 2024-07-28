package com.teamAirlines.flightManagementSystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.teamAirlines.flightManagementSystem.bean.FlightDatewise;

@Repository
@Service
public class FlightDatewiseDaoImpl implements FlightDatewiseDao{

	@Autowired
	private FlightDatewiseRepository repository;
	
	@Override
	public void save(FlightDatewise flightDatewise) {
		repository.save(flightDatewise);	
	}

	@Override
	public FlightDatewise findByDateAndFlightNumber(String date, Long flightNumber) {
		return repository.findByDateAndFlightNumber(date, flightNumber);
	}
}