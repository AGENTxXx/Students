<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<tr id="student_<%=request.getAttribute("id")%>">
    <td id="fio_<%=request.getAttribute("id")%>"><%=request.getAttribute("name")%></td>
    <td id="sex_<%=request.getAttribute("id")%>"><%=request.getAttribute("sex")%></td>
    <td id="group_<%=request.getAttribute("id")%>"><%=request.getAttribute("groupNum")%></td>
    <td><%=request.getAttribute("lectionCount")%></td>
    <td>

        <a href="/Stud/students/lections/<%=request.getAttribute("id")%>">
            <button type="button" class="btn btn-success">
                Посещённые лекции
            </button>
        </a>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <button type="button" class="btn btn-info" onclick="editStudent(<%=request.getAttribute("id")%>)">
                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
            </button>
            <button type="button" class="btn btn-danger" onclick="removeStudent(<%=request.getAttribute("id")%>)">
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
            </button>
        </sec:authorize>
    </td>
</tr>
