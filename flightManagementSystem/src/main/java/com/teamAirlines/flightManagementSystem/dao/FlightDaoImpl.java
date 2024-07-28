package com.teamAirlines.flightManagementSystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.teamAirlines.flightManagementSystem.bean.Flight;

@Repository
@Service
public class FlightDaoImpl implements FlightDao {
	
	@Autowired
	private FlightRepository repository;
	
	@Override
	public void addFlight(Flight flight) {
		repository.save(flight);
	}

	@Override
	public Flight showFlight(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Flight> showAllFlights() {
		return repository.findAll();
	}

	@Override
	public List<Long> findAllFlightNumbers() {
		return repository.findAllFlightNumbers();
	}

	@Override
	public List<Flight> findByRouteId(Long routeId) {
		return repository.findByRouteId(routeId);
	}

	@Override
	public List<Object[]> findFlightsByDate(String date) {
		// TODO Auto-generated method stub
		return repository.findFlightsByDate(date);
	}
	
}
