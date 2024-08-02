<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>

<div class="pcoded-content">

    <!-- Page-header start -->
    <div class="page-header">
        <div class="page-block">
            <div class="row align-items-center">
                <div class="col-md-8">
                    <div class="page-header-title">
                        <h5 class="m-b-10">Flight Report of ${date}</h5>
                    </div>
                </div>
                
                <div class="col-md-4">
                    <ul class="breadcrumb">
                        <li class="breadcrumb-item">
                            <i class="fa fa-home"></i>
                        </li>
                        <li class="breadcrumb-item">Flights</li>
                        <li class="breadcrumb-item">View Flights Datewise</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- Page-header end -->

    <div class="pcoded-inner-content">
      
            <div class="page-wrapper">
                <div class="page-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <h5>All Flights</h5>
                                    <div class="card-header-right">
                                        <ul class="list-unstyled card-option">
                                            <li><i class="fa fa fa-wrench open-card-option"></i></li>
                                            <li><i class="fa fa-window-maximize full-card"></i></li>
                                            <li><i class="fa fa-minus minimize-card"></i></li>
                                            <li><i class="fa fa-refresh reload-card"></i></li>
                                            <li><i class="fa fa-trash close-card"></i></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="card-block table-border-style">
                                    <div class="table-responsive">
                                        <table class="table table-hover">
                                            <thead>
                <tr align="center">
					<th>Flight Number</th>
					<th>Carrier Name</th>
					<th>Seat Capacity</th>
					<th>Route Id</th>
					<th>Arrival Time</th>
					<th>Departure Time</th>
					<th>Seat Available</th>
				</tr>
            </thead>
                                            <tbody>
                <c:forEach var="flight" items="${flights}">
                    <tr align="center">
                        <td>${flight.flightNumber}</td>
						<td>${flight.carrierName}</td>
						<td>${flight.seatCapacity}</td>
						<td>${flight.routeId}</td>
						<td>${flight.arrivalTime}</td>
						<td>${flight.departureTime}</td>
						<c:set var="seatAvailable" scope="session" value="${flight.seatCapacity-flight.seatBooked}"/>
						<td>${seatAvailable}
                    </tr>
                </c:forEach>
            </tbody>
                                        </table>
                                    </div>
                                    <a href="/viewAllFlights" class="btn btn-secondary">Back</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                </div>
            </div>
        </div>
    </div>

<%@ include file="footer.jsp" %>