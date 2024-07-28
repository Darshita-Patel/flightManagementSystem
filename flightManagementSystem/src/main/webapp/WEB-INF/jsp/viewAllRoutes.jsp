<!-- %@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>View All Routes</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #00D9A5;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        h3 {
            color: #333;
        }
        a {
        	display: inline-block;
        	padding: 10px 20px;
        	border-radius: 5px;
        	background-color: #00D9A5;
        	color: #fff;
        	text-decoration: none;
        	font-size: 16px;
        	margin-top: 3px;
    	}
    	a:hover {
        	background-color: #009970;
    	}
    </style>
</head>
<body>
<div align="center">
<h3>Displaying All Routes Data</h3>
<table>
<thead>
<tr>
<th>Route Id</th>
<th>Source Airport Code</th>
<th>Department Airport Code</th>
<th>Ticket Cost</th>
<th>View Flights on this Route</th>
</tr>
</thead>
<tbody>
<c:forEach var="route" items="${list}">
<tr>
<td>${route.routeId}</td>
<td>${route.sourceAirportCode}</td>
<td>${route.destinationAirportCode}</td>
<td>${route.ticketCost}</td>
<td><a href="/viewFlightOnRoute/${route.routeId}">View</a></td>
</tr>
</c:forEach>
</tbody>
</table>
<a href="/start">Back</a>
</div>
</body>
</html -->
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
                        <h5 class="m-b-10">Route Report</h5>
                    </div>
                </div>
                
                <div class="col-md-4">
                    <ul class="breadcrumb">
                        <li class="breadcrumb-item">
                            <i class="fa fa-home"></i>
                        </li>
                        <li class="breadcrumb-item">Route</li>
                        <li class="breadcrumb-item">View All Routes</a></li>
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
                                    <h5>All Routes</h5>
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
                    <th>Route Id</th>
                    <th>Source Airport Code</th>
                    <th>Destination Airport Code</th>
                    <th>Ticket Cost</th>
                    <th>Update</th>
                </tr>
            </thead>
                                            <tbody>
                <c:forEach var="route" items="${list}">
                    <tr align="center">
                        <td>${route.routeId}</td>
                        <td>${route.sourceAirportCode}</td>
                        <td>${route.destinationAirportCode}</td>
                        <td>${route.ticketCost}</td>
                        <td><a href="/updateRoute/${route.routeId}" class="btn btn-primary waves-effect waves-light">Update</a></td>
                    </tr>
                </c:forEach>
            </tbody>
                                        </table>
                                    </div>
                                    <a href="/index" class="btn btn-secondary">Back</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                </div>
            </div>
        </div>
    </div>

<%@ include file="footer.jsp" %>