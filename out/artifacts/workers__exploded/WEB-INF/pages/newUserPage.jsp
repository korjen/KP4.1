<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>Регистрация нового пользователя</title>
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
            <form:form name="registerForm" action="addUser" method="post" modelAttribute="user" onsubmit="return validation()">
                <h4>Номер трудового договора:</h4> <form:input type="number" path="worker.contract.contractNumber" />
                <h4>Логин:</h4> <form:input type="text" path="login" />
                <h4>Пароль:</h4> <form:password path="password" />
                <h4>Права:</h4>
                <form:select path="role">
                    <form:option value="ROLE_ADMIN" label="администратор" />
                    <form:option value="ROLE_MANAGER" label="менеджер" />
                    <form:option value="ROLE_WORKER" label="сотрудник" />
                </form:select>
                <br/>
                <br/>
                <c:if test="${not empty error}">
                    <span class="input-error" align="center" class="error">${error}</span>
                </c:if>
                <br/>
                <input type="submit" value="Добавить">
                <br/>
                <br/>
            </form:form>
        </div>
        <nav> <a class="link" href="<c:url value="/admin/users/list"/>">Назад</a></nav>
        <footer/>
    </div>
</div>
</body>
</html>