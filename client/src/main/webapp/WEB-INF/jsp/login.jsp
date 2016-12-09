<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>Login Page</title>
<style>
.error {
    padding: 15px;
    margin-bottom: 20px;
    border: 1px solid transparent;
    border-radius: 4px;
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
}

.msg {
    padding: 15px;
    margin-bottom: 20px;
    border: 1px solid transparent;
    border-radius: 4px;
    color: #31708f;
    background-color: #d9edf7;
    border-color: #bce8f1;
}

#login-box {
    width: 500px;
    padding: 20px;
    margin: 100px auto;
    background: #fff;
    -webkit-border-radius: 2px;
    -moz-border-radius: 2px;
    border: 1px solid #000;
}
</style>
    <jsp:include page="head.jsp" />
</head>
<body onload='document.loginForm.username.focus();'>

    <div id="login-box">

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>

        <div class="container" style="width: 450px;">
        <form action="<c:url value="/j_spring_security_check"></c:url>" method="post" role="form">
            <div class="form-groupNum">
                <label for="j_username">User Name</label>
                <input type="text" class="form-control" id="j_username" name="j_username" placeholder="User Name">
            </div>
            <div class="form-groupNum">
                <label for="j_password">Password:</label>
                <input type="text" class="form-control" id="j_password" name="j_password" placeholder="Password">
            </div>
            <button type="submit" class="btn btn-primary">
                Log In
            </button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
      </form>
    </div>

</body>
</html>