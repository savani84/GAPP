<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	
	<div class="container">
		<div class="page-header">
			<h2>Graduate Program Applications <small>Student (Welcome ${username})</small></h2>
			<a href="studenthome.html"><input style="float:left;" type="button" class="btn btn-primary" value="Home" /></a>
			<a href="logout.html"><input style="float:right;" type="button" class="btn btn-primary" value="Log Out" /></a>
		</div>     
		
  <div class="panel-group" id="accordion">
    <div class="panel panel-primary">
      <div class="panel-heading">
        <h4 class="panel-title">
            <table width="100%">
            	<tr>
            		<td>
            			Application Details
            		</td>
            		<td align="right"><a class="accordion-toggle" style="color: inherit;" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"><span style="cursor: pointer;" class="glyphicon glyphicon-plus"></span></a></td>
            	</tr>
            </table>
        </h4>
      </div>
      <div id="collapseOne" class="panel-collapse collapse in">
        <div class="panel-body">
          <table width="100%">
          	   <tr>
          	   		<c:choose>
          	   			<c:when test="${application.status.status != 'Not Submitted'}">
          	   				<td>Submitted Date</td>
          	   			</c:when>
          	   			<c:otherwise>
          	   				<td style="font-weight: bold;">Last Updated Date</td>
          	   			</c:otherwise>
          	   		</c:choose>
          	   		<fmt:formatDate pattern="MM/dd/yyyy" value="${application.applicationDate}" var="submitdate" />
	          		<td>${submitdate}</td>
	          		<td></td>
	          		<td></td>
	          		<td></td>
	          	</tr>
          	<c:if test="${application.cin != ''}" >
	          	<tr>
	          		<td style="font-weight: bold;">CIN</td>
	          		<td>${application.cin}</td>
	          		<td></td>
	          		<td></td>
	          		<td></td>
	          	</tr>
          	</c:if>
          	<tr>
          		<td style="font-weight: bold;" width="13%">Term</td>
          		<td width="35%">${application.term}</td>
          		<td width="4%"></td>
          		<td style="font-weight: bold;" width="13%">Current Status</td>
          		<td width="35%">${application.status.status}</td>
          	</tr>	
          	<tr>
          		<td style="font-weight: bold;">Department</td>
          		<td>${application.department.deptName}</td>
          		<td></td>
          		<td style="font-weight: bold;">Program</td>
          		<td>${application.program.progName}</td>
          	</tr>
          	<tr>
          		<td style="font-weight: bold;">First Name</td>
          		<td>${application.firstName}</td>
          		<td></td>
          		<td style="font-weight: bold;">Last Name</td>
          		<td>${application.lastName}</td>
          	</tr>
          	<tr>
          		<td style="font-weight: bold;">Date of Birth</td>
          		<fmt:formatDate pattern="MM/dd/yyyy" value="${application.dob}" var="dob" />
          		<td>${dob}</td>
          		<td></td>
          		<td style="font-weight: bold;">Gender</td>
          		<td>${application.gender}</td>
          	</tr>
          	<tr>
          		<td style="font-weight: bold;">Citizen Country</td>
          		<td>${application.citizenCountry}</td>
          		<td></td>
          		<td style="font-weight: bold;">Phone No</td>
          		<td>${application.phoneno}</td>
          	</tr>
          </table>
        </div>
      </div>
    </div>
    <c:if test="${application.degree.size() > 0}">
    <div class="panel panel-primary">
      <div class="panel-heading">
        <h4 class="panel-title">
          <table width="100%">
            	<tr>
            		<td>
            			Academics Details
            		</td>
            		<td align="right"><a class="accordion-toggle" style="color: inherit;" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo"><span style="cursor: pointer;" class="glyphicon glyphicon-plus"></span></a></td>
            	</tr>
            </table>
        </h4>
      </div>
      <div id="collapseTwo" class="panel-collapse collapse">
        <div class="panel-body">
          <table width="100%">
          	<c:forEach items="${application.degree}" var="deg" varStatus="status">
          	<tr>
          		<td style="font-weight: bold;" width="13%">University Name</td>
          		<td width="35%">${deg.universityName}</td>
          		<td width="4%"></td>
          		<td style="font-weight: bold;" width="13%"></td>
          		<td width="35%"></td>
          	</tr>
          	<tr>
          		<td style="font-weight: bold;">Degree Name</td>
          		<td>${deg.degreeName}</td>
          		<td></td>
          		<td style="font-weight: bold;">Major</td>
          		<td>${deg.major}</td>
          	</tr>
          	<tr>
          		<td style="font-weight: bold;">GPA</td>
          		<td>${deg.gpa}</td>
          		<td></td>
          		<td style="font-weight: bold;">Time Period</td>
          		<td>${deg.timePeriod}</td>
          	</tr>
          	<tr style="border-bottom:1px solid;">
          		<td style="font-weight: bold;">Transcript</td>
          		<td><a href="downloadFile.html?type=degree&filename=${deg.transcript}&applicationid=${application.id}">Download the transcript</a></td>
          		<td></td>
          		<td style="font-weight: bold;"></td>
          		<td></td>
          	</tr>
          	</c:forEach>
          </table>
        </div>
      </div>
    </div>
    </c:if>
    <c:if test="${application.otherFieldValue.size() > 0}">
    <div class="panel panel-primary">
      <div class="panel-heading">
        <h4 class="panel-title">
          <table width="100%">
            	<tr>
            		<td>
            			Additional Details
            		</td>
            		<td align="right"><a class="accordion-toggle" style="color: inherit;" data-toggle="collapse" data-parent="#accordion" href="#collapseThree"><span style="cursor: pointer;" class="glyphicon glyphicon-plus"></span></a></td>
            	</tr>
            </table>
        </h4>
      </div>
      <div id="collapseThree" class="panel-collapse collapse">
        <div class="panel-body">
          <table width="100%">
          		<c:forEach items="${application.otherFieldValue}" var="otherval" varStatus="status">
						<tr>
							<td style="font-weight: bold;" width="15%">
								${otherval.otherField.nameField}
							</td>
							<td width="35%">
								<c:choose>
									<c:when test="${fn:toUpperCase(otherval.otherField.typeField) != 'FILE'}">
										${otherval.otherValue}
									</c:when>
									<c:otherwise>
										<a href="downloadFile.html?type=other&filename=${otherval.otherValue}&applicationid=${application.id}">Download the ${otherval.otherField.nameField}</a>
									</c:otherwise>
								</c:choose>
								
							</td>
							<td width="30%"></td>
						</tr>
				</c:forEach>
          </table>
        </div>
      </div>
    </div>
  </div>
  </c:if>
		
	</div>
<script>
	$(function(){
	  $('.panel-collapse:not(".in")')
	    .collapse('show');
	});
</script>
</body>
</html>