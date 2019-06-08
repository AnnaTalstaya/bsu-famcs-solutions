<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 29.05.2019
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope[\"lang\"]}"/>
<fmt:setBundle basename="localization.language"/>

<html>
<head>
    <title>Deals</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="css/contact.css" media="screen"/>
    <style>
        table{
            width: 100%;
            border-collapse: collapse; /* Убираем двойную рамку между ячейками */
            font-family: arial, sans-serif;
        }
        td, th{
            padding: 6px; /* Поля вокруг текста */
            text-align: left;
            border: 1px solid #dddddd; /* Параметры границы */
        }
        tr:nth-child(even) { /*Псевдокласс :nth-child используется для добавления стиля к элементам(tr) на основе нумерации в дереве элементов,even - применяется ко всем четным элементам*/
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<table class="table" style="margin-top:80px">
    <thead>
    <tr>
        <th><fmt:message key="idDeal"/></th>
        <th><fmt:message key="typeDeal"/></th>
        <th><fmt:message key="title"/></th>
        <th><fmt:message key="dateDeal"/></th>
        <th><fmt:message key="sumDeal"/></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${requestScope.deals}" var="deal" varStatus="loop">
        <tr>
            <td> ${deal.getIdDeal()}</td>
            <td> ${deal.getTypeDeal()}</td>
            <td> ${deal.getTitle()}</td>
            <td> ${deal.getDateDeal()}</td>
            <td> ${deal.getSumDeal()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:if test="${deals.size() > 0}">
    <h3>Total count = ${count}</h3>
</c:if>

<c:if test="${deals.size() > 0}">
    <h3>Total sum = ${sum}</h3>
</c:if>

<a href="${pageContext.servletContext.contextPath}/deals?lang=en">English</a>
<a href="${pageContext.servletContext.contextPath}/deals?lang=ru">Русский</a>
</body>
</html>
