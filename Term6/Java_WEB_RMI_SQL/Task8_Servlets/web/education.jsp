<%@ page import="by.talstaya.task08.web.exception.CustomException" %>
<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.regex.Matcher" %><%--
  Created by IntelliJ IDEA.
  User: Anna Talstaya
  Date: 29.05.2019
  Time: 0:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp"%>

<%
    String dateOfBirth = String.valueOf(session.getAttribute("dateOfBirth"));
    final String STRING_REGEX_DATE = "^((0?[1-9])|([12]\\d)|(3[01]))[.-/](0?[1-9]|1[12])[.-/]((000[1-9])|(00[1-9]{2})|(0[1-9]{3})|([1-9]\\d{3}))$";

    Pattern pattern = Pattern.compile(STRING_REGEX_DATE);
    Matcher matcher = pattern.matcher(dateOfBirth);
    if (!matcher.find()) {
        throw new CustomException("Incorrect format of date!");
    }

%>

<html>
<head>
    <title>Education</title>
    <link href="style/style.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/header.jsp"/>

<div class="form-style">
    <h1>Describe your education</h1>
    <form method="post" action="${pageContext.servletContext.contextPath}/Education">
        <input type="text" name="education" value="${sessionScope.get("education")}" placeholder="Your education" required/>
        <input type="submit" value="Next"/>
    </form>
    <button onclick="location.href='${pageContext.servletContext.contextPath}/DateOfBirth'">Back</button>
</div>
</body>
</html>
