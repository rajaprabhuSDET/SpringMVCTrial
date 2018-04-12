<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<script>
function SyncScroll(phoneFaceId) {
  var face1 = document.getElementById("phoneface1");
  var face2 = document.getElementById("phoneface2");
  if (phoneFaceId=="phoneface1") {
    face2.scrollTop = face1.scrollTop;
    face2.scrollLeft = face1.scrollLeft;
  }
  else {
    face1.scrollTop = face2.scrollTop;
    face1.scrollLeft = face2.scrollLeft;
  }
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users List</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
	<div class="generic-containerright">
		<%@include file="authheader.jsp"%>
		<div class="panel panel-default" id="phoneface1" onscroll="SyncScroll('phoneface1')">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">MEL Table </span>
			</div>
			<table class="table table-hover">
				<c:set var="metaa" value="${meta}" />
				<thead>
					<tr>
						<c:forEach items="${meta}" var="columnHeader">
							<th>${columnHeader}</th>
						</c:forEach>
						<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
							<th width="100"></th>
						</sec:authorize>
						<sec:authorize access="hasRole('ADMIN')">
							<th width="100"></th>
						</sec:authorize>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${tableContent}" var="tabledata">
						<tr>
							<c:forEach items="${tabledata.getValue()}" var="rowdata">
								<td>${rowdata.getValue()}</td>

							</c:forEach>
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
	<div class="generic-containerleft">
		<%@include file="authheader.jsp"%>
		<div class="panel panel-default" id="phoneface2" onscroll="SyncScroll('phoneface2')">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">MEL Table </span>
			</div>
			<table class="table table-hover">
				<c:set var="metaa" value="${meta}" />
				<thead>
					<tr>
						<c:forEach items="${meta}" var="columnHeader">
							<th>${columnHeader}</th>
						</c:forEach>
						<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
							<th width="100"></th>
						</sec:authorize>
						<sec:authorize access="hasRole('ADMIN')">
							<th width="100"></th>
						</sec:authorize>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${tableContent}" var="tabledata">
						<tr>
							<c:forEach items="${tabledata.getValue()}" var="rowdata">
								<td>${rowdata.getValue()}</td>

							</c:forEach>
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