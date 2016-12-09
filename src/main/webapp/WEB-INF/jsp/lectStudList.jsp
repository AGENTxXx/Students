<%@ page import="ru.innopolis.models.Lection" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 24.11.2016
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="head.jsp" />
    <title>Список лекций</title>
</head>
<body>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <div class="modal fade" id="addStudentLectionLectionModal" tabindex="-1" role="dialog" aria-labelledby="addStudentLectionLectionModal">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="addStudentLectionLectionModalLabel">Добавление лекции для студента</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" class="form-control" id="student_id" name="student_id">
                        <select name="lectionList" id="lectionList" class="form-control">
                            <option value="">Выберите лекцию</option>
                        </select>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                        <button type="button" class="btn btn-primary" onclick="addStudentToLection()">Добавить</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="removeStudentLectionModal" tabindex="-1" role="dialog" aria-labelledby="removeStudentLectionModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="removeStudentLectionModalLabel">Удаление лекции у студента </h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" class="form-control" id="id_delete_lection" name="id_delete_lection">
                    Вы уверены что хотите удалить лекцию <span id="delete_lection_name"></span>?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Нет</button>
                    <button type="button" class="btn btn-primary" onclick="removeStudentFromLectionQuery()">Да</button>
                </div>
            </div>
        </div>
    </div>
    </sec:authorize>
    <jsp:include page="logout.jsp" />
    <div style="padding:50px">
        <a href="<c:url value="/" />students"><button type="button" class="btn btn-primary">Список студентов</button></a>
        <a href="<c:url value="/" />lections"><button type="button" class="btn btn-primary">Список лекций</button></a>
    </div>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <button type="button" class="btn btn-primary" onclick="getAllLections(<%=request.getAttribute("id")%>)" >Добавить посещение</button>
    </sec:authorize>
    <h3>
        Посещенные лекции студеном "<%=request.getAttribute("fio")%>":
    </h3>
    <table>
        <tbody id="lection_list">
        <tr>
            <th style="width:100px;">Дата</th>
            <th style="width:300px;">Тема</th>
            <th style="width:200px;">Действие</th>
        </tr>

    <%
        List<Lection> lections = (List<Lection>)request.getAttribute("lections");
        for (Lection lection : lections ) {
            request.setAttribute("id", lection.getId());
            request.setAttribute("date", lection.getDate());
            request.setAttribute("theme", lection.getTheme());
            %>
            <jsp:include page="./lectStudTpl.jsp" />
            <%
        }
    %>
        </tbody>
    </table>
</body>
</html>
