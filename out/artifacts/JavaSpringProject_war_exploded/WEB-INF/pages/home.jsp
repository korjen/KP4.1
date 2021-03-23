<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"--%>
<%--"http://www.w3.org/TR/html4/loose.dtd">--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Начальная страница</title>
    <jsp:include page="head.jsp"/>
    <link rel="stylesheet" type="text/css" href="
      <c:url value="/resources/css/home.css"/> "/>
</head>
<body>

<div class="container">
    <header>

    </header>
    <div class="innerContainer">
         <aside>
         </aside>
        <div class="middle" align="center">
        </div>
        <nav>
            <a class="link" href="<c:url value="/login"/>">Авторизация</a>
            <a class="link" href="<c:url value="/register"/>">Регистрация</a>
        </nav>
    </div>
    <footer/>
</div>
</body>
</html>