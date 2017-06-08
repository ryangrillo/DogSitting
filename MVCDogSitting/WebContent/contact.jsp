<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="master.css" >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contact Information</title>
</head>
<body>
<section class="navbar">
<div class="grid-row row">
<c:if test="${sessionScope.user.userName == null}">
<div class="col"><a class="button" href="welcomePage.do">Home</a></div>
<div class="col"><a class="button" href="loginPage.do">Login</a></div>
<div class="col"><a class="button" href="profilePage.do">Profile</a></div>
 </c:if>
 
 <c:if test="${sessionScope.user.userName != null}">
<div class="col"><a class="button" href="welcomePage.do">Home</a></div>
<div class="col"><a class="button" href="logout.do">Logout</a></div>
 <div class="col">Hi ${sessionScope.user.contact.firstName}!</div>
<div class="col"><a class="button" href="profilePage.do">Profile</a></div>
 </c:if>
  </div>
</section>
	<form:form action="createContact.do" method="POST" modelAttribute="contact">
		<label>First Name: <form:input path="firstName" /></label>
		<form:errors path="firstName" />
		<br />
		<label>Last Name: <form:input path="lastName" /></label>
		<form:errors path="lastName" />
		<br />
		
		<label>Email: <form:input path="email" /></label>
		<form:errors path="email" />
		<br />
		<label>Phone Number: <form:input path="phone" /></label>
		<form:errors path="phone" />
		<br />
		<label>Street Address: <form:input path="street" /></label>
		<br />
		<label>City: <form:input path="city" /></label>
		<br />
		<label>State: <form:input path="state" /></label>
		<br />
		<label>ZipCode: <form:input path="zipCode" /></label>
		<form:errors path="zipCode" />
		<br />
		<form:hidden path="id" value="${contactId}"/>
		<input type="submit" value="Register" />
	</form:form>
</body>
</html>