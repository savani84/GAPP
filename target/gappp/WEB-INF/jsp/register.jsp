<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script type="text/javascript">
	function registerValidation()
	{
		if(document.getElementById('firstName').value == ''){
			alert("Enter the First Name...");
			document.getElementById('firstName').focus();
			return false;
		}
		if(document.getElementById('lastName').value == ''){
			alert("Enter the Last Name...");
			document.getElementById('lastName').focus();
			return false;
		}
		if(document.getElementById('eMail').value == ''){
			alert("Enter the Email ID...");
			document.getElementById('eMail').focus();
			return false;
		}
		if(document.getElementById('password').value == ''){
			alert("Enter the Password...");
			document.getElementById('password').focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>

	<div class="container">

		<div class="page-header">
			<h2>Graduate Program Applications</h2>
		</div>
		<div class="row">
			<div class="col-md-4">
				<h3>User Registeration</h3>
				<hr />

				<form:form modelAttribute="user" onSubmit="return registerValidation();">				  
				  <div class="form-group">
				    <label for="newModuleName">First Name</label>
				    <form:input path="firstName" class="form-control" placeholder="Enter the First Name"/>
				    <form:errors path="firstName" />
				  </div>
				  <div class="form-group">
				    <label for="newModuleName">Last Name</label>
				    <form:input path="lastName" class="form-control" placeholder="Enter the Last Name"/>
				    <form:errors path="lastName" />
				  </div>
				  <div class="form-group">
				    <label for="newModuleName">Email</label>
				    <form:input path="eMail" class="form-control" placeholder="Enter the Email ID"/>
				    <form:errors path="eMail" />
				  </div>
				  <div class="form-group">
				    <label for="newModuleName">Password</label>
				    <form:input path="password" class="form-control" placeholder="Enter the Password"/>
				    <form:errors path="password" />
				  </div>
				  <div class="form-group text-center">
				  	<input type="submit" class="btn btn-primary" name="action" value="Register" />
				  </div>
				</form:form>
			</div>
		</div>  
	</div>
</body>
</html>