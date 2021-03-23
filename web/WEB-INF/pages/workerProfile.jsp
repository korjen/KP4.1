<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>Профиль сотрудника</title>
    <jsp:include page="head.jsp"/>
    <link rel="stylesheet" type="text/css" href="
      <c:url value="/resources/css/profile.css"/> "/>
</head>
<body>
<div class="container">
    <header>
    </header>
    <div class="innerContainer">
        <aside></aside>
        <div class="middle" align="center">
        <form:form modelAttribute="worker">
            <br/> <br/>
            <table id="list">
                <tr>
                    <td class="title" align="center">Фамилия</td>
                    <td class="cell" align="center">${worker.passport.surname}</td>
                </tr>
                <tr>
                    <td class="title" align="center">Имя</td>
                    <td class="cell" align="center">${worker.passport.name}</td>
                </tr>
                <tr>
                    <td class="title" align="center">Отчество</td>
                    <td class="cell" align="center">${worker.passport.patronymic}</td>
                </tr>
                <tr>
                    <td class="title" align="center">Пол</td>
                    <td class="cell" align="center">${worker.passport.gender}</td>
                </tr>
                <tr>
                    <td class="title" align="center">Дата рождения</td>
                    <td class="cell" align="center">${worker.passport.birthDate}</td>
                </tr>
                <tr>
                    <td class="title" align="center">Количество иждивенцев</td>
                    <td class="cell" align="center">${worker.numberOfDependants}</td>
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
                    <td class="cell" align="center">${worker.passport.address}</td>
                </tr>
                <tr>
                    <td class="title" align="center">Серия и номер паспорта</td>
                    <td class="cell" align="center">${worker.passport.passportSeriesNumber}</td>
                </tr>
                <tr>
                    <td class="title" align="center">Номер счета</td>
                    <td class="cell" align="center">${worker.accountNumber}</td>
                </tr>
                <tr>
                    <td class="title" align="center">Номер трудового договора</td>
                    <td class="cell" align="center">${worker.contract.contractNumber}</td>
                </tr>
                <tr>
                    <td class="title" align="center">Должность</td>
                    <td class="cell" align="center">${worker.contract.position}</td>
                </tr>
                <tr>
                    <td class="title" align="center">Стаж</td>
                    <td class="cell" align="center">${worker.experience}</td>
                </tr>
                <tr>
                    <td class="title" align="center">Оклад</td>
                    <td class="cell" align="center">${worker.contract.rate}</td>
                </tr>
                <tr>
                    <td class="title" align="center">Дата заключения договора</td>
                    <td class="cell" align="center">${worker.contract.signDate}</td>
                </tr>
                <tr>
                    <td class="title" align="center">Дата истечения договора</td>
                    <td class="cell" align="center">${worker.contract.endDate}</td>
                </tr>

                <c:choose>
                    <c:when test="${worker.active==false}">
                        <tr>
                            <td class="title" align="center">Дата увольнения</td>
                            <td class="cell" align="center">${worker.contract.dismissalDate}</td>
                        </tr>
                        <tr>
                            <td class="title" align="center">Номер приказа об увольнении</td>
                            <td class="cell" align="center">${worker.contract.orderNumber}</td>
                        </tr>
                    </c:when>
                </c:choose>

            </table>
            <br/> <br/>

            <button class="button"><a class="link" href="<c:url value="workerUpdate/${worker.idWorker}"/>">Редактировать</a></button>
            <c:choose>
                <c:when test="${worker.active==true}">
                    <button class="button"><a class="link" href="<c:url value="getWorkerTime/${worker.idWorker}"/>">Изменить время работы</a></button>
                </c:when>
            </c:choose>

            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <c:choose>
                <c:when test="${worker.active==false}">
                <button class="button"><a class="link" href="<c:url value="workerDelete/${worker.idWorker}"/>">Удалить</a></button>
                </c:when>
                </c:choose>
            </sec:authorize>

            <c:choose>
                <c:when test="${worker.active==true}">
                    <button class="button"><a class="link" href="<c:url value="workerDeactivate/${worker.idWorker}"/>">Отметить как неактивного</a></button>
                </c:when>
                <c:otherwise>
                    <button class="button"><a class="link" href="<c:url value="workerActivate/${worker.idWorker}"/>">Отметить как активного</a></button>
                </c:otherwise>
            </c:choose>
        </form:form>
            </div>
        <nav><a class="link" href="<c:url value="list"/>">Назад</a></nav>
    </div>
    <footer>

    </footer>
</div>
</body>
</html>
