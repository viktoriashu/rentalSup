<%--
  Created by IntelliJ IDEA.
  User: Виктория
  Date: 28.04.2024
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/registration" method="post">
    <label for="firstName">First Name:
        <input type="text" name="firstName" id="firstName">
    </label><br>

    <label for="lastName">Last name:
        <input type="text" name="lastName" id="lastName">
    </label><br>

    <label for="login">Login:
        <input type="text" name="login" id="login">
    </label><br>

    <label for="password">Password:
        <input type="password" name="password" id="password">
    </label><br>

    <label for="number">Number:
        <input type="text" name="number" id="number">
    </label><br>

    <label for="role">Role:
        <select name="role" id="role">
            <c:forEach var="role" items="${requestScope.roles}">
                <option value="${role.id}">${role.roleName}</option>
            </c:forEach>
        </select>
    </label><br>


    <button type="submit">Send</button>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span>
                <br>
            </c:forEach>
        </div>
    </c:if>

</form>
</body>
</html>