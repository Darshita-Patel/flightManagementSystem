package com.teamAirlines.flightManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.teamAirlines.flightManagementSystem.bean.FlightUser;
import com.teamAirlines.flightManagementSystem.service.FlightUserService;

@RestController
public class LoginController {
	
	@Autowired
	private	FlightUserService service;
	
	@Autowired 
	private BCryptPasswordEncoder bCrypt;
	
	@GetMapping("/loginpage")
	public ModelAndView showLoginPage() {
		return new ModelAndView("loginPage");
	}
	
	@GetMapping("/loginerror")
	public ModelAndView showLoginErrorPage() {
		return new ModelAndView("loginError");
	}
	
	@GetMapping("/index")
	public ModelAndView showIndexPage() {
		String indexPage = "";
		String userType = service.getType();
		if(userType.equalsIgnoreCase("Admin"))
			indexPage="indexAdmin";
		else if(userType.equalsIgnoreCase("Customer"))
			indexPage="indexCustomer";
		return new ModelAndView(indexPage);
	}
	
	@GetMapping("/signup")
	public ModelAndView showSignUpPage() {
		FlightUser user = new FlightUser();
		ModelAndView mv = new ModelAndView("signUpPage");
		mv.addObject("userRecord",user);
		return mv;
	}
	
	@PostMapping("/signup")
	public ModelAndView saveSignUpPage(@ModelAttribute("userRecord") FlightUser user) {
		String encodedPassword = bCrypt.encode(user.getPassword()); // encryptes the password
		FlightUser newUser=new FlightUser();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(encodedPassword);
		newUser.setType(user.getType());
		service.save(newUser);
		return new ModelAndView("loginPage");
	}
	
	@GetMapping("/logout")
	public ModelAndView showLogoutPage() {
		return new ModelAndView("loginPage");
	}
	
		
}
