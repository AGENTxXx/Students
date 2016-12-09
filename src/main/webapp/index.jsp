<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 24.11.2016
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap-theme.css" />">
    <script src="<c:url value="/resources/js/jquery-3.1.1.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.js" />"></script>
    <script src="<c:url value="/resources/js/core.js" />"></script>
    <title>Система учёта посещений студентами</title>
  </head>
  <body>
  <div style="padding:50px">
    <a href="/Stud/students"><button type="button" class="btn btn-primary">Список студентов</button></a>
    <a href="/Stud/lections"><button type="button" class="btn btn-primary">Список лекций</button></a>
  </div>
  Сайт посвящён студентам по система учёта
  </body>
</html>
