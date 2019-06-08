<%--
  Created by IntelliJ IDEA.
  User: Anna Talstaya
  Date: 05.06.2019
  Time: 7:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="style/header.css">
<header class="header-style">
    <div style="height: 100%;  margin-left:150px;">

        <nav style="height: 100%">
            <nav>
                <a href="${pageContext.servletContext.contextPath}/main">Main</a>
                <a href='${pageContext.servletContext.contextPath}/main?notebook=y'>Notebook</a>
                <a href="${pageContext.servletContext.contextPath}/main?add=y">Add new person</a>
            </nav>
        </nav>

    </div>

    <div style="left: 870px; width: 277px; position: absolute; margin-left: calc((100% - 980px) * 0.5); top: 5px;"
         class="text-header">
        <a href="tel:+375 44 734 69 54" style=" text-decoration: none;  color: rgb(85, 26, 139); font-size:24px;">+375
            44 734 69 54</a>
        <p style="font-size:16px; color: rgb(85, 26, 139); margin-left:10px; " class="phone">Call us</p>

    </div>

</header>
