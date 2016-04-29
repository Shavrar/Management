<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@attribute name="object" required="true"
             rtexprvalue="true" type="Entities.Entity"%>
<%@attribute name="link" required="true"
             rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="back" required="true"
             rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="delete" required="true"
             rtexprvalue="true" type="java.lang.String"%>              
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<FORM id="edit${fn:substringAfter(object.className,'.')}" action="${link}" method="post">
<c:if test="${!empty Fail}"> 
<p>FAILU</p>
</c:if>      
            
	    
    <INPUT type="hidden" name="id-t" value="${object.id}">  
			
   			  <c:if test="${!empty object.className.declaredFields}">
	       		 
		            <c:forEach var="attr" items="${object.className.declaredFields}">
		            	
		            	<c:if test="${attr.name ne 'id' and attr.name ne 'client' and attr.name ne 'role' and attr.name ne 'all' and attr.name ne 'finished'}">
			            	<div class="form-group">
			            	<label>${attr.name}</label>
			            	
			            	
			            	<INPUT type="text" id="${attr.name}" class="form-control" name="${attr.name}-t" value="${object[attr.name]}">
			            	
			            	
			            	</div>          			            	    			            	
		            	</c:if>
		            	
		            	<c:if test="${attr.name eq 'client'}">
			            	<div class="form-group">
			            	<label>${attr.name}</label> 
			            	<INPUT type="text" class="form-control" name="client-t" value="${object.client}" readonly>  
			            	</div>
		            	</c:if>
		            	
		            	<c:if test="${attr.name eq 'role'}">
		            			<div class="form-group">
		            			<label>${attr.name}</label>
		            			<c:if test="${object.role ne 'manager'}">
				                <select class="form-control" name="role-t">
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
					            </div>
		            	</c:if>   
		            	
		            	           
		            </c:forEach>
	       		   
    		</c:if>	 
			
            <BUTTON class="btn btn-default" type="submit">Save ${object.stringName}</BUTTON>
          
</FORM>
<c:if test="${object.id ne null}">
<FORM id="del-form" action="${delete}" method="post">
<INPUT type="hidden" name="id" value="${object.id}">  

<BUTTON class="btn btn-default" type="submit">Delete ${object.stringName}</BUTTON>
</FORM>
</c:if>
<A class="btn btn-default" role="button" href="${back}">Back</A>
       			