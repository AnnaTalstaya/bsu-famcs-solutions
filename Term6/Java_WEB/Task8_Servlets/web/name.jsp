<%--
  Created by IntelliJ IDEA.
  User: Anna Talstaya
  Date: 29.05.2019
  Time: 0:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Name</title>
    <link href="style/style.css" rel="stylesheet">
</head>
<body>
<div class="form-style">
    <h1>Enter your name</h1>
    <form method="post" action="${pageContext.servletContext.contextPath}/Name">
        <input type="text" name="userName" value="${sessionScope.get("name")}" placeholder="Your Name" required/>
        <input type="submit" value="Next"/>
    </form>
</div>
</body>
</html>
