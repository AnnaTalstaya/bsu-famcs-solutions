<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 07.06.2019
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
<form method="post">
    <input class="form-control mr-sm-2" type="text" name = "firstName" value="${param.firstName}" placeholder="First name" aria-label="Search">
    <input class="form-control mr-sm-2" type="text" name = "surname" value="${param.surname}" placeholder="Surname" aria-label="Search">
    <input class="form-control mr-sm-2" type="text" name = "mobileNumber" value="${param.mobileNumber}" placeholder="Mobile number" aria-label="Search">
    <button class="btn btn-primary" type="submit" style="margin-left: 10px;">Add</button>
</form>

</body>
</html>
