<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>


	<head>
	    <title>Ejetty Twitter App - </title>
	    
	    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
	    <%@ include file="/WEB-INF/views/includes/style.jsp" %>
	    <%@ include file="/WEB-INF/views/includes/js.jsp" %>
	</head>
	<body>
		<div id="header">
			<div id="header-topbar">
                          
                          <%@ include file="/WEB-INF/views/includes/navigation.jsp" %>         
		
			</div>
			<a href="/" id="logo"><img src="<c:url value="/resources/images/logo.png"/>" /></a>
		</div>
	    <div id="content">
            
	    </div>
	    <%@ include file="/WEB-INF/views/includes/footer.jsp" %>
		</body>
</html>
