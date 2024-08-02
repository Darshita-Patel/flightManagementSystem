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
                        <h5 class="m-b-10">New Route Registry</h5>
                    </div>
                </div>
                <div class="col-md-4">
                    <ul class="breadcrumb">
                        <li class="breadcrumb-item">
                            <i class="fa fa-home"></i>
                        </li>
                        <li class="breadcrumb-item">Route
                        </li>
                        <li class="breadcrumb-item">Add New Route
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
                                    <h5>Add Route</h5>
                                </div>
                                <div class="card-block">
                                    <form:form id="newRouteForm" action="/addRoute" class="form-material" method="post" modelAttribute="routeRecord">
                                        <form:hidden path="routeId"/>
                                        <div class="form-group form-default form-static-label">
                                            <!-- form:input type="text" id="sourceAirportCode" path="sourceAirportCode" class="form-control" placeholder="Enter Source Airport Code" required="true"/-->
                                            <label>Select Source Airport City:</label>
                                            <span class="form-bar"></span>
                                            <form:select path="sourceAirportCode" id="sourceAirportCode" class="form-control">
            								<option value="" disabled selected>Select Source Airport City</option>
            								<c:forEach var="location" items="${locationList}">
                							<option value="${location}">${location}</option>
            								</c:forEach>
     										</form:select>
                                        </div>
                                        <div class="form-group form-default form-static-label">
                                            <!-- form:input type="text" id="destinationAirportCode" path="destinationAirportCode" class="form-control" placeholder="Enter Destination Airport Code" required="true"/-->
                                            <label>Select Destination Airport City:</label>
                                            <span class="form-bar"></span>
                                            <form:select path="destinationAirportCode" id="destinationAirportCode" class="form-control">
            								<option value="" disabled selected>Select Destination Airport City</option>
            								<c:forEach var="location" items="${locationList}">
                								<option value="${location}">${location}</option>
            								</c:forEach>
     										</form:select>
                                        </div>
                                        <div class="form-group form-default form-static-label">
                                        	<label>Enter Ticket Cost:</label> 
                                        	<span class="form-bar"></span>
                                        	<form:input type="number" path="ticketCost" id="ticketCost" class="form-control" placeholder="Enter Ticket Cost" required = "true"/>					
    									</div>	
                                        <div class="form-group form-default form-static-label">
                                            <button type="button" onclick="checkFields()" class="btn btn-primary waves-effect waves-light">Add</button>
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
<script type="text/javascript">
function checkFields(){
	var sourceAirportCode = document.getElementById("sourceAirportCode").value;
	var destinationAirportCode = document.getElementById("destinationAirportCode").value;
	var ticketCost = document.getElementById("ticketCost").value;

    if(sourceAirportCode === ""){
    	alert("Source Airport City is required.");
    }
    else if(destinationAirportCode === ""){
    	alert("Destination Airport Ciy is required.");
    }
    else if(ticketCost === ""){
    	alert("Ticket Cost is required.");
    }
    else if (destinationAirportCode === sourceAirportCode){
		alert("Source Airport City and Destination Airport City cannot be same.");
		return false;
	} else {
		document.getElementById("newRouteForm").submit();
		alert("Route Details Submited Successfully");
		document.getElementById("newRouteForm").reset();
		return true;
	}
}

</script>
<%@ include file="footer.jsp" %>