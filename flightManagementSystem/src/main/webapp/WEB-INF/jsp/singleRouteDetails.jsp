<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="header.jsp" %>

<div class="pcoded-content">
    <!-- Page-header start -->
    <div class="page-header">
        <div class="page-block">
            <div class="row align-items-center">
                <div class="col-md-8">
                    <div class="page-header-title">
                        <h5 class="m-b-10">Update Route Details</h5>
                    </div>
                </div>
                <div class="col-md-4">
                    <ul class="breadcrumb">
                        <li class="breadcrumb-item">
                            <i class="fa fa-home"></i>
                        </li>
                        <li class="breadcrumb-item">Routes
                        </li>
                        <li class="breadcrumb-item">View All Routes
                        </li>
                        <li class="breadcrumb-item">Update Route Details
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    
    <div class="pcoded-inner-content">
        <!-- Main-body start -->
        <div class="main-body">
            <div class="page-wrapper">
                <div class="page-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <h5>Update Route Details</h5>
                                </div>
                                <div class="card-block">
                                    <form:form action="/saveRouteUpdate" method="post" modelAttribute="route"> 
                                        <div class="form-group form-default form-static-label">
                                            <label class="float-label">Route Id: ${route.routeId}</label>
                                            <span class="form-bar"></span> 
                                            <form:input type="hidden" name="routeId" value="${route.routeId}" path="routeId"/>     
                                        </div>
                                        <div class="form-group form-default form-static-label">
                                            <label class="float-label">Source Airport Code: ${route.sourceAirportCode}</label>
                                            <span class="form-bar"></span> 
                                            <form:input type="hidden" name="sourceAirportCode" value="${route.sourceAirportCode}" path="sourceAirportCode"/>     
                                        </div>
                                        <div class="form-group form-default form-static-label">
                                            <label class="float-label">Destination Airport Code: ${route.destinationAirportCode}</label>
                                            <span class="form-bar"></span> 
                                            <form:input type="hidden" name="destinationAirportCode" value="${route.destinationAirportCode}" path="destinationAirportCode"/>     
                                        </div>
                                        <div class="form-group form-default form-static-label">
                                            <label class="float-label">Enter Ticket Cost:</label>
    										<form:input type="number" name="ticketCost" value="${route.ticketCost}" class="form-control" path="ticketCost" required="true"/>
                                            <span class="form-bar"></span>                                           
                                        </div>
                                        <div class="form-group form-default form-static-label">
                                            <button type="submit" class="btn btn-primary waves-effect waves-light">Update</button>
    										<a href="/viewAllRoutes" class="btn btn-secondary">Back</a>
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