<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 29.05.2019
  Time: 4:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<html>
<head>
    <title>Result</title>
    <link href="style/style.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/header.jsp"/>

<div class="form-style">
    <ctg:result-info name="${sessionScope.get(\"name\")}"
                     dateOfBirth="${sessionScope.get(\"dateOfBirth\")}"
                     education="${sessionScope.get(\"education\")}"
                     experience="${sessionScope.get(\"experience\")}">Created person</ctg:result-info>
    <button onclick="location.href='${pageContext.servletContext.contextPath}/Name'">Back to the beginning</button>
</div>
</body>
</html>
