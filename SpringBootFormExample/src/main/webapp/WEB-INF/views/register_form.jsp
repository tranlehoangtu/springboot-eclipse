<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration Form</title>

<style type="text/css">
	label {
		display: inline-block;
		width: 200px;
		margin: 5px;
		text-align: left;
	}
	
	button {
		padding: 10px;
		margin: 10px;
	}
</style>
</head>
<body>
	<div align="center">
		<h2>User Registration</h2>
		<form:form action="register" method="post" modelAttribute="user">
			<form:label path="name">Full name:</form:label>
			<form:input path="name" /><br />
			
			<form:label path="email">E-mail:</form:label>
			<form:input path="email" /><br />
			
			<form:label path="password">Password:</form:label>
			<form:password path="password" /><br />
			
			<form:label path="profession">profession:</form:label>
			<form:select path="profession" items="${professionList}"/><br />
			
			<form:button>Register</form:button>
		</form:form>
	</div>
</body>
</html>