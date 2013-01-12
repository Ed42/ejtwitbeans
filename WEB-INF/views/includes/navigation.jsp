<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div class="navigation">

    <sec:authorize access = "isAuthenticated()">    
        <span id="menu-username"><%=SecurityContextHolder.getContext().getAuthentication().getName()%></span>
        <%@ include file="/WEB-INF/views/includes/search.jsp" %>
        <a href="<c:url value="/"  />">Home</a>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <a href="<c:url value="/admin" />">Administration</a>
        </sec:authorize>


        <a href="<c:url value="j_spring_security_logout" />">Logout</a>
    </sec:authorize>
    <sec:authorize access = "isAnonymous()">
        <a href="<c:url value="/auth/login" />">Login</a>
        <a href="<c:url value="/auth/registerpage" />">Register</a>                             
        <%@ include file="/WEB-INF/views/includes/search.jsp" %>

    </sec:authorize>
</div>