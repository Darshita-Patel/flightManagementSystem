package com.teamAirlines.flightManagementSystem.bean;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class FlightDateEmbed implements Serializable{
	
	@NotNull
	private Long flightNumber;
	
	@NotNull
	private String date;
	
	
	public FlightDateEmbed() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FlightDateEmbed(@NotNull Long flightNumber, @NotNull String date) {
		super();
		this.flightNumber = flightNumber;
		this.date = date;
	}
	
	public Long getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(Long flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, flightNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlightDateEmbed other = (FlightDateEmbed) obj;
		return Objects.equals(date, other.date) && Objects.equals(flightNumber, other.flightNumber);
	}
	
}
