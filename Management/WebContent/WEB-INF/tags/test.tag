<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@attribute name="string" required="true"
             rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="domain" 
             rtexprvalue="true" type="java.lang.String"%>
       
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="number" value="0" scope="session"></c:set>
<p>${string}</p>
<c:if test="${!empty string}">
<c:choose>
	<c:when test="${fn:length(string)>1}">
		<u:test string="${fn:substring(string,0,fn:length(string)-1)}"></u:test>
		<c:set var="string" value="0"></c:set>
	</c:when>
	
	<c:otherwise>
		<p>${string}</p>
	</c:otherwise>
</c:choose>
</c:if>    
<p>${string}</p>