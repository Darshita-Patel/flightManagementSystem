package com.teamAirlines.flightManagementSystem.controller;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.teamAirlines.flightManagementSystem.bean.Flight;
import com.teamAirlines.flightManagementSystem.bean.Passenger;
import com.teamAirlines.flightManagementSystem.bean.Route;
import com.teamAirlines.flightManagementSystem.bean.Ticket;
import com.teamAirlines.flightManagementSystem.bean.TicketPassengerEmbed;
import com.teamAirlines.flightManagementSystem.dao.AirportDao;
import com.teamAirlines.flightManagementSystem.dao.FlightDao;
import com.teamAirlines.flightManagementSystem.dao.PassengerDao;
import com.teamAirlines.flightManagementSystem.dao.RouteDao;
import com.teamAirlines.flightManagementSystem.dao.TicketDao;
import com.teamAirlines.flightManagementSystem.exception.RouteNotFoundException;
import com.teamAirlines.flightManagementSystem.exception.SeatNotFoundException;
import com.teamAirlines.flightManagementSystem.exception.FlightNotFoundException;
import com.teamAirlines.flightManagementSystem.service.TicketService;

@ControllerAdvice
@RestController
public class FlightBookingController {
	
	@Autowired
	private AirportDao airportDao;	
	
	@Autowired
	private FlightDao flightDao;
	
	@Autowired
	private RouteDao routeDao;
	
	@Autowired
	private TicketDao ticketDao;
	
	@Autowired
	private PassengerDao passengerDao;
	
	@Autowired
	private TicketService ticketService;
	
	@GetMapping("/flightSearch")
	 public ModelAndView showRouteSelectPage() {
		 List<String> locationList = airportDao.findAllAirportLocations();
	     ModelAndView mv = new ModelAndView("routeSelectPage");
	     mv.addObject("locationList", locationList);
	     return mv;
	 }
	 
	 @PostMapping("/flightSearch")
	 public ModelAndView showRouteFlightsPage(@RequestParam("fromCity") String fromCity,@RequestParam("toCity") String toCity, HttpSession session ) {
		String fromAirport = airportDao.findAirportCodeByLocation(fromCity);
		String toAirport = airportDao.findAirportCodeByLocation(toCity);
		Route route = routeDao.findRouteBySourceAndDestination(fromAirport, toAirport);
		if(route==null) {
			throw new RouteNotFoundException();
		}
		List<Flight> flightList = flightDao.findByRouteId(route.getRouteId());
		if(flightList.isEmpty()) {
			throw new FlightNotFoundException();
		}
		
		Map<Long, String> flightDurations = new HashMap<>();
	    for (Flight flight : flightList) {
	        LocalTime departureTime = LocalTime.parse(flight.getDepartureTime());
	        LocalTime arrivalTime = LocalTime.parse(flight.getArrivalTime());
	        Duration duration = Duration.between(departureTime, arrivalTime);
	        String tripDuration = String.format("%dh %02dm", duration.toHours(), duration.toMinutesPart());
	        flightDurations.put(flight.getFlightNumber(), tripDuration);
	    }
	    
	    session.setAttribute("fromCity", fromCity);
	    session.setAttribute("toCity", toCity);
	    session.setAttribute("route", route);	
	    
	    ModelAndView mv = new ModelAndView("routeFlightsShowPage");
	    mv.addObject("flightList", flightList);
	    mv.addObject("flightDurations", flightDurations);
	    mv.addObject("fromAirport", fromCity);
	    mv.addObject("toAirport", toCity);
	    mv.addObject("ticketCost", route.getTicketCost());
	    return mv;
	 }
	 
	 @ExceptionHandler(value = RouteNotFoundException.class)
	 public ModelAndView handlingRouteNotFoundException(RouteNotFoundException routeNotFoundException) {
		 String message = "No such route found.";
		 ModelAndView mv = new ModelAndView("routeError");
		 mv.addObject("errorMessage",message);
		 return mv;
	 }
	 
	 @ExceptionHandler(value = FlightNotFoundException.class)
	 public ModelAndView handlingFlightNotFoundException(FlightNotFoundException flightNotFoundException) {
		 String message = "No Flights found on this Route.";
		 ModelAndView mv = new ModelAndView("routeError");
		 mv.addObject("errorMessage",message);
		 return mv;
	 }
	 	 
