<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>


<head>
    <title>Ejetty Twitter App - </title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery.ui.all.css" type="text/css">
    <link rel="stylesheet" href="resources/css/mint-choc/jquery-ui-1.9.2.custom.min.css" type="text/css" />
    <script src="resources/js/jquery-1.8.3.js"></script>
    <script src="resources/js/jquery-ui-1.9.2.custom.min.js"></script>
    <script src="resources/tweetHelper.js"></script>


</head>
<body>
    <div id="header" class="ui-widget-header ui-widget ui-corner-all">
        <div id="header-topbar">
            <%@ include file="/WEB-INF/views/includes/navigationStart.jsp" %>         
        </div>
        <a  href="/" id="logo">Search  Twitter Application</a>
    </div>
    <div id="content">
        <div class =" introText  ui-corner-all ui-helper-reset ">
        <h2>Demo Twitter Application</h2>
	<p>Running as standard java program with embedded jetty server server and spring security.
            Demonstrates  twitter timeline and keyword search with spring social api.
            Demonstrates in memory neo4j backend.</p>
        <p>Instructions</p>
        <p>Log In as User "test"  "test"</p>
        <p>Log In as Admin user "adminuser"  "adminpwd"</p>
        <p>As Admin user select "Administration to test admin panel, add, update and delete user functions</p>
         </div>
        <div class =" searchForm  ui-corner-all ui-helper-reset ">
            <form class ="search" action="/search"  method="get">
            <button type="submit" id ="searchButton">Search</button ><input class ="searchInput" name="search" value="search twitter" />
            </form>
            
        </div>
    </div>
    <%@ include file="/WEB-INF/views/includes/footer.jsp" %>
</body>
</html>
