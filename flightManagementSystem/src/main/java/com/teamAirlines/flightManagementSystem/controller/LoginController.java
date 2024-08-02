package com.teamAirlines.flightManagementSystem.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.teamAirlines.flightManagementSystem.bean.FlightUser;
import com.teamAirlines.flightManagementSystem.dao.AirportDao;
import com.teamAirlines.flightManagementSystem.dao.FlightUserRepository;
import com.teamAirlines.flightManagementSystem.exception.DuplicateUsernameException;
import com.teamAirlines.flightManagementSystem.service.FlightUserService;

@ControllerAdvice
@RestController
public class LoginController {
	
	@Autowired
	private FlightUserRepository repository;
	
	@Autowired
	private	FlightUserService service;
	
	@Autowired
	private AirportDao airportDao;
	
	@Autowired 
	private BCryptPasswordEncoder bCrypt;
	
	// Display the login page
	@GetMapping("/loginpage")
	public ModelAndView showLoginPage() {
		return new ModelAndView("loginPage");
	}
	
	// Display the login error page
	@GetMapping("/loginerror")
	public ModelAndView showLoginErrorPage() {
		return new ModelAndView("loginError");
	}
	
	// Display the index page based on user type
	@GetMapping("/index")
	public ModelAndView showIndexPage(HttpServletRequest request) {
		String indexPage = "";
		String username = request.getUserPrincipal().getName();
		List<String> locationList = airportDao.findAllAirportLocations();
		String userType = service.getType();
		if(userType.equalsIgnoreCase("Admin"))
			indexPage="indexAdmin";
		else if(userType.equalsIgnoreCase("Customer"))
			indexPage="indexCustomer";
		ModelAndView mv = new ModelAndView(indexPage);
		mv.addObject("username", username);
		mv.addObject("locationList", locationList);
		return mv;
	}
	
	// Display the sign-up page
	@GetMapping("/signup")
	public ModelAndView showSignUpPage() {
		FlightUser user = new FlightUser();
		ModelAndView mv = new ModelAndView("signUpPage");
		mv.addObject("userRecord",user);
		return mv;
	}
	
	// Handle user sign-up
	@PostMapping("/signup")
	public ModelAndView saveSignUpPage(@ModelAttribute("userRecord") FlightUser user) {
		String encodedPassword = bCrypt.encode(user.getPassword());
		// Check for duplicate usernames
		Optional<FlightUser> temp = repository.findById(user.getUsername());		
		if(temp.isPresent()) {
			throw new DuplicateUsernameException();
		}
		// Save the new user
		FlightUser newUser=new FlightUser();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(encodedPassword);
		newUser.setType(user.getType());
		service.save(newUser);
		return new ModelAndView("loginPage");
	}
	
	// Mapping to handle logout
	@GetMapping("/logout")
	public ModelAndView showLogoutPage() {
		return new ModelAndView("loginPage");
	}
	
	 // Handle DuplicateUsernameException
	@ExceptionHandler(value = DuplicateUsernameException.class)
	 public ModelAndView handlingDuplicateUsernameException(DuplicateUsernameException duplicateUsernameException) {
		 return new ModelAndView("duplicateUsernameError");
	 }
}
