<%@ page import="java.util.*"%>
<%@ page import="java.util.Map.Entry"%>
<%@ page import="com.solartis.spring.controller.AppController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users List</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
	<div class="generic-container">
		<%@include file="authheader.jsp"%>
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">List of Users </span>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<%String[] ss =(String[]) pageContext.getAttribute("meta"); 
						for(int i=0; i<ss.length;i++)
						{
							%><th><%System.out.println(ss[i]);%></th><%
						}%>
						<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
							<th width="100"></th>
						</sec:authorize>
						<sec:authorize access="hasRole('ADMIN')">
							<th width="100"></th>
						</sec:authorize>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user">
						<c:set var="userr" value="${users}" />
						<tr>
							<%
								LinkedHashMap<Integer, LinkedHashMap<String, String>> table = (LinkedHashMap<Integer, LinkedHashMap<String, String>>) pageContext
											.getAttribute("userr");
									for (Entry<Integer, LinkedHashMap<String, String>> entry : table.entrySet()) {
										LinkedHashMap<String, String> rowInputColVerify = entry.getValue();
										for (Entry entrry : rowInputColVerify.entrySet()) {
							%><td>
								<%
									entrry.getValue();
								%>
							</td>
							<%
								}
									}
							%>
							<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
								<td><a href="<c:url value='/edit-user-${user.ssoId}' />"
									class="btn btn-success custom-width">edit</a></td>
							</sec:authorize>
							<sec:authorize access="hasRole('ADMIN')">
								<td><a href="<c:url value='/delete-user-${user.ssoId}' />"
									class="btn btn-danger custom-width">delete</a></td>
							</sec:authorize>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<sec:authorize access="hasRole('ADMIN')">
			<div class="well">
				<a href="<c:url value='/newuser' />">Add New User</a>
			</div>
		</sec:authorize>
	</div>
</body>
</html>