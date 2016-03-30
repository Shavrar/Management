<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Тест JSP" stylesheet="CSS/boot/bootstrap.min.css">
    
    
        <FORM action="deleteUser.html" method="post">
       
            <TABLE class="table table-bordered">
                <TR>
                    <TH>&nbsp;</TH>
                    <TH>Login</TH>
                    <TH>Password</TH>
                    <TH>Role</TH>   
                </TR>
                <c:forEach var="usert" items="${users}">
                    <TR>
                        
                        <TD>
                            <INPUT type="checkbox" name="idUser"
                                   value="${usert.id}">
                        </TD>
                        
                        <TD>                           
                            <A class="btn btn-default" role="button" href="editUser.html?idU=${usert.id}">                           
                                ${usert.login}                           
                            </A>
                        </TD>
                        <TD>${usert.password}</TD>
                        <TD>${usert.role}</TD>
                    </TR>
                </c:forEach>
            </TABLE>
        
            <P>
                <A class="btn btn-default" role="button" href="editUser.html">Add</A>
                <BUTTON class="btn btn-default" type="submit">Delete</BUTTON>
            </P>
           
        </FORM>
        <A class="btn btn-default" role="button" href="index.html">Back</A>
</u:html>