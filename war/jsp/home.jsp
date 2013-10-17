Menu
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<% 	UserService userService = UserServiceFactory.getUserService();
	if (userService.isUserLoggedIn()) {
		pageContext.setAttribute("user", userService.getCurrentUser());
%>
	Ciao <%= userService.getCurrentUser().getNickname() %>
<%	} else {	%>
		<a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a>
<%	}	%>

<%= request.getAttribute("ciao") %>
<%= request.getAttribute("menu") %>