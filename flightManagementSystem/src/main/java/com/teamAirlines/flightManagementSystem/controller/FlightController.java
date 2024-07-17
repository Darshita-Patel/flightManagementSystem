package com.teamAirlines.flightManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.teamAirlines.flightManagementSystem.bean.Flight;
import com.teamAirlines.flightManagementSystem.dao.FlightDao;
import com.teamAirlines.flightManagementSystem.dao.RouteDao;
import com.teamAirlines.flightManagementSystem.service.FlightService;

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
		 Flight flight2 = flightService.createReturnFlight(flight1,dtime,atime);
		 flightDao.addFlight(flight1);
		 flightDao.addFlight(flight2);
		 return new ModelAndView("redirect:/index");
	 }
	 
	 @GetMapping("/viewAllFlights")
		public ModelAndView showViewAllFlightsPage() {
			List <Flight> li = flightDao.showAllFlights();
			ModelAndView mv = new ModelAndView("viewAllFlights"); 
			mv.addObject("list",li);
			return mv;
		}
}