<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"--%>
<%--"http://www.w3.org/TR/html4/loose.dtd">--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>Регистрация</title>
    <jsp:include page="head.jsp"/>
    <link rel="stylesheet" type="text/css" href="
      <c:url value="/resources/css/login.css"/> "/>
</head>
<body>
<script>
    function validation() {
        var x = document.forms["registerForm"]["worker.contract.contractNumber"].value;
        if (x == null || x == "") {
            alert("Номер договора не был введен");
            return false;
        }
        x = document.forms["registerForm"]["login"].value;
        var numbers = /^[0-9][0-9\s]*$/;
        if (x == null || x == "") {
            alert("Логин не был введен");
            return false;
        }
        else if (x.length < 4 || x.length > 16) {
            alert("Логин должен содержать от 4 до 16 символов");
            return false;
        }
        x = document.forms["registerForm"]["password"].value;
        if (x == null || x == "") {
            alert("Пароль не был введен");
            return false;
        }
        else if (x.length < 6 || x.length > 16) {
            alert("Пароль должен содержать от 6 до 16 символов");
            return false;
        }
        else if (!(x.match(/[a-z]/g) && x.match(/[A-Z]/g)
            && x.match(/[0-9]/g))){
            alert("Пароль должен содержать цифры и буквы нижнего и верхнего регистров");
            return false
        }
        return true;
    }
</script>
<div class="container">
    <header>
    </header>
    <div class="innerContainer">
        <aside></aside>
        <div class="middle" align="center">
            <form:form name="registerForm" action="checkRegister" method="post" modelAttribute="user" onsubmit="return validation()">
                <h4>Номер трудового договора:</h4> <form:input type="text" path="worker.contract.contractNumber" />
                <h4>Логин:</h4> <form:input type="text" path="login" />
                <h4>Пароль:</h4> <form:password path="password" />
                <br/> <br/>
                <c:if test="${not empty error}">
                    <span class="input-error" align="center" class="error">${error}</span>
                </c:if>
                <br/>
                <br/>
                <input type="submit" value="Зарегистрироваться">
                <br/>
                <br/>
            </form:form>
        </div>
        <nav><a class="link" href="<c:url value="/"/>">Назад</a></nav>
    </div>
    <footer/>
</div>
</body>
</html>