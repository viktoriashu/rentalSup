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

<div>
    <c:if test="${not empty sessionScope.user_type}">
        <div id="logout">
            <form action="/logout" method="post">
                <button type="submit">Logout</button>
            </form>
        </div>
    </c:if>
    <div id="locale">
        <form action="/locale" method="post">
            <button type="submit" name="lang" value="ru_RU">RU</button>
            <button type="submit" name="lang" value="en_US">EN</button>
        </form>
    </div>
    <fmt:setLocale value="${sessionScope.lang != null
                            ? sessionScope.lang
                            : (param.lang != null ? param.lang : 'en_US')}"/>
    <fmt:setBundle basename="translations" />
</div>
