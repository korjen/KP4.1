<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>Профиль текущего пользователя</title>
    <jsp:include page="head.jsp"/>
    <link rel="stylesheet" type="text/css" href="
      <c:url value="/resources/css/profile.css"/> "/>
</head>
<body>
<script>
    function validation() {
        var x = document.forms["changePasswordForm"]["password"].value;
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
            <form:form modelAttribute="user">
                <br/> <br/>
                <table id="list">
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
                        <td class="title" align="center">Пол</td>
                        <td class="cell" align="center">${user.worker.passport.gender}</td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Дата рождения</td>
                        <td class="cell" align="center">${user.worker.passport.birthDate}</td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Количество иждивенцев</td>
                        <td class="cell" align="center">${user.worker.numberOfDependants}</td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Одинокий опекун</td>
                        <c:choose>
                            <c:when test="${worker.soloParent==true}">
                                <td class="cell" align="center">да</td>
                            </c:when>
                            <c:otherwise>
                                <td class="cell" align="center">нет</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>
                        <td class="title" align="center">Адрес</td>
                        <td class="cell" align="center">${user.worker.passport.address}</td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Серия и номер паспорта</td>
                        <td class="cell" align="center">${user.worker.passport.passportSeriesNumber}</td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Номер счета</td>
                        <td class="cell" align="center">${user.worker.accountNumber}</td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Номер трудового договора</td>
                        <td class="cell" align="center">${user.worker.contract.contractNumber}</td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Должность</td>
                        <td class="cell" align="center">${user.worker.contract.position}</td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Стаж</td>
                        <td class="cell" align="center">${user.worker.experience}</td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Оклад</td>
                        <td class="cell" align="center">${user.worker.contract.rate}</td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Дата заключения договора</td>
                        <td class="cell" align="center">${user.worker.contract.signDate}</td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Дата истечения договора</td>
                        <td class="cell" align="center">${user.worker.contract.endDate}</td>
                    </tr>
                </table>
            </form:form>

            <c:choose>
                <c:when test="${worker.user!=null}">
                    <form:form name="changePasswordForm" action="changePassword" method="post" modelAttribute="userDTO" onsubmit="return validation()">
                        <form:input path="login" type="hidden" id="login"/>
                        <h4>Новый пароль:</h4> <form:password path="password" />
                        <br/> <br/>
                        <input type="submit" value="Подтвердить">
                        <br/>
                        <br/>
                    </form:form>
                </c:when>
            </c:choose>

        </div>
        <nav>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a class="link" href="<c:url value="/admin/home"/>">Назад</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_MANAGER')">
                <a class="link" href="<c:url value="/manager/home"/>">Назад</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_WORKER')">
                <a class="link" href="<c:url value="/account/home"/>">Назад</a>
            </sec:authorize>
        </nav>
    </div>
    <footer>

    </footer>
</div>
</body>
</html>
