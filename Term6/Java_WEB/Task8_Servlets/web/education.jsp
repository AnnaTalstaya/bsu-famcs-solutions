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
    <title>Education</title>
    <link href="style/style.css" rel="stylesheet">
</head>
<body>
<div class="form-style">
    <h1>Describe your education</h1>
    <form method="post" action="${pageContext.servletContext.contextPath}/Education">
        <input type="text" name="education" value="${sessionScope.get("education")}" placeholder="Your education" required/>
        <input type="submit" value="Next"/>
    </form>
    <button onclick="location.href='${pageContext.servletContext.contextPath}/Name'">Back</button>
</div>
</body>
</html>
