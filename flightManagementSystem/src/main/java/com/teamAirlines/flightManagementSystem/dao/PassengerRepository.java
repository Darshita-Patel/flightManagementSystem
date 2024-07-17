package com.teamAirlines.flightManagementSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamAirlines.flightManagementSystem.bean.Passenger;
import com.teamAirlines.flightManagementSystem.bean.TicketPassengerEmbed;

public interface PassengerRepository extends JpaRepository<Passenger,TicketPassengerEmbed>{

}
