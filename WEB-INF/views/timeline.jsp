<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<head>
    <title>Ejetty Twitter App - </title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery.ui.all.css" type="text/css">
    <link rel="stylesheet" href="resources/css/redmond/jquery-ui-1.9.2.custom.min.css" type="text/css" />
    <script src="resources/js/jquery-1.8.3.js"></script>
    <script src="resources/js/jquery-ui-1.9.2.custom.min.js"></script>
    <script src="resources/tweetHelper.js"></script>


</head>
<body>
       <div id="header" class="ui-widget-header ui-widget ui-corner-all">
        <div id="header-topbar">
            <%@ include file="/WEB-INF/views/includes/navigation.jsp" %>         
        </div>
        <a  href="/" id="logo"><img  class="ui-widget ui-corner-all" src="<c:url value="/resources/images/logo.png"/>" /></a>
    </div>
    <div id="content">
      
<h3>Your Twitter Timeline</h3>


<div class="feed">
<ul class="imagedList">
<c:forEach items="${timeline}" var="tweet">
	<li class="imagedItem">
		<div class="image">
			<c:if test="${not empty tweet.profileImageUrl}"><img src="<c:out value="${tweet.profileImageUrl}"/>" align="left"/></c:if>
		</div>
		<div class="content">
		<strong><a href="http://twitter.com/<c:out value="${tweet.fromUser}" />"><c:out value="${tweet.fromUser}" /></a></strong><br/>
		<c:out value="${tweet.text}" /><br/>
		<span class="postTime"><c:out value="${tweet.createdAt}"/></span>
		</div>
	</li>
</c:forEach>
</ul>
</div>
</div>
    <%@ include file="/WEB-INF/views/includes/footer.jsp" %>
</body>
</html>