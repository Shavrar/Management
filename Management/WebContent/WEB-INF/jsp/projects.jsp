<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Тест JSP" stylesheet="CSS/boot/bootstrap.min.css">
    
    	<%--
        <FORM action="deleteProject.html" method="post">
       
            <TABLE class="table table-bordered">
                <TR>
                	<c:if test="${user.role eq 'manager'}">
                    <TH>&nbsp;</TH>
                    </c:if>
                    <TH>Client</TH>
                    <TH>Name</TH>
                    <TH>Begining</TH>
                    <TH>Planed</TH>
                    <TH>Real</TH>
                    <TH>Successfully finished</TH>
                       
                </TR>
                <c:forEach var="project" items="${projects}">
                	
                    <TR>                   	
                        <c:if test="${user.role eq 'manager'}"> 
                        <TD>
                            <INPUT type="checkbox" name="idProject"
                                   value="${project.id}">
                        </TD>
                        </c:if>
                        <TD>${project.client}</TD>
                        
                        <TD> 
                        
                        	<c:if test="${user.role eq 'manager'}">                          
                            <A  class="btn btn-default" role="button" href="editProject.html?id=${project.id}">
                            </c:if>                           
                                ${project.name} 
                            <c:if test="${user.role eq 'manager'}">                          
                            </A>
                            </c:if>
                        </TD>
                        <TD>${project.begining}</TD>
                        <TD>${project.planed}</TD>
                        <TD>${project.real}</TD>
                        <TD>${project.finished}</TD>
                    </TR>
                </c:forEach>
            </TABLE>
        	<c:if test="${user.role eq 'manager'}">
            <P>
                <A class="btn btn-default" role="button" href="editProject.html?ClientName=${ClientName}">Add Project</A>
                <BUTTON class="btn btn-default" type="submit">Delete Project</BUTTON>
            </P>
           </c:if>
        </FORM>
        <A class="btn btn-default" role="button" href="index.html">Back</A>
         --%>
        
        <FORM action="deleteProject.html" method="post">
        <u:test objects="${projects}" role="${user.role}" back="index.html" creator="editProject.html?ClientName=${ClientName}" editor="editProject.html?id="></u:test>
        <c:if test="${user.role eq 'manager'}">
            <P>               
                <BUTTON class="btn btn-default" type="submit">Delete Selected Projects</BUTTON>
            </P>
        </c:if>
        </FORM>
        
</u:html>