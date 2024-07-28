package com.teamAirlines.flightManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamAirlines.flightManagementSystem.bean.Flight;
import com.teamAirlines.flightManagementSystem.bean.Route;
import com.teamAirlines.flightManagementSystem.dao.FlightDao;
import com.teamAirlines.flightManagementSystem.dao.RouteDao;

@Service
public class FlightService {
	@Autowired
	private RouteDao routeDao;
	
	@Autowired
	private FlightDao flightDao;
	public Flight createReturnFlight(Flight flight, String dtime, String atime) {
		Long newId = flight.getFlightNumber()+1;
		Route route = routeDao.showRoute(flight.getRouteId());
		String sourceCode = route.getDestinationAirportCode();
		String destinationCode = route.getSourceAirportCode();
		Route route2 = routeDao.findRouteBySourceAndDestination(sourceCode, destinationCode);
		return new Flight(newId,flight.getCarrierName(),route2.getRouteId(), flight.getSeatCapacity(),dtime,atime);
	}
	
	public List<Flight> getFlightsByDate(String date) {
        List<Object[]> results = flightDao.findFlightsByDate(date);
        List<Flight> flights = new ArrayList<>();
        for (Object[] result : results) {
            Flight flight = (Flight) result[0];
            int seatBooked = (Integer) result[1];
            flight.setSeatBooked(seatBooked);
            flights.add(flight);
            flightDao.addFlight(flight);
        }
        return flights;
    }
	
	public void setSeatBookedToZero() {
		List<Flight> flights = flightDao.showAllFlights();
		for(Flight flight: flights) {
			flight.setSeatBooked(0);
			flightDao.addFlight(flight);
		}
	}
}
