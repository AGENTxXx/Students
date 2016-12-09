<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tr id="lection_<%=request.getAttribute("id")%>">
    <td id="date_<%=request.getAttribute("id")%>"><%=request.getAttribute("date")%></td>
    <td id="theme_<%=request.getAttribute("id")%>"><%=request.getAttribute("theme")%></td>
    <td>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <button type="button" class="btn btn-info" onclick="editLection(<%=request.getAttribute("id")%>)">
                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
            </button>
            <button type="button" class="btn btn-danger" onclick="removeLection(<%=request.getAttribute("id")%>)">
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
            </button>
        </sec:authorize>
    </td>
</tr>