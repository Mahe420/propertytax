<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Zonal-wise report</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/report.css" type="text/css">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/report.js">
	
</script>
</head>
<body>
	<header>
		<div class="button-nav">
        <button id="cancel">
            <i class="arrow left"></i><span class="button-text">Back</span>
        </button></div>
        <div class="title">
        <h1>Zone wise Collection of Property Tax</h1>
        </div>
	</header>
	<main>
	<table  class="content-table">
		<thead>
			<tr>
				<th>Zone Name</th>
				<th>Property Type</th>
				<th>Amount Collected</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="map" items="${map}">
				<tr>
					<th rowspan="2">${map.key}</th>
					<c:forEach var="innerMap" items="${map.value}">
						<th class="sideline">${innerMap.key}</th>
						<th >&#8377; ${innerMap.value}</th>
				</tr>
			</c:forEach>
			</c:forEach>
		</tbody>
	</table>
	</main>
</body>
</html>