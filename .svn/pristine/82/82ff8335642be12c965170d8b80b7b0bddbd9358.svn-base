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
	function deptValidation()
	{
		if(document.getElementById('deptname').value == ''){
			alert("Enter the Department name...");
			document.getElementById('deptname').focus();
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
		</div>
		<div class="row">
			<div class="col-md-4">
				<h3>Edit Department</h3>
				<hr />

				<form action="editDpt.html" method="POST" onSubmit="return deptValidation();">				  
				  <div class="form-group">
				    <label for="newModuleName">Department Name</label>
				    <input type="text" name="deptname" value="${departments.deptName}" class="form-control" placeholder="Enter the Department Name"/>
				    <input type="hidden" name="id" value="${id}" />
				  </div>
				  <div class="form-group text-center">
				  	<input type="submit" class="btn btn-primary" name="action" value="Save" />
				  </div>
				  <input type="hidden" value="" name="prgmdeptname" />
				</form>
			</div>
		</div>  
	</div>
</body>
</html>