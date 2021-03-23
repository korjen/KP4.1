<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"--%>
<%--"http://www.w3.org/TR/html4/loose.dtd">--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>500</title>
    <jsp:include page="../head.jsp"/>
    <link rel="stylesheet" type="text/css" href="
      <c:url value="/resources/css/home.css"/> "/>
</head>
<body>

<div class="container">
    <header>
        <h2 class="warning">Произошла внутренняя ошибка сервера</h2>
    </header>
    <div class="innerContainer">
        <aside>
        </aside>
        <div class="middle" align="center">
            <img src="<c:url value="/resources/img/500.png"/>"/>
        </div>
        <nav>
            <a class="link" href="<c:url value="/backToCurrentUser"/>">На гланую страницу</a>
        </nav>
    </div>
    <footer/>
</div>
</body>
</html>