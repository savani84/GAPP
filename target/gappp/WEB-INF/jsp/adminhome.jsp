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
		if(document.getElementById('deptName').value == ''){
			alert("Enter the Department name...");
			document.getElementById('deptName').focus();
			return false;
		}
		return true;
	}
	function prgmValidation()
	{
		if(document.getElementById('progName').value == ''){
			alert("Enter the Program name...");
			document.getElementById('progName').focus();
			return false;
		}
		if(document.getElementById('prgmdeptname').value == 'Select Department'){
			alert("Select Department name...");
			document.getElementById('prgmdeptname').focus();
			return false;
		}
		return true;
	}
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
				<h3>Add Department</h3>
				<hr />

				<form:form modelAttribute="department" onSubmit="return deptValidation();">				  
				  <div class="form-group">
				    <label for="newModuleName">Department Name</label>
				    <form:input path="deptName" class="form-control" placeholder="Enter the Department Name"/>
				  </div>
				  <div class="form-group text-center">
				  	<input type="submit" class="btn btn-primary" name="action" value="Add Department" />
				  </div>
				  <input type="hidden" value="" name="prgmdeptname" />
				  <input type="hidden" value="" name="otherdeptname" />
				</form:form>
			</div>
			<div class="col-md-4">
				<h3>Add Program</h3>
				<hr />
				<form:form modelAttribute="program"  onSubmit="return prgmValidation();">
				  <div class="form-group">
				    <label for="newStudentName">Program Name</label>
				    <form:input path="progName" class="form-control" placeholder="Enter the Program name" />
				  </div>

				  <div class="form-group">
				    <label for="targetGroup">Department</label>
                    <select class="form-control" name="prgmdeptname">
                    	<c:forEach items="${departments}" var="department" varStatus="status">
                    		<option value="${department.deptName}">${department.deptName}</option>
                    	</c:forEach>
                    	<option value="Select Department" selected>Select Department</option>
                    </select>
				  </div>
				  
				  <div class="form-group text-center">
				  	<input type="submit" class="btn btn-primary" name="action" value="Add Program" />
				  </div>
				  <input type="hidden" value="" name="otherdeptname" />
				</form:form>
			</div>
			<div class="col-md-4">
				<h3>Add Additional Requirement</h3>
				<hr />
				<form:form modelAttribute="otherField"  onSubmit="return otherValidation();">
				  <div class="form-group">
				    <label for="newTaskText">Name Of Field</label>
				    <form:input path="nameField" class="form-control" placeholder="Enter the name of field" />
				  </div>
				  <div class="form-group">
				    <label for="newTaskText">Type Of Field</label>
				    <form:input path="typeField" class="form-control" placeholder="Enter the type of field" />
				  </div>
				  <div class="form-group">
				    <label for="newTaskText">Check if Required</label>
				    <form:checkbox path="required" />
				  </div>
				  <div class="form-group">
				    <label for="targetGroup">Department</label>
                    <select class="form-control" name="otherdeptname" id="otherdeptname">
                    	<c:forEach items="${departments}" var="department" varStatus="status">
                    		<option value="${department.deptName}">${department.deptName}</option>
                    	</c:forEach>
                    	<option value="Select Department" selected>Select Department</option>
                    </select>
				  </div>

				  
				  <div class="form-group text-center">
				  	<input type="submit" class="btn btn-primary" name="action" value="Add Additional Requirement" />
				  </div>
				  <input type="hidden" value="" name="prgmdeptname" />
				</form:form>
			</div>
		</div>

		<h3>Department <small>${fn:length(departments)} Total</small></h3>
		<hr />	

		<c:forEach items="${departments}" var="department" varStatus="status">
	    <div class="row">
            <div class="col-xs-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <div class="panel-title">
                            ${department.deptName}
                            <a href="deleteDpt.html?dptid=${department.id}"><span class="pull-right glyphicon glyphicon-trash" style="cursor: pointer;"></span></a>
                            <a href="editDpt.html?dptid=${department.id}"><span class="pull-right glyphicon glyphicon-edit" style="cursor: pointer; padding-right:20px;"></span></a>
                        </div>
                    </div>
                    <div class="panel-body">
                        
                        <div class="row">
                            <div class="col-md-3">
                                <h3>Programs</h3>
                                <hr />

                                <!-- Note: Here, you should use ng-repeat to list all students who are part of this group. -->
                                <ul class="list-unstyled">
                                	<c:forEach items="${programs}" var="Program" varStatus="status">
                                    	<c:if test="${Program.department.deptName == department.deptName}">
                                    		<li>${Program.progName}
                                    			<a href="editPrgm.html?prgmid=${Program.id}"><span class="glyphicon glyphicon-edit" style="cursor: pointer; padding-right:10px;"></span></a>
                                    			<a href="deletePrgm.html?prgmid=${Program.id}"><span style="cursor: pointer;" class="glyphicon glyphicon-trash"></span></a>
                                    		</li>
                                		</c:if>
                                	</c:forEach>
                                </ul>
                            </div>
                            <div class="col-md-8">
                                <h3>Additional Requirement</h3>
                                <hr />
                                
                                <ul class="list-unstyled">
                                    <!-- Note: Here, you should use ng-repeat to iterate 
                                               over all of the tasks associated with the current group   -->
                                    <li>
                                    	<table width="100%">
                                    	<tr>
                                    		<th>Name of field</th>
                                    		<th>Type of field</th>
                                    		<th>Required</th>
                                    	</tr>
                                        <c:forEach items="${otherFields}" var="OtherField" varStatus="status">
											<c:if test="${OtherField.department.deptName == department.deptName}">
											<tr>
												<td>
													${OtherField.nameField}
												</td>
												<td>
													${OtherField.typeField}
												</td>
												<td>
													${OtherField.required}
												</td>
												<td>
													<a href="editOther.html?Otherid=${OtherField.id}"><span class="glyphicon glyphicon-edit" style="cursor: pointer; padding-right:10px;"></span></a>
													<a href="deleteOther.html?Otherid=${OtherField.id}"><span style="cursor: pointer;" class="glyphicon glyphicon-trash"></span></a>
												</td>
											</tr>
											</c:if>
                                        </c:forEach>
                                        </table>
                                        
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </c:forEach>
        
	</div>
</body>
</html>