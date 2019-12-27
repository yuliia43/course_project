<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='base.jsp'/>


<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="repair_vocab"/>

<html>
<head>
    <title>UserInfo</title>
    <style>
        .sexPicture {
            height: 200px;
            display: inline-block;
        }
    </style>
</head>
<body>
<div class="box">
    <h3><fmt:message key="label.mainMenu.userPage"/></h3>
    <hr align="left">
    <table>
        <tr>
            <td align="right">
                <fmt:message key="userInfo.firstName"/>: <br>
                <fmt:message key="userInfo.lastName"/>: <br>
                <fmt:message key="label.authorisation.email"/>: <br>
            </td>
            <td>
                <c:out value="${user.getFirstName()}"/> <br>
                <c:out value="${user.getLastName()}"/> <br>
                <c:out value="${user.getEmail()}"/> <br>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
