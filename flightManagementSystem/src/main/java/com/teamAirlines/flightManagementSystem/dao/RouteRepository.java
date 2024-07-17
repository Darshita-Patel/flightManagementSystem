package com.teamAirlines.flightManagementSystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teamAirlines.flightManagementSystem.bean.Route;

public interface RouteRepository extends JpaRepository<Route, Long>{
	@Query("select routeId from Route")
	public List<Long> findAllRouteId();
	
	@Query("select max(routeId) from Route")
	public Long findLastRouteId();

	@Query("select r from Route r where sourceAirportCode=?1 And destinationAirportCode=?2")
	public Route findRouteBySourceAndDestination(String sourceAirportCode, String destinationAirportCode);
}
