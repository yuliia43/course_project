<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='../pages/base.jsp'/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="repair_vocab"/>

<html>
<head>
    <title>Incorrect role</title>
</head>
<body>
<div class="box">
    <h3><fmt:message key="error.wrongPermissions"/>!</h3>
    <hr align="left">
    <div class="table-fill" style="margin-left: 0px; width: 300px; height: 150px; padding: 50px">
        <fmt:message key="error.wrongPermissionExplanation"/>!
        <br><br><br>
        <a href="/signOut" class="btn"><fmt:message key="label.mainMenu.signOut"/></a>
    </div>
</div>
</body>
</html>
