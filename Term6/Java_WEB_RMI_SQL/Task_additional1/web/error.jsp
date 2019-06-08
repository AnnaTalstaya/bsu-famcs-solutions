<%--
  Created by IntelliJ IDEA.
  User: Anna Talstaya
  Date: 04.06.2019
  Time: 23:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Error Page</title>
</head>
<body>
<div style="padding-bottom: 30px;">
    <div style="float: left;">
        <img src="https://i.pinimg.com/originals/34/9a/dd/349add0e16d9b1d7531ade340766d402.png">
    </div>
    <div style="float: left;"><h1>Oops</h1><h2>${pageContext.exception.message}</h2></div>
</div>
</body>
</html>
