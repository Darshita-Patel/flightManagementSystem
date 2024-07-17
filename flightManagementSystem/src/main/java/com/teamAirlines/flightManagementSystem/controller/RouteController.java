package com.teamAirlines.flightManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.teamAirlines.flightManagementSystem.bean.Flight;
import com.teamAirlines.flightManagementSystem.bean.Route;
import com.teamAirlines.flightManagementSystem.dao.AirportDao;
import com.teamAirlines.flightManagementSystem.dao.FlightDao;
import com.teamAirlines.flightManagementSystem.dao.RouteDao;
import com.teamAirlines.flightManagementSystem.service.RouteService;

@RestController
public class RouteController {
	
	@Autowired
	private AirportDao airportDao;	
	
	@Autowired
	private FlightDao flightDao;
	
	@Autowired
	private RouteDao routeDao;
	
	@Autowired
	private RouteService routeService;
	
	@GetMapping("/addRoute")
	 public ModelAndView showAddNewRoutePage() {
		 Long newRouteId = routeDao.generateRouteId();
	     Route route = new Route();
	     route.setRouteId(newRouteId);
	     List<String> locationList = airportDao.findAllAirportLocations();
	     ModelAndView mv = new ModelAndView("addNewRoute");
	     mv.addObject("locationList", locationList);
	     mv.addObject("routeRecord",route);
	     return mv;
	 }
	 
	 @PostMapping("/addRoute")
	 public ModelAndView saveAddNewRoutePage(@ModelAttribute ("routeRecord") Route route1) {
		 String sourceCode = airportDao.findAirportCodeByLocation(route1.getSourceAirportCode());
		 String destinationCode = airportDao.findAirportCodeByLocation(route1.getDestinationAirportCode());
		 route1.setSourceAirportCode(sourceCode);
		 route1.setDestinationAirportCode(destinationCode);
		 Route route2 = routeService.createReturnRoute(route1);
		 routeDao.addRoute(route1);
		 routeDao.addRoute(route2);
		 return new ModelAndView("indexAdmin");
	 }
	 
	 @GetMapping("/viewAllRoutes")
		public ModelAndView showViewAllSchedulesPage() {
			List <Route> li = routeDao.showAllRoutes();
			ModelAndView mv = new ModelAndView("viewAllRoutes"); 
			mv.addObject("list",li);
			return mv;
		}
	 
	 /*@GetMapping("/viewFlightOnRoute/{id}")
	 public ModelAndView showFlightOnRoutePage(@PathVariable("id") Long id) {
	     List <Flight> li = flightDao.findByRouteId(id);
	     ModelAndView mv = new ModelAndView("viewFlightOnRoute");
	     mv.addObject("list", li);
	     return mv;
	 }*/
}