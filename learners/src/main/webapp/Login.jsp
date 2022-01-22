<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script> 
<script type="text/javascript" src="js/main.js"></script>
<link href="css/main.css" rel="stylesheet" type="text/css">
<meta charset="ISO-8859-1">
<title>:: Login ::</title>
</head>
<body>

<div class="login" align="center">
	<h1>
	ADMIN LOGIN
	</h1>
	
	<form name="loginForm" id="loginForm" action="authenticate">
		<table>
			<tr>
				<td><span style="font-family:verdana">USERNAME:</span></td>
				<td><input type="text" name="username" id="username" /></td>
			</tr>
			<tr>
				<td><span style="font-family:verdana">PASSWORD:</span></td>
				<td><input type="password" name="password" id="password" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" name="btnSub" id="btnSub" value="Submit" />
				</td>
			</tr>
		</table>
	</form>
</div>

<div id="errorDiv" class="loginPageError">
	<c:out value="${param.message}" />
</div>


</body>
</html>