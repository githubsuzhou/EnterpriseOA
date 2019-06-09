<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<jsp:include page="top.jsp"/>--%>
<table >
    <tbody>
    <c:forEach items="${list}" var="dept">
        <tr class="message-unread">

            <td>${dept.sn}</td>
            <td>${dept.name}</td>
            <td>${dept.address}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


