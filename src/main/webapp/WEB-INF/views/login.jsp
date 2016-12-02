<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>


<form:form method="POST" commandName="doctor" action="check-user" class="box login">
	<fieldset class="boxBody">
	  <label>Логин</label>
	  <form:input path="login" type="text" tabindex="1" placeholder="Логин"/>
	  <label>Пароль</label>
	  <form:password path="password"  tabindex="2" placeholder="Пароль"/>
	</fieldset>
	<footer>
	  <label class="error">${message}</label>
	  <input type="submit" class="btnLogin" value="Вход" tabindex="3">
	</footer>
</form:form>


</body>
</html>