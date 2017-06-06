<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<p>Please login or create an account to continue</p>
<body>
	<form:form action="login.do" method="POST" modelAttribute="user">
		<form:label path="userName">User Name:</form:label>
		<form:input path="userName" />
		<form:errors path="userName" />
		<br />
		<form:label path="password">Password:</form:label>
		<form:input path="password" />
		<form:errors path="password" />
		<input type="hidden" value="${sitter.id}" name ="sitterId"/>
		<input type="submit" value="Login" />
	</form:form>
	
	<a href="goToCreateUser.do">Create New Account</a>
	
</body>
</html>