<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Тест JSP" stylesheet="CSS/boot/bootstrap.min.css">

    	<div class="form-group">
        <c:choose>
            <c:when test="${not empty user}">
                <A class="btn btn-default" role="button" href="logout.html"> ${user.login}&nbsp;&mdash; exit</A>
            </c:when>
            <c:otherwise>
                <A class="btn btn-default" role="button" href="login-form.jsp">enter</A>
            </c:otherwise>
        </c:choose>
        </div>
        <div class="form-group">
        <c:if test="${user.role eq 'manager'}">
        
        </c:if>
            <TABLE class="table table-bordered">
                <TR>                
                    <TH>Name</TH>
                    <TH>Adress</TH>
                    <TH>All Projects</TH>
                    <TH>Finished Projects</TH>
                    <TH>&nbsp;</TH>
                </TR>
                <c:forEach var="client" items="${clients}">
                    <c:if test="${user.role ne 'manager'}"><TR></c:if>
                	<c:if test="${user.role eq 'manager'}"><TR class="hov" id="editClient.html?id=${client.id}"></c:if>
                        <c:if test="${user.role eq 'manager'}">                        
                        </c:if>
                        <TD>${client.name}</TD>
                        <TD>${client.adress}</TD>
                        <TD>${client.counta} </TD>
                        <TD>${client.countf} </TD>
                        <TD><A class="btn btn-default" role="button" href="projects.html?ClientName=${client.name}">View Projects</A></TD>       
                    </TR>
                </c:forEach>
            </TABLE>
            
        <c:if test="${user.role eq 'manager'}">
            <P>
                <A class="btn btn-default" role="button" href="editClient.html">Add Client</A>
                
            </P>
        </c:if>
        </div>
         <c:if test="${user.role eq 'admin'}">
            <P>
                <A class="btn btn-default" role="button" href="editUsers.html">ViewUsers</A>
            </P>
        </c:if>
        
        <c:if test="${user.role eq 'manager'}">
        
        </c:if>
        
       <u:test objects="${clients}" role="${user.role}" creator="editClient.html" editor="editClient.html?id="></u:test>
       
</u:html>