package com.teamAirlines.flightManagementSystem.dao;

import java.util.List;

import com.teamAirlines.flightManagementSystem.bean.Flight;

public interface FlightDao {
	public void addFlight(Flight flight);
	public List<Flight> showAllFlights();
	public Flight showFlight(Long id);
	//public List<Long> findAllFlightNumbers();
	public List<Flight> findByRouteId(Long routeId);
}
