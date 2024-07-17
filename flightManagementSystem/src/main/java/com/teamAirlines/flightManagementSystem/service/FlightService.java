package com.teamAirlines.flightManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamAirlines.flightManagementSystem.bean.Flight;
import com.teamAirlines.flightManagementSystem.bean.Route;
import com.teamAirlines.flightManagementSystem.dao.RouteDao;

@Service
public class FlightService {
	@Autowired
	private RouteDao routeDao;
	public Flight createReturnFlight(Flight flight, String dtime, String atime) {
		Long newId = flight.getFlightNumber()+1;
		Route route = routeDao.showRoute(flight.getRouteId());
		String sourceCode = route.getDestinationAirportCode();
		String destinationCode = route.getSourceAirportCode();
		Route route2 = routeDao.findRouteBySourceAndDestination(sourceCode, destinationCode);
		return new Flight(newId,flight.getCarrierName(),route2.getRouteId(), flight.getSeatCapacity(),dtime,atime);
	}
}
