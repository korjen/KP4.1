<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

            <html>

            <head>
                <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
                <title>Авторизация</title>
                <jsp:include page="head.jsp" />
                <link rel="stylesheet" type="text/css" href="
      <c:url value=" /resources/css/login.css "/> "/>
            </head>

            <body>
                <script>
                    function validation() {
                        var x = document.getElementById('login').value;
                        var numbers = /^[0-9][0-9\s]*$/;
                        if (x == null || x == "") {
                            alert("Логин не был введен");
                            return false;
                        } else if (x.length < 4 || x.length > 16) {
                            alert("Логин должен содержать от 4 до 16 символов");
                            return false;
                        }
                        x = document.getElementById('password').value;;
                        if (x == null || x == "") {
                            alert("Пароль не был введен");
                            return false;
                        } else if (x.length < 6 || x.length > 16) {
                            alert("Пароль должен содержать от 6 до 16 символов");
                            return false;
                        } else if (!(x.match(/[a-z]/g) && x.match(/[A-Z]/g) &&
                                x.match(/[0-9]/g))) {
                            alert("Пароль должен содержать цифры и буквы нижнего и верхнего регистров");
                            return false
                        }
                        return true;
                    }
                </script>
                <div class="container">
                    <header>
                    </header>
                    <div class="innerContainer">
                        <aside></aside>
                        <div class="middle" align="center">
                            <br/><br/>
                            <form:form method="post" onsubmit="return validation()">
                                <c:if test="${not empty error}">
                                    <span class="input-error" align="center" class="error">${error}</span>
                                </c:if>
                                <h4>Логин:</h4> <input type="text" name="login" id="login" />
                                <h4>Пароль:</h4> <input type="password" name="password" id="password" />
                                <br/>
                                <br/><input type="submit" value="Авторизироваться" id="submit">
                            </form:form>
                        </div>
                        <nav><a class="link" href="<c:url value=" / "/>">Назад</a></nav>
                    </div>
                    <footer/>
                </div>
            </body>

            </html>