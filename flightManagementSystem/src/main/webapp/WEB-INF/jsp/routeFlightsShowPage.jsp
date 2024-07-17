<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Flight Search Results</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        .flight-card {
            border: 1px solid #ccc;
            border-radius: 8px;
            padding: 16px;
            margin: 16px 0;
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 80%;
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
        }
        .flight-time {
            margin: 8px 0;
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
            margin-right: 25px;
        }
        .book-btn {
            background-color: #4CAF50;
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
        }
    </style>
</head>
<body>
    <h1>Available Flights from ${fromAirport} to ${toAirport}</h1>
    <c:forEach var="flight" items="${flightList}">
        <form action="/bookFlight" method="post" class="flight-card">
            <div class="flight-info">
                <div class="flight-carrier">${flight.carrierName}</div>
                <div class="flight-number">${flight.flightNumber}</div>
                <input type="hidden" name="carrierName" value="${flight.carrierName}" />
                <input type="hidden" name="flightNumber" value="${flight.flightNumber}" />
            </div>
            <div class="flight-info">
                <div class="flight-time">${flight.departureTime}</div>
                <div class="airport-city">${fromAirport}</div>
                <input type="hidden" name="departureTime" value="${flight.departureTime}" />
                <input type="hidden" name="fromAirport" value="${fromAirport}" />
            </div>
            <div class="flight-info">
                <div class="trip-duration">${flightDurations[flight.flightNumber]}</div>
                <input type="hidden" name="tripDuration" value="${flightDurations[flight.flightNumber]}" />
            </div>
            <div class="flight-info">
                <div class="flight-time">${flight.arrivalTime}</div>
                <div class="airport-city">${toAirport}</div>
                <input type="hidden" name="arrivalTime" value="${flight.arrivalTime}" />
                <input type="hidden" name="toAirport" value="${toAirport}" />
            </div>
            <div class="price-book-container">
                <div class="price">Rs. ${ticketCost}</div>
                <input type="hidden" name="ticketCost" value="${ticketCost}" />
                <button type="submit" class="book-btn">Book</button>
            </div>
        </form>
    </c:forEach>
    <a href="/flightSearch">Back</a>
</body>
</html>

