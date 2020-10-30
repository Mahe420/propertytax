<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Form</title>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/user.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/user.css" type="text/css">

</head>
<body>
	<h1>
		<spring:message code="user.title" />
	</h1>
	<div class="container">
		<form:form method="post" modelAttribute="user" action="/user"
			id="form">
			<table class="table table-hover table-md">
				<tr>
					<td><spring:message code="user.year" /></td>
					<td><form:input path="yearAssessment" /></td>
					<td><form:errors path="yearAssessment" cssClass="error" />
						<div class="errors" id="year">
							<spring:message code="YearContraint.yearAssessment" />
						</div></td>
				</tr>
				<tr>
					<td><spring:message code="user.name" /></td>
					<td><form:input path="name" /></td>
					<td><div style="width: 255px;">
							<form:errors path="name" cssClass="error" />
						</div></td>
				</tr>
				<tr>
					<td><spring:message code="user.email" /></td>
					<td><form:input path="email" /></td>
					<td><form:errors path="email" cssClass="error" /></td>
				</tr>
				<tr>
					<td><spring:message code="user.address" /></td>
					<td><form:textarea path="address" rows="3" cols="50" /></td>
					<td><form:errors path="address" cssClass="error" /></td>
				</tr>
				<tr>
					<td><spring:message code="user.zone" /></td>
					<td><form:select path="zone.name" items="${zone}" /></td>
					<td></td>
				</tr>
				<tr>
					<td><spring:message code="user.description" /></td>
					<td><form:select path="description.description"
							items="${description}" /></td>
					<td></td>
				</tr>
				<tr>
					<td><spring:message code="user.status" /></td>
					<td><form:select path="description.status" items="${status}" /></td>
					<td></td>
				</tr>
				<tr>
					<td><spring:message code="user.contructed" /></td>
					<td><form:input path="constructedYear" /></td>
					<td><form:errors path="constructedYear" cssClass="error" />
						<div class="errors" id="contruct">
							<spring:message code="YearContraint.constructedYear" />
						</div>
						<div class="errors" id="exceed" style="width: 255px;">
							<spring:message code="exceed.constructedYear" />
						</div></td>
				</tr>
				<tr>
					<td><spring:message code="user.area" /></td>
					<td><form:input path="area" /></td>
					<td><form:errors path="area" cssClass="error" />
						<div class="errors" id="areas">
							<spring:message code="Pattern.area" />
						</div></td>
				</tr>
				<tr>
					<td><spring:message code="user.tax" /></td>
					<td><form:input path="totalTax" type="number" id="totalAmount"
							step=".01" /></td>
							<td></td>
				</tr>
			</table>
			<div class="center">
				<input type="submit" id ="pay" value=<spring:message code="user.submit" />
					class="btn btn-lg btn-primary" />
			</div>
		</form:form>
		<div class="center">
			<button id="cancel" class="btn btn-lg btn-danger">
				<spring:message code="user.cancel" />
			</button>
		</div>
	</div>

</body>
</html>