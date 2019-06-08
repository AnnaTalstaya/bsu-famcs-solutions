<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 29.05.2019
  Time: 4:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
    <link href="style/style.css" rel="stylesheet">
</head>
<body>
<div class="form-style">
    <h1>Created person</h1>
    <p>Name: ${sessionScope.get("name")}</p>
    <br>
    <p>Education: ${sessionScope.get("education")}</p>
    <br>
    <p>Experience: ${sessionScope.get("experience")}</p>
    <button onclick="location.href='${pageContext.servletContext.contextPath}/Name'">Back to the beginning</button>
</div>
</body>
</html>
