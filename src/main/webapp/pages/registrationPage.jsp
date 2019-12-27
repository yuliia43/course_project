<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='base.jsp'/>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="repair_vocab"/>

<html>
<head>
    <title>Registration</title>
</head>
<body>
<div class="box">
    <h3><fmt:message key="label.mainMenu.signUp"/></h3>
    <hr align="left">
    <form method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <table>
            <tr>
                <td valign="top">
                    <div style="margin-bottom: 13.5px"><fmt:message key="userInfo.firstName"/>*:</div>
                    <div style="margin-bottom: 13.5px"><fmt:message key="userInfo.lastName"/>*:</div>
                    <div style="margin-bottom: 13.5px"><fmt:message key="userInfo.role"/>*:</div>
                    <div style="margin-bottom: 13.5px"><fmt:message key="label.authorisation.email"/>*:</div>
                    <div style="margin-bottom: 13.5px"><fmt:message key="label.authorisation.password"/>*:</div>
                    <div style="margin-bottom: 13.5px"><fmt:message key="userInfo.confirmPassword"/>*:</div>
                </td>
                <td valign="top">
                    <input style="margin-bottom: 10px" name="firstName" value="${firstName}"><br>
                    <input style="margin-bottom: 10px" name="lastName" value="${lastName}"><br>
                    <span style="margin-left: -130px">
                    <input type="radio" ${role eq 'user' ? 'checked' : ''}
                           style="margin-left: 30px; margin-right: 0px; margin-bottom: 10px" class="radio"
                           name="role" value="user">
                    <fmt:message key="userInfo.role.user"/>
                <input type="radio" ${role eq 'manager' ? 'checked' : ''}
                       style="margin-left: 20px; margin-right: 0px; margin-bottom: 10px" class="radio"
                       name="role" value="manager">
                    <fmt:message key="userInfo.role.manager"/>
                <input type="radio" ${role eq 'master' ? 'checked' : ''}
                       style="margin-left: 20px; margin-right: 0px; margin-bottom: 10px" class="radio"
                       name="role" value="master">
                    <fmt:message key="userInfo.role.master"/>
                </span><br>
                    <input style="margin-bottom: 10px" name="email" value="${email}"><br>
                    <input style="margin-bottom: 10px" name="password" type="password" value="${password}"><br>
                    <input name="passwordConfirmation" type="password" value="${password2}">
                </td>
            </tr>
        </table>
        <c:if test="${fnWrong}">
            <p class="error"><fmt:message key="error.wrongInput.incorrectName"/></p>
        </c:if>
        <c:if test="${lnWrong}">
            <p class="error"><fmt:message key="error.wrongInput.incorrectSurname"/></p>
        </c:if>
        <c:if test="${emailWrong}">
            <p class="error"><fmt:message key="error.wrongInput.incorrectEmail"/></p>
        </c:if>
        <c:if test="${passwordsNotEqual}">
            <p class="error"><fmt:message key="error.wrongInput.passwordsNotEqual"/></p>
        </c:if>
        <c:if test="${emptyFields}">
            <p class="error">
                <fmt:message key="error.wrongInput.allFieldsRequired"/></p>
        </c:if>
        <button class="btn" style="float: right; margin-right: 100px"><fmt:message
                key="label.mainMenu.signUp"/></button>
    </form>
</div>
</body>
</html>
