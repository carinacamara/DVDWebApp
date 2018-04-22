<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" session="true" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DVD App</title>
</head>
<body>
	<div align="center">
		<h1>Create new DVD</h1>
		<form:form action="/Client/saveDVD" method="POST" modelAttribute="DVD" >
		
		<table>		
			<tr>
				<td>Title:</td>
				<td><form:input path="title"/></td>
			</tr>
			<tr>
				<td>Year:</td>
				<td><form:input path="year" /></td>
			</tr>
			<tr>
				<td>Price:</td>
				<td><form:input path="price"/></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Save"></td>
			</tr>
		</table>
		</form:form>
	</div>
</body>
</html>