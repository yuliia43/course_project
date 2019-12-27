<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>

    <fmt:setLocale value="${lang}"/>
    <fmt:setBundle basename="repair_vocab"/>

    <title>Base</title>
    <style>
        <%@include file="../styles/style.css" %>
    </style>
</head>
<body>
<ul class="menu">
    <li><a href="/"><fmt:message key="label.mainMenu.about"/></a></li>
    <security:authorize access="isAuthenticated()">
        <li><a href="/userPage"><fmt:message key="label.mainMenu.userPage"/></a></li>
    </security:authorize>
    <security:authorize access="hasAuthority('user')">
        <li><a href="/user/applications"><fmt:message key="label.mainMenu.myApplications"/></a></li>
        <li><a href="/user/newApplication"><fmt:message key="label.mainMenu.newApplication"/></a></li>
    </security:authorize>
    <security:authorize access="hasAuthority('manager')">
        <li><a href="/manager/applications"><fmt:message key="label.mainMenu.viewApplications"/></a></li>
    </security:authorize>
    <security:authorize access="hasAuthority('master')">
        <li><a href="master/newApplication"><fmt:message key="label.mainMenu.viewApplications"/></a></li>
        <li><a href="/master/applications"><fmt:message key="label.mainMenu.myApplications"/></a></li>
    </security:authorize>
    <li><a href="/mastersStats"><fmt:message key="label.mainMenu.viewMasters"/></a></li>
    <security:authorize access="!isAuthenticated()">
        <li><a href="/authorisation"><fmt:message key="label.mainMenu.signIn"/></a></li>
        <li><a href="/registration"><fmt:message key="label.mainMenu.signUp"/> </a></li>
    </security:authorize>
    <security:authorize access="isAuthenticated()">
        <li><a href="/signOut"><fmt:message key="label.mainMenu.signOut"/> </a></li>
    </security:authorize>
    <li>
        <form method="get">
            <select name="lang" style="margin: 20px 20px; float: right" onchange="this.form.submit()">
                <option value="en" ${lang eq 'en' ? 'selected' : ''}>EN</option>
                <option value="uk" ${lang eq 'uk' ? 'selected' : ''}>UK</option>
            </select>
        </form>
    </li>
</ul>
</body>
</html>
