package com.teamAirlines.flightManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.teamAirlines.flightManagementSystem.bean.FlightUser;
import com.teamAirlines.flightManagementSystem.dao.FlightUserRepository;

@Service
public class FlightUserService implements UserDetailsService{
	@Autowired
	private FlightUserRepository repository;
	private String type;
	
	/**
     * Saves the user details in the database.
     * 
     * @param user : The FlightUser object containing user details.
     */
	public void save(FlightUser user) {
		repository.save(user);
	}
	
	/**
     * Retrieves the type of the current user.
     * 
     * @return : The type of the current user.
     */
	public String getType() {
		return type;
	}
	
	  /**
     * Loads the user by their username.
     * 
     * @param username : The username of the user to be loaded.
     * @return UserDetails : The UserDetails object containing user information.
     * @throws : UsernameNotFoundException if the user is not found.
     */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		// Retrieve the user from the repository
		FlightUser users = repository.findById(username).get();
		
		// Set the type for the current user
		type = users.getType();
		return users;
	}
}
