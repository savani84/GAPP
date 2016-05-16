<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script type="text/javascript">
	function otherValidation()
	{
		if(document.getElementById('nameField').value == ''){
			alert("Enter the Name of field...");
			document.getElementById('nameField').focus();
			return false;
		}
		if(document.getElementById('typeField').value == ''){
			alert("Enter the Type of field...");
			document.getElementById('typeField').focus();
			return false;
		}
		if(document.getElementById('otherdeptname').value == 'Select Department'){
			alert("Select Department name...");
			document.getElementById('otherdeptname').focus();
			return false;
		}
		document.getElementById("required").checked = true;
		return true;
	}
</script>
</head>
<body>

	<div class="container">

		<div class="page-header">
			<h2>Graduate Program Applications <small>Administrator (Welcome ${username})</small></h2>
			<a href="logout.html"><input style="float:right;" type="button" class="btn btn-primary" value="Log Out" /></a>
		</div>
		<div class="row">
			<div class="col-md-4">
				<h3>Add Additional Requirement</h3>
				<hr />
				<form action="editOther.html" method="post"  onSubmit="return otherValidation();">
				  <div class="form-group">
				    <label for="newTaskText">Name Of Field</label>
				    <input type="text" name="nameField" id="nameField" value="${others.nameField}" class="form-control" placeholder="Enter the name of field" />
				  	<input type="hidden" name="id" value="${others.id}" />
				  </div>
				  <div class="form-group">
				    <label for="newTaskText">Type Of Field</label>
				    <input type="text" name="typeField" id="typeField" value="${others.typeField}" class="form-control" placeholder="Enter the type of field" />
				  </div>
				  <div class="form-group">
				    <label for="newTaskText">Check if Required</label>
					<c:choose>
						<c:when test="${others.required == true}">
							<input type="checkbox" name="required" id="required" checked />
						</c:when>
						<c:when test="${others.required == false}">
							<input type="checkbox" name="required" id="required" />
						</c:when>
					</c:choose>
				    	<input type="hidden" name="requiredhidd" value="True" />
				  </div>
				  <div class="form-group">
				    <label for="targetGroup">Department</label>
                    <select class="form-control" name="otherdeptname" id="otherdeptname">
                    	<c:forEach items="${departments}" var="department" varStatus="status">
                    		<option 
                    		<c:if test="${department.deptName == others.department.deptName}">
                    			selected
                    		</c:if>
                    		value="${department.deptName}">${department.deptName}</option>
                    	</c:forEach>
                    	<option value="Select Department">Select Department</option>
                    </select>
				  </div>

				  
				  <div class="form-group text-center">
				  	<input type="submit" class="btn btn-primary" name="action" value="Add Additional Requirement" />
				  </div>
				</form>
			</div>
		</div>

	</div>
</body>
</html>