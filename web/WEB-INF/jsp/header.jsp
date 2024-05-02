<%--
  Created by IntelliJ IDEA.
  User: Виктория
  Date: 01.05.2024
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <c:if test="${not empty sessionScope.user_type}">
        <form action="/logout" method="post">
            <button type="submit">Logout</button>
        </form>
    </c:if>
</div>
