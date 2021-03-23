<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>Деактивация трудового договора сотрудника</title>
    <jsp:include page="head.jsp"/>
    <link rel="stylesheet" type="text/css" href="
      <c:url value="/resources/css/profile.css"/> "/>
</head>
<body>
<script>
    $(document).ready(function() {
        $('#newEndDate').datepicker({dateFormat:"yy-mm-dd"});
    });
</script>
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
                        <td class="title" align="center">Номер трудового договора</td>
                        <td class="cell" align="center">${worker.contract.contractNumber}</td>
                    </tr>
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
                        <td class="title" align="center">Должность</td>
                        <td class="cell" align="center">${worker.contract.position}</td>
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
            </form:form><br/> <br/>
            <form:form modelAttribute="deactivatedWorker" method="post" action="deactivate/${worker.idWorker}">
                <h4>Дата расторжения договора</h4>
                <form:input id="newEndDate" path="contract.dismissalDate" placeholder="гггг-мм-дд"/>
                <br/><h4>Номер приказа об увольнении</h4>
                <form:input id="orderNumber" path="contract.orderNumber" type="text"/>
                <br/> <br/>
                <input type="submit" value="Подтвердить" id="submit">
            </form:form>
             </div>
        <nav>
            <a class="link" href="<c:url value="list"/>">Назад</a>
        </nav>
    </div>
    <footer>

    </footer>
</div>
</body>
</html>
