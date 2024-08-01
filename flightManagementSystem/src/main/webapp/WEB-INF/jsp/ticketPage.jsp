<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file = "header1.jsp" %>

<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f0f0f0;
    margin: 0;
    padding: 0;
}

.header {
    background-color: #00bfa5;
    color: white;
    padding: 20px;
    text-align: center;
}

.header h1 {
    margin: 0;
    font-size: 24px;
}

.ticketcontainer {
    width: 90%;
    margin: 20px auto;
    padding: 20px;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.card {
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    background-color: white;
    margin-bottom: 20px;
}

.card h2 {
    border-bottom: 1px solid #ddd;
    padding-bottom: 10px;
    margin-bottom: 20px;
    font-size: 20px;
    color: #333;
}

.table-info {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
    border: 1px solid #cfd8dc;
}

.table-info th, .table-info td {
    padding: 10px;
    border: 1px solid #cfd8dc;
    text-align: left;
    color: #000000; 
}

.table-info th {
    background-color: #1a237e; 
    color: #ffffff; 
    font-weight: bold;
}

.table-info tr:nth-child(even) {
    background-color: #e3f2fd;
}

.table-info tr:nth-child(odd) {
    background-color: #ffffff; 
}

.table-info .highlight {
    background-color: #f8bbd0;
    font-weight: bold;
}

.btn {
    display: inline-block;
    color: #fff;
    border: none;
    border-radius: 4px;
    text-decoration: none;
    text-align: center;
    margin: 10px 5px;
    cursor: pointer;
}

</style>
<section class="w3l-about-breadcrumb text-left">
    <div class="breadcrumb-bg breadcrumb-bg-about py-sm-5 py-4">
      <div class="container py-2">
        <h2 class="title">Confirmation</h2>
        <ul class="breadcrumbs-custom-path mt-2">
          <li><a href="#url">Home</a></li>
          <li class="active"><span class="fa fa-arrow-right mx-2" aria-hidden="true"></span>Available Flights</li>
          <li class="active"><span class="fa fa-arrow-right mx-2" aria-hidden="true"></span>Book Flight</li>
          <li class="active"><span class="fa fa-arrow-right mx-2" aria-hidden="true"></span>Ticket Confirmation</li>
        </ul>
      </div>
    </div>
  </section>
    <div class="ticketcontainer">
        <div class="card">
            <h2>Ticket Confirmation</h2>
            <table class="table-info">
                <tr>
                    <th>Ticket No:</th>
                    <td>${ticket.ticketNumber}</td>
                    <th>Airlines Name:</th>
                    <td>${ticket.carrierName}</td>
                    <th>Flight Number:</th>
                    <td>${ticket.flightNumber}</td>
                </tr>
                <tr>
                    <th>From:</th>
                    <td>${fromAirport}</td>
                    <th>To:</th>
                    <td>${toAirport}</td>
                    <th>Date:</th>
                    <td>${date}</td>
                </tr>
            </table>
        </div>
        <div class="card">
            <h2>Passenger Information</h2>
            <table class="table-info">
                <tr>
                    <th>Passenger Name</th>
                    <th>Date Of Birth</th>
                    <th>Fare</th>
                </tr>
                <c:forEach var="passenger" items="${passengers}">
                    <tr>
                        <td>${passenger.passengerName}</td>
                        <td>${passenger.passengerDOB}</td>
                        <td>${passenger.fare}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="2" style="font-weight: bold;">Total</td>
                    <td>${ticket.totalAmount}</td>
                </tr>
            </table>
        </div>
        <div class="buttons">
            <form action="/flightSearch" method="post" style="display:inline;">
                <input type="hidden" name="fromCity" value="${fromAirport}">
                <input type="hidden" name="toCity" value="${toAirport}">
                <button type="submit" class="btn btn-primary">Back to Flights</button>
            </form>
            <a href="/payment" class="btn btn-secondary">Proceed to Pay</a>
        </div>
    </div>

<%@ include file = "footer1.jsp" %>