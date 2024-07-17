<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ticket Confirmation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            width: 80%;
            margin: auto;
            overflow: hidden;
        }
        .header, .footer {
            background: #333;
            color: #fff;
            padding: 10px 0;
            text-align: center;
        }
        .header h1, .footer h1 {
            margin: 0;
            font-size: 24px;
        }
        .ticket-info, .passenger-info {
            background: #fff;
            padding: 20px;
            margin: 20px 0;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .ticket-info table, .passenger-info table {
            width: 100%;
            border-collapse: collapse;
        }
        .ticket-info th, .ticket-info td, .passenger-info th, .passenger-info td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        .ticket-info th, .passenger-info th {
            background: #333;
            color: #fff;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background: #333;
            color: #fff;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            margin: 20px 0;
        }
        .btn:hover {
            background: #555;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>Ticket Confirmation</h1>
    </div>
    <div class="container">
        <div class="ticket-info">
            <table>
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
        <div class="passenger-info">
            <table>
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
                    <td colspan="2">Total</td>
                    <td>${ticket.totalAmount}</td>
                </tr>
            </table>
        </div>
        <!-- Form to navigate back to the passenger details page -->
        <form action="/bookFlight" method="post">
            <input type="hidden" name="carrierName" value="${ticket.carrierName}">
            <input type="hidden" name="flightNumber" value="${ticket.flightNumber}">
            <input type="hidden" name="fromAirport" value="${fromAirport}">
            <input type="hidden" name="toAirport" value="${toAirport}">
            <button type="submit" class="btn">Back to Passenger Details</button>
        </form>
        <!-- Link to proceed to payment -->
        <a href="/payment" class="btn">Proceed to Pay</a>
    </div>
</body>
</html>