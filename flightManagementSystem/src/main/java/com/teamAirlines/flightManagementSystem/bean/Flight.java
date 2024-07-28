package com.teamAirlines.flightManagementSystem.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Flight {
	@Id
	private Long flightNumber;
	private String carrierName;
	private Long routeId;
	private Integer seatCapacity;
	private String departureTime;
	private String arrivalTime;
	private Integer seatBooked;
	
	public Flight() {
		super();
		this.seatBooked = 0;
	}

	public Flight(Long flightNumber, String carrierName, Long routeId, Integer seatCapacity, String departureTime,
			String arrivalTime) {
		super();
		this.flightNumber = flightNumber;
		this.carrierName = carrierName;
		this.routeId = routeId;
		this.seatCapacity = seatCapacity;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.seatBooked = 0;	}

	public Long getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(Long flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}

	public Integer getSeatCapacity() {
		return seatCapacity;
	}

	public void setSeatCapacity(Integer seatCapacity) {
		this.seatCapacity = seatCapacity;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Integer getSeatBooked() {
		return seatBooked;
	}

	public void setSeatBooked(Integer seatBooked) {
		this.seatBooked = seatBooked;
	}
	
}
