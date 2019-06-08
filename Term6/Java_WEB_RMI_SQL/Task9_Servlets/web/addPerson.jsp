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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>


<div style="position: fixed; pointer-events: none; top: 0px; transform-style: preserve-3d; width: 100%; height: 742px; left: 0px;"
     data-effect="BackgroundParallax" data-fitting="fill" data-align="center" class="strc1inlineContent">
    <div data-type="image"
         style="transform: translate3d(0px, 17.6px, 0px); position: relative; width: 100%; height: 742px;">
        <img src="${pageContext.servletContext.contextPath}/images/add.jpg" data-type="image"
             style="width: 100%; height: 742px; object-fit: cover;">
    </div>
</div>

<form method="post" style="margin-top: 200px; margin-left: 550px; position: absolute;">
    <input class="form-control mr-sm-2" type="text" name = "firstName" value="${param.firstName}" placeholder="First name" aria-label="Search">
    <input class="form-control mr-sm-2" type="text" name = "surname" value="${param.surname}" placeholder="Surname" aria-label="Search">
    <input class="form-control mr-sm-2" type="text" name = "mobileNumber" value="${param.mobileNumber}" placeholder="Mobile number" aria-label="Search">
    <button class="btn btn-primary" type="submit">Add</button>
</form>

</body>
</html>
