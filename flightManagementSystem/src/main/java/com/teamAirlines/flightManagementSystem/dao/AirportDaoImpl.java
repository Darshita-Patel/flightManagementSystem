package com.teamAirlines.flightManagementSystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.teamAirlines.flightManagementSystem.bean.Airport;

@Repository
@Service
public class AirportDaoImpl implements AirportDao {
	
	@Autowired
	private AirportRepository repository;
	
	@Override
	public void addAirport(Airport airport) {
		repository.save(airport);
	}

	@Override
	public List<Airport> showAllAirports() {
		return repository.findAll();
	}

	@Override
	public Airport showAirport(String id) {
		return repository.findById(id).get();
	}

	@Override
	public List<String> findAllAirportCodes() {
		return repository.findAllAirportCodes();
	}

	@Override
	public List<String> findAllAirportLocations() {
		return repository.findAllAirportLocations();
	}

	@Override
	public String findAirportCodeByLocation(String airportLocation) {
		return repository.findAirportCodeByLocation(airportLocation);
	}

}
