<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Application for student</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

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
				<form action="editApplication.html" method="POST" onSubmit="return deptValidation();">	
				<table width="100%">
					<tr>
						<td colspan="5">
							<h1>Personal Details</h1>
						</td>
					</tr>
					<tr>
						<td>
							<label for="targetGroup">Term</label>
						</td>
						<td width="35%">
							<select class="form-control" name="term">
							    <option value="Fall 2015"
							    <c:if test="${application.term == 'Fall 2015'}">
		                    			selected
		                    	</c:if>
							    >Fall 2015</option>
							    <option value="Spring 2016"
							    <c:if test="${application.term == 'Spring 2016'}">
		                    			selected
		                    	</c:if>
							    >Spring 2016</option>
							    <option value="Fall 2016"
							    <c:if test="${application.term == 'Fall 2016'}">
		                    			selected
		                    	</c:if>
							    >Fall 2016</option>
		                    	<option value="Select Term">Select Term</option>
		                    </select>
						</td>
					</tr>
					<tr>
						<td width="13%">
							<label for="targetGroup">Department</label>
						</td>
						<td width="35%">
							<select class="form-control" disabled name="deptname" id="deptname" value="${application.department.deptName}">
		                    	<c:forEach items="${departments}" var="department" varStatus="status">
		                    		<option value="${department.deptName}"
		                    		<c:if test="${department.deptName == application.department.deptName}">
		                    			selected
		                    		</c:if>
		                    		>${department.deptName}</option>
		                    	</c:forEach>
		                    	<option value="Select Department">Select Department</option>
		                    </select>
		                    <input type="hidden" name="deptname" value="${application.department.deptName}" />
						</td>
						<td width="4%"></td>
						<td width="13%">
							<label for="targetGroup">Program</label>
						</td>
						<td width="35%">
							<select class="form-control" name="prgmname" id="prgmname">
								<c:forEach items="${programs}" var="program" varStatus="status">
		                    		<option value="${program.progName}"
		                    		<c:if test="${program.progName == application.program.progName}">
		                    			selected
		                    		</c:if>
		                    		>${program.progName}</option>
		                    	</c:forEach>
		                    	<option value="Select Program">Select Program</option>
		                    </select>
						</td>
					</tr>
					<tr>
						<td>
							<label for="targetGroup">First Name</label>
						</td>
						<td>
							<input type="text" name="firstName" class="form-control" placeholder="Enter the First Name" value="${application.firstName}"/>
						</td>
						<td></td>
						<td>
							<label for="targetGroup">Last Name</label>
						</td>
						<td>
							<input type="text" name="lastName" class="form-control" placeholder="Enter the Last Name" value="${application.lastName}"/>
						</td>
					</tr>
					<tr>
						<td>
							<label for="targetGroup">Date of Birth</label>
						</td>
						<td>
							<fmt:formatDate pattern="MM/dd/yyyy" value="${application.dob}" var="dobDate" />
							<input type="text" name="dateofbirth" class="form-control" 
							placeholder="Enter the Date of Birth (MM/dd/yyyy)" 
							value="${dobDate}" />
							
						</td>
						<td></td>
						<td>
							<label for="targetGroup">Gender</label>
						</td>
						<td>
							<select class="form-control" name="gender">
								<option value="Male"
							    <c:if test="${application.gender == 'Male'}">
		                    			selected
		                    	</c:if>
							    >Male</option>
								<option value="Female"
							    <c:if test="${application.gender == 'Female'}">
		                    			selected
		                    	</c:if>
							    >Female</option>
		                    	<option value="Select Gender">Select Gender</option>
		                    </select>
						</td>
					</tr>
					<tr>
						<td>
							<label for="targetGroup">Citizen Country</label>
						</td>
						<td>
							<input type="text" name="citizenCountry" class="form-control" placeholder="Enter the Citizen Country" value="${application.citizenCountry}" />
						</td>
						<td></td>
						<td>
							<label for="targetGroup">Phone No</label>
						</td>
						<td>
							<input type="text" name="phoneno" class="form-control" placeholder="Enter the Phone No" value="${application.phoneno}" />
						</td>
					</tr>
					<tr>
						<td colspan="5" align="right">
							<input type="hidden" name="applicationid" value="${application.id}" />
							<input style="float:right;" type="submit" class="btn btn-primary" value="Update & Next" />
						</td>
					</tr>
				</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>