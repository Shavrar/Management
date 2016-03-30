<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@attribute name="number" required="true"
             rtexprvalue="true" type="java.lang.Integer"%>          
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags" %>

<c:if test="${!empty number}">
<c:choose>
	<c:when test="${number ne 0}">
		<u:test number="${number-1}"></u:test>
		<p>${number}</p>
	</c:when>
	
	<c:otherwise>
		<p>${number}</p>
	</c:otherwise>
</c:choose>
</c:if>       			