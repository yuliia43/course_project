<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='../pages/base.jsp'/>


<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="repair_vocab"/>

<html>
<head>
    <title>SQLException</title>
</head>
<body>
<div class="box">
    <h3><fmt:message key="error"/></h3>
    <hr>
    <fmt:message key="error.SQLException"/>
</div>
</body>
</html>
