<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="header1.jsp" %>

<style>
    .bookingcontainer {
        width: 90%;
        margin: 20px auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    .header {
        text-align: center;
        margin-bottom: 20px;
    }
    .header h1, .header h2 {
        margin: 0;
        padding-bottom: 10px;
    }
    .details {
        display: grid;
        grid-template-columns: 120px 1fr 60px 1fr;
        column-gap: 10px;
        row-gap: 10px;
        margin-bottom: 10px;
    }
    .passenger-details {
        display: grid;
        grid-template-columns: 70px 1fr 120px 1fr;
        column-gap: 10px;
        row-gap: 10px;
        margin-bottom: 10px;
    }
    .details label, .passenger-details label {
        font-weight: bold;
        align-self: center;
        margin-right: 0px;
    }
    .details span, .details input {
        align-self: center;
    }
    .details span {
        margin-left: 0px; 
    }
    .details input {
        padding: 5px;
        border: 1px solid #ccc;
        border-radius: 4px;
        width: 150px;
        box-sizing: border-box;
    }
    .passenger-details input {
        padding: 5px;
        border: 1px solid #ccc;
        border-radius: 4px;
        width: 100%;
        box-sizing: border-box;
    }
    .journey-date {
        width: 120px;
    }
    
    .btn{
        display: inline-block;
    	color: #fff;
    	border: none;
    	border-radius: 4px;
    	text-decoration: none;
    	text-align: center;
    	margin: 10px 5px;
    	cursor: pointer;
    }

</style>

<section class="w3l-about-breadcrumb text-left">
    <div class="breadcrumb-bg breadcrumb-bg-about py-sm-5 py-4">
      <div class="container py-2">
        <h2 class="title">Book Flight</h2>
        <ul class="breadcrumbs-custom-path mt-2">
          <li><a href="#url">Home</a></li>
          <li class="active"><span class="fa fa-arrow-right mx-2" aria-hidden="true"></span>Available Flights</li>
          <li class="active"><span class="fa fa-arrow-right mx-2" aria-hidden="true"></span>Book Flight</li>
        </ul>
      </div>
    </div>
  </section>
<section class="w3l-contact" id="contact">
<div class="bookingcontainer">
    <div class="header">
        <h1>Book Your Flight</h1>
    </div>
    <form:form id="bookingForm" action="/confirmBooking" method="post" modelAttribute="ticketRecord" onsubmit="return combinedValidation()">
        <div class="details">
            <label>Ticket Number:</label>
            <span>${ticketRecord.ticketNumber}</span>
            <label>Fare:</label>
            <span><fmt:formatNumber value="${fare}" type="currency" currencySymbol="Rs " /></span>
            <label>Airlines Name:</label>
            <span>${ticketRecord.carrierName}</span>
            <label>From:</label>
            <span>${fromAirport}</span>
            <label>Flight Number:</label>
            <span>${ticketRecord.flightNumber}</span>
            <label>To:</label>
            <span>${toAirport}</span>
            <label>Journey Date:</label>
            <input type="date" name="journeyDate" value="<fmt:formatDate value='${journeyDate}' pattern='yyyy-MM-dd' />" required="true" class="journey-date" />
        </div>
        <div class="header">
            <h2>Enter Passenger Details</h2>
        </div>
        <div id="passengerDetailsContainer">
            <c:forEach var="i" begin="0" end="5">
                <div class="passenger-details">
                    <label>Name:</label>
                    <input type="text" name="passengerDetails[${i}].name" value="--" />
                    <label>Date of Birth:</label>
                    <input type="date" name="passengerDetails[${i}].dob" />
                </div>
            </c:forEach>
        </div>
        <form:input type="hidden" name="ticketNumber" path="ticketNumber" value="${ticket.ticketNumber}" />
        <form:input type="hidden" name="routeId" path="routeId" value="${ticket.routeId}" />
        <form:input type="hidden" name="carrierName" path="carrierName" value="${ticket.carrierName}" />
        <form:input type="hidden" name="flightNumber" path="flightNumber" value="${ticket.flightNumber}" />
        <input type="hidden" name="fromAirport" value="${fromAirport}" />
        <input type="hidden" name="toAirport" value="${toAirport}" />
        <form:input type="hidden" name="fare" path="totalAmount" value="${fare}" />
        <div class="form-row">
        <form action="/flightSearch" method="post">
        <input type="hidden" name="fromCity" value="${fromAirport}">
        <input type="hidden" name="toCity" value="${toAirport}">
        <button type="submit" class="btn btn-primary">Back to Flights</button>
  		</form> 
        <button type="submit" class="btn btn-secondary">Submit</button>
     </div>
	</form:form>
</div>
</section>
<script>
    function validateForm() {
        const container = document.getElementById('passengerDetailsContainer');
        const inputs = container.getElementsByTagName('input');
        let validEntryCount = 0;

        for (let i = 0; i < inputs.length; i += 2) {
            const name = inputs[i].value.trim();
            const dob = inputs[i + 1].value.trim();

            if (name && name.toUpperCase() !== "--") {
                if (dob) {
                    validEntryCount++;
                } else {
                    alert("Please provide Date of Birth for passenger: " + name);
                    return false;
                }
            }
        }

        if (validEntryCount === 0) {
            alert("Please provide details for at least one passenger.");
            return false;
        }

        return true;
    }

    function checkJourneyDate() {
    	const journeyDateInput = document.querySelector("input[name='journeyDate']");
        const selectedDate = journeyDateInput.value;
        const currentDate = new Date().toISOString().split('T')[0];

        if (selectedDate < currentDate) {
            alert("You cannot select a past date. Please choose a valid journey date.");
            return false;
        }

        if (selectedDate === currentDate) {
            return confirm("The journey date is set to the current date. Do you want to proceed?");
        }

        return true;
    }

    function combinedValidation() {
        return validateForm() && checkJourneyDate();
    }
</script>
<%@ include file="footer1.jsp" %>

