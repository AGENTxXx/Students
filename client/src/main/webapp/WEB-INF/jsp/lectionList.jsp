<%@ page import="ru.innopolis.common.models.Lection" %>
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
        <div class="modal fade" id="addLectionModal" tabindex="-1" role="dialog" aria-labelledby="addLectionModal">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="addLectionModalLabel">Добавление лекции</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-groupNum">
                            <label for="date">Дата</label>
                            <input type="text" class="form-control" id="date" name="date" placeholder="Дата">
                        </div>
                        <div class="form-groupNum">
                            <label for="theme">Тема</label>
                            <input type="text" class="form-control" id="theme" name="theme" placeholder="Тема">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                        <button type="button" class="btn btn-primary" onclick="lectionAddQuery()">Добавить</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="editLectionModal" tabindex="-1" role="dialog" aria-labelledby="editLectionModal">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="editLectionModalLabel">Редактирование лекции</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" class="form-control" id="id_edit_lection" name="id_edit_lection">
                        <div class="form-groupNum">
                            <label for="edit_date">Дата</label>
                            <input type="text" class="form-control" id="edit_date" name="edit_date" placeholder="Дата">
                        </div>
                        <div class="form-groupNum">
                            <label for="edit_theme">Тема</label>
                            <input type="text" class="form-control" id="edit_theme" name="edit_theme" placeholder="Тема">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                        <button type="button" class="btn btn-primary" onclick="lectionEditQuery()">Сохранить</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="removeLectionModal" tabindex="-1" role="dialog" aria-labelledby="removeLectionModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="removeLectionModalLabel">Удаление лекции </h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" class="form-control" id="id_delete_lection" name="id_delete_lection">
                    Вы уверены что хотите удалить лекцию <span id="delete_lection_name"></span>?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Нет</button>
                    <button type="button" class="btn btn-primary" onclick="removeLectionQuery()">Да</button>
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
        <button type="button" class="btn btn-primary"  data-toggle="modal" data-target="#addLectionModal" >Добавить лекцию</button>
    </sec:authorize>
    <% if (request.getAttribute("lections") != null) { %>
    <h3>
        Список лекций
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
                request.setAttribute("lection", lection);
                %>
                <jsp:include page="./lectionTpl.jsp" />
                <%
            }


    %>
        </tbody>
    </table>
    <%
    }
    else {
            %>
            <h3>Список лекций пуст!</h3>
            <%
        }
    %>
</body>
</html>
