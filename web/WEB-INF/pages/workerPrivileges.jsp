<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>Налоговые льготы сотрудника</title>
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
                    <th class="name">Условие</th>
                    <th class="name">Налоговый вычет</th>
                </tr>
                <c:forEach items="${privilegesList}" var="prilege">
                    <tr class="border" id=${prilege.idPrivilege}>
                        <td class="cell" align="center">${prilege.name}</td>
                        <td class="cell" align="center">${prilege.effect}</td>
                    </tr>
                </c:forEach>
            </table>
            <br/> <br/>

        </div>
        <nav>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a class="link" href="<c:url value="/admin/paymentHistory"/>">Назад</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_MANAGER')">
                <a class="link" href="<c:url value="/manager/paymentHistory"/>">Назад</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_WORKER')">
                <a class="link" href="<c:url value="/account/paymentHistory"/>">Назад</a>
            </sec:authorize>
        </nav>
    </div>
    <footer>
    </footer>
</div>
</body>
</html>
