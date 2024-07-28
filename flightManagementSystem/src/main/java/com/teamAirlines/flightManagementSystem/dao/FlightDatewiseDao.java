package com.teamAirlines.flightManagementSystem.dao;

import com.teamAirlines.flightManagementSystem.bean.FlightDatewise;

public interface FlightDatewiseDao {
	public void save(FlightDatewise flightDatewise);
	public FlightDatewise findByDateAndFlightNumber(String date, Long flightNumber);
}
