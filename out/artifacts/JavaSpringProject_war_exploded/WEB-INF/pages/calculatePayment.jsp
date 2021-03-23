<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>Расчет зарплат</title>
    <jsp:include page="head.jsp"/>
    <link rel="stylesheet" type="text/css" href="
      <c:url value="/resources/css/home.css"/> "/>
    <link rel="stylesheet" type="text/css" href="
    <c:url value="/resources/css/list.css"/> "/>
</head>
<body>
<div class="container">
    <header>
    </header>
    <div class="innerContainer">
        <aside></aside>
        <div class="middle" align="center">
            <table id="list">
                <tr>
                    <th class="name">Фамилия</th>
                    <th class="name">Имя</th>
                    <th class="name">Отчество</th>
                    <th class="name">Оклад</th>
                    <th class="name">Раб. дней в месяце</th>
                    <th class="name">Отраб. дни</th>
                    <th class="name">Отпуск</th>
                    <th class="name">К получению</th>
                    <th class="name">Сумма налогов</th>
                    <th class="name">Месяц расчета</th>
                    <th class="name">Дата начисления</th>
                    <th class="name">Льготы</th>
                    <th></th>
                </tr>
                <c:forEach items="${salariesList}" var="salary">
                    <tr class="border" id=${salary.idSalary}>
                        <td class="cell" align="center">${salary.worker.passport.surname}</td>
                        <td class="cell" align="center">${salary.worker.passport.name}</td>
                        <td class="cell" align="center">${salary.worker.passport.patronymic}</td>
                        <td class="cell" align="center">${salary.worker.contract.rate}</td>
                        <td class="cell" align="center">${salary.monthDays}</td>
                        <td class="cell" align="center">${salary.workingDays}</td>
                        <td class="cell" align="center">${salary.vacationDays}</td>
                        <td class="cell" align="center">${salary.sum}</td>
                        <td class="cell" align="center">${salary.tax}</td>
                        <td class="cell" align="center">${salary.calculationMonth}</td>
                        <td class="cell" align="center">${salary.payDate}</td>
                        <c:choose>
                            <c:when test="${salary.privilege==true}">
                                <td class="cell" align="center">да</td>
                            </c:when>
                            <c:otherwise>
                                <td class="cell" align="center">нет</td>
                            </c:otherwise>
                        </c:choose>
                        <td>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <button class="button"><a class="link" href="<c:url value="/admin/workersPaymentList/profile/${salary.worker.idWorker}"/>">Профиль</a></button>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ROLE_MANAGER')">
                                <button class="button"><a class="link" href="<c:url value="/manager/workersPaymentList/profile/${salary.worker.idWorker}"/>">Профиль</a></button>
                            </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br/> <br/>

            <form:form name="setDataForm" action="calculate" method="post" modelAttribute="deduction">
                <table>
                    <tr>
                        <th class="name">Ставка подоходного налога</th>
                        <th class="name">Пенсионное страхование</th>
                        <th class="name">Белгосстрах</th>
                        <th class="name">Месяц расчета</th>
                        <th class="name">Количество рабочих дней в месяце</th>
                    </tr>
                    <tr>
                        <td align="center">
                            <form:input id="taxPercent" path="taxPercent" type="number" readonly="true"/>
                        </td>
                        <td align="center">
                            <form:input id="pensionInsurancePercent" path="pensionInsurancePercent" type="number"  readonly="true"/>
                        </td>
                        <td align="center">
                            <form:input id="socialInsurancePercent" path="socialInsurancePercent" type="number"  readonly="true"/>
                        </td>
                        <td align="center">
                            <form:input id="month" path="month" type="number"/>
                        </td>
                        <td align="center">
                            <form:input id="workingDays" path="workingDays" type="number"/>
                        </td>
                    </tr>
                </table>
                <br/> <br/> <input type="submit" id="submit" value="Подтвердить"/>
                <br/>
                <h4 class="warning">Зарплата для работников, имеющих больничные в расчетном месяце, а также для работников, уходивших в отпуск,
                    для которых не может быть расчитана среднемесячная зарплата, рассчитываться не будет</h4>
            </form:form>

        </div>
        <nav>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a class="link" href="<c:url value="/admin/home"/>">Назад</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_MANAGER')">
                <a class="link" href="<c:url value="/manager/home"/>">Назад</a>
            </sec:authorize>
        </nav>
    </div>
    <footer>
        <br/><br/>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td><a href="<c:url value="/admin/workersPaymentList?page=${i}"/>">${i}</a></td>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_MANAGER')">
                        <td><a href="<c:url value="/manager/workersPaymentList?page=${i}"/>">${i}</a></td>
                    </sec:authorize>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </footer>
</div>
</body>
</html>
