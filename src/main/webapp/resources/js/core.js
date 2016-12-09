/**
 * Created by Administrator on 23.11.2016.
 */

function addStudentToLection() {
    var query = new Object();
    query.method = "add";
    query.lection_id = $("#lectionList").val();
    query.student_id = $("#student_id").val();

    sendAjax("/Stud/students/lection/add",query,addStudentFromLectionAnswer,ErrorRequest);
}

function addStudentFromLectionAnswer(method, data) {
    var result = JSON.parse(data);
    if (result.success == true) {
        var append = '<tr id="lection_'+result.id+'" data-lection-id="'+result.id+'">' +
                    '<td id="date_'+result.id+'">'+lections[result.lection_id].date +'</td>' +
                    '<td id="theme_'+result.id+'">'+lections[result.lection_id].theme+'</td>' +
                    '<td>' +
                        '<button type="button" class="btn btn-danger" onclick="removeStudentFromLection('+result.id+')">' +
                            '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                        '</button>' +
                    '</td>' +
                '</tr>';
        $("#lection_list").append(append);
        $("#addStudentLectionLectionModal").modal('hide');
    }
}

function removeStudentFromLection(id) {
    $("#id_delete_lection").text(id);
    $("#delete_lection_name").text($("#theme_"+id).text());
    $("#removeStudentLectionModal").modal('toggle');
}

function removeStudentFromLectionQuery() {
    var id = $("#id_delete_lection").text();
    var query = new Object();
    query.method = "remove";

    sendAjax("/Stud/students/lection/remove/" + id,query,removeStudentFromLectionAnswer,ErrorRequest);
}

function removeStudentFromLectionAnswer(method, data) {
    var result = JSON.parse(data);
    if (result.success == true) {
        $("#lection_" + result.id).remove();

        $('#removeStudentLectionModal').modal('hide');
    }
}

function getAllLections(id) {
    var query = new Object();
    query.method = "getLections";
    $("#student_id").val(id);
    sendAjax("/Stud/lections/forstudent/" + id,query,getAllLectionsAnswer,ErrorRequest);
}

var lections;
function getAllLectionsAnswer(method, data) {
    var result = JSON.parse(data);
    if (result.success == true) {
        lections = null;
        lections = new Object();
        $("#lectionList").empty();
        $("#lectionList").append('<option value="">Выберите лекцию</option>');
        for(key in result.lections) {
            lections[result.lections[key].id] = result.lections[key];
            $("#lectionList").append('<option value="'+result.lections[key].id+'">'+result.lections[key].theme+'</option>');
        }
        $('#addStudentLectionLectionModal').modal('show');
    }
}

function studentRegistrationQuery() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    var query = new Object();
    query.fio = $("#reg_fio").val();
    query.sex = $("#reg_sex").val();
    query.group = $("#reg_group").val();
    query.token = token;
    query.header = header;

    query.method = "add";

    sendAjax("/Stud/students/add",query,regAnswer,ErrorRequest);
}

function regAnswer(method, data) {
    var result = JSON.parse(data);
    if (result.success == true) {
        var append = '<tr id="student_'+result.user_id+'">'+
                        '<td id="fio_'+result.user_id+'">'+$("#reg_fio").val()+'</td>'+
                        '<td id="sex_'+result.user_id+'">'+$("#reg_sex").val()+'</td>'+
                        '<td id="group_'+result.user_id+'">'+$("#reg_group").val()+'</td>'+
                        '<td>0</td>'+
                        '<td>'+
                            '<button type="button" class="btn btn-info" onclick="editStudent('+result.user_id+')">'+
                                '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>'+
                            '</button> '+
                            '<a href="/Stud/students/lections/'+result.user_id+'"><button type="button" class="btn btn-info" >'+
                                'Просмотр лекций'+
                            '</button></a> '+
                            '<button type="button" class="btn btn-danger" onclick="removeStudent('+result.user_id+')">'+
                                '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>'+
                            '</button>'+
                        '</td>'+
                    '</tr>';
        $("#user_list").append(append);

        $("#reg_fio").val("");
        $("#reg_sex").val("man");
        $("#reg_group").val("");

        $('#regModal').modal('toggle');
    }
}



function studentEditQuery() {
    var id = $("#id_edit_student").text()
    var query = new Object();
    query.fio = $("#edit_fio").val();
    query.sex = $("#edit_sex").val();
    query.group = $("#edit_group").val();
    query.method = "update";

    sendAjax("/Stud/students/update/"+id,query,editAnswer,ErrorRequest);
}

