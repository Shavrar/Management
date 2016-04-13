<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@attribute name="title" required="true"
             rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="stylesheet" required="false"
             rtexprvalue="true" type="java.lang.String"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<HTML>
    <HEAD>
    
        <meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
   		<meta name="viewport" content="width=device-width, initial-scale=1">
    	<LINK rel="stylesheet" type="text/css" href="CSS/Main.css">
        <TITLE>${title}</TITLE>
        <c:if test="${not empty stylesheet}">
            <LINK rel="stylesheet" type="text/css" href="${stylesheet}">
        </c:if>
       
    </HEAD>
    <BODY>
    <div class="container">
        <jsp:doBody/>
    </div>
    
    <SCRIPT type="text/javascript" src="JS/main.js"></SCRIPT>
    </BODY>
</HTML>