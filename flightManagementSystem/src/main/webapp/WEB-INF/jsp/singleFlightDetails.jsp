<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
<div class="pcoded-content">
    <!-- Page-header start -->
    <div class="page-header">
        <div class="page-block">
            <div class="row align-items-center">
                <div class="col-md-8">
                    <div class="page-header-title">
                        <h5 class="m-b-10">Update Flight Details</h5>
                    </div>
                </div>
                <div class="col-md-4">
                    <ul class="breadcrumb">
                        <li class="breadcrumb-item">
                            <i class="fa fa-home"></i>
                        </li>
                        <li class="breadcrumb-item">Flights</li>
                        <li class="breadcrumb-item">View All Flights</li>
                        <li class="breadcrumb-item">Update Flight Details</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- Page-header end -->
    <div class="pcoded-inner-content">
        <!-- Main-body start -->
        <div class="main-body">
            <div class="page-wrapper">
                <div class="page-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <h5>Update Flight Details</h5>
                                </div>
                                <div class="card-block">
                                    <form:form action="/saveFlightUpdate" method="post" modelAttribute="flight">
                                        <div class="form-group form-default form-static-label">
                                        <label for="flightNumber" class="float-label">Flight Number: ${flight.flightNumber}</label>
                                        <form:input type="hidden" name="flightNumber" value="${flight.flightNumber}" path="flightNumber"/>
                                           <span class="form-bar"></span> 
                                           </div>
                                           <div class="form-group form-default form-static-label">
                                            <label for="carrierName" class="float-label">Carrier Name: ${flight.carrierName}</label>
                                            <form:input type="hidden" name="carrierName" value="${flight.carrierName}" path="carrierName"/>
                                            <span class="form-bar"></span> 
                                            </div>
                                            <div class="form-group form-default form-static-label">
                                        	<label for="routeId" class="float-label">Route Id: ${flight.routeId}</label>
                                        	<form:input type="hidden" name="routeId" value="${flight.routeId}" path="routeId"/>
                                            <span class="form-bar"></span>
                                            </div>
                                            <div class="form-group form-default form-static-label"> 
                                            <label for="seatCapacity" class="float-label">Seat Capacity: ${flight.seatCapacity}</label>
                                            <form:input type="hidden" name="seatCapacity" value="${flight.seatCapacity}" path="seatCapacity"/>
                                            <span class="form-bar"></span> 
                                            </div>
                                            <div class="form-group form-default form-static-label">
                                        	<label for="departure" class="float-label">Enter Departure Time:</label>
                                        	<span class="form-bar"></span> 
    										<form:input type="time" id="departureTime" path="departureTime" name="departureTime" value="${flight.departureTime}" class="form-control" required="true"/>
    										</div>
    										<div class="form-group form-default form-static-label">
    										<label for="arrival" class="float-label">Enter Arrival Time:</label>
    										<span class="form-bar"></span>
    										<form:input type="time" id="arrivalTime" path="arrivalTime" name="arrivalTime" value="${flight.arrivalTime}" class="form-control" required="true"/>
    										</div>
    										<form:hidden path="seatBooked" value="${flight.seatBooked}"/>
                                       
                                        <div class="form-group form-default form-static-label"> 
    									<button type="submit" class="btn btn-primary waves-effect waves-light">Update</button>
    									<a href="/updateFlights" class="btn btn-secondary">Back</a>
										</div>
									</form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
