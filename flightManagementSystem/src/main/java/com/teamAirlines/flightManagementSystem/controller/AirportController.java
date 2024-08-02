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

import com.teamAirlines.flightManagementSystem.bean.Airport;
import com.teamAirlines.flightManagementSystem.dao.AirportDao;
import com.teamAirlines.flightManagementSystem.exception.DuplicateAirportCodeException;

@ControllerAdvice
@RestController
public class AirportController {
	
	@Autowired
	private AirportDao airportDao;
	
	// Show the page for adding a new airport
	@GetMapping("/newAirport")
	public ModelAndView showNewAirportPage() {
		Airport airport = new Airport();
		ModelAndView mv = new ModelAndView("newAirportPage");
		mv.addObject("airport_data",airport);
		return mv;
	}
	
	 // Handle the submission of the new airport form
	@PostMapping("/newAirport")
	public ModelAndView saveNewAirportPage(@ModelAttribute ("airport_data") Airport airport) {
		// Convert airport code and location to uppercase
		String str = airport.getAirportCode().toUpperCase();
		airport.setAirportCode(str);
		String stg = airport.getAirportLocation().toUpperCase();
		airport.setAirportLocation(stg);
		
		 // Check for duplicate airport code
		List<String> codes = airportDao.findAllAirportCodes();
		for(String s : codes) {
			if(str.equalsIgnoreCase(s)) {
				throw new DuplicateAirportCodeException();
			}
		}
		
		// Add the new airport to the database
		airportDao.addAirport(airport);
		return new ModelAndView("redirect:/index");
	}
	
	 // Show the page with the list of all airports
	@GetMapping("/viewAllAirports")
	public ModelAndView showViewAllAirportPage() {
		List <Airport> li = airportDao.showAllAirports();
		ModelAndView mv = new ModelAndView("viewAllAirports"); 
		mv.addObject("list",li);
		return mv;
	}
	
	// Show the page for updating an airport's details
	@GetMapping("/updateAirport/{id}")
	 public ModelAndView showUpdateAirportPage(@PathVariable("id") String id) {
	     Airport airport = airportDao.showAirport(id);
	     ModelAndView mv = new ModelAndView("singleAirportDetails");
	     mv.addObject("airport", airport);
	     return mv;
	 }
	
	// Handle the submission of the updated airport details
	@PostMapping("/saveAirportUpdate")
	public ModelAndView showUpdateAirportSuccessfulPage(@ModelAttribute("airport") Airport airport) {
		// Convert airport location to uppercase
		String str = airport.getAirportLocation().toUpperCase();
		airport.setAirportLocation(str);
		
		// Update the airport in the database
		airportDao.addAirport(airport);
		return new ModelAndView("redirect:/viewAllAirports");
	}
	
	// Handle the exception for duplicate airport code
	 @ExceptionHandler(value = DuplicateAirportCodeException.class)
	 public ModelAndView handlingDuplicateAirportCodeException(DuplicateAirportCodeException duplicateAirportCodeException) {
		 return new ModelAndView("airportCodeError");
	 }
}
