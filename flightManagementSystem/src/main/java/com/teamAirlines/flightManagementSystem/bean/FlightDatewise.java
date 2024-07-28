package com.teamAirlines.flightManagementSystem.bean;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class FlightDatewise {
	@EmbeddedId
	private FlightDateEmbed embeddedId;
	private Integer seatBooked;
	
	public FlightDatewise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FlightDatewise(FlightDateEmbed embeddedId, Integer seatBooked) {
		super();
		this.embeddedId = embeddedId;
		this.seatBooked = seatBooked;
	}

	public FlightDateEmbed getEmbeddedId() {
		return embeddedId;
	}

	public void setEmbeddedId(FlightDateEmbed embeddedId) {
		this.embeddedId = embeddedId;
	}

	public Integer getSeatBooked() {
		return seatBooked;
	}

	public void setSeatBooked(Integer seatBooked) {
		this.seatBooked = seatBooked;
	}
	
}
