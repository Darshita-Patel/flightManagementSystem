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
		List<String> codes = airportDao.findAllAirportCodes();
		for(String s : codes) {
			if(str.equalsIgnoreCase(s)) {
				throw new DuplicateAirportCodeException();
			}
		}
		airportDao.addAirport(airport);
		return new ModelAndView("redirect:/index");
	}
	
	@GetMapping("/viewAllAirports")
	public ModelAndView showViewAllAirportPage() {
		List <Airport> li = airportDao.showAllAirports();
		ModelAndView mv = new ModelAndView("viewAllAirports"); 
		mv.addObject("list",li);
		return mv;
	}
	
	
	@GetMapping("/updateAirport/{id}")
	 public ModelAndView showUpdateAirportPage(@PathVariable("id") String id) {
	     Airport airport = airportDao.showAirport(id);
	     ModelAndView mv = new ModelAndView("singleAirportDetails");
	     mv.addObject("airport", airport);
	     return mv;
	 }
	
	@PostMapping("/saveAirportUpdate")
	public ModelAndView showUpdateAirportSuccessfulPage(@ModelAttribute("airport") Airport airport) {
		String str = airport.getAirportLocation().toUpperCase();
		airport.setAirportLocation(str);
		airportDao.addAirport(airport);
		return new ModelAndView("redirect:/viewAllAirports");
	}
	
	 @ExceptionHandler(value = DuplicateAirportCodeException.class)
	 public ModelAndView handlingDuplicateAirportCodeException(DuplicateAirportCodeException duplicateAirportCodeException) {
		 return new ModelAndView("airportCodeError");
	 }
}
