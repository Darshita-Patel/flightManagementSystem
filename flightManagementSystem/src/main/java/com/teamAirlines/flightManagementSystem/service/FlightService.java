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
	
	 /**
     * Creates a return flight based on the given flight details.
     * 
     * @param flight : The original flight object.
     * @param dtime : The departure time of the return flight.
     * @param atime : The arrival time of the return flight.
     * @return : The new return flight object.
     */	
	public Flight createReturnFlight(Flight flight, String dtime, String atime) {
		Long newId = flight.getFlightNumber()+1;
		Route route = routeDao.showRoute(flight.getRouteId());
		String sourceCode = route.getDestinationAirportCode();
		String destinationCode = route.getSourceAirportCode();
		Route route2 = routeDao.findRouteBySourceAndDestination(sourceCode, destinationCode);
		return new Flight(newId,flight.getCarrierName(),route2.getRouteId(), flight.getSeatCapacity(),dtime,atime);
	}
	
	 /**
     * Retrieves flights by date and sets the number of seats booked for each flight.
     * 
     * @param date : The date for which flights are to be retrieved.
     * @return : A list of flights for the given date.
     */	
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
	
	/**
     * Sets the number of seats booked to zero for all flights.
     */
	public void setSeatBookedToZero() {
		List<Flight> flights = flightDao.showAllFlights();
		for(Flight flight: flights) {
			flight.setSeatBooked(0);
			flightDao.addFlight(flight);
		}
	}
}
