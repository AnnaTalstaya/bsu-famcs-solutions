<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 29.05.2019
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Students</title>
</head>
<body>
<table class="students">
    <thead>
    <tr>
        <th>Id</th>
        <th>Full name</th>
        <th>Course</th>
        <th>Group</th>
        <th>Average score</th>
        <th>From village</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${requestScope.students}" var="student" varStatus="loop">
        <tr>
            <td> ${student.getIdStudent()}</td>

            <td><a href="">${student.getFullName()}</a></td>
            <td>${student.getCourse()}</td>
            <td>${student.getGroup()}</td>
            <td>${student.getAverageScore()}</td>
            <td>
                <c:if test="${student.isFromVillage()}">
                    <input type="checkbox" checked disabled>
                </c:if>
                <c:if test="${student.isFromVillage()}">
                    <input type="checkbox" checked disabled>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
