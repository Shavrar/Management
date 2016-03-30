<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@attribute name="object" required="true"
             rtexprvalue="true" type="Entities.Entity"%>
<%@attribute name="link" required="true"
             rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="back" required="true"
             rtexprvalue="true" type="java.lang.String"%>             
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<FORM action="${link}" method="post">
       
            
	    
    <INPUT type="hidden" name="id-t" value="${object.id}">  
			
   			  <c:if test="${!empty object.className.declaredFields}">
	       		 <ul>
		            <c:forEach var="attr" items="${object.className.declaredFields}">
		            	
		            	<c:if test="${attr.name ne 'id' and attr.name ne 'client' and attr.name ne 'role'}">
		            	<li><b>${attr.name}</b>:&nbsp;<INPUT type="text" name="${attr.name}-t" value="${object[attr.name]}"></li>            			            	    			            	
		            	</c:if>
		            	
		            	<c:if test="${attr.name eq 'client'}"> 
		            	<INPUT type="hidden" name="client-t" value="${object.client}">  
		            	</c:if>
		            	
		            	<c:if test="${attr.name eq 'role'}">
		            			<b>${attr.name}</b>:&nbsp;
		            			<c:if test="${object.role ne 'manager'}">
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
		            	</c:if>   
		            	
		            	           
		            </c:forEach>
	       		 </ul>
    		</c:if>	 
			
            <BUTTON class="btn btn-default" type="submit">Save ${object.stringName}</BUTTON>
          
</FORM>

<A class="btn btn-default" role="button" href="${back}">Back</A>
       			