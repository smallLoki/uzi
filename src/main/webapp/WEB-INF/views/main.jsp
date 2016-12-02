<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" type="text/javascript" src="<c:url value="/resources/js/script.js" />"></script>
<script language="javascript" type="text/javascript" src="<c:url value="/resources/js/jquery-3.1.1.min.js" />"></script>
<title>Insert title here</title>
</head>

<body>
<div id="main">
    <div id="header">
      <div id="logo">
        <div id="logo_text">
          <h1><a href="">Кабинет <span class="logo_colour">УЗИ</span></a></h1><br>
          <h2>Врач:   ${loginDoc.fio}</h2>
        </div>
      </div>
      <div id="menubar" style="dasplay: ${ADM? 'none': 'block'}">
        <ul id="menu">
          <li id="tag1" class="${TAB==1?'selected':''}" onclick="goTags(1)"><a>Список пациентов</a></li>
          <li id="tag2" class="${TAB==2?'selected':''}" onclick="goTags(2)"><a>Диагнозы</a></li>
          <li id="tag3" class="${TAB==3?'selected':''}" onclick="goTags(3)"><a>Статистика</a></li>
          <li id="tag4" class="${TAB==4?'selected':''}" onclick="goTags(4)"><a>Сменя пароля</a></li>
          <li><a href="login">Выход</a></li>
        </ul>
      </div>
      <div id="menubar" style="dasplay: ${!ADM? 'none': 'block'}">
        <ul id="menu">
          <li id="tag1" class="${TAB==1?'selected':''}" onclick="goTags(1)"><a>Список врачей</a></li>
          <li id="tag2" class="${TAB==2?'selected':''}" onclick="goTags(2)"><a>Список пациентов</a></li>
          <li id="tag3" class="${TAB==3?'selected':''}" onclick="goTags(3)"><a>Статистика</a></li>
          <li id="tag4" class="${TAB==4?'selected':''}" onclick="goTags(4)"><a>Сменя пароля</a></li>
          <li><a href="login">Выход</a></li>
        </ul>
      </div>
    </div>
    <div id="site_content">
      
        
        
		<div id="tab">
			<h2>${welcom}</h2>
		</div>
		<div id="tab1" hidden>
			<h2>Список больных</h2>
        	<p>${T1}</p>
        	<table style="width:100%; border-spacing:0;">
          		<tr><th style="width:10%;">Номер</th><th style="width:50%;">ФИО</th><th style="width:20%;">Дата рождения</th><th style="width:20%;">Действие</th></tr>
          		<c:forEach items="${sick}" var="elem" varStatus="varStatus">
					<tr><td>${varStatus.index+1}</td><td>${elem.fio}</td><td>${elem.hb}</td><td>Спиок диагнозов, Поставить диагноз</td></tr>
				</c:forEach>
        	</table>
		</div>
		<div id="tab2" hidden>
			<h2>Все диагнозы</h2>
        	<p>${T2}</p>
			<table style="width:100%; border-spacing:0;">
			  	<tr>
			  		<th style="width:7%;">Номер</th>
			  		<th style="width:15%;">ФИО пациент</th>
			  		<th style="width:15%;">ФИО Врача</th>
			  		<th style="width:50%;">Диагноз</th>
			  		<th style="width:10%;">Дата</th></tr>
			  	<c:forEach items="${diagn}" var="elem" varStatus="varStatus">
					<tr>
						<td>${varStatus.index+1}</td>
						<td>${elem.sick}</td>
						<td>${elem.doctor}</td>
						<td>${elem.diagnosis}</td>
						<td>${elem.date}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div id="tab3" hidden>
			<h2>Личная статистика</h2>
		</div>
		<div id="tab4" hidden>
			<h2>Смена пароля</h2>
			<p><span>${MES}</span></p>
			
		</div>
    </div>
    <div id="content_footer"></div>
    <div id="footer">
		Приложение разработал: Стариков Никита Андреевич
    </div>
  </div>

</body>
</html>
