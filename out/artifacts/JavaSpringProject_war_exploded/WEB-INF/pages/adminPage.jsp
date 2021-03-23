<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <head>
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
        <title>Домашняя страница администратора</title>
        <jsp:include page="head.jsp"/>
        <link rel="stylesheet" type="text/css" href="
      <c:url value="/resources/css/home.css"/> "/>
    </head>
</head>
<body>

<div class="container">
    <header>
        <br/>
        <h2 class="message">${greeting}</h2>
        <h2 class="warning">${warning}</h2>
    </header>
    <div class="innerContainer">
        <aside></aside>
        <div class="middle" align="center">
        </div>
        <nav>
            <a class="link" href="<c:url value="/admin/userProfile"/>">Профиль</a>
                <a class="link" href="<c:url value="/admin/workingTime"/>">Рабочее время</a>
            <a class="link" href="<c:url value="/admin/paymentHistory"/>">Зарплата</a>
            <a class="link" href="<c:url value="/admin/workers/list"/>">Список сотрудников</a>
                <a class="link" href="<c:url value="/admin/users/list"/>">Список пользователей</a>
            <a class="link" href="<c:url value="/admin/workersPaymentList"/>">Расчет зарплаты</a>
            <a class="link" href="<c:url value="/exit"/>">Выход</a>
        </nav>
    </div>
    <footer/>
</div>
</body>
</html>