function editAnswer(method, data) {
    var result = JSON.parse(data);
    if (result.success == true) {
        alert("Студент отредактирован!");

        $("#fio_" + result.user_id).text($("#edit_fio").val());
        $("#sex_" + result.user_id).text($("#edit_sex").val());
        $("#group_" + result.user_id).text($("#edit_group").val());

        $('#editStudentModal').modal('toggle');
    }
}

function removeStudentQuery() {

    var query = new Object();
    query.id = $("#id_delete_student").val();
    query.method = "delete";

    sendAjax("/Stud/students/remove/"+query.id,query,removeAnswer,ErrorRequest);

}

function sendAjax(url,data, succesFunc, failFunc) {
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        async:false,
        dataType: "text",
        //dataType: "json",
        beforeSend:function(xhr){
            if (data.header != null && data.token != null) {
                xhr.setRequestHeader(data.header, data.token);
            }
        },
        success: function(data){
            succesFunc(data.method, data);
        },
        fail: function(errMsg) {
            failFunc(errMsg);
        }
    });
}

function ErrorRequest(errMsg) {
    alert(errMsg);
}

function removeAnswer(method, data) {
    var result = JSON.parse(data);
    if (result.success == true) {
        $("#student_" + result.user_id).remove();
        $('#removeStudentModal').modal('toggle');
    }
}

function editStudent(id) {
    $("#id_edit_student").text(id);
    $("#edit_fio").val($("#fio_"+ id).text());
    $("#edit_sex").val($("#sex_"+ id).text());
    $("#edit_group").val($("#group_"+ id).text());
    //$('#editStudentModal').modal('toggle');
    $('#editStudentModal').modal('show');
}

function removeStudent(id) {
    $("#delete_student_name").text($("#fio_" + id).text());
    $("#id_delete_student").val(id);
    $('#removeStudentModal').modal('show');
}


function lectionAddQuery() {
    var query = new Object();
    query.date = $("#date").val();
    query.theme = $("#theme").val();
    query.method = "add";

    sendAjax("/Stud/lections/add",query,lectionAddAnswer,ErrorRequest);
}

function lectionAddAnswer(method, data) {

    var result = JSON.parse(data);
    if (result.success == true) {
        var append = '<tr id="lection_'+result.lection_id+'">'+
                        '<td id="date_'+result.lection_id+'">'+$("#date").val()+'</td>'+
                        '<td id="theme_'+result.lection_id+'">'+$("#theme").val()+'</td>'+
                        '<td>' +
                            '<button type="button" class="btn btn-info" onclick="editLection('+result.lection_id+')">' +
                                '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>' +
                            '</button> ' +
                            '<button type="button" class="btn btn-danger" onclick="removeLection('+result.lection_id+')">' +
                                '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                            '</button>' +
                        '</td>' +
                    '</tr>'
        $("#lection_list").append(append);
        $('#addLectionModal').modal('hide');
    }
}

function removeLection(id) {
    $("#id_delete_lection").text(id);
    $("#delete_lection_name").text($("#theme_"+id).text());
    $('#removeLectionModal').modal('show');
}

function removeLectionQuery() {
    var id = $("#id_delete_lection").text();
    var query = new Object();
    query.method = "remove";

    sendAjax("/Stud/lections/remove/" + id,query,lectionRemoveAnswer,ErrorRequest);
}

function lectionRemoveAnswer(method, data) {
    var result = JSON.parse(data);
    if (result.success == true) {
        $("#student_" + result.lection_id).remove();
        $('#removeLectionModal').modal('hide');
    }
}

function editLection(id) {
    $("#id_edit_lection").text(id);
    $("#edit_date").val($("#date_"+ id).text());
    $("#edit_theme").val($("#theme_"+ id).text());
    $('#editLectionModal').modal('show');
}

function lectionEditQuery() {
    var id = $("#id_edit_lection").text();
    var query = new Object();
    query.date = $("#edit_date").val();
    query.theme = $("#edit_theme").val();
    query.method = "update";

    sendAjax("/Stud/lections/update/" + id,query,lectionEditAnswer,ErrorRequest);
}

function lectionEditAnswer(method, data) {
    var result = JSON.parse(data);
    if (result.success == true) {
        $("#date_"+ result.lection_id).text($("#edit_date").val());
        $("#theme_"+ result.lection_id).text($("#edit_theme").val());
        $('#editLectionModal').modal('toggle');
    }
}