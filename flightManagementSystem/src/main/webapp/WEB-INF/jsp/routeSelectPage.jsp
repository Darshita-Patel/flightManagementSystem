<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="header1.jsp" %>
<div class="pcoded-content">
    <!-- Page-header start -->
    <div class="page-header">
        <div class="page-block">
            <div class="row align-items-center">
                <div class="col-md-8">
                    <div class="page-header-title">
                        <h5 class="m-b-10">Book Flight</h5>
                    </div>
                </div>
                <div class="col-md-4">
                    <ul class="breadcrumb">
                        <li class="breadcrumb-item">
                            <i class="fa fa-home"></i>
                        </li>
                        <li class="breadcrumb-item">Book Flight
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
                                    <h5>Select Route</h5>
                                </div>
                                <div class="card-block">
                                    <form:form id="flightBooking" action="/flightSearch" method="post" class="form-material"> 
									    <div class="form-group form-default form-static-label">
									    <label for="fromCity">Select Source Airport City:</label>
									    <span class="form-bar"></span>
									    <select id="fromCity" name="fromCity" class="form-control">
									            <option value="" disabled selected>Select Airport City</option>
									            <c:forEach var="location" items="${locationList}">
									                <option value="${location}">${location}</option>
									            </c:forEach>
									     </select>
									     </div>
									    <div class="form-group form-default form-static-label">
									    <label for="toCity">Select Destination Airport City:</label>
									    <span class="form-bar"></span>
									    <select id="toCity" name="toCity" class="form-control">
									            <option value="" disabled selected>Select Airport City</option>
									            <c:forEach var="location" items="${locationList}">
									                <option value="${location}">${location}</option>
									            </c:forEach>
									     </select>
									     </div>	
                                        <div class="form-group form-default form-static-label">
                                            <button type="submit" class="btn btn-primary waves-effect waves-light">Search</button>
                                            <button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
                                        </div>
                                    </form:form>
                                    <a href="/index" class="btn btn-secondary">Return</a>
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