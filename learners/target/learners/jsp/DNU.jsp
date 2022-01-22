<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
			
			
	<div align="center" class="">
		<h1>ADMIN DASHBOARD</h1>
		<br />
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
	<div class="students" align="center">
		<h1>ASSIGN STUDENTS</h1>
		<br />
		<form action="updateStudents" name="updateStudents" id="updateStudents">

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
			</table>
		</form>
	</div>
	<div class="buttonDiv">	
		<table>
			<tr>
				<td><input type="hidden" name="selStudents" id="selStudents" /></td>
				<td><input type="submit" name="btnUPDStudent" id="btnUPDStudent" value="Submit" onclick="return validateAndSubmit()"></td>
			</tr>
		</table>
		<table>
			<tr>
				<td>
					<span style="font-family: courier"> <c:out value="${param.message}" /></span>
				</td>
			</tr>
		</table>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<div class="subTeachers" align="center">
		<h1>ASSIGN CLASSES, SUBJECTS AND TEACHERS</h1>
		<br />
		<form action="updateTeachersAndSub" name="updateTeachersAndSub" id="updateTeachersAndSub">	

			<table>
				<tr>
					<th>Select</th>
					<th>Name of the Class</th>
					<th>Teacher</th>
					<th>Subject</th>
				</tr>
				<c:forEach var="clasx" items="${listOfClasses}">					
					<tr>
						<td><input type="checkbox" name="chkbox" id="chkbox${clasx.key}" value="${clasx.key}" onclick="" /></td>
						<td><span style="font-family: courier"><c:out value="${clasx.value}" /></span></td>
						<td>						
							<c:forEach var="teacherName" items="${listOfTeachers}">
								<input type="checkbox" name="chkTeachers${clasx.key}" id="chkTeachers${clasx.key}" value="${teacherName.key}" onclick="" />${teacherName.value}							
							</c:forEach>
						</td>
						<td>
							<c:forEach var="subName" items="${listOfSubjects}">
								<input type="checkbox" name="chkSubjects${clasx.key}" id="chkSubjects${clasx.key}" value="${subName.key}" onclick="" />${subName.value}				
							</c:forEach>
						</td>
					</tr>
				</c:forEach>				
			</table>
		
	</div>
	<div class="btnDiv">	
		<table>
			<tr>
				<td><input type="hidden" name="selClasses" id="selClasses" /></td>
				<td><input type="submit" name="btnUPDStudent" id="btnUPDStudent" value="Submit"></td>
			</tr>
		</table>
		<table>
			<tr>
				<td>
					<span style="font-family: courier"> <c:out value="${param.message2}" /></span>
				</td>
			</tr>
		</table>
		
		</form>
	</div>
</body>
</html>