<%@ page import="ru.innopolis.common.models.Student" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<% Student student = (Student)request.getAttribute("student"); %>
<tr id="student_<%=student.getId()%>">
    <td id="fio_<%=student.getId()%>"><%=student.getFio()%></td>
    <td id="sex_<%=student.getId()%>"><%=student.getSex()%></td>
    <td id="group_<%=student.getId()%>"><%=student.getGroup()%></td>
    <td><%=student.getLectionCount()%></td>
    <td>

        <a href="<c:url value="/" />students/lections/<%=student.getId()%>">
            <button type="button" class="btn btn-success">
                Посещённые лекции
            </button>
        </a>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <button type="button" class="btn btn-info" onclick="editStudent(<%=student.getId()%>)">
                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
            </button>
            <button type="button" class="btn btn-danger" onclick="removeStudent(<%=student.getId()%>)">
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
            </button>
        </sec:authorize>
    </td>
</tr>
