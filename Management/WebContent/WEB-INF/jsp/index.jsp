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
        <FORM action="deleteClient.html" method="post">
        </c:if>
            <TABLE class="table table-bordered">
                <TR>
                    <c:if test="${user.role eq 'manager'}"><TH>&nbsp;</TH></c:if>
                    <TH>Name</TH>
                    <TH>Adress</TH>
                    <TH>All Projects</TH>
                    <TH>Finished Projects</TH>
                    <TH>&nbsp;</TH>
                </TR>
                <c:forEach var="client" items="${clients}">
                    <TR>
                        <c:if test="${user.role eq 'manager'}">
                        <TD>
                            <INPUT type="checkbox" name="id"
                                   value="${client.id}">
                        </TD>
                        </c:if>
                        <TD>
                            <c:if test="${user.role eq 'manager'}">
                            <A class="btn btn-default" role="button" href="editClient.html?id=${client.id}">
                            </c:if>
                                ${client.name}
                            <c:if test="${user.role eq 'manager'}">
                            </A>
                            </c:if>
                        </TD>
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
                <BUTTON class="btn btn-default" type="submit">Delete Client</BUTTON>
            </P>
        </c:if>
        </div>
         <c:if test="${user.role eq 'admin'}">
            <P>
                <A class="btn btn-default" role="button" href="editUsers.html">ViewUsers</A>
            </P>
        </c:if>
        
        <c:if test="${user.role eq 'manager'}">
        </FORM>
        </c:if>
        <u:test number="5"></u:test>
</u:html>