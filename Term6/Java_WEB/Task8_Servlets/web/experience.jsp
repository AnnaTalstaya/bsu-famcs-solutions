<%--
  Created by IntelliJ IDEA.
  User: Anna Talstaya
  Date: 29.05.2019
  Time: 0:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Experience</title>
    <link href="style/style.css" rel="stylesheet">
</head>
<body>
<div class="form-style">
    <h1>Describe your experience</h1>
    <form method="post" action="${pageContext.servletContext.contextPath}/Experience">
        <input type="text" name="experience" value="${sessionScope.get("experience")}" placeholder="Your experience" required/>
        <input type="submit" value="Submit"/>
    </form>
    <button onclick="location.href='${pageContext.servletContext.contextPath}/Education'">Back</button>
</div>
</body>
</html>
