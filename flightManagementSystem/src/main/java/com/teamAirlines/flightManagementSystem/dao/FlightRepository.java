package com.teamAirlines.flightManagementSystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teamAirlines.flightManagementSystem.bean.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long>{
	/*@Query("select flightNumber from Flight")
	public List<Long> findAllFlightNumbers();*/
	
	@Query("select f from Flight f where f.routeId=?1")
	public List<Flight> findByRouteId(Long routeId);
}
