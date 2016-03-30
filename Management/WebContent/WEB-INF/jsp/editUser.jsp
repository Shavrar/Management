<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Тест JSP" stylesheet="CSS/Main.css">
       <%-- <FORM action="saveUser.html" method="post">
			
            <c:if test="${not empty userT}">
                <INPUT type="hidden" name="id-t" value="${userT.id}">
            </c:if>
			<c:choose>
   				 <c:when test="${not empty userT}">
        			<P>Login:</P>

		            <INPUT type="text" name="login-t" value="${userT.login}" required>
		
		            <P>Password:</P>
		
		            <INPUT type="text" name="password-t" value="${userT.password}" required>
		            
		            <P>Role:</P>
					<c:if test="${userT.role eq 'admin'}">
		                <select name="role-t">
					    <option value="admin" selected>admin</option>
					    <option value="manager">manager</option>
				    </select>
		            </c:if>
		            <c:if test="${userT.role eq 'manager'}">
		                <select name="role-t">
					    <option value="admin" >admin</option>
					    <option value="manager" selected>manager</option>
				    </select>
		            </c:if>
            		
   				 </c:when>
   				 <c:otherwise>
   				 	<P>Login:</P>

		            <INPUT type="text" name="login-t" value="" required>
		
		            <P>Password:</P>
		
		            <INPUT type="text" name="password-t" value="" required>
		            
		            <P>Role:</P>   
		            <select name="role-t">
					    <option value="admin">admin</option>
					    <option value="manager">manager</option>
				    </select>
    			 </c:otherwise>
			</c:choose>
           
  
            <BUTTON class="btn btn-default" type="submit">Save</BUTTON>
            <A class="btn btn-default" role="button" href="editUsers.html">Back</A>
        </FORM>
        --%>
        <u:form object="${userT}" link="saveUser.html" back="editUsers.html"></u:form>
</u:html>