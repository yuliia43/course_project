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
    <script>
        function openMenu(id) {
            var selector = this.document.getElementsByName('selector');
            switch (selector.item(id).value) {
                case ("confirm"): {
                    var inputList = Array.prototype.slice.call(selector);
                    inputList.forEach(closeItems);
                    selector.item(id).value = 'confirm';
                    var confirmation = this.document.getElementsByName('confirmationDiv');
                    confirmation.item(id).style.display = 'block';
                    break;
                }
                case ("reject"): {
                    var inputList = Array.prototype.slice.call(selector);
                    inputList.forEach(closeItems);
                    selector.item(id).value = 'reject';
                    var rejection = this.document.getElementsByName('rejectionDiv');
                    rejection.item(id).style.display = 'block';
                    break;
                }
                case ("empty"): {
                    break;
                }
            }
        }

        function closeItems(value, index, ar) {
            document.getElementsByName('confirmationDiv').item(index).style.display = 'none';
            document.getElementsByName('rejectionDiv').item(index).style.display = 'none';
            document.getElementsByName('selector').item(index).value = 'empty';
        }
    </script>
</head>
<body>
<div class="box">
    <h3><fmt:message key="label.mainMenu.viewApplications"/></h3>
    <c:if test="${emptyFields}">
        <p class="error">
            <fmt:message key="error.wrongInput.allFieldsRequired"/></p>
    </c:if>
    <c:if test="${applications.size() != 0}">
        <table>
            <tr>
                <th><fmt:message key="label.applications.repairDetails"/></th>
            </tr>
            <c:forEach items="${applications}" var="application" varStatus="loop">
                <tr>
                    <td align="center">${application.getRepairDetails()}</td>
                    <td align="center">
                        <select name="selector" onchange="openMenu(${loop.index})">
                            <option value="empty"></option>
                            <option value="confirm"><fmt:message key="label.newApplication.confirm"/></option>
                            <option value="reject"><fmt:message key="label.newApplication.reject"/></option>
                        </select>
                        <div name="confirmationDiv" style="background-color: #bdc3c7; position: fixed; display: none">
                            <c:if test="${priceEmpty}">
                                <p class="error"><fmt:message key="error.wrongInput.incorrectName"/></p>
                            </c:if>
                            <form method="post">
                                <security:csrfInput/>
                                <table>
                                    <tr>
                                        <td>
                                            <fmt:message key="label.applications.price"/>: <br>
                                        </td>
                                        <td>
                                            <input name="price" type="number">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <fmt:message key="label.applications.confirmationDetails"/>:
                                        </td>
                                        <td>
                                            <textarea name="details" rows="3" cols="25"></textarea>
                                        </td>
                                    </tr>
                                </table>
                                <input type="hidden" name="applicationId" value="${application.getApplicationId()}">
                                <button name="confirm" value="true" style="float: right" onclick="this.form.submit()">
                                    <fmt:message
                                            key="label.newApplication.confirm"/></button>
                            </form>
                        </div>
                        <div name="rejectionDiv" style="background-color: #bdc3c7; display: none">
                            <c:if test="${detailsEmpty}">
                                <p class="error"><fmt:message key="error.wrongInput.incorrectName"/></p>
                            </c:if>
                            <form method="post">
                                <security:csrfInput/>
                                <table>
                                    <td>
                                        <fmt:message key="label.applications.rejectDetails"/>:
                                    </td>
                                    <td>
                                        <textarea name="details" rows="3" cols="25"></textarea>
                                    </td>
                                    </tr>
                                </table>
                                <input type="hidden" name="applicationId" value="${application.getApplicationId()}">
                                <button name="reject" value="true" style="float: right" onclick="this.form.submit()">
                                    <fmt:message key="label.newApplication.reject"/>
                                </button>
                            </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${applications.size() == 0}">
        <fmt:message key="label.applications.noWorkLeft"/>.
        <fmt:message key="label.applications.goHome"/>!
    </c:if>
</div>
</body>
</html>
