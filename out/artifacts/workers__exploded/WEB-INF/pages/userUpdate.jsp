<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>Редактирование профиля сотрудника</title>
    <jsp:include page="head.jsp"/>
    <link rel="stylesheet" type="text/css" href="
      <c:url value="/resources/css/profile.css"/> "/>
</head>
<body>
<script>
    function validation() {
        var x = document.forms["editForm"]["password"].value;
        if (x != null && x != "") {
            if (x.length < 6 || x.length > 16) {
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
        return true;
    }
</script>
<div class="container">
    <header>
    </header>
    <div class="innerContainer">
        <aside></aside>
        <div class="middle" align="center">
            <form:form name="editForm" modelAttribute="user" method="post" action="update/${user.login}" onsubmit="return validation()">
                <br/> <br/>
                <table id="list">
                    <tr>
                        <td class="title" align="center">Логин</td>
                        <td class="cell" align="center">${user.login}</td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Новый пароль</td>
                        <td class="cell" align="center"><form:password path="password" /></td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Фамилия</td>
                        <td class="cell" align="center">${user.worker.passport.surname}</td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Имя</td>
                        <td class="cell" align="center">${user.worker.passport.name}</td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Отчество</td>
                        <td class="cell" align="center">${user.worker.passport.patronymic}</td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Должность</td>
                        <td class="cell" align="center">${user.worker.contract.position}</td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Права</td>
                        <c:choose>
                            <c:when test="${user.role=='ROLE_ADMIN'}">
                                <td class="cell" align="center">администратор</td>
                            </c:when>
                            <c:when test="${user.role=='ROLE_MANAGER'}">
                                <td class="cell" align="center">менеджер</td>
                            </c:when>
                            <c:when test="${user.role=='ROLE_WORKER'}">
                                <td class="cell" align="center">сотрудник</td>
                            </c:when>
                        </c:choose>
                    </tr>
                </table>
            <br/> <br/>
                <h4>Изменить права доступа</h4><br/>
                <form:select path="role">
                    <form:option value="ROLE_ADMIN" label="администратор" />
                    <form:option value="ROLE_MANAGER" label="менеджер" />
                    <form:option value="ROLE_WORKER" label="сотрудник" />
                </form:select>
                <br/> <br/>
                <input type="submit" value="Подтвердить" id="submit">
            </form:form>
                <%--<br/> <br/>--%>
            <%--<button class="button"><a class="link" href="<c:url value="update/${user.login}/${user.role}"/>">Подтвердить</a></button>--%>
        </div>
        <nav><a class="link" href="<c:url value="/admin/users/list"/>">Назад</a></nav>
    </div>
    <footer>

    </footer>
</div>
</body>
</html>
