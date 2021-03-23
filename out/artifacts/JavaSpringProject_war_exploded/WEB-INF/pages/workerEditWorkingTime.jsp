<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>Редактирование рабочего времени</title>
    <jsp:include page="head.jsp"/>
    <link rel="stylesheet" type="text/css" href="
      <c:url value="/resources/css/home.css"/> "/>
    <link rel="stylesheet" type="text/css" href="
    <c:url value="/resources/css/list.css"/> "/>
</head>
<body>
<script>
    $(document).ready(function() {
        $('#startTime').datetimepicker({
            format: 'Y-m-d H:i'
        });
        $('#endTime').datetimepicker({
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
                <form:form name="editTimeForm" action="editWorkingTime" method="post" modelAttribute="time">
            <table>
                    <form:input path="worker.idWorker" type="hidden" id="workerId"/>
                    <tr>
                        <th class="name">Начало</th>
                        <th class="name">Завершение</th>
                        <th class="name">Посещение</th>
                        <th class="name">Причина пропуска</th>
                    </tr>
                    <tr>
                        <td align="center">
                            <form:input id="startTime" path="startTime" placeholder="гггг-мм-дд чч:мм"/>
                        </td>
                        <td align="center">
                            <form:input id="endTime" path="endTime" placeholder="гггг-мм-дд чч:мм"/>
                        </td>
                        <td align="center">
                            <form:select path="attendance" id="attendance">
                                <form:option value="true" label="да" />
                                <form:option value="false" label="нет" />
                            </form:select>
                        </td>
                        <td align="center">
                            <form:select path="reason" id="reason">
                                <form:option value="" label="-" />
                                <form:option value="выходной/праздник" label="выходной/праздник" />
                                <form:option value="отпуск" label="отпуск" />
                                <form:option value="больничный" label="больничный" />
                                <form:option value="отпуск за свой счет" label="отпуск за свой счет" />
                            </form:select>
                        </td>
                    </tr>
            </table>
                    <br/>
                    <c:if test="${not empty error}">
                        <span class="input-error" align="center" class="error">${error}</span>
                    </c:if>
                    <br/> <input type="submit" id="submit" value="Подтвердить"/>
                </form:form>

        </div>
        <nav>
            <a class="link" href="<c:url value="list"/>">Назад</a>
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
                        <td><a href="<c:url value="/admin/workers/profile/getWorkerTime?page=${i}"/>">${i}</a></td>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_MANAGER')">
                        <td><a href="<c:url value="/manager/workers/profile/getWorkerTime?page=${i}"/>">${i}</a></td>
                    </sec:authorize>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </footer>
</div>
</body>
</html>
