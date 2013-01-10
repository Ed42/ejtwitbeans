<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <h3>Embedded Jetty with neo4j back end </h3>
    <div id="big-search-wrap">
        <form action="/getTweets" method="POST">
        <input type="text" class="big-search" name="q" value="Find tweet" onfocus="this.value='';" onblur="this.value = (this.value=='') ? 'Find tweet' : this.value;" />
        <input type="submit" class="big-search-submit" value="Search"/>
        </form>
    </div>
</div>