<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<jsp:include page='base.jsp'/>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="repair_vocab"/>

<html>
<head>
    <title>Authorisation</title>
    <style>
        .form {
            height: 100px;
            width: 400px;
            padding: 30px 55px 100px;
        }
    </style>
</head>
<body>

<div class="box">
    <h3><fmt:message key="label.mainMenu.signIn"/></h3><hr align="left">
    <form method="post" action="/authorisation">
    <table class="form">
        <tr>
            <td width="40%">
                <fmt:message key="label.authorisation.email"/>: <br><br>
                <fmt:message key="label.authorisation.password"/>: <br>
                <br>
            </td>
            <td style="float: left; padding: 0px">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <input type="text" name="username"><br><br>
                    <input type="password" name="password"><br>
            </td>
        </tr>
        <tr>
            <td colspan="2" height="30px">
                <c:choose>
                    <c:when test="${fail eq true}">
                        <p class="error"><fmt:message key="error.wrongInput.incorrectData"/></p>
                    </c:when>
                    <c:when test="${emptyField eq true}">
                        <p class="error"><fmt:message key="error.wrongInput.allFieldsRequired"/></p>
                    </c:when>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <button class="btn" style="float: right"><span><fmt:message key="label.mainMenu.signIn"/></span></button>
            </td>
        </tr>
    </table>
    </form>
</div>

</body>
</html>
