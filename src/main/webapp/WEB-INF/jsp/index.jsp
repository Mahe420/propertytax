<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Property Tax</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/index.js">
	
</script>
</head>
<body>
	<header>
		<h1>
			<spring:message code="index.title" />
		</h1>
		<label> <select id="locales">
				<option value="">Language</option>
				<option value="en">English</option>
				<option value="fr">Française</option>
				<option value="es">Español</option>
		</select>
		</label>
	</header>
	<main>
	<button class="button1">
		<a href="user"><spring:message code="index.form" /></a>
	</button>
	<button class="button2">
		<a href="report"><spring:message code="index.report" /></a>
	</button>
	</main>
</body>
</html>