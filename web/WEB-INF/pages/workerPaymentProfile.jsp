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
                        <td class="title" align="center">Статус</td>
                        <c:choose>
                            <c:when test="${worker.active==true}">
                                <td class="cell" align="center">активен</td>
                            </c:when>
                            <c:otherwise>
                                <td class="cell" align="center">неактивен</td>
                            </c:otherwise>
                        </c:choose>
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
                </table>

                <c:choose>
                    <c:when test="${worker.privileges.size()>0}">
                        <h4>Льготы</h4>
                        <table id="list">
                            <tr>
                                <th class="title">Условие</th>
                                <th class="title">Налоговый вычет</th>
                            </tr>
                            <c:forEach items="${worker.privileges}" var="prilege">
                                <tr class="border" id=${prilege.idPrivilege}>
                                    <td class="cell" align="center">${prilege.name}</td>
                                    <td class="cell" align="center">${prilege.effect}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                </c:choose>
                <br/>
            </form:form>
        </div>
        <nav>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a class="link" href="<c:url value="/admin/workersPaymentList"/>">Назад</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_MANAGER')">
                <a class="link" href="<c:url value="/manager/workersPaymentList"/>">Назад</a>
            </sec:authorize>
        </nav>
    </div>
    <footer>

    </footer>
</div>
</body>
</html>
