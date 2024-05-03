<%--
  Created by IntelliJ IDEA.
  User: Виктория
  Date: 30.04.2024
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%@include file="header.jsp" %>
<form action="/login" method="post">
    <label for="login"><fmt:message key="page.login.login"/>:
        <input type="text" name="login" id="login" value="${param.login}" required>
    </label><br>
    <label for="password"><fmt:message key="page.login.password"/>:
        <input type="password" name="password" id="password" required>
    </label><br>
    <button type="submit"><fmt:message key="page.login.submit.button"/></button>
    <a href="/registration">
        <button type="button"><fmt:message key="page.login.register.button"/></button>
    </a>
    <c:if test="${param.error != null}">
        <div style="color: red">
            <span>Login or password is not correct</span>
            <span><fmt:message key="page.login.error"/><</span>

        </div>

    </c:if>
</form>
</body>
</html>
