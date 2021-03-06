<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Application for student</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<style>
th, td { padding: 5px; }
</style>
</head>
<body>
	
	<div class="container">
		<div class="page-header">
			<h2>Graduate Program Applications <small>Student (Welcome ${username})</small></h2>
			<a href="studenthome.html"><input style="float:left;" type="button" class="btn btn-primary" value="Home" /></a>
			<a href="logout.html"><input style="float:right;" type="button" class="btn btn-primary" value="Log Out" /></a>
		</div>     
	
		<div class="row">
			<div class="col-md-12">
					
				<table width="70%">
					<tr>
						<td colspan="3">
							<h1>Other Requirement</h1>
						</td>
					</tr>
					<c:forEach items="${otherfields}" var="OtherField" varStatus="status">
						
							<tr>
								<td>
									<label for="targetGroup">${OtherField.nameField}</label>
									
								</td>
								
									<c:set var="valueforother" value=""/>
									<c:set var="idforother" value=""/>
									<c:forEach items="${otherFieldValues}" var="otherFieldValue" varStatus="status">
										<c:if test="${OtherField.id == otherFieldValue.otherField.id}">
											<c:set var="valueforother" value="${otherFieldValue.otherValue}"/>
											<c:set var="idforother" value="${otherFieldValue.id}"/>
										</c:if>
									</c:forEach>
												
									<c:choose>
										<c:when test="${fn:toUpperCase(OtherField.typeField) != 'FILE'}">
											<form action="editOtherRequiremt.html" method="POST">
												<input type="hidden" name="appid" value="${param.applicationid}" />
												<input type="hidden" name="otherid" value="${OtherField.id}" />
												<input type="hidden" name="othervalueID" value="${idforother}" />
												<td>
													<input type="text"  name="OtherValue" class="form-control" placeholder="Enter the Value" value="${valueforother}"/>
												</td>
												<td>
													
													<c:choose>
													<c:when test="${valueforother == ''}">
														<input type="submit" class="btn btn-primary" value="Save Value" />
													</c:when>
													<c:otherwise>
														<input type="submit" class="btn btn-primary" value="Update Value" />
														<a href="deleteOtherRequirement.html?othervalueID=${idforother}&type=value&applicationid=${param.applicationid}"><input type="button" class="btn btn-primary" value="Delete Value" /></a>
													</c:otherwise>
													</c:choose>
												</td>
											</form>
										</c:when>
										<c:otherwise>
											<form action="uploadeditOtherFile.html" method="POST"  enctype="multipart/form-data">
												<input type="hidden" name="appid" value="${param.applicationid}" />
												<input type="hidden" name="otherid" value="${OtherField.id}" />
											<td>
												<input type="file" name="otherfile" />
												<c:if test="${valueforother != ''}">
													<a href="downloadFile.html?type=other&filename=${valueforother}&applicationid=${param.applicationid}">Download the ${OtherField.nameField}</a>
												</c:if>
											</td>
											<td>
												<input type="hidden" name="othervalueID" value="${idforother}" />
												<c:choose>
												<c:when test="${valueforother == ''}">
													<input type="submit" class="btn btn-primary" value="Save Value" />
												</c:when>
												<c:otherwise>
													<input type="submit" class="btn btn-primary" value="Update Value" />
													<a href="deleteOtherRequirement.html?othervalueID=${idforother}&type=file&applicationid=${param.applicationid}"><input type="button" class="btn btn-primary" value="Delete Value" /></a>
												</c:otherwise>
												</c:choose>
											</td>
											</form>
										</c:otherwise>
									</c:choose>
									
								
							</tr>
						
					</c:forEach>
					<tr>
						<td colspan="3" align="center">
							<a href="submitApplication.html?applicationid=${param.applicationid}"><input type="button" class="btn btn-primary" value="Submit Application" /></a>
						</td>
					</tr>
				</table>
				
			</div>
		</div>
	</div>
</body>
</html>