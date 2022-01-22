<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<meta charset="ISO-8859-1">
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<link href="css/main.css" rel="stylesheet" type="text/css">
<title>Admin Dashboard</title>
</head>
<body>

	<c:set var="map" value='${requestScope["dataMap"]}' />
	
	<c:set var="resultMap" value='${requestScope["mapOfClassTeachSub"]}' />
	
	<c:set var="classStudMap" value='${requestScope["mapOfClassStudents"]}' />

	<c:forEach var="entry" items="${map}">
		<c:if test="${entry.key == 'students'}">
			<c:set var="listOfStudents" value='${entry.value}' />
		</c:if>
		<c:if test="${entry.key == 'classes'}">
			<c:set var="listOfClasses" value='${entry.value}' />
		</c:if>
		<c:if test="${entry.key == 'teachers'}">
			<c:set var="listOfTeachers" value='${entry.value}' />
		</c:if>
		<c:if test="${entry.key == 'subjects'}">
			<c:set var="listOfSubjects" value='${entry.value}' />
		</c:if>		
	</c:forEach>
			
			
	<div align="center" class="mainHeading">
		<h1>ADMIN DASHBOARD</h1>
		<br />
	</div>
	
	
	
	
	<div class="addStudTeachDiv" align="center">
	<h3>ADD NEW STUDENTS AND TEACHERS</h3>
	<br />
		<form name="addStudents" id="addStudents" action="addMasterData" method="post">
		<table>
			<tr>
				<td>
					Student Name: <input type="text" name="txtStudentName" id="txtStudentName" />				
					<input type="submit" name="btnAddStud" value="ADD" />
				</td>
			</tr>
		</table>
		
		<br/>
		
		<table>
			<tr>
				<td>
					Teacher Name: <input type="text" name="txtTeacherName" id="txtTeacherName" />
					<input type="submit" name="btnAddTeacher" value="ADD" />
				</td>
			</tr>
		</table>
		
		</form>
	</div>	
	
	
	
	<div class="students" align="center">
		<h3>ASSIGN STUDENTS</h3>
		<br />
		<form action="updateStudents" name="updateStudents" id="updateStudents" method="post">

			<table>
				<tr>
					<th>Select</th>
					<th>Name of the Student</th>
					<th>Class</th>
				</tr>
				<c:forEach var="stud" items="${listOfStudents}">					
					<tr>
						<td><input type="checkbox" name="chk" id="chk${stud.key}" value="${stud.key}" onclick="enableClass(this)" /></td>
						<td><span style="font-family: courier"><c:out value="${stud.value}" /></span></td>
						<td>
							<select name="sel${stud.key}" id="sel${stud.key}" disabled="disabled">
								<option value="" style="font-family: courier">Select</option>
								<c:forEach var="className" items="${listOfClasses}">
									<option value="${className.key}" style="font-family: courier"><c:out value="${className.value}" /></option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td style="border:0px;">
						<input type="hidden" name="selStudents" id="selStudents" />
						<input type="submit" name="btnUPDStudent" id="btnUPDStudent" value="Submit" onclick="return validateAndSubmit()">
					</td>
				</tr>				
			</table>
		</form>
	</div>
	
	
	
	
	
	
	
	
	
	<div class="subTeachers" align="center">
		<h3>ASSIGN CLASSES, SUBJECTS AND TEACHERS</h3>
		<br />
		<form action="updateTeachersAndSub" name="updateTeachersAndSub" id="updateTeachersAndSub" method="post">
			<table>
				<tr>					
					<th>Name of the Class</th>
					<th>Teacher</th>
					<th>Subject</th>					
				</tr>
							
				<tr>
					<td>
						<select name="selClasses" id="selClasses">
							<option value="" style="font-family: courier">Select</option>
							<c:forEach var="className" items="${listOfClasses}">
								<option value="${className.key}" style="font-family: courier"><c:out value="${className.value}" /></option>
							</c:forEach>
						</select>
					</td>
					
					<td>
						<select name="selTeachers" id="selTeachers">
							<option value="" style="font-family: courier">Select</option>
							<c:forEach var="teachers" items="${listOfTeachers}">
								<option value="${teachers.key}" style="font-family: courier"><c:out value="${teachers.value}" /></option>
							</c:forEach>
						</select>
					</td>
					
					<td>
						<c:forEach var="subjects" items="${listOfSubjects}">
							<input type="checkbox" name="chkSubjects" id="chkSubjects" value="${subjects.value}" />${subjects.value}
						</c:forEach>						
					</td>
					
				</tr>
				
				<tr>
					<td style="border:0px;">						
						<input type="submit" name="btnUPDStudent" id="btnUPDStudent" value="Submit">
					</td>
				</tr>
									
			</table>
		</form>	
	</div>
	
	
	
	
	
	
	<div class="resultSet" align="center">
		<h3>ASSIGNMENT REPORT</h3>
		<br />
		
		<table>
			<tr>					
				<th width="50">Class</th>
				<th width="20">Teacher</th>
				<th width="30">Subject</th>				
			</tr>
			
			<c:if test="${fn:length(resultMap) == 0 }">
				<tr>
					<td colspan="3" align="center">No Records Found</td>
				</tr>
			</c:if>
			<c:forEach var="entry" items="${resultMap}">
				<tr>
					<c:set var="mapOfClassTeachSub" value='${entry.value}' />
					<c:forEach var="listOfCTS" items="${mapOfClassTeachSub}">
						<td>${listOfCTS}</td>
					</c:forEach>
				</tr>				
			</c:forEach>	
		</table>
		
		<br/>
		
		<table>
			<tr>					
				<th>Class</th>				
				<th>Student Name</th>				
			</tr>
			
			<c:if test="${fn:length(classStudMap) == 0 }">
				<tr>
					<td colspan="3" align="center">No Records Found</td>
				</tr>
			</c:if>
			<c:forEach var="entry" items="${classStudMap}">
				<tr>
					<td>${entry.key}</td>
					<td>${entry.value}</td>
				</tr>				
			</c:forEach>	
		</table>
	</div>
	
	
	
	
	
	<div class="messageDiv">
		<table>
			<tr>
				<td>
					<span style="font-family: courier"><b><c:out value="${param.message}" /></b></span>
				</td>
			</tr>
		</table>
	</div>
	
		
	
	<div align="right" class="logout">		
		<table>
			<tr>
				<td>
					<a href="<%=request.getContextPath() %>/logout" >Logout</a>
				</td>
			</tr>
		</table>		
	</div>	
</body>
</html>