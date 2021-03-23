<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>Установка рабочего времени</title>
    <jsp:include page="head.jsp"/>
    <link rel="stylesheet" type="text/css" href="
      <c:url value="/resources/css/home.css"/> "/>
    <link rel="stylesheet" type="text/css" href="
    <c:url value="/resources/css/list.css"/> "/>
</head>
<body>
<script>
    $(document).ready(function() {
        $('#inputStartTime').datetimepicker({
            format: 'Y-m-d H:i'
        });
        $('#inputEndTime').datetimepicker({
            format: 'Y-m-d H:i'
        });
    });
</script>
<div class="container">
    <header>
    </header>
    <div class="innerContainer">
        <aside></aside>
        <div class="middle" align="center">
            <table id="list">
                <tr>
                    <th class="name">Начало</th>
                    <th class="name">Завершение</th>
                    <th class="name">Отработанное время</th>
                    <th class="name">Посещение</th>
                    <th class="name">Причина пропуска</th>
                </tr>
                <c:forEach items="${timeList}" var="time">
                    <tr class="border" id=${time.idTimetable}>
                        <td class="cell" align="center">${time.startTime}</td>
                        <td class="cell" align="center">${time.endTime}</td>
                        <td class="cell" align="center">${time.workingHours}</td>
                        <c:choose>
                            <c:when test="${time.attendance==true}">
                                <td class="cell" align="center">да</td>
                            </c:when>
                            <c:otherwise>
                                <td class="cell" align="center">нет</td>
                            </c:otherwise>
                        </c:choose>
                        <td class="cell" align="center">${time.reason}</td>
                    </tr>
                </c:forEach>
            </table>
            <br/> <br/>

            <table>
                <tr>
                    <form:form name="startTimeForm" action="setStartTime" method="post" modelAttribute="startTime">
                        <td align="center">
                            <form:input id="inputStartTime" path="startTime" placeholder="гггг-мм-дд чч:мм"/>
                        </td>
                        <td align="center">
                        <input class="margin-left" type="submit" id="submitStart" value="Время начала"/>
                        </td>
                    </form:form>
                </tr>
                <tr>
                    <form:form name="endTimeForm" action="setEndTime" method="post" modelAttribute="endTime">
                    <td align="center"><form:input id="inputEndTime" path="endTime" placeholder="гггг-мм-дд чч:мм"/>
                    </td>
                    <td align="center">
                    <input class="margin-left" type="submit" id="submitEnd" value="Время завершения"/>
                    </td>
                    </form:form>
                </tr>
            </table>
        </div>
        <nav>
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
                        <td><a href="<c:url value="/admin/workingTime?page=${i}"/>">${i}</a></td>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_MANAGER')">
                        <td><a href="<c:url value="/manager/workingTime?page=${i}"/>">${i}</a></td>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_WORKER')">
                        <td><a href="<c:url value="/account/workingTime?page=${i}"/>">${i}</a></td>
                    </sec:authorize>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </footer>
</div>
</body>
</html>
