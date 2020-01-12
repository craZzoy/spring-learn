<%--
  Created by IntelliJ IDEA.
  User: zwz
  Date: 2019/5/18
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--使用Spring的jsp标签-->
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!--使用Spring的通用标签标签-->
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/resources/style.css"/>
</head>
<body>
<h1>Register</h1>

<%--<form method="post">
    First Name：<input type="text" name="firstName" /> <br>
    Last Name：<input type="text" name="lastName" /> <br>
    UserName：<input type="text" name="username" /> <br>
    Password：<input type="text" name="password" /> <br>

    <input type="submit" value="Register">
</form>--%>

<sf:form method="post" commandName="newSpittle">
    <sf:errors path="*" element="div" cssClass="errors"></sf:errors>

    <sf:label path="firstname" cssErrorClass="error">First Name：</sf:label>
    <sf:input path="firstname"/>
    <sf:errors path="firstname" cssClass="error"></sf:errors><br>

    <sf:label path="lastname" cssErrorClass="error">Last Name：</sf:label>
    <sf:input path="lastname"/>
    <sf:errors path="lastname" cssClass="error"></sf:errors><br>


    <sf:label path="username" cssErrorClass="error">UserName：</sf:label>
    <sf:input type="text" path="username"/>
    <sf:errors path="username" cssClass="error"></sf:errors><br>

    <sf:label path="password" cssErrorClass="error">Password：</sf:label>
    <sf:input type="text" path="password"/>
    <sf:errors path="password" cssClass="error"></sf:errors><br>

    <input type="submit" value="Register">
</sf:form>
</body>
</html>
