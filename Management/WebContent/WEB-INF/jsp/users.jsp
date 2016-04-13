<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Тест JSP" stylesheet="CSS/boot/bootstrap.min.css">
    
    
       
       
            <TABLE class="table table-bordered">
                <TR>
                    
                    <TH>Login</TH>
                    <TH>Password</TH>
                    <TH>Role</TH>   
                </TR>
                <c:forEach var="usert" items="${users}">
                    <c:if test="${user.role ne 'admin'}"><TR></c:if>
                	<c:if test="${user.role eq 'admin'}"><TR class="hov" id="editUser.html?idU=${usert.id}"></c:if>
                                                                       
                        <TD>${usert.login}</TD>
                        <TD>${usert.password}</TD>
                        <TD>${usert.role}</TD>
                    </TR>
                </c:forEach>
            </TABLE>
        
            <P>
                <A class="btn btn-default" role="button" href="editUser.html">Add</A>
                
            </P>
           
        
        <A class="btn btn-default" role="button" href="index.html">Back</A>
</u:html>