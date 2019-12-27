<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='base.jsp'/>


<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="repair_vocab"/>

<html>
<head>
    <title>Masters</title>
</head>
<body>
<div class="box">
    <h3><fmt:message key="label.mainMenu.viewMasters"/></h3>
    <br>
    <c:if test="${applications.size() != 0}">
        <table>
            <tr>
                <th><fmt:message key="label.mastersStats.master"/></th>
                <th><fmt:message key="label.mastersStats.details"/></th>
                <th><fmt:message key="label.mastersStats.feedback"/></th>
            </tr>
            <c:forEach items="${applications}" var="application">
                <tr>
                    <td>
                        <c:set value="${application.getFeedback()}" var="feedback" scope="page"/>
                        <c:set value="${feedback.getMaster()}" var="master" scope="page"/>
                            ${master.getFirstName()} ${master.getLastName()}
                    </td>
                    <td>
                            ${application.getRepairDetails()}
                    </td>
                    <td>
                        <c:if test="${feedback.getFeedback() != null}">
                            ${feedback.getFeedback()}
                        </c:if>
                        <c:if test="${feedback.getFeedback() == null}">
                            -
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${applications.size() == 0}">
        <fmt:message key="label.mastersStats.noStats"/>!
    </c:if>
</div>
</body>
</html>
