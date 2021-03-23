<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>Редактирование профиля сотрудника</title>
    <jsp:include page="head.jsp"/>
    <link rel="stylesheet" type="text/css" href="
      <c:url value="/resources/css/profile.css"/> "/>
</head>
<body>
<script>
    $(document).ready(function() {
        $('#birthDate').datepicker({dateFormat:"yy-mm-dd"});
        $('#signDate').datepicker({dateFormat:"yy-mm-dd"});
        $('#endDate').datepicker({dateFormat:"yy-mm-dd"});
        $('#dismissalDate').datepicker({dateFormat:"yy-mm-dd"});
    });
</script>
<div class="container">
    <header>
    </header>
    <div class="innerContainer">
        <aside></aside>
        <div class="middle" align="center">
            <form:form id="editForm" action="workerUpdateProcess" method="post" modelAttribute="worker">
                <table id="list">
                    <form:input path="idWorker" type="hidden" id="workerId"/>
                    <form:input path="user.login" type="hidden" id="login"/>
                    <form:input path="user.role" type="hidden" id="role"/>
                    <form:input path="user.password" type="hidden" id="password"/>
                    <form:input path="contract.contractNumber" type="hidden" id="number"/>
                    <form:input path="active" type="hidden" id="active"/>

                    <tr>
                        <td class="title" align="center">Фамилия</td>
                        <td class="cell" align="center"><form:input path="passport.surname" type="text" id="surname"/></td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Имя</td>
                        <td class="cell" align="center"><form:input path="passport.name" type="text" id="name"/></td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Отчество</td>
                        <td class="cell" align="center"><form:input path="passport.patronymic" type="text" id="patronymic"/></td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Пол</td>
                        <td class="cell" align="center">
                            <form:select path="passport.gender" id="gender">
                                <form:option value="женский" label="женский" />
                                <form:option value="мужской" label="мужской" />
                                <form:option value="другой" label="другой" />
                            </form:select>
                    </tr>
                    <tr>
                        <td class="title" align="center">Дата рождения</td>
                        <td class="cell" align="center">
                            <form:input id="birthDate" path="passport.birthDate" placeholder="гггг-мм-дд"/>
                           </td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Количество иждивенцев</td>
                        <td class="cell" align="center">
                            <form:input id="numberOfDependants" path="numberOfDependants" type="number"/></td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Одинокий опекун</td>
                        <td class="cell" align="center">
                            <form:select path="soloParent" id="soloParent">
                                <form:option value="true" label="да" />
                                <form:option value="false" label="нет" />
                            </form:select>
                            </td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Адрес</td>
                        <td class="cell" align="center"><form:input path="passport.address" type="text" id="address"/></td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Серия и номер паспорта</td>
                        <td class="cell" align="center"><form:input path="passport.passportSeriesNumber" type="text" id="passportSeriesNumber"/></td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Номер счета</td>
                        <td class="cell" align="center"><form:input path="accountNumber" type="text" id="accountNumber"/></td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Номер трудового договора</td>
                        <td class="cell" align="center">${worker.contract.contractNumber}</td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Должность</td>
                        <td class="cell" align="center"><form:input path="contract.position" type="text" id="position"/></td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Стаж</td>
                        <td class="cell" align="center"><form:input path="experience" type="number" step="0.1" id="experience"/></td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Оклад</td>
                        <td class="cell" align="center"><form:input path="contract.rate" type="number" step="0.01" id="rate"/></td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Дата заключения договора</td>
                        <td class="cell" align="center"> <form:input id="signDate" path="contract.signDate" placeholder="гггг-мм-дд"/>
                            </td>
                    </tr>
                    <tr>
                        <td class="title" align="center">Дата истечения договора</td>
                        <td class="cell" align="center"><form:input id="endDate" path="contract.endDate" placeholder="гггг-мм-дд"/></td>
                    </tr>

                    <c:choose>
                        <c:when test="${worker.active==false}">
                            <tr>
                                <td class="title" align="center">Дата увольнения</td>
                                <td class="cell" align="center"> <form:input id="dismissalDate" path="contract.dismissalDate" placeholder="гггг-мм-дд"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="title" align="center">Номер приказа об увольнении</td>
                                <td class="cell" align="center"><form:input id="orderNumber" path="contract.orderNumber"/></td>
                            </tr>
                        </c:when>
                    </c:choose>
                </table>
                <br/>
                <input type="submit" id="submit" value="Подтвердить"/>
            </form:form>
        </div>
        <nav><a class="link" href="<c:url value="list"/>">Назад</a></nav>
    </div>
    <footer>

    </footer>
</div>
</body>
</html>
