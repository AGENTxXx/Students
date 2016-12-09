<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="ru.innopolis.models.Student" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 24.11.2016
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="head.jsp" />
    <title>Список студентов</title>
</head>
<body>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <div class="modal fade" id="regModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="regModalLabel">Регистрация</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-groupNum">
                            <label for="reg_fio">ФИО</label>
                            <input type="text" class="form-control" id="reg_fio" name="reg_fio" placeholder="ФИО">
                        </div>
                        <div class="form-groupNum">
                            <label for="reg_sex">Пол</label>
                            <select name="reg_sex" id="reg_sex"  class="form-control">
                                <option value="man" selected="selected">Мужской</option>
                                <option value="woman">Женский</option>
                            </select>
                        </div>
                        <div class="form-groupNum">
                            <label for="reg_group">Группа</label>
                            <input type="text" class="form-control" id="reg_group" name="reg_group" placeholder="Группа">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                        <button type="button" class="btn btn-primary" onclick="studentRegistrationQuery()">Создать</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="editStudentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="editStudentModalLabel">Редактирование пользователя</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" class="form-control" id="id_edit_student" name="id_edit_student">
                        <div class="form-groupNum">
                            <label for="reg_fio">ФИО</label>
                            <input type="text" class="form-control" id="edit_fio" name="edit_fio" placeholder="ФИО">
                        </div>
                        <div class="form-groupNum">
                            <label for="edit_sex">Пол</label>
                            <select name="edit_sex" class="form-control" id="edit_sex">
                                <option value="man">Мужской</option>
                                <option value="woman">Женский</option>
                            </select>
                        </div>
                        <div class="form-groupNum">
                            <label for="edit_group">Группа</label>
                            <input type="text" class="form-control" id="edit_group" name="edit_group" placeholder="Группа">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                        <button type="button" class="btn btn-primary" onclick="studentEditQuery()">Сохранить</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="removeStudentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="deleteStudentModalLabel">Удаление студента </h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" class="form-control" id="id_delete_student" name="id_delete_student">
                    Вы уверены что хотите удалить студента <span id="delete_student_name"></span>?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Нет</button>
                    <button type="button" class="btn btn-primary" onclick="removeStudentQuery()">Да</button>
                </div>
            </div>
        </div>
    </div>
    </sec:authorize>

    <jsp:include page="logout.jsp" />
    <div style="padding:50px">
    <a href="/Stud/students"><button type="button" class="btn btn-primary">Список студентов</button></a>
    <a href="/Stud/lections"><button type="button" class="btn btn-primary">Список лекций</button></a>
    </div>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <button type="button" class="btn btn-primary"  data-toggle="modal" data-target="#regModal" >Добавить студента</button>
    </sec:authorize>
    <h3>Список студентов</h3>
    <table>
        <tbody id="user_list">
        <tr>
            <th style="width:300px;">ФИО</th>
            <th style="width:100px;">Пол</th>
            <th style="width:100px;">Группа</th>
            <th style="width:100px;">Посещений</th>
            <th style="width:300px;">Действие</th>
        </tr>

    <%
        List<Student> students = (List<Student>)request.getAttribute("students");
        for (Student student : students ) {
            request.setAttribute("id", student.getId());
            request.setAttribute("name", student.getFio());
            request.setAttribute("sex", student.getSex());
            request.setAttribute("groupNum", student.getGroupNum());
            request.setAttribute("lectionCount", student.getLectionCount());

            %>
            <jsp:include page="./studentTpl.jsp" />
            <%
        }
    %>
        </tbody>
    </table>
</body>
</html>
