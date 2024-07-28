package com.teamAirlines.flightManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.teamAirlines.flightManagementSystem.bean.Route;
import com.teamAirlines.flightManagementSystem.dao.AirportDao;
import com.teamAirlines.flightManagementSystem.dao.RouteDao;
import com.teamAirlines.flightManagementSystem.exception.DuplicateRouteException;
import com.teamAirlines.flightManagementSystem.service.RouteService;

@ControllerAdvice
@RestController
public class RouteController {
	
	@Autowired
	private AirportDao airportDao;	
		
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
		 Route route = routeDao.findRouteBySourceAndDestination(sourceCode, destinationCode);
		 if(route != null) {
			 throw new DuplicateRouteException();
		 }
		 Route route2 = routeService.createReturnRoute(route1);
		 routeDao.addRoute(route1);
		 routeDao.addRoute(route2);
		 return new ModelAndView("redirect:/index");
	 }
	 
	 @GetMapping("/viewAllRoutes")
		public ModelAndView showViewAllSchedulesPage() {
			List <Route> li = routeDao.showAllRoutes();
			ModelAndView mv = new ModelAndView("viewAllRoutes"); 
			mv.addObject("list",li);
			return mv;
		}
	 
	 @GetMapping("/updateRoute/{id}")
	 public ModelAndView showFlightOnRoutePage(@PathVariable("id") Long id) {
	     Route route = routeDao.showRoute(id);
	     ModelAndView mv = new ModelAndView("singleRouteDetails");
	     mv.addObject("route", route);
	     return mv;
	 }
	 
	 @PostMapping("/saveRouteUpdate")
		public ModelAndView showUpdateRouteSuccessfulPage(@ModelAttribute("route") Route route) {
			routeDao.addRoute(route);
			return new ModelAndView("redirect:/viewAllRoutes");
		}
	 
	 @ExceptionHandler(value = DuplicateRouteException.class)
	 public ModelAndView handlingDuplicateRouteException(DuplicateRouteException duplicateRouteException) {
		 return new ModelAndView("duplicateRouteError");
	 }
}
