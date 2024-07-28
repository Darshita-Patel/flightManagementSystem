<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="header1.jsp" %>

<style>

    .main-container {
        width:78%;
        margin: 100px 40px 20px 260px;
        max-width: 1200px;
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    h1 {
        text-align: center;
        margin-bottom: 20px;
        color: #333;
    }

    .flight-card {
        border: 1px solid #ccc;
        border-radius: 8px;
        padding: 20px;
        margin: 16px 0;
        display: flex;
        justify-content: space-between;
        align-items: center;
        width: 100%;
        background-color: #fff;
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
    }

    .flight-info {
        display: flex;
        flex-direction: column;
        align-items: center;
        flex: 1;
        text-align: center;
        min-width: 150px;
    }

    .flight-carrier {
        font-weight: bold;
        color: #007bff;
        font-size: 16px;
    }

    .flight-number,
    .flight-time,
    .trip-duration,
    .airport-city {
        margin: 8px 0;
        color: #555;
    }

    .price-book-container {
        display: flex;
        align-items: center;
        justify-content: center;
        min-width: 200px;
    }

    .price {
        font-size: 18px;
        font-weight: bold;
        color: #448aff;
        margin-right: 25px;
    }

    .book-btn {
        background-color: #448aff;
        color: white;
        padding: 10px 24px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px 2px;
        cursor: pointer;
        border: none;
        border-radius: 8px;
        transition: background-color 0.3s;
    }

    .book-btn:hover {
        background-color: #45a049;
    }

</style>

<div class="main-container">
    <h1>Available Flights from ${fromAirport} to ${toAirport}</h1>
    <c:forEach var="flight" items="${flightList}">
        <form action="/bookFlight" method="post" class="flight-card">
            <div class="flight-info">
                <div class="flight-carrier">${flight.carrierName}</div>
                <div class="flight-number">Flight No: ${flight.flightNumber}</div>
                <input type="hidden" name="carrierName" value="${flight.carrierName}" />
                <input type="hidden" name="flightNumber" value="${flight.flightNumber}" />
            </div>
            <div class="flight-info">
                <div class="flight-time">${flight.departureTime}</div>
                <div class="airport-city">Departure: ${fromAirport}</div>
                <input type="hidden" name="departureTime" value="${flight.departureTime}" />
                <input type="hidden" name="fromAirport" value="${fromAirport}" />
            </div>
            <div class="flight-info">
                <div class="trip-duration">Duration: ${flightDurations[flight.flightNumber]}</div>
                <input type="hidden" name="tripDuration" value="${flightDurations[flight.flightNumber]}" />
            </div>
            <div class="flight-info">
                <div class="flight-time">${flight.arrivalTime}</div>
                <div class="airport-city">Arrival: ${toAirport}</div>
                <input type="hidden" name="arrivalTime" value="${flight.arrivalTime}" />
                <input type="hidden" name="toAirport" value="${toAirport}" />
            </div>
            <div class="price-book-container">
                <div class="price">Rs. ${ticketCost}</div>
                <input type="hidden" name="ticketCost" value="${ticketCost}" />
                <button type="submit" class="btn btn-primary waves-effect waves-light">Book</button>
            </div>
        </form>
    </c:forEach>
    <a href="/flightSearch" class="btn btn-secondary">Back</a>
</div>

<%@ include file="footer.jsp" %>
