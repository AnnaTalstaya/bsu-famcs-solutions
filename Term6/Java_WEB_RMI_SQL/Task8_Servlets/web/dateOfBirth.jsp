<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 04.06.2019
  Time: 1:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Date of birth</title>
    <link href="style/style.css" rel="stylesheet">
</head>
<body>

<jsp:include page="/header.jsp"/>

<div class="form-style">
    <h1>Enter date of birth</h1>
    <form method="post" action="${pageContext.servletContext.contextPath}/DateOfBirth">
        <input type="text" name="dateOfBirth" value="${sessionScope.get("dateOfBirth")}" placeholder="Your date of birth" required/>
        <input type="submit" value="Next"/>
    </form>
    <button onclick="location.href='${pageContext.servletContext.contextPath}/Name'">Back</button>
</div>

</body>
</html>
