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
    <link rel="stylesheet" href="resources/css/mint-choc/jquery-ui-1.9.2.custom.min.css" type="text/css" />
    <script src="resources/js/jquery-1.8.3.js"></script>
    <script src="resources/js/jquery-ui-1.9.2.custom.min.js"></script>
    <script src="resources/searchHelper.js"></script>
    <script src="resources/tweetHelper.js"></script>


</head>
<body>
       <div id="header" class="ui-widget-header ui-widget ui-corner-all">
        <div id="header-topbar">
            <%@ include file="/WEB-INF/views/includes/navigation.jsp" %>         
        </div>
      <a  href="/" id="logo">Search  Twitter Application</a>
    </div>
    <div id="contentTwitfeed">
      
<h3>Search Results for:   <c:out value="${searchTerm}"/></h3>


<div class="feed">
<ul class="imagedList">
<c:forEach items="${seResults}" var="tokenizedTweet">
	<li class="imagedItem">
            <p>Analysis:  [word]  [frequency] </p>
            <c:out value="${tokenizedTweet.wordList}"/>
	</li>
</c:forEach>
</ul>
</div>
</div>
    <%@ include file="/WEB-INF/views/includes/footer.jsp" %>
</body>
</html>