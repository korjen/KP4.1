<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>Список пользователей</title>
    <jsp:include page="head.jsp"/>
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
                    <th class="name">№</th>
                    <th class="name">Логин</th>
                    <th class="name">Фамилия</th>
                    <th class="name">Имя</th>
                    <th class="name">Отчество</th>
                    <th class="name">Права</th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${usersList}" var="user">
                    <tr id=${user.login}>
                        <td class="cell" align="center">${user.worker.idWorker}</td>
                        <td class="cell" align="center">${user.login}</td>
                        <td class="cell" align="center">${user.worker.passport.surname}</td>
                        <td class="cell" align="center">${user.worker.passport.name}</td>
                        <td class="cell" align="center">${user.worker.passport.patronymic}</td>
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
                            <%--<td class="buttonCell">--%>
                        <td>
                            <button class="button"><a class="link" href="<c:url value="profile/${user.login}"/>">Профиль</a></button>
                        </td>
                        <td>
                            <button class="button"><a class="link" href="<c:url value="deleteUser/${user.login}"/>">Удалить</a></button>
                                <%--<button class="button" onclick="RestDelete(${user.login})">Удалить</button>--%>
                        </td>
                        <td>
                            <button class="button"><a class="link" href="<c:url value="editUser/${user.login}"/>">Редактировать</a></button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <nav>
            <a class="link" href="<c:url value="/admin/users/newUser"/>">Добавить пользователя</a>
            <a class="link" href="<c:url value="/admin/home"/>">Назад</a>
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
                    <td><a href="<c:url value="/admin/users/list?page=${i}"/>">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </footer>
</div>
</body>
</html>
