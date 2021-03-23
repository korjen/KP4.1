<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>История выплат</title>
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
                    <th class="name">Номер счета</th>
                    <th class="name">Оклад</th>
                    <th class="name">Раб. дней в месяце</th>
                    <th class="name">Отраб. дни</th>
                    <th class="name">Отпуск</th>
                    <th class="name">К получению</th>
                    <th class="name">Сумма налогов</th>
                    <th class="name">Месяц расчета</th>
                    <th class="name">Дата начисления</th>
                    <th class="name">Льготы</th>
                </tr>
                <c:forEach items="${salariesList}" var="salary">
                    <tr class="border" id=${salary.idSalary}>
                        <td class="cell" align="center">${salary.worker.accountNumber}</td>
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
                    </tr>
                </c:forEach>
            </table>
            <br/> <br/>

        </div>
        <nav>

            <c:choose>
                <c:when test="${hasPrivilege==true}">
                    <a class="link" href="<c:url value="paymentHistory/workerPrivileges"/>">Льготы</a>
                </c:when>
            </c:choose>
            <a class="link" href="<c:url value="home"/>">Назад</a>
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
                    <td><a href="<c:url value="/admin/paymentHistory?page=${i}"/>">${i}</a></td>
                </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_MANAGER')">
                        <td><a href="<c:url value="/manager/paymentHistory?page=${i}"/>">${i}</a></td>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_WORKER')">
                        <td><a href="<c:url value="/account/paymentHistory?page=${i}"/>">${i}</a></td>
                    </sec:authorize>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </footer>
</div>
</body>
</html>
