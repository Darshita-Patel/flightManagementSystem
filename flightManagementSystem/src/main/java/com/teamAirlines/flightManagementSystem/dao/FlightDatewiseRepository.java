package com.teamAirlines.flightManagementSystem.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teamAirlines.flightManagementSystem.bean.FlightDateEmbed;
import com.teamAirlines.flightManagementSystem.bean.FlightDatewise;

public interface FlightDatewiseRepository extends JpaRepository<FlightDatewise,FlightDateEmbed>{
	@Query("select fd from FlightDatewise fd where fd.embeddedId.date=?1 and fd.embeddedId.flightNumber=?2")
	public FlightDatewise findByDateAndFlightNumber(String date, Long flightNumber);
	
}
