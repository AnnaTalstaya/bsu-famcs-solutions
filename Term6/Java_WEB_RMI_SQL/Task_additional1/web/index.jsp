<%--
  Created by IntelliJ IDEA.
  User: Anna Talstaya
  Date: 05.06.2019
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp"%>
<%@ taglib prefix="ctg" uri="customtags" %>

<html>
  <head>
    <title>Hi</title>
  </head>
  <body>
  <ctg:letter type="Literature" image="${pageContext.servletContext.contextPath}/images/letter.jpg">
      <ctg:main color="#f000ff">Congratulation</ctg:main>
      <ctg:signature color="#00ff00">Anna Talstaya</ctg:signature>
  </ctg:letter>
  <ctg:main color="#f000ff">Congratulation</ctg:main>
  </body>
</html>
