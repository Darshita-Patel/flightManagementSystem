package com.teamAirlines.flightManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.teamAirlines.flightManagementSystem.bean.Airport;
import com.teamAirlines.flightManagementSystem.dao.AirportDao;

@RestController
public class AirportController {
	
	@Autowired
	private AirportDao airportDao;
	
	@GetMapping("/newAirport")
	public ModelAndView showNewAirportPage() {
		Airport airport = new Airport();
		ModelAndView mv = new ModelAndView("newAirportPage");
		mv.addObject("airport_data",airport);
		return mv;
	}
	
	@PostMapping("/newAirport")
	public ModelAndView saveNewAirportPage(@ModelAttribute ("airport_data") Airport airport) {
		String str = airport.getAirportCode().toUpperCase();
		airport.setAirportCode(str);
		String stg = airport.getAirportLocation().toUpperCase();
		airport.setAirportLocation(stg);
		airportDao.addAirport(airport);
		return new ModelAndView("redirect:/index");
	}
	
	@GetMapping("/viewAllAirports")
	public ModelAndView showViewAllAirportPage() {
		List <Airport> li = airportDao.showAllAirports();
		ModelAndView mv = new ModelAndView("viewAllAirport"); 
		mv.addObject("list",li);
		return mv;
	}
	
	
	/*@GetMapping("/viewSingleAirport/{id}")
	 public ModelAndView showSingleAirportDetailsPage(@PathVariable("id") String id) {
	     Airport airport = airportDao.showAirport(id);
	     ModelAndView mv = new ModelAndView("singleAirportDetails");
	     mv.addObject("airport", airport);
	     return mv;
	 }*/
}