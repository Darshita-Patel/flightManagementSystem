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

.container {
    width: 79%;
    margin: 80px 0px 20px 250px;
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
}

.table-info th, .table-info td {
    padding: 10px;
    border: 1px solid #8c8787;
    text-align: left;
}

.table-info th {
    background-color: #f7f7f7;
    font-weight: bold;
}

.btn {
    display: inline-block;
    padding: 10px 20px;
    color: #fff;
    background-color: #007bff;
    border: none;
    border-radius: 4px;
    text-decoration: none;
    text-align: center;
    margin: 10px 5px;
    cursor: pointer;
}

.btn:hover {
    background-color: #0056b3;
}
</style>

    <div class="container">
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
                <button type="submit" class="btn">Back to Flights</button>
            </form>
            <a href="/payment" class="btn">Proceed to Pay</a>
        </div>
    </div>

<%@ include file = "footer.jsp" %>