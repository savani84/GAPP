<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script type="text/javascript">
	function prgmValidation()
	{
		if(document.getElementById('progName').value == ''){
			alert("Enter the Program name...");
			document.getElementById('progName').focus();
			return false;
		}
		if(document.getElementById('prgmdeptname').value == 'Select Department'){
			alert("Enter the Program name...");
			document.getElementById('prgmdeptname').focus();
			return false;
		}
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
				<h3>Edit Program</h3>
				<hr />
				<form action="editPrgm.html" method="POST" onSubmit="return prgmValidation();">
				  <div class="form-group">
				    <label for="newStudentName">Program Name</label>
				    <input type="text" name="progName" value="${programs.progName}" class="form-control" placeholder="Enter the Program name" />
				  	<input type="hidden" name="id" value="${programs.id}" />
				  </div>

				  <div class="form-group">
				    <label for="targetGroup">Department</label>
                    <select class="form-control" name="prgmdeptname">
                    	<c:forEach items="${departments}" var="department" varStatus="status">
                    		<option 
                    		<c:if test="${department.deptName == programs.department.deptName}">
                    			selected
                    		</c:if>
                    		value="${department.deptName}">${department.deptName}</option>
                    	</c:forEach>
                    	<option value="Select Department">Select Department</option>
                    </select>
				  </div>
				  
				  <div class="form-group text-center">
				  	<input type="submit" class="btn btn-primary" name="action" value="Save" />
				  </div>
				</form>
			</div>
		</div>
        
	</div>
</body>
</html>