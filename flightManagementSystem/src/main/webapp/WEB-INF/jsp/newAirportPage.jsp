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
                        <h5 class="m-b-10">New Airport Registry</h5>
                    </div>
                </div>
                <div class="col-md-4">
                    <ul class="breadcrumb">
                        <li class="breadcrumb-item">
                            <i class="fa fa-home"></i>
                        </li>
                        <li class="breadcrumb-item">Airport
                        </li>
                        <li class="breadcrumb-item">Add New Airport
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
                                    <h5>Add Airport</h5>
                                </div>
                                <div class="card-block">
                                    <form:form id="newAirportRegistrationForm" action="/newAirport" method="post" modelAttribute="airport_data"> 
                                        <div class="form-group form-default form-static-label">
                                            <label class="float-label">Enter Airport Code:</label>
                                            <span class="form-bar"></span> 
                                            <form:input type="text" id="airportCode" path="airportCode" class="form-control" placeholder="Enter Airport Code" required="true"/>                                                                                       
                                        </div>
                                        <div class="form-group form-default form-static-label">
                                            <label class="float-label">Enter Airport Location:</label>
                                            <form:input type="text" id="airportLocation" path="airportLocation" class="form-control" placeholder="Enter Airport Location" required="true"/>
                                            <span class="form-bar"></span>                                           
                                        </div>
                                        <div class="form-group form-default form-static-label">
                                            <button type="submit" class="btn btn-primary waves-effect waves-light">Add</button>
                                            <button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
                                        </div>
                                    </form:form>
                                    <a href="/index" class="btn btn-secondary">Back</a>
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