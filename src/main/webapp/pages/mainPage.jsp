<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<jsp:include page='base.jsp'/>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="repair_vocab"/>

<html>
<head>
    <title>Main</title>
</head>
<body>
<div class="box">
    <h3><fmt:message key="label.mainPage.welcome"/></h3>
    <hr align="left">
    <fmt:message key="label.mainPage.systemInfo"/>
    <br><br><br>
    <security:authorize access="!isAuthenticated()">
        <a href="/authorisation" class="btn"><fmt:message key="label.mainMenu.signIn"/></a>
        <a href="/registration" class="btn"><fmt:message key="label.mainMenu.signUp"/></a>
    </security:authorize>
</div>
</body>
</html>