	 /*@PostMapping("/bookFlight")
	 public ModelAndView showBookingPage1(@RequestParam("carrierName") String carrierName,@RequestParam("flightNumber") Long flightNumber, HttpSession session) {
		 Ticket ticket = new Ticket();
		 long ticketNumber = ticketDao.findLastTicketNumber();
		 ticket.setTicketNumber(ticketNumber);
		 Route route = (Route) session.getAttribute("route");
		 ticket.setRouteId(route.getRouteId());
		 ticket.setCarrierName(carrierName);
		 ticket.setFlightNumber(flightNumber);
		 session.setAttribute("ticket", ticket);	
		 ModelAndView mv = new ModelAndView("bookFlightPage");
		 mv.addObject("ticketRecord",ticket);
		 mv.addObject("fare",route.getTicketCost());
		 LocalDate jDate = LocalDate.now();
		 Date d = Date.from(jDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		 mv.addObject("journeyDate",d);
		 mv.addObject("toAirport",session.getAttribute("toCity"));
		 mv.addObject("fromAirport",session.getAttribute("fromCity"));
		 return mv;
	 }*/
	 
	 @PostMapping("/bookFlight")
	 public ModelAndView showBookingPage(@RequestParam("carrierName") String carrierName, 
	                                     @RequestParam("flightNumber") Long flightNumber, 
	                                     HttpSession session) {
	     Ticket ticket = (Ticket) session.getAttribute("ticket");
	     if (ticket == null) {
	         ticket = new Ticket();
	         long ticketNumber = ticketDao.findLastTicketNumber();
	         ticket.setTicketNumber(ticketNumber);
	         Route route = (Route) session.getAttribute("route");
	         ticket.setRouteId(route.getRouteId());
	         ticket.setCarrierName(carrierName);
	         ticket.setFlightNumber(flightNumber);
	         session.setAttribute("ticket", ticket);
	     }

	     ModelAndView mv = new ModelAndView("bookFlightPage");
	     mv.addObject("ticketRecord", ticket);
	     Route route = (Route) session.getAttribute("route");
	     mv.addObject("fare", route.getTicketCost());
	     LocalDate jDate = LocalDate.now();
	     Date d = Date.from(jDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	     mv.addObject("journeyDate", d);
	     mv.addObject("toAirport", session.getAttribute("toCity"));
	     mv.addObject("fromAirport", session.getAttribute("fromCity"));
	     mv.addObject("passengers", session.getAttribute("passengers"));
	     return mv;
	 }

	 	 
	 @PostMapping("/confirmBooking")
	 public ModelAndView showTicketPage(@ModelAttribute("ticketRecord") Ticket ticket, HttpServletRequest request, HttpSession session) {
		 ModelAndView mv = new ModelAndView("ticketPage");
		 List<Passenger> passengers = new ArrayList<>();
		 String toAirport = (String) session.getAttribute("toCity");;
		 String fromAirport = (String) session.getAttribute("fromCity");;
		 String journeyDate = request.getParameter("journeyDate");
		 LocalDate date = LocalDate.parse(journeyDate);
		 Double totalFare = 0.0;
		 Integer noOfPassengers=0;
	        for (int i = 0; i <= 5; i++) {
	            String passengerName = request.getParameter("passengerDetails[" + i + "].name");
	            if(!passengerName.equals("--")) {
	            	String passengerDOB = request.getParameter("passengerDetails[" + i + "].dob");
	            	TicketPassengerEmbed embed = new TicketPassengerEmbed(ticket.getTicketNumber(),i+1);
	            	Passenger passenger = new Passenger(embed,passengerName,passengerDOB,ticket.getTotalAmount());
	            	Double fare = ticketService.discountCalculation(passenger);
	            	noOfPassengers++;
	            	totalFare = totalFare + fare;
	            	passenger.setFare(fare);
	            	//passengerDao.save(passenger);
	            	passengers.add(passenger);
	            }
	        }
	        if(ticketService.capacityCalculation(noOfPassengers, ticket.getFlightNumber())) {
	  	      ticket.setTotalAmount(totalFare);
		      //ticketDao.save(ticket);
	        }else {
	        	throw new SeatNotFoundException();
	        }
	        
	        session.setAttribute("ticket", ticket);
	        session.setAttribute("passengers", passengers);
	        session.setAttribute("journeyDate", date);	
	        
	        mv.addObject("ticket",ticket);
	        mv.addObject("passengers",passengers);
	        mv.addObject("toAirport",toAirport);
	        mv.addObject("fromAirport",fromAirport);
	        mv.addObject("date",date);
		 return mv;
	 }
	 
	 @ExceptionHandler(value = SeatNotFoundException.class)
	 public ModelAndView handlingSeatNotFoundException(SeatNotFoundException seatNotFoundException) {
		 String message = "Not Enough Seats on this Flight.";
		 ModelAndView mv = new ModelAndView("seatError");
		 mv.addObject("errorMessage",message);
		 return mv;
	 }
	 
	 @GetMapping("/payment")
	 public ModelAndView showConfirmTicketPage(HttpSession session) {
		 Ticket ticket = (Ticket)session.getAttribute("ticket");
		 ticketDao.save(ticket);
		 List<Passenger> passengers = (List<Passenger>)session.getAttribute("passengers");
		 for (Passenger passenger : passengers) {
			 passengerDao.save(passenger);
	        }
		 return new ModelAndView("redirect:/index");
	 }
}