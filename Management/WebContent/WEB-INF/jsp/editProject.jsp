<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Тест JSP" stylesheet="CSS/boot/bootstrap.min.css">
  		
		<u:form object="${ProjectT}" link="saveProject.html" back="projects.html?ClientName=${ProjectT.client}" delete="deleteProject.html"></u:form>
		
</u:html>