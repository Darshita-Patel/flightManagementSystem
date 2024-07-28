package com.teamAirlines.flightManagementSystem.controller;

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
import com.teamAirlines.flightManagementSystem.bean.FlightDateEmbed;
import com.teamAirlines.flightManagementSystem.bean.FlightDatewise;
import com.teamAirlines.flightManagementSystem.bean.Passenger;
import com.teamAirlines.flightManagementSystem.bean.Route;
import com.teamAirlines.flightManagementSystem.bean.Ticket;
import com.teamAirlines.flightManagementSystem.bean.TicketPassengerEmbed;
import com.teamAirlines.flightManagementSystem.dao.AirportDao;
import com.teamAirlines.flightManagementSystem.dao.FlightDao;
import com.teamAirlines.flightManagementSystem.dao.FlightDatewiseDao;
import com.teamAirlines.flightManagementSystem.dao.PassengerDao;
import com.teamAirlines.flightManagementSystem.dao.RouteDao;
import com.teamAirlines.flightManagementSystem.dao.TicketDao;
import com.teamAirlines.flightManagementSystem.exception.FlightNotFoundException;
import com.teamAirlines.flightManagementSystem.exception.RouteNotFoundException;
import com.teamAirlines.flightManagementSystem.exception.SeatNotFoundException;
import com.teamAirlines.flightManagementSystem.exception.TicketNotFoundException;
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
	
	@Autowired
	private FlightDatewiseDao flightDatewiseDao;
	
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
	         LocalDate jDate = LocalDate.now();
		     Date d = Date.from(jDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		     String date = d.toString();
		     ticket.setDate(date);
	         session.setAttribute("ticket", ticket);
	     }

	     ModelAndView mv = new ModelAndView("bookFlightPage");
	     mv.addObject("ticketRecord", ticket);
	     Route route = (Route) session.getAttribute("route");
	     mv.addObject("fare", route.getTicketCost());
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
		 ticket.setDate(journeyDate);
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
	        if(ticketService.capacityCalculation(noOfPassengers, ticket.getFlightNumber(),journeyDate)) {
	  	      ticket.setTotalAmount(totalFare);
		      //ticketDao.save(ticket);
	        }else {
	        	throw new SeatNotFoundException();
	        }
	        
	        FlightDatewise fdw = new FlightDatewise();
			FlightDateEmbed fd = new FlightDateEmbed(ticket.getFlightNumber(),journeyDate);
			fdw.setEmbeddedId(fd);
			fdw.setSeatBooked(noOfPassengers);
			
	        session.setAttribute("ticket", ticket);
	        session.setAttribute("passengers", passengers);
	        session.setAttribute("journeyDate", date);
	        session.setAttribute("flightDatewise", fdw);
	        
	        mv.addObject("ticket",ticket);
	        mv.addObject("passengers",passengers);
	        mv.addObject("toAirport",toAirport);
	        mv.addObject("fromAirport",fromAirport);
	        mv.addObject("date",date);
		 return mv;
	 }
	 
	 @GetMapping("/payment")
	 public ModelAndView showConfirmTicketPage(HttpSession session) {
		 Ticket ticket = (Ticket)session.getAttribute("ticket");
		 ticketDao.save(ticket);
		 FlightDatewise fdw = (FlightDatewise)session.getAttribute("flightDatewise");
		 FlightDateEmbed fde = fdw.getEmbeddedId();
		 FlightDatewise fd = flightDatewiseDao.findByDateAndFlightNumber(fde.getDate(),fde.getFlightNumber());
		 if(fd==null) {
			 flightDatewiseDao.save(fdw); 
		 }
		 List<Passenger> passengers = (List<Passenger>)session.getAttribute("passengers");
		 for (Passenger passenger : passengers) {
			 passengerDao.save(passenger);
	        }
		
		 return new ModelAndView("redirect:/index");
	 }
	 
	 @GetMapping("/cancelBooking")
	 public ModelAndView showTicketNumberSelectPage() {
		 return new ModelAndView("selectTicketNumber");
	 }
	 
	 @PostMapping("/cancelBooking")
	 public ModelAndView showTicketPage(@RequestParam("ticketNumber") Long ticketNumber, HttpSession session) {
		 Ticket ticket = ticketDao.findByTicketNumber(ticketNumber);
		 if(ticket==null) {
			 throw new TicketNotFoundException();
		 }
		 List<Passenger> passengers = passengerDao.findByTicketNumber(ticketNumber);
		 Route route = routeDao.showRoute(ticket.getRouteId());
		 String fromAirport = airportDao.findAirportLocationByCode(route.getSourceAirportCode());
		 String toAirport = airportDao.findAirportLocationByCode(route.getDestinationAirportCode());
		 ModelAndView mv = new ModelAndView("showTicketPage");
		 session.setAttribute("ticket", ticket);
		 session.setAttribute("passengers", passengers);
		 mv.addObject("ticket",ticket);
		 mv.addObject("passengers",passengers);
		 mv.addObject("fromAirport",fromAirport);
		 mv.addObject("toAirport",toAirport);
		 return mv;
	 }
	 
	 @GetMapping("/cancel")
	 public ModelAndView showTicket(HttpSession session) {
		 Ticket ticket = (Ticket) session.getAttribute("ticket");
		 List<Passenger> passengers = (List<Passenger>) session.getAttribute("passengers");
		 
		 LocalDate journeyDate = LocalDate.parse(ticket.getDate());
		 LocalDate currentDate = LocalDate.now();

		  if (journeyDate.isBefore(currentDate)) {
		      return new ModelAndView("cancellationError");
		    }
		 
		 ticketDao.cancelTicket(ticket.getTicketNumber());
		 ticketService.ticketCancellation(ticket.getDate(),ticket.getFlightNumber(),passengers.size());
		 for(Passenger p : passengers) {
			 passengerDao.deletePassenger(p);
		 }
		 return new ModelAndView("redirect:/index");
	 }
	 
	 @GetMapping("/viewAllTickets")
	 public ModelAndView showAllTicketsPage() {
		 List<Ticket> li = ticketDao.showAllTickets();
		 ModelAndView mv = new ModelAndView("viewAllTickets");
		 mv.addObject("list",li);
		 return mv;
	 }
	 
	 @GetMapping("/viewAllPassengerDetails")
	 public ModelAndView showAllPassengerDetails() {
		 List<Passenger> li = passengerDao.showAllPassengerDetails();
		 ModelAndView mv = new ModelAndView("viewAllPassengerDetails");
		 mv.addObject("list",li);
		 return mv;
	 }
	 
	 @ExceptionHandler(value = RouteNotFoundException.class)
	 public ModelAndView handlingRouteNotFoundException(RouteNotFoundException routeNotFoundException) {
		 return new ModelAndView("routeError");
	 }
	 
	 @ExceptionHandler(value = FlightNotFoundException.class)
	 public ModelAndView handlingFlightNotFoundException(FlightNotFoundException flightNotFoundException) {
		 return new ModelAndView("flightError");
	 }
	 
	 @ExceptionHandler(value = SeatNotFoundException.class)
	 public ModelAndView handlingSeatNotFoundException(SeatNotFoundException seatNotFoundException) {
		 return new ModelAndView("seatError");
	 }
	 
	 @ExceptionHandler(value = TicketNotFoundException.class)
	 public ModelAndView handlingTicketNotFoundException(TicketNotFoundException ticketNotFoundException) {
		 return new ModelAndView("ticketError");
	 }
}
