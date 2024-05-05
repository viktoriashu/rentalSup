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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>LOGIN</title>
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
                                    key="page.login.heading"/></h2>

                            <form action="/login" method="post">

                                <div data-mdb-input-init class="form-outline mb-4">
                                    <label class="form-label" for="login"><fmt:message key="page.login.login"/>:</label>
                                    <input type="text" name="login" id="login" value="${param.login}" required
                                           class="form-control form-control-lg"/>
                                </div>

                                <div data-mdb-input-init class="form-outline mb-4">
                                    <label class="form-label" for="password"><fmt:message
                                            key="page.login.password"/>:</label>
                                    <input type="password" name="password" id="password" required
                                           class="form-control form-control-lg"/>
                                </div>


                                <div class="d-flex justify-content-center">
                                    <button type="submit"
                                            data-mdb-button-init data-mdb-ripple-init
                                            class="btn btn-primary btn-block btn-lg gradient-custom-4 text-body me-3">
                                        <fmt:message key="page.login.submit.button"/></button>
                                    <a href="/registration">
                                        <button type="button"
                                                data-mdb-button-init data-mdb-ripple-init
                                                class="btn btn-primary btn-block btn-lg gradient-custom-4 text-body me-3">
                                            <fmt:message key="page.login.register.button"/></button>
                                    </a>
                                    <c:if test="${param.error != null}">
                                        <div style="color: red">
                                            <span>Login or password is not correct</span>
                                            <span><fmt:message key="page.login.error"/></span>

                                        </div>

                                    </c:if>
                                </div>

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


<%--<form action="/login" method="post">--%>

<%--    <label for="login"><fmt:message key="page.login.login"/>:--%>
<%--        <input type="text" name="login" id="login" value="${param.login}" required>--%>
<%--    </label><br>--%>
<%--    <label for="password"><fmt:message key="page.login.password"/>:--%>
<%--        <input type="password" name="password" id="password" required>--%>
<%--    </label><br>--%>
<%--    <button type="submit"><fmt:message key="page.login.submit.button"/></button>--%>
<%--    <a href="/registration">--%>
<%--        <button type="button"><fmt:message key="page.login.register.button"/></button>--%>
<%--    </a>--%>
<%--    <c:if test="${param.error != null}">--%>
<%--        <div style="color: red">--%>
<%--            <span>Login or password is not correct</span>--%>
<%--            <span><fmt:message key="page.login.error"/><</span>--%>

<%--        </div>--%>

<%--    </c:if>--%>
<%--</form>--%>
<%--</section>--%>
<%--</body>--%>
<%--</html>--%>