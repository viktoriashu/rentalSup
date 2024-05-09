<%--
  Created by IntelliJ IDEA.
  User: Виктория
  Date: 01.05.2024
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SUP-BOARD</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
</head>

<body>
<%@ include file="header.jsp"%>
    <div class="bg-primary d-flex
             align-items-center
             justify-content-center"
         style="background: url(
'https://images.unsplash.com/photo-1645204181497-afcaf6f227c5?q=80&w=1880&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D')
             no-repeat center center fixed;
             background-size: cover;
             height: 100vh;">


        <div class="container text-center text-white">
        <h1 class="text-black fw-bold">SUP-BOARD:</h1>
            <h3 class="text-warning fw-bold">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
                    <c:forEach var="sup" items="${(requestScope.sup)}">
                        <li>${fn:toUpperCase(sup.description)}</li>
                    </c:forEach>
            </h3>
        </div>
        </div>

<%--<h1>Список SUP-BOARD:</h1>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>--%>
<%--<ul>--%>
<%--    <c:forEach var="sup" items="${requestScope.sup}">--%>
<%--        <li>${fn:toLowerCase(sup.description)}</li>--%>
<%--    </c:forEach>--%>

<%--</ul>--%>
</body>
</html>


