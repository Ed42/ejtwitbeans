<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                          <%@ include file="/WEB-INF/views/includes/search.jsp" %>
		
			</div>
			<a href="/" id="logo"><img src="<c:url value="/resources/images/logo.png"/>" /></a>
		</div>
	    <div id="content">
            
	    </div>
	    <%@ include file="/WEB-INF/views/includes/footer.jsp" %>
		</body>
</html>
