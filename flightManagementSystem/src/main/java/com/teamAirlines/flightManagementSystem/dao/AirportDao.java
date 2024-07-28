package com.teamAirlines.flightManagementSystem.dao;

import java.util.List;

import com.teamAirlines.flightManagementSystem.bean.Airport;

public interface AirportDao {
	public void addAirport(Airport airport);
	public List<Airport> showAllAirports();
	public Airport showAirport(String id);
	public List<String> findAllAirportCodes();
	public List<String> findAllAirportLocations();
	public String findAirportCodeByLocation(String airportLocation);
	public String findAirportLocationByCode(String airportCode);
}
