package com.teamAirlines.flightManagementSystem.service;

import org.springframework.stereotype.Service;

import com.teamAirlines.flightManagementSystem.bean.Route;

@Service
public class RouteService {
	
	 /**
     * Creates a return route based on the given route. The return route will have 
     * the source and destination airport codes swapped and a new route ID.
     * 
     * @param route : The original Route object.
     * @return Route : The new Route object representing the return route.
     */
	public Route createReturnRoute(Route route) {
		// Generate a new route ID by incrementing the current route ID
		Long newId = route.getRouteId() + 1;
		
		// Swap the source and destination airport codes
		String sourceCode = route.getDestinationAirportCode();
		String destinationCode = route.getSourceAirportCode();
		
		// Create and return the new Route object for the return route
		return new Route(newId,sourceCode,destinationCode,route.getTicketCost());
	}
}
