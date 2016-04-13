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
        	<div class="form-group">
            <P>User name:</P>
            <INPUT type="text" class="form-control" name="login" required>
            </div>
            <div class="form-group">
            <P>Password:</P>
          	<INPUT type="password" class="form-control" name="password" required>
            
            </div>
            <BUTTON class="btn btn-default" type="submit">Enter</BUTTON>
        </FORM>
        <A class="btn btn-default" role="button" href="index.html">Back</A>
</u:html>