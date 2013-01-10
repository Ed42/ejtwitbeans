<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" media="screen" href="resources/css/style.css"/>
        <link rel="stylesheet" type="text/css" media="screen" href="resources/css/admin.css"/>
        <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery.ui.all.css" type="text/css">
        <link rel="stylesheet" href="resources/css/redmond/jquery-ui-1.9.2.custom.min.css" type="text/css" />
        <!-- Load jQuery first before jQuery UI! -->
        <script src="resources/js/jquery-1.8.3.js"></script>
        <script src="resources/js/jquery-ui-1.9.2.custom.min.js"></script>
        <script src="resources/tweetHelper.js"></script>
      
        <title>User Tweets Page</title>
    </head>

    <body>
        <h1 id="banner" class=" ui-widget-header ui-widget ui-corner-all">Administration Control Panel</h1>
        <hr/>

        <div id = "center" class="span-15  colborder last ui-widget ui-widget-content ui-corner-all ui-helper-reset">

            <div id="centerTabs" class="span-14 last ui-tabs ui-widget-content ui-corner-top ui-helper-reset">
                <ul id="viewInfoTabs" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-content ui-helper-reset ui-corner-top">
                    <li><a href="#tab-1" id = "DataViewHeader">Users Tweets View</a></li>
                </ul>  
                <div id="tab-1">
                    <table id="Table1" class="full" border="1"> 

                        <th><p class = 'tableText'>Username</p></th>
                        <th><p class = 'tableText'>Email</p></th>

                        <c:forEach items="${uTweetDto.uTweets}" var="uTweets">
                            <tr>
                                <td>${uTweets.username}</td>
                                <td>${uTweets.email}</td>
                            </tr>         
                        </c:forEach>


                    </table>
                </div><%-- end tab-1 divt --%>

            </div><%-- end center tabs --%> 
        </div><%-- end center --%> 

    </body>
</html>