<%--
  Created by IntelliJ IDEA.
  User: Виктория
  Date: 30.04.2024
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form action="/login" method="post">
        <label for="login">Login:
            <input type="text" name="login" id="login" value="${param.login}" required>
        </label><br>
        <label for="password">Password:
            <input type="password" name="password" id="password" required>
        </label><br>
        <button type="submit">Login</button>
        <a href="/registration">
            <button type="button">Register</button>
        </a>
        <c:if test="${param.error != null}">
            <div style="color: red">
               <span>Login and password is not correct</span>

            </div>

        </c:if>
    </form>
</body>
</html>
