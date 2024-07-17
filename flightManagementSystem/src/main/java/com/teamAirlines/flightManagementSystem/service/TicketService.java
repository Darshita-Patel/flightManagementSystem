package com.teamAirlines.flightManagementSystem.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamAirlines.flightManagementSystem.bean.Flight;
import com.teamAirlines.flightManagementSystem.bean.Passenger;
import com.teamAirlines.flightManagementSystem.dao.FlightDao;

@Service
public class TicketService {
	
	@Autowired
	private FlightDao flightDao;
	public Double discountCalculation(Passenger passenger) {
		Integer age = ageCalculation(passenger.getPassengerDOB());
		Double fare = passenger.getFare();
		if(age<=14) {
			fare = passenger.getFare()/2.0;
		}
		else if(age>=60) {
			fare = passenger.getFare()-passenger.getFare()*0.30;
		}
		return fare;
	}

	public Integer ageCalculation(String passengerDOB) {
		LocalDate dob = LocalDate.parse(passengerDOB);
		LocalDate today = LocalDate.now();
		Integer age = Period.between(dob, today).getYears();
		return age;
	}
	
	public boolean capacityCalculation(int numberOfSeatBooking,long flightNumber) {
		Flight flight = flightDao.showFlight(flightNumber);
		int bookedSeat = flight.getSeatBooked()+numberOfSeatBooking;
		int remainingSeat = flight.getSeatCapacity()-bookedSeat;
		if(remainingSeat<0) {
			return false;
		}
		else {
			flight.setSeatBooked(flight.getSeatBooked()+numberOfSeatBooking);;
			return true;
		}
	}
}