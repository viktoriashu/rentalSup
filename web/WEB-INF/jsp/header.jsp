<%--
  Created by IntelliJ IDEA.
  User: Виктория
  Date: 01.05.2024
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
</head>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<nav class="navbar navbar-dark bg-dark">
    <div>
        <c:if test="${not empty sessionScope.user_type}">
            <div id="logout">
                <form action="/logout" method="post">
                    <button type="submit" class="btn btn-danger me-3">Logout</button>
                </form>
            </div>
        </c:if>
        <div id="locale">
            <form action="/locale" method="post">
                <button type="submit" class="btn btn-primary me-3" name="lang" value="ru_RU">RU</button>
                <button type="submit" class="btn btn-primary me-3" name="lang" value="en_US">EN</button>
            </form>
        </div>
        <fmt:setLocale value="${sessionScope.lang != null
                            ? sessionScope.lang
                            : (param.lang != null ? param.lang : 'en_US')}"/>
        <fmt:setBundle basename="translations"/>
    </div>
</nav>
