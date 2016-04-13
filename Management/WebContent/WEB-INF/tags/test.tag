<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@attribute name="objects" required="true"
             rtexprvalue="true" type="java.util.Collection"%>
<%@attribute name="domain" 
             rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="role" required="true"
             rtexprvalue="true" type="java.lang.String"%>    

<%@attribute name="back" 
             rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="creator" 
             rtexprvalue="true" type="java.lang.String"%>             

<%@attribute name="editor" required="true"
             rtexprvalue="true" type="java.lang.String"%> 
                          
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>




<ul class="list-group">

<c:choose>
	<c:when test="${!empty domain}">
		<c:forEach var="object" items="${objects}">
			<c:choose>
				<c:when test="${object.domain_name eq domain}">
						
						<li class="list-group-item">
						<label>Project: ${object.name}</label>
						                        
						<ul class="list-group">
					            <c:forEach var="attr" items="${object.className.declaredFields}">
					            	
					            	<c:if test="${attr.name ne 'id' and attr.name ne 'client' and attr.name ne 'role' and attr.name ne 'domain_name'}">
					            	<li class="list-group-item"><b>${attr.name}</b>:&nbsp;${object[attr.name]}</li>            			            	    			            	
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
					            
					            <c:if test="${fn:substringAfter(object.className,'.') eq 'Project'}">
					            	<li class="list-group-item"><b>Successfully finished</b>:&nbsp;${object.finished}</li>
					            </c:if>
					            
					            <c:if test="${role eq 'manager' and fn:substringAfter(object.className,'.') eq 'Project'}">
					            	<li class="list-group-item"><a  class="btn btn-default" role="button" href="${editor}${object.id}">Edit</a></li>
					            </c:if>
	       		 		</ul>	
					</li>
				</c:when>
				
				<c:when test="${fn:contains(object.domain_name,domain) and object.domain_name ne domain}">
					<c:set var="lower_domain" value="${fn:substringAfter(fn:substringAfter(object.domain_name,domain),'.')}"/>
					<li class="list-group-item">
					<c:if test="${fn:contains(lower_domain,'.')}">
						<label>${fn:substringBefore(lower_domain,'.')}</label>
						<c:set var="lower_domain" value="${fn:substringBefore(object.domain_name,fn:substringBefore(lower_domain,'.'))}"/>
					</c:if>
					
					<c:if test="${!fn:contains(lower_domain,'.')}">
						<label>${lower_domain}</label>
						<c:set var="lower_domain" value="${object.domain_name}"/>
					</c:if>
										
					<u:test objects="${objects}" role="${role}" domain="${lower_domain}" editor="${editor}"></u:test>
					</li>					
					<c:forEach var="obj" items="${objects}">
						<c:if test="${fn:contains(obj.domain_name,lower_domain)}">
							<c:set target="${obj}" property="domain_name" />
							<c:set target="${obj}" property="id" value="${null}"/>
						</c:if>
					</c:forEach>
					
				</c:when>
				
			</c:choose>
		</c:forEach>
	</c:when>
	
	
	
	<c:otherwise> 
		
		<c:forEach var="object" items="${objects}">
		
					<c:if test="${fn:substringAfter(object.className,'.') eq 'Project'}">
					
								<c:if test="${empty object.domain_name and !empty object.id}">
									<li class="list-group-item">
									<label>Project: ${object.name}</label>
									<ul class="list-group">
														                                   
										    
										    <c:forEach var="attr" items="${object.className.declaredFields}">
										            	
										            	<c:if test="${attr.name ne 'id' and attr.name ne 'client' and attr.name ne 'role' and attr.name ne 'domain_name'}">
										            	<li class="list-group-item"><b>${attr.name}</b>:&nbsp;${object[attr.name]}</li>            			            	    			            	
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
										    
										    <c:if test="${fn:substringAfter(object.className,'.') eq 'Project'}">
										    <li class="list-group-item">
										    	<b>Successfully finished</b>:&nbsp;${object.finished}
										    </li>
										    </c:if>
										    
										    <c:if test="${role eq 'manager' and fn:substringAfter(object.className,'.') eq 'Project'}">
										            	<li class="list-group-item"><a  class="btn btn-default" role="button" href="${editor}${object.id}">Edit</a></li>
										    </c:if>
						       		 </ul>			
									</li>				
								</c:if>
								
								<c:if test="${!empty object.domain_name and fn:contains(object.domain_name,'.')}">
									<c:set var="dmn" value="${fn:substringBefore(object.domain_name,'.')}"/>
									<li class="list-group-item">
									<label>${dmn}</label>									
									<u:test objects="${objects}" role="${role}" domain="${dmn}" editor="${editor}"></u:test>
									</li>
									<c:forEach var="obj" items="${objects}">
										<c:if test="${fn:contains(obj.domain_name,dmn)}">
											<c:set target="${obj}" property="domain_name"/>
											<c:set target="${obj}" property="id" value="${null}"/>
										</c:if>
									</c:forEach>
								</c:if>
								
								<c:if test="${!empty object.domain_name and !fn:contains(object.domain_name,'.')}">	
											
									<li class="list-group-item"><label>${object.domain_name}</label>
									
									<u:test objects="${objects}" role="${role}" domain="${object.domain_name}" editor="${editor}"></u:test>
									</li>
									
									<c:forEach var="obj" items="${objects}">
										<c:if test="${fn:contains(obj.domain_name,object.domain_name) and !empty object.domain_name}">
											
											<c:set target="${obj}" property="domain_name" />
											<c:set target="${obj}" property="id" value="${null}"/>
										</c:if>
									</c:forEach>
								</c:if>
					</c:if>
				
		</c:forEach>
	</c:otherwise>
</c:choose>

</ul>



<c:if test="${empty domain and !empty back}">
		<c:if test="${role eq 'manager' and !empty creator}">
		 	<A class="btn btn-default" role="button" href="${creator}">Add Project</A>
		</c:if>
		<A class="btn btn-default" role="button" href="${back}">Back</A>
</c:if>
   
