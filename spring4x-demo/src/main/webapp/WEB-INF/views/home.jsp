<%--
  Created by IntelliJ IDEA.
  User: zwz
  Date: 2019/5/15
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" session="false" %>
<!--使用Spring的通用标签-->
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Spittr</title>
    <link rel="stylesheet" type="text/css" href="/resources/style.css"/>
</head>
<body>
<%--<s:url value="" >--%>
<h1><s:message code="spittr.welcome"/></h1>
<%--<a href="<c:url value="/spittles"/>">spittles</a>
<a href="<c:url value="/spitter/register"/>">register</a>--%>
<%--<s:url value="/spitter/register" var="registerUrl"/>
<a href="${registerUrl}">register</a>

<s:url value="/spittles" htmlEscape="true" var="registerUrl" javaScriptEscape="true">
    <s:param name="max" value="60" />
    <s:param name="count" value="20" />
</s:url>

<script>
    //渲染为：上下文\/spittles\/?max=60&count=20
    var registerUrl = "{registerUrl}";
</script>

<s:escapeBody htmlEscape="true" javaScriptEscape="true">
    <h1>Hello</h1>
</s:escapeBody>--%>

</body>
</html>