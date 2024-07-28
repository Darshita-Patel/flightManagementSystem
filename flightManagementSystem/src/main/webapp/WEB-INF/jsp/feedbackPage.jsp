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
                        <h5 class="m-b-10">Feedback Form</h5>
                    </div>
                </div>
                <div class="col-md-4">
                    <ul class="breadcrumb">
                        <li class="breadcrumb-item">
                            <i class="fa fa-home"></i>
                        </li>
                        <li class="breadcrumb-item">Feedback
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
                                    <h5>Feedback</h5>
                                </div>
                                <div class="card-block">
                                    <form:form id="feedbackForm" action="/saveFeedback" method="post" modelAttribute="feedback"> 
                                        <div class="form-group form-default form-static-label">
                                            <label class="float-label">Enter Your Username:</label>
                                            <span class="form-bar"></span> 
                                            <form:input type="text" id="username" path="username" class="form-control" placeholder="Enter Your Username" required="true"/>                                                                                       
                                        </div>
                                        <div class="form-group form-default form-static-label">
                                            <label class="float-label">Type Your Feedback/Remarks:</label>
                                            <form:textarea rows="5" cols="50" path="content" class="form-control" placeholder="Type Your Message" required="true"/>
                                            <span class="form-bar"></span>                                           
                                        </div>
                                        <div class="form-group form-default form-static-label">
                                            <button type="submit" class="btn btn-primary waves-effect waves-light">Submit</button>
                                            <a href="/index" class="btn btn-secondary">Return</a>
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