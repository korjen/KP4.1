<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a class="link" href="<c:url value="/admin/home"/>">На гланую страницу</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_MANAGER')">
                <a class="link" href="<c:url value="/manager/home"/>">На гланую страницу</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_WORKER')">
                <a class="link" href="<c:url value="/account/home"/>">На гланую страницу</a>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
                <a class="link" href="<c:url value="/"/>">На гланую страницу</a>
            </sec:authorize>
        </nav>
    </div>
    <footer/>
</div>
</body>
</html>