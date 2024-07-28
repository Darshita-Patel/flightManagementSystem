package com.teamAirlines.flightManagementSystem.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ticket {
	@Id
	private Long ticketNumber;
	private Long routeId;
	private Long flightNumber;
	private String carrierName;
	private Double totalAmount;
	private String date;
	
	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Ticket(Long ticketNumber, Long routeId, Long flightNumber, String carrierName, Double totalAmount,
			String date) {
		super();
		this.ticketNumber = ticketNumber;
		this.routeId = routeId;
		this.flightNumber = flightNumber;
		this.carrierName = carrierName;
		this.totalAmount = totalAmount;
		this.date = date;
	}

	public Long getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(Long ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public Long getRouteId() {
		return routeId;
	}
	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}
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
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
