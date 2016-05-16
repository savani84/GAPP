<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
	
	<div class="container">
		<div class="page-header">
			<h2>Graduate Program Applications <small>Student (Welcome ${username})</small></h2>
			<a href="logout.html"><input style="float:right;" type="button" class="btn btn-primary" value="Log Out" /></a>
		</div>     
		
		<div class="row">
			<div class="col-md-12">
				<table width="100%" class="table table-striped">
					<caption>
						<a href="addApplication.html">Add New Application</a>
					</caption>
					<tr class="success">
						<th>
							Term
						</th>
						<th>
							Department
						</th>
						<th>
							Program
						</th>
						<th>
							Submitted Date
						</th>
						<th>
							Current Status
						</th>
						<th>
							Edit
						</th>
						<th>
							View
						</th>
					</tr>
					<c:forEach items="${applications}" var="application" varStatus="status">
						<tr>
							<td>
								${application.term}
							</td>
							<td>
								${application.department.deptName}
							</td>
							<td>
								${application.program.progName}
							</td>
							<td>
								<fmt:formatDate pattern="MM/dd/yyyy" value="${application.applicationDate}" var="submitted" />
								<c:out value="${submitted}" />
							</td>
							<td>
								${application.status.status}
							</td>
							<td>
								<c:if test="${application.status.status == 'Not Submitted'}">
									<a href="editApplication.html?applicationid=${application.id}">Edit Application</a>
								</c:if>
							</td>
							<td>
								<a href="viewApplication.html?applicationid=${application.id}">View Application</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>