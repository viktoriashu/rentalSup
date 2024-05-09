<%--
  Created by IntelliJ IDEA.
  User: Виктория
  Date: 28.04.2024
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>REGISTRATION</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
</head>
<body>
<%@include file="header.jsp" %>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<section class="vh-100 bg-image"
         style="background: url('https://img.freepik.com/free-photo/abstract-2d-colorful-wallpaper-with-grainy-gradients_23-2151001646.jpg?t=st=1714907735~exp=1714911335~hmac=864627fbdd58a69ac68ee0f54430af7d9927ab3c2860bf5b7131c485e3503634&w=1800')
         no-repeat center center fixed;
         background-size: cover;
         height: 100vh;">
    <div class="mask d-flex align-items-center h-100 gradient-custom-3">
        <div class="container h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                    <div class="card" style="border-radius: 15px;">
                        <div class="card-body p-5">

                            <h2 class="text-uppercase text-center mb-5"><fmt:message
                                    key="page.registration.heading"/></h2>

                            <form action="${pageContext.request.contextPath}/registration" method="post">

                                <div data-mdb-input-init class="form-outline mb-4">
                                    <label class="form-label" for="firstName"><fmt:message
                                            key="page.registration.firstName"/>:</label>
                                    <input type="text" name="firstName" id="firstName"
                                           class="form-control form-control-lg"/>
                                </div>

                                <div data-mdb-input-init class="form-outline mb-4">
                                    <label class="form-label" for="lastName"><fmt:message
                                            key="page.registration.lastName"/>:</label>
                                    <input type="text" name="lastName" id="lastName"
                                           class="form-control form-control-lg"/>
                                </div>

                                <div data-mdb-input-init class="form-outline mb-4">
                                    <label class="form-label" for="login"><fmt:message
                                            key="page.registration.login"/>:</label>
                                    <input type="text" name="login" id="login" class="form-control form-control-lg"/>
                                </div>

                                <div data-mdb-input-init class="form-outline mb-4">
                                    <label class="form-label" for="password"><fmt:message
                                            key="page.registration.password"/>:</label>
                                    <input type="password" name="password" id="password"
                                           class="form-control form-control-lg"/>
                                </div>

                                <div data-mdb-input-init class="form-outline mb-4">
                                    <label class="form-label" for="number"><fmt:message key="page.registration.number"/>:</label>
                                    <input type="text" name="number" id="number" class="form-control form-control-lg"/>
                                </div>


<%--                                для админки--%>
<%--                                <div class="form-outline mb-4">--%>
<%--                                    <label class="form-label" for="role"><fmt:message--%>
<%--                                            key="page.registration.role"/>:</label>--%>

<%--                                    <select data-mdb-select-init name="role" id="role">--%>
<%--                                        <c:forEach var="role" items="${requestScope.roles}">--%>
<%--                                            <option value="${role.id}">${role.roleName}</option>--%>
<%--                                        </c:forEach>--%>
<%--                                    </select>--%>

<%--                                </div>--%>

                                <div class="d-flex justify-content-center">
                                    <button type="submit"
                                            data-mdb-button-init data-mdb-ripple-init
                                            class="btn btn-primary btn-block btn-lg gradient-custom-4 text-body">
                                        <fmt:message key="page.registration.create.button"/></button>
                                    <c:if test="${not empty requestScope.errors}">
                                        <div style="color: red">
                                            <c:forEach var="error" items="${requestScope.errors}">
                                                <span>${error.message}</span>
                                                <br>
                                            </c:forEach>
                                        </div>
                                    </c:if>
                                </div>

                                <p class="text-center text-muted mt-5 mb-0"><fmt:message
                                        key="page.registration.questionLogin"/> <a href="/login"
                                                                                   class="fw-bold text-body"><u><fmt:message
                                        key="page.registration.refLogin"/>
                                </u></a></p>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>


<%--<form action="${pageContext.request.contextPath}/registration" method="post">--%>
<%--    &lt;%&ndash;     <label for="firstName">First Name: &ndash;%&gt;--%>
<%--    <label for="firstName"><fmt:message key="page.registration.firstName"/>:--%>
<%--        <input type="text" name="firstName" id="firstName">--%>
<%--    </label><br>--%>

<%--    &lt;%&ndash; <label for="lastName">Last name:&ndash;%&gt;--%>
<%--    <label for="lastName"><fmt:message key="page.registration.lastName"/>:--%>
<%--        <input type="text" name="lastName" id="lastName">--%>
<%--    </label><br>--%>

<%--    &lt;%&ndash;    <label for="login">Login:&ndash;%&gt;--%>
<%--    <label for="login"><fmt:message key="page.registration.login"/>:--%>
<%--        <input type="text" name="login" id="login">--%>
<%--    </label><br>--%>

<%--    &lt;%&ndash;    <label for="password">Password:&ndash;%&gt;--%>
<%--    <label for="password"><fmt:message key="page.registration.password"/>:--%>
<%--        <input type="password" name="password" id="password">--%>
<%--    </label><br>--%>

<%--    &lt;%&ndash;    <label for="number">Number:&ndash;%&gt;--%>
<%--    <label for="number"><fmt:message key="page.registration.number"/>:--%>
<%--        <input type="text" name="number" id="number">--%>
<%--    </label><br>--%>
<%--    &lt;%&ndash;убрать возможность выбора роли при регистрации&ndash;%&gt;--%>
<%--    &lt;%&ndash;    <label for="role">Role:&ndash;%&gt;--%>
<%--    <label for="role"><fmt:message key="page.registration.role"/>:--%>
<%--        <select name="role" id="role">--%>
<%--            <c:forEach var="role" items="${requestScope.roles}">--%>
<%--                <option value="${role.id}">${role.roleName}</option>--%>
<%--            </c:forEach>--%>
<%--        </select>--%>
<%--    </label><br>--%>


<%--    &lt;%&ndash;    <button type="submit">Send</button>&ndash;%&gt;--%>
<%--    <button type="submit"><fmt:message key="page.registration.send.button"/></button>--%>
<%--    <c:if test="${not empty requestScope.errors}">--%>
<%--        <div style="color: red">--%>
<%--            <c:forEach var="error" items="${requestScope.errors}">--%>
<%--                <span>${error.message}</span>--%>
<%--                <br>--%>
<%--            </c:forEach>--%>
<%--        </div>--%>
<%--    </c:if>--%>

<%--</form>--%>
<%--</body>--%>
<%--</html>--%>