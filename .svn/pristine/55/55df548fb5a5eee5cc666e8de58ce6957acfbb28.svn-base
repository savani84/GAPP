<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<table width="100%">
					<tr>
						<td>
							<h1>Education Details</h1>
						</td>
					</tr>
					<c:forEach items="${degrees}" var="degree" varStatus="status">
						<tr>
							<td>
								<table width="100%" style="border:1px solid #2e6da4;">
									<tr>
										<td>
											<label for="targetGroup">University Name</label>
										</td>
										<td>
											<input type="text" name="universityName" value="${degree.universityName}" class="form-control" placeholder="Enter the University Name"/>
										</td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td width="13%">
											<label for="targetGroup">Degree name</label>
										</td>
										<td width="35%">
											<input type="text" name="degreeName" value="${degree.degreeName}" class="form-control" placeholder="Enter the Degree Name"/>
										</td>
										<td width="4%"></td>
										<td width="13%">
											<label for="targetGroup">Major</label>
										</td>
										<td width="35%">
											<input type="text" name="major" value="${degree.major}" class="form-control" placeholder="Enter the Major"/>
										</td>
									</tr>
									<tr>
										<td>
											<label for="targetGroup">GPA</label>
										</td>
										<td>
											<input type="text" name="gpa" value="${degree.gpa}" class="form-control" placeholder="Enter the GPA"/>
										</td>
										<td></td>
										<td>
											<label for="targetGroup">Time Period</label>
										</td>
										<td>
											<input type="text" name="timePeriod" value="${degree.timePeriod}" class="form-control" placeholder="Enter the Time Period"/>
										</td>
									</tr>
									<tr>
										<td>
											<label for="targetGroup">Transcript / Result</label>
										</td>
										<td>
											<input type="file" name="transcript"/>
										</td>
										<td></td>
										<td colspan="2">
											<c:if test="${degree.transcript != null}">
												<c:if test="${degree.transcript != ''}">
													<a href="downloadFile.html?type=degree&filename=${degree.transcript}&applicationid=${param.applicationid}">Download the transcript</a>	
												</c:if>
											</c:if>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td>
							<form:form modelAttribute="degree" onSubmit="return deptValidation();" enctype="multipart/form-data">	
							<table width="100%" style="border:1px solid #2e6da4;">
								<tr>
									<td>
										<label for="targetGroup">University Name</label>
									</td>
									<td>
										<form:input path="universityName" class="form-control" placeholder="Enter the University Name"/>
									</td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td width="13%">
										<label for="targetGroup">Degree name</label>
									</td>
									<td width="35%">
										<form:input path="degreeName" class="form-control" placeholder="Enter the Degree Name"/>
									</td>
									<td width="4%"></td>
									<td width="13%">
										<label for="targetGroup">Major</label>
									</td>
									<td width="35%">
										<form:input path="major" class="form-control" placeholder="Enter the Degree Name"/>
									</td>
								</tr>
								<tr>
									<td>
										<label for="targetGroup">GPA</label>
									</td>
									<td>
										<form:input path="gpa" class="form-control" placeholder="Enter the Degree Name"/>
									</td>
									<td></td>
									<td>
										<label for="targetGroup">Time Period<br/>(Eg # year(s))</label>
									</td>
									<td>
										<form:input path="timePeriod" class="form-control" placeholder="Enter the Degree Name"/>
									</td>
								</tr>
								<tr>
									<td>
										<label for="targetGroup">Transcript / Result</label>
									</td>
									<td>
										<input type="file" name="transcriptfile"/>
									</td>
									<td></td>
									<td>
										<input type="hidden" name="appid" value="${param.applicationid}" />
									</td>
									<td>
										<input style="float:right;" type="submit" class="btn btn-primary" value="Save Academic" />
									</td>
								</tr>
							</table>
							</form:form>
						</td>
					</tr>
					<tr>
						<td>
							<a href="saveOtherRequiremt.html?applicationid=${param.applicationid}"><input style="float:right;" type="button" class="btn btn-primary" value="Next >>" /></a>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>