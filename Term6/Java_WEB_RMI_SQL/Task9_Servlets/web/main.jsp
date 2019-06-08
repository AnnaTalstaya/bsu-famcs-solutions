<%--
  Created by IntelliJ IDEA.
  User: Anna Talstaya
  Date: 06.06.2019
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Main page</title>
    <link rel="stylesheet" href="style/main.css">

</head>
<body>

<jsp:include page="header.jsp"/>

<div class="sections" id="main">
    <section style="position: absolute; margin-left: 0px; top: 1px; left: 0px; width: 100%; " class="strc1">
        <div style="width:100%;height:742px;background-color:rgba(255, 255, 255, 1);top:0;position:fixed">
            <div style="width:100%;height:100%;position:absolute"></div>
        </div>

        <div style="position: fixed; pointer-events: none; top: 0px; transform-style: preserve-3d; width: 100%; height: 742px; left: 0px;"
             data-effect="BackgroundParallax" data-fitting="fill" data-align="center" class="strc1inlineContent">
            <div data-type="image"
                 style="transform: translate3d(0px, 17.6px, 0px); position: relative; width: 100%; height: 742px;">
                <img src="${pageContext.servletContext.contextPath}/images/main.jpg" data-type="image"
                     style="width: 100%; height: 742px; object-fit: cover;">
            </div>
        </div>

        <div style="width:100%;position:absolute;top:0;bottom:0" class="strc1inlineContent">

            <div style="left: 540px; width: 520px; position: absolute; margin-left: calc((100% - 800px) * 0.5); top: 163px; height: 250px;">
                <div class="style-io2bebd6bg"></div>
                <div class="style-io2bebd6inlineContent"></div>

                <div style="left: 10px; width: 500px; position: absolute; margin-left: calc((100% - 500px) * 0.5); top: 30px;"
                     class="text">
                    <h1 style="font-size:51px; line-height:1.2em;" class="font_0">
                        <span style="font-size:81px; color:#FFFFFF;"><c:out value="NOTEBOOK"/></span>
                    </h1>
                </div>

                <div data-packed="true"
                     style="left: 16px; width: 512px; position: absolute; margin-left: calc((100% - 500px) * 0.5); top: 160px;"
                     class="text">
                    <h4 style="font-size:33px; line-height:1.2em; " class="font_4">
                        <span style="font-weight:bold; font-size:33px;" class="color_11">Welcome to our website!</span>
                    </h4>
                </div>

            </div>
        </div>

    </section>

</div>

</body>
</html>
