<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Тест JSP" stylesheet="CSS/boot/bootstrap.min.css">

        <H1>Authorization</H1>
        <c:if test="${not empty param['message']}">
            <P style="color: red;">${param['message']}</P>
        </c:if>
        <FORM action="login.html" method="post">
            <P>User name:</P>
            <P><INPUT type="text" name="login" required></P>
            <P>Password:</P>
            <P><INPUT type="password" name="password" required></P>
            <P><BUTTON class="btn btn-default" type="submit">Enter</BUTTON></P>
            
        </FORM>
        <A class="btn btn-default" role="button" href="index.html">Back</A>
</u:html>