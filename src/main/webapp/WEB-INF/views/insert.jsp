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


        <div id="logo_text">
          <h1><a href="">Кабинет <span class="logo_colour">УЗИ</span></a></h1><br>
          <h2>Врач:   ${loginDoc.fio}</h2>
        </div>
<form:form method="POST" commandName="newpass" action="up-pass" class="box login">
	<fieldset class="boxBody">
	  <label>Старый пароль</label>
	  <form:input path="oldpass" type="text" tabindex="1" placeholder="Логин"/>
	  <label>Пароль</label>
	  <form:password path="newpass1"  tabindex="2" placeholder="Пароль"/>
	  <label>Повторить пароль</label>
	  <form:password path="newpass2"  tabindex="3" placeholder="Повторить пароль"/>
	</fieldset>
	<footer>
	  <label class="error">${message}</label>
	  <input type="submit" class="btnLogin" value="Ввод" tabindex="4">
	</footer>
</form:form>


</body>
</html>