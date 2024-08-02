package com.teamAirlines.flightManagementSystem.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamAirlines.flightManagementSystem.bean.Flight;
import com.teamAirlines.flightManagementSystem.bean.FlightDatewise;
import com.teamAirlines.flightManagementSystem.bean.Passenger;
import com.teamAirlines.flightManagementSystem.dao.FlightDao;
import com.teamAirlines.flightManagementSystem.dao.FlightDatewiseDao;

@Service
public class TicketService {
	
	@Autowired
	private FlightDao flightDao;
	
	@Autowired
	private FlightDatewiseDao flightDatewiseDao;
	
	/**
     * Calculates the discounted fare based on passenger age.
     * - Passengers 14 years old or younger get a 50% discount.
     * - Passengers 60 years old or older get a 30% discount.
     *
     * @param passenger : The passenger whose fare needs to be calculated.
     * @return : The discounted fare.
     */
	public Double discountCalculation(Passenger passenger) {
		Integer age = ageCalculation(passenger.getPassengerDOB()); // Calculate age from DOB
		Double fare = passenger.getFare(); // get original fare
		if(age<=14) {
			fare = passenger.getFare()/2.0; // 50% discount for children
		}
		else if(age>=60) {
			fare = passenger.getFare()-passenger.getFare()*0.30; // 30% discount for senior citizens
		}
		return fare;
	}
	
	/**
     * Calculates the age of the passenger based on their date of birth.
     *
     * @param passengerDOB :  The date of birth of the passenger in YYYY-MM-DD format.
     * @return : The age of the passenger.
     */
	public Integer ageCalculation(String passengerDOB) {
		LocalDate dob = LocalDate.parse(passengerDOB); // Convert DOB string to LocalDate
		LocalDate today = LocalDate.now(); // get Current Date
		Integer age = Period.between(dob, today).getYears(); // Calculate age in years
		return age;
	}
	
	 /**
     * Checks if there is enough capacity on the flight for the given number of seats.
     * If capacity allows, updates the booked seat count.
     *
     * @param numberOfSeatBooking : The number of seats being booked.
     * @param flightNumber : The flight number to check.
     * @param date : The date of the flight.
     * @return : True if booking is successful, false otherwise.
     */
	public boolean capacityCalculation(int numberOfSeatBooking,long flightNumber, String date) {
		FlightDatewise fd = flightDatewiseDao.findByDateAndFlightNumber(date,flightNumber); // Find flight details by date and flight number
		if(fd==null) {
			return true; // If no records found, booking is possible
		}
		Flight flight = flightDao.showFlight(flightNumber); // Get flight details
		int bookedSeat = fd.getSeatBooked()+numberOfSeatBooking; // Calculate new booked seat count
		int remainingSeat = flight.getSeatCapacity()-bookedSeat; // Calculate remaining seats
		if(remainingSeat<0) {
			return false; // Not enough seats available
		}
		else {
			fd.setSeatBooked(bookedSeat); // Update booked seat count
			flightDatewiseDao.save(fd); // Save updated flight datewise details
			return true; // Booking successful
		}
	}
	
	 /**
     * Cancels the specified number of tickets and updates the booked seat count.
     *
     * @param date : The date of the flight.
     * @param flightNumber : The flight number to cancel tickets for.
     * @param noOfPassengers : The number of passengers to cancel.
     */
	
	public void ticketCancellation(String date, Long flightNumber, int noOfPassengers) {
		FlightDatewise fd = flightDatewiseDao.findByDateAndFlightNumber(date, flightNumber); // Find flight details by date and flight number
		fd.setSeatBooked(fd.getSeatBooked()-noOfPassengers); // Update booked seat count
		flightDatewiseDao.save(fd); // Save updated flight datewise details
	}
}
