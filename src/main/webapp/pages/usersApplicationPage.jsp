<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<jsp:include page='base.jsp'/>


<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="repair_vocab"/>

<html>
<head>
    <title>My applications</title>
    <script>
        function openMenu(id) {
            var details = this.document.getElementsByName("details");
            if (details.item(id).style.display == 'none') {
                var inputList = Array.prototype.slice.call(details);
                inputList.forEach(closeItem);
                details.item(id).style.display = 'inline-block';
            } else
                details.item(id).style.display = 'none';
        }

        function closeItem(value, index, ar) {
            document.getElementsByName('details').item(index).style.display = 'none';
        }
    </script>
</head>
<body>
<div class="box">
    <h3><fmt:message key="label.mainMenu.myApplications"/></h3>
    <br>
    <c:if test="${applications.size() != 0}">
        <table>
            <tr>
                <th><fmt:message key="label.applications.repairDetails"/></th>
                <th><fmt:message key="label.applications.status"/></th>
                <th></th>
            </tr>
            <c:forEach items="${applications}" var="application" varStatus="loop">
                <tr>
                    <td align="center">${application.getRepairDetails()}</td>
                    <td align="center">
                        <c:choose>
                            <c:when test="${application.getStatus() eq 'created'}">
                                <fmt:message key="label.applications.status.created"/>
                            </c:when>
                            <c:when test="${application.getStatus() eq 'accepted'}">
                                <fmt:message key="label.applications.status.accepted"/>
                            </c:when>
                            <c:when test="${application.getStatus() eq 'refused'}">
                                <fmt:message key="label.applications.status.refused"/>
                            </c:when>
                            <c:when test="${application.getStatus() eq 'in_process'}">
                                <fmt:message key="label.applications.status.in_process"/>
                            </c:when>
                            <c:when test="${application.getStatus() eq 'finished'}">
                                <fmt:message key="label.applications.status.finished"/>
                            </c:when>
                        </c:choose>
                    </td>
                    <td>
                        <c:if test="${!(application.getStatus() eq 'created')}">
                            <button onclick="openMenu(${loop.index})"><fmt:message
                                    key="label.applications.viewDetails"/></button>
                        </c:if>

                        <div name="details" style="display: none">
                            <b><fmt:message key="userInfo.role.manager"/>:</b>
                                ${application.getManager().getFirstName()} ${application.getManager().getLastName()}
                            <br>
                            <c:if test="${application.getPrice() != null}">
                                <b><fmt:message key="label.applications.price"/>:</b>
                                ${application.getPrice()}
                                <br>
                            </c:if>
                            <c:if test="${(application.getManagerDetails() != null) && (application.getStatus() eq 'refused')}">
                                <b><fmt:message key="label.applications.rejectDetails"/>:</b>
                                ${application.getManagerDetails()}
                                <br>
                            </c:if>
                            <c:if test="${(application.getManagerDetails() != null) && !(application.getStatus() eq 'refused')}">
                                <b><fmt:message key="label.applications.viewDetails"/>:</b>
                                ${application.getManagerDetails()}
                                <br>
                            </c:if>
                            <c:set value="${application.getFeedback()}" var="feedbacks" scope="page"/>
                            <c:if test="${feedbacks != null}">
                                <c:if test="${feedbacks.getFeedback() != null}">
                                    <b><fmt:message key="label.mastersStats.feedback"/>:</b>
                                    ${feedbacks.getFeedback()}<br>
                                </c:if>
                                <c:if test="${feedbacks.getFeedback() == null}">
                                    <form method="post">
                                        <security:csrfInput/>
                                        <input type="hidden" name="feedbackId" value="${feedbacks.getFeedbackId()}">
                                        <textarea name="feedback" rows="3" cols="25"></textarea>
                                        <button onclick="this.form.submit()"><fmt:message
                                                key="label.feedbacks.send"/></button>
                                    </form>
                                </c:if>
                            </c:if>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${applications.size() == 0}">
        <fmt:message key="label.applications.noApplications"/>!
        <br>
        <a href="/user/newApplication" class="btn"><fmt:message key="label.mainMenu.newApplication"/></a>
    </c:if>
</div>
</body>
</html>
