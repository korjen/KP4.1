<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>Список сотрудников</title>
    <jsp:include page="head.jsp"/>
    <link rel="stylesheet" type="text/css" href="
      <c:url value="/resources/css/list.css"/> "/>
</head>
<body>
<script>
    function search() {
        var element = document.getElementById(value);

    }
</script>
<div class="container">
    <header>
    </header>
    <div class="innerContainer">
        <aside></aside>
        <div class="middle" align="center">

            <table id="list">
                <tr>
                    <th class="name">№</th>
                    <th class="name">Номер договора</th>
                    <th class="name">Фамилия</th>
                    <th class="name">Имя</th>
                    <th class="name">Отчество</th>
                    <th class="name">Должность</th>
                    <th class="name">Стаж</th>
                    <th class="name">Статус</th>
                    <th></th>
                </tr>
                <c:forEach items="${workersList}" var="worker">
                    <tr class="border" id=${worker.idWorker}>
                        <td class="cell" align="center">${worker.idWorker}</td>
                        <td class="cell" align="center">${worker.contract.contractNumber}</td>
                        <td class="cell" align="center">${worker.passport.surname}</td>
                        <td class="cell" align="center">${worker.passport.name}</td>
                        <td class="cell" align="center">${worker.passport.patronymic}</td>
                        <td class="cell" align="center">${worker.contract.position}</td>
                        <td class="cell" align="center">${worker.experience}</td>
                        <c:choose>
                            <c:when test="${worker.active==true}">
                                <td class="cell" align="center">активен</td>
                            </c:when>
                            <c:otherwise>
                                <td class="cell" align="center">неактивен</td>
                            </c:otherwise>
                        </c:choose>
                        <%--<td class="buttonCell">--%>
                        <td>
                            <button class="button"><a class="link" href="<c:url value="profile/${worker.idWorker}"/>">Профиль</a></button>
                            <%--<button class="button" onclick="RestProfile(${user.login})">Профиль</button>--%>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <nav>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a class="link" href="<c:url value="/admin/workers/newWorker"/>">Добавить сотрудника</a>
                <a class="link" href="<c:url value="/admin/home"/>">Назад</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_MANAGER')">
                <a class="link" href="<c:url value="/manager/workers/newWorker"/>">Добавить сотрудника</a>
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
                        <td><a href="<c:url value="/admin/workers/list?page=${i}"/>">${i}</a></td>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_MANAGER')">
                        <td><a href="<c:url value="/manager/workers/list?page=${i}"/>">${i}</a></td>
                    </sec:authorize>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </footer>
</div>
</body>
</html>
