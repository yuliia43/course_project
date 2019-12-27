<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<jsp:include page='base.jsp'/>


<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="repair_vocab"/>

<html>
<head>
    <title>Applications</title>
</head>
<body>
<div class="box">
    <h3><fmt:message key="label.mainMenu.viewApplications"/></h3>
    <hr>
    <c:if test="${applications.size() != 0}">
        <c:if test="${notChecked}">
            <p class="error">
                <fmt:message key="error.wrongInput.NothingChosen"/></p>
        </c:if>
    <form method="post">
        <security:csrfInput/>
        <table>
            <tr>
                <th></th>
                <th><fmt:message key="label.applications.repairDetails"/></th>
                <th><fmt:message key="label.applications.price"/></th>
            </tr>
            <c:forEach items="${applications}" var="application" varStatus="loop">
                <tr>
                    <td><input name="applicationId" type="checkbox"
                               value="${application.getApplicationId()}"></td>
                    <td>${application.getRepairDetails()}</td>
                    <td>${application.getPrice()}</td>
                </tr>
            </c:forEach>
        </table>
        <button><fmt:message key="label.applications.takeApplication"/></button>
    </form>
    </c:if>
    <c:if test="${applications.size() == 0}">
        <fmt:message key="label.applications.noWorkLeft"/>.
    </c:if>
</div>
</body>
</html>
