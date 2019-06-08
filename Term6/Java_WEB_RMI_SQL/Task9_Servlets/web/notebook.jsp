<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 07.06.2019
  Time: 0:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Notebook</title>

    <link rel="stylesheet" href="style/notebook.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>

<form method="post">
    <div style="margin-top: 100px;">
        <%--<nav class="navbar navbar-light bg-light" >--%>
            <%--<form class="form-inline" method="post">--%>
                <%--<!-- Group of default radios - option 1 -->--%>
                <%--<div class="custom-control custom-radio" style="margin-left: 10px;" >--%>
                    <%--<input type="radio" class="custom-control-input" id="defaultGroupExample1" name="1" value="1">--%>
                    <%--<label class="custom-control-label" for="defaultGroupExample1">Search by surname</label>--%>
                <%--</div>--%>

                <%--<!-- Group of default radios - option 2 -->--%>
                <%--<div class="custom-control custom-radio" style="margin-left: 10px;">--%>
                    <%--<input type="radio" class="custom-control-input" id="defaultGroupExample2" name="2"  value="2">--%>
                    <%--<label class="custom-control-label" for="defaultGroupExample2">Search by mobile number</label>--%>
                <%--</div>--%>

                <%--<input class="form-control mr-sm-2" type="search" name = "query" value="${param.query}" placeholder="Search" aria-label="Search">--%>
                <%--<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search by surname</button>--%>
            <%--</form>--%>
        <%--</nav>--%>

        <%--<button class="btn btn-primary" type="submit" style="margin-left: 10px;">Show notebook</button>--%>
    </div>
</form>


<table class="table">

    <thead class="thead-dark">
    <tr>
        <th scope="col">Id</th>
        <th scope="col">First name</th>
        <th scope="col">Surname</th>
        <th scope="col">Mobile number</th>
        <th scope="col"></th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="person" items="${notebook}">
        <tr>
            <th scope="row">${person.id}</th>
            <td>${person.firstName}</td>
            <td><i><c:out value="${person.surname}"/></i></td>
            <td>${person.mobileNumber.mobileNumber}</td>
            <td>
                <form method="post">
                    <input type="hidden" name="delete" value="${person.id}">
                    <button type="submit">
                        Delete
                    </button>
                </form>

            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
