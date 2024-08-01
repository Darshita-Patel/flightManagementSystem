<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="header1.jsp" %>

<section class="w3l-about-breadcrumb text-left">
    <div class="breadcrumb-bg breadcrumb-bg-about py-sm-5 py-4">
      <div class="container py-2">
        <h2 class="title">Cancel Bookings</h2>
        <ul class="breadcrumbs-custom-path mt-2">
          <li><a href="#url">Home</a></li>
          <li class="active"><span class="fa fa-arrow-right mx-2" aria-hidden="true"></span>Ticket Cancellation</li>
        </ul>
      </div>
    </div>
  </section>

<section class="w3l-contact" id="contact">
    <div class="contact-infubd py-5">
      <div class="container py-lg-3">
        <div class="contact-grids row">
          <div class="col-lg-12 contact-left">
            <div class="partners">
              <div class="cont-details">
  
                <form action="/cancelBooking" method="post" class="signin-form">
                <label>Enter Ticket Number:</label>
        		<input type="number" id="ticketNumber" name="ticketNumber" class="contact-input" required>
                <div class="form-group form-default form-static-label">
                <a href="/index" class="btn btn-primary">Back</a>
                <button type="submit" class="btn btn-secondary">Search</button>                                       
                </div>
                </form>
<%@ include file="footer1.jsp" %>