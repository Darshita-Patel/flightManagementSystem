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
                        <h5 class="m-b-10">Ticket Report</h5>
                    </div>
                </div>
                
                <div class="col-md-4">
                    <ul class="breadcrumb">
                        <li class="breadcrumb-item">
                            <i class="fa fa-home"></i>
                        </li>
                        <li class="breadcrumb-item">Tickets</li>
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
                                    <h5>All Tickets</h5>
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
                    <th>Ticket Number</th>
                    <th>Flight Number</th>
                    <th>Carrier Name</th>
                    <th>Route Id</th>
                    <th>Date</th>
                    <th>Total Amount</th>
                </tr>
            </thead>
                                            <tbody>
                <c:forEach var="ticket" items="${list}">
                    <tr align="center">
                        <td>${ticket.ticketNumber}</td>
                        <td>${ticket.flightNumber}</td>
                        <td>${ticket.carrierName}</td>
                        <td>${ticket.routeId}</td>
                        <td>${ticket.date}</td>
                        <td>${ticket.totalAmount}</td>
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