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
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
$(function(){
    $('#deptname').change(function(){
    	if($('#deptname').val() == 'Select Department'){
    		$('#prgmname').html("");
    		$('#prgmname').html("<option value='Select Program' selected>Select Program</option>");
    	}else{
	    	$.ajax( 'programFmDept.html', {
	    		data: "deptname=" + $('#deptname').val(),
	    		success: function(data){
	    			$('#prgmname').html("");
	    	        $('#prgmname').html(data);
	    		},
		    	error: function() {
		            alert('Error occured');
		        }
	        });
    	}
    });
});
</script>
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
				<form:form modelAttribute="application" onSubmit="return deptValidation();">	
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
							<form:select class="form-control" path="term">
							    <option value="Fall 2015" selected>Fall 2015</option>
							    <option value="Spring 2016" selected>Spring 2016</option>
							    <option value="Fall 2016" selected>Fall 2016</option>
							    <option value="Spring 2017" selected>Spring 2017</option>
							    <option value="Fall 2017" selected>Fall 2017</option>
		                    	<option value="Select Term" selected>Select Term</option>
		                    </form:select>
						</td>
					</tr>
					<tr>
						<td width="13%">
							<label for="targetGroup">Department</label>
						</td>
						<td width="35%">
							<select class="form-control" name="deptname" id="deptname">
		                    	<c:forEach items="${departments}" var="department" varStatus="status">
		                    		<option value="${department.deptName}">${department.deptName}</option>
		                    	</c:forEach>
		                    	<option value="Select Department" selected>Select Department</option>
		                    </select>
						</td>
						<td width="4%"></td>
						<td width="13%">
							<label for="targetGroup">Program</label>
						</td>
						<td width="35%">
							<select class="form-control" name="prgmname" id="prgmname">
		                    	<option value="Select Program" selected>Select Program</option>
		                    </select>
						</td>
					</tr>
					<tr>
						<td>
							<label for="targetGroup">First Name</label>
						</td>
						<td>
							<form:input path="firstName" class="form-control" placeholder="Enter the First Name" value="${user.firstName}"/>
						</td>
						<td></td>
						<td>
							<label for="targetGroup">Last Name</label>
						</td>
						<td>
							<form:input path="lastName" class="form-control" placeholder="Enter the Last Name" value="${user.lastName}"/>
						</td>
					</tr>
					<tr>
						<td>
							<label for="targetGroup">Date of Birth</label>
						</td>
						<td>
							<input type="text" name="dateofbirth" class="form-control" placeholder="Enter the Date of Birth (MM/dd/yyyy)" />
						</td>
						<td></td>
						<td>
							<label for="targetGroup">Gender</label>
						</td>
						<td>
							<form:select class="form-control" path="gender">
								<option value="Male">Male</option>
								<option value="Female">Female</option>
		                    	<option value="Select Gender" selected>Select Gender</option>
		                    </form:select>
						</td>
					</tr>
					<tr>
						<td>
							<label for="targetGroup">Citizen Country</label>
						</td>
						<td>
							<form:input path="citizenCountry" class="form-control" placeholder="Enter the Citizen Country" />
						</td>
						<td></td>
						<td>
							<label for="targetGroup">Phone No</label>
						</td>
						<td>
							<form:input path="phoneno" class="form-control" placeholder="Enter the Phone No" />
						</td>
					</tr>
					<tr>
						<td colspan="5" align="right">
							<input style="float:right;" type="submit" class="btn btn-primary" value="Save & Next" />
						</td>
					</tr>
				</table>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>