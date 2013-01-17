<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div class="navigation  ui-widget ui-corner-all">

    <sec:authorize access = "isAuthenticated()"> 
        <a class = "home" href="<c:url value="/"  />">Home</a>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
           <a class = "admin" href="<c:url value="/admin" />">Administration</a>
        </sec:authorize>
        <a class = "logout" href="<c:url value="j_spring_security_logout" />">Logout</a>
        <span id="menu-username"><%=SecurityContextHolder.getContext().getAuthentication().getName()%></span>
       
    </sec:authorize>
    <sec:authorize access = "isAnonymous()">
        <a class = "home" href="<c:url value="/"  />">Home</a>
        <a class = "login" href="<c:url value="/auth/login" />">Login</a>
        <a class = "register" href="<c:url value="/auth/registerpage" />">Register</a>                             
       

    </sec:authorize>
</div>