package com.teamAirlines.flightManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.teamAirlines.flightManagementSystem.bean.Flight;
import com.teamAirlines.flightManagementSystem.dao.FlightDao;
import com.teamAirlines.flightManagementSystem.dao.RouteDao;
import com.teamAirlines.flightManagementSystem.exception.DuplicateFlightNumberException;
import com.teamAirlines.flightManagementSystem.service.FlightService;

@ControllerAdvice
@RestController
public class FlightController {
	@Autowired
	private FlightDao flightDao;
	
	@Autowired
	private RouteDao routeDao;
	
	@Autowired
	private FlightService flightService;
	
	@GetMapping("/addFlights")
	 public ModelAndView showAddNewFlightsPage() {	 
	       Flight flight = new Flight(); 
	       List<Long> routeList = routeDao.findAllRouteId();
	       ModelAndView mv = new ModelAndView("addNewFlights");
	       mv.addObject("routeList",routeList);
	       mv.addObject("flightRecord",flight);
	       return mv;
	 }
	 
	 @PostMapping("/addFlights")
	 public ModelAndView saveAddNewFlightsPage(@ModelAttribute ("flightRecord") Flight flight1, @RequestParam("dtime") String dtime, @RequestParam("atime") String atime) {
		 List<Long> flightNumbers = flightDao.findAllFlightNumbers();
		 for(Long l : flightNumbers) {
			 if(l.equals(flight1.getFlightNumber())) {
				 throw new DuplicateFlightNumberException();
			 }
		 }
		 Flight flight2 = flightService.createReturnFlight(flight1,dtime,atime);
		 flightDao.addFlight(flight1);
		 flightDao.addFlight(flight2);
		 return new ModelAndView("redirect:/index");
	 }
	 
	 @GetMapping("/viewAllFlights")
		public ModelAndView showDateSelectPage() {
			ModelAndView mv = new ModelAndView("selectDatePage"); 
			return mv;
		}
	 
	 @PostMapping("/viewAllFlights")
	 public ModelAndView showViewAllFlightsPage(@RequestParam("date") String date) {
	     List<Flight> li = flightService.getFlightsByDate(date);
	     if(li.isEmpty()) {
	    	 flightService.setSeatBookedToZero();
	     }
	     List<Flight> flights = flightDao.showAllFlights();
	     ModelAndView mv = new ModelAndView("viewAllFlights"); 
	     mv.addObject("flights", flights);
	     mv.addObject("date",date);
	     return mv;
	 }
	 
	 @GetMapping("updateFlights")
	 public ModelAndView showAllFlightsPage() {
		 List<Flight> flights = flightDao.showAllFlights();
		 ModelAndView mv = new ModelAndView("flightsUpdate");
		 mv.addObject("flights",flights);
		 return mv;
	 }
	 
	 @GetMapping("/updateFlight/{id}")
	 public ModelAndView showUpdateFlightPage(@PathVariable("id") Long id) {
	     Flight flight = flightDao.showFlight(id);
	     ModelAndView mv = new ModelAndView("singleFlightDetails");
	     mv.addObject("flight", flight);
	     return mv;
	 }
	
	@PostMapping("/saveFlightUpdate")
	public ModelAndView showUpdateFlightSuccessfulPage(@ModelAttribute("flight") Flight flight) {
		flightDao.addFlight(flight);
		return new ModelAndView("redirect:/updateFlights");
	}
	
	 @ExceptionHandler(value = DuplicateFlightNumberException.class)
	 public ModelAndView handlingDuplicateFlightNumberException(DuplicateFlightNumberException duplicateFlightNumberException) {
		 return new ModelAndView("flightNumberError");
	 }
}
