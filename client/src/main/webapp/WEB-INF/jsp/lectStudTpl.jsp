<%@ page import="ru.innopolis.common.models.Lection" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Lection lection = (Lection)request.getAttribute("lection");%>
<tr id="lection_<%=lection.getId()%>" data-lection-id="<%=lection.getId()%>">
    <td id="date_<%=lection.getId()%>"><%=lection.getDate()%></td>
    <td id="theme_<%=lection.getId()%>"><%=lection.getTheme()%></td>
    <td>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <button type="button" class="btn btn-danger" onclick="removeStudentFromLection(<%=lection.getId()%>)">
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
            </button>
        </sec:authorize>
    </td>
</tr>