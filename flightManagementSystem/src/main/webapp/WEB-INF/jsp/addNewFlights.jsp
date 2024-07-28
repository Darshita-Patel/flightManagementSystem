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
                        <h5 class="m-b-10">New Flight Entry</h5>
                    </div>
                </div>
                <div class="col-md-4">
                    <ul class="breadcrumb">
                        <li class="breadcrumb-item">
                            <i class="fa fa-home"></i>
                        </li>
                        <li class="breadcrumb-item">Flights</li>
                        <li class="breadcrumb-item">Add New Flight</li>
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
                                    <h5>Add Flight</h5>
                                </div>
                                <div class="card-block">
                                    <form:form id="newFlightForm" action="/addFlights" class="form-material" method="post" modelAttribute="flightRecord">
                                        <div class="form-group row">
                                            <label for="flightNumber" class="col-sm-2 col-form-label">Enter Flight Id:</label>
                                            <div class="col-sm-4">
                                                <form:input type="text" id="flightNumber" path="flightNumber" class="form-control" placeholder="Enter Flight Id" required="true"/>
                                            </div>
                                            <label for="carrierName" class="col-sm-2 col-form-label">Enter Carrier Name:</label>
                                            <div class="col-sm-4">
                                                <form:input type="text" id="carrierName" path="carrierName" class="form-control" placeholder="Enter Airlines Name" required="true"/>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="routeId" class="col-sm-2 col-form-label">Select Route ID:</label>
                                            <div class="col-sm-4">
                                                <form:select path="routeId" id="routeId" class="form-control">
                                                    <option value="">Select Route ID:</option>
                                                    <c:forEach var="route" items="${routeList}">
                                                        <option value="${route}">${route}</option>
                                                    </c:forEach>
                                                </form:select>
                                            </div>
                                            <label for="seatCapacity" class="col-sm-2 col-form-label">Enter Seat Capacity:</label>
                                            <div class="col-sm-4">
                                                <form:input type="number" id="seatCapacity" path="seatCapacity" class="form-control" placeholder="Enter Seat Capacity" required="true"/>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="departure" class="col-sm-2 col-form-label">Enter Departure Time:</label>
                                            <div class="col-sm-4">
                                                <form:input type="time" id="departureTime" path="departureTime" name="departureTime" placeholder="Enter Departure Time"  class="form-control" required="true"/>
                                            </div>
                                            <label for="arrival" class="col-sm-2 col-form-label">Enter Arrival Time:</label>
                                            <div class="col-sm-4">
                                                <form:input type="time" id="arrivalTime" path="arrivalTime" name="arrivalTime" placeholder="Enter Arrival Time" class="form-control" required="true"/>
                                            </div>
                                        </div>
                                        <form:hidden path="seatBooked" value="0"/>
                                        <div class="form-group row">
                                            <label for="returnDeparture" class="col-sm-2 col-form-label">Enter Return Flight's Departure Time:</label>
                                            <div class="col-sm-4">
                                                <input type="time" id="dtime" name="dtime" class="form-control" placeholder="Enter Return Flight's Departure Time" required="true"/>
                                            </div>
                                            <label for="returnArrival" class="col-sm-2 col-form-label">Enter Return Flight's Arrival Time:</label>
                                            <div class="col-sm-4">
                                                <input type="time" id="atime" name="atime" class="form-control" placeholder="Enter Return Flight's Arrival Time"  required="true"/>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-sm-2"></div>
                                            <div class="col-sm-10">
                                                <button type="button" onclick="checkFields()" class="btn btn-primary waves-effect waves-light">Add</button>
                                                <button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
                                                <a href="/index" class="btn btn-secondary">Return</a>
                                            </div>
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
<script type="text/javascript">
function checkFields(){
    var flightId = document.getElementById("flightNumber").value;
    var carrierName = document.getElementById("carrierName").value;
    var seatCapacity = document.getElementById("seatCapacity").value;
    var routeId = document.getElementById("routeId").value;
    var arrivalTime = document.getElementById("arrivalTime").value;
    var departureTime = document.getElementById("departureTime").value;
    var aTime = document.getElementById("atime").value;
    var dTime = document.getElementById("dtime").value;

    if (flightId === ""){
        alert("Flight Number is required.");
        return false;
    } else if (carrierName === ""){
        alert("Carrier Name is required.");
        return false;
    } else if (seatCapacity === ""){
        alert("Seat Capacity is required.");
        return false;
    } else if (routeId === ""){
        alert("Route Id is required.");
        return false;
    } else if (arrivalTime === ""){
        alert("Arrival Time is required.");
        return false;
    } else if (departureTime === ""){
        alert("Departure Time is required.");
        return false;
    } else if (dTime === ""){
        alert("Return Flight's Departure Time is required.");
        return false;
    } else if (aTime === ""){
        alert("Return Flight's Arrival Time is required.");
        return false;
    } else if (departureTime === arrivalTime){
        alert("Arrival Time and Departure Time cannot be same.");
        return false;
    } else if (dTime === aTime){
        alert("Return Flight's Arrival Time and Departure Time cannot be same.");
        return false;
    } else if (new Date('1970-01-01T' + departureTime) >= new Date('1970-01-01T' + arrivalTime)){
        alert("Arrival Time should be after Departure Time.");
        return false;
    } else if (new Date('1970-01-01T' + dTime) >= new Date('1970-01-01T' + aTime)){
        alert("Arrival Time should be after Departure Time for Return Flight.");
        return false;
    } else {
        document.getElementById("newFlightForm").submit();
        alert("Flight Details Submitted Successfully");
        document.getElementById("newFlightForm").reset();
        return true;
    }
}

</script>
<%@ include file="footer.jsp" %>