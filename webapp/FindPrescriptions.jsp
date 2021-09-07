<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<title>Find Prescriptions</title>
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
		<div class="container-fluid">
      		<a href="./">
      			<img src="logo.png" alt="" width="30" height="24">
      		</a> &nbsp; &nbsp;
			<a class="navbar-brand" href="./"> MedSearch</a>
			<div class="collapse navbar-collapse">
				<ul class="navbar-nav me-auto mb-2 mb-sm-0">
					<li class="nav-item"><a class="nav-link active" href="about">About</a></li>
					<li class="nav-item"><a class="nav-link active" href="create">Sign up</a></li>
					<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="dropdown1"
						data-bs-toggle="dropdown">Login</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="customerlogin">Customer Login</a></li>
							<li><a class="dropdown-item" href="medproviderlogin">Medical Provider Login</a></li>
						</ul>
					</li>
					<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="dropdown"
						data-bs-toggle="dropdown">Demo</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="finddrugsbykeyword">Search for Drugs</a></li>
							<li><a class="dropdown-item" href="findinteractions">Search for Drug Interactions</a></li>
						</ul>
					</li>
				</ul>
				<ul class="navbar-nav my-2 my-md-0">
				<li class="nav-item"><a class="nav-link active" href="home">My Account »</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
<section class="mt-4 px-5">
	<div align="center">
		<h1 class="display-4">MedSearch: Find Prescriptions</h1>
		<h2 class="lead">Look Up Prescription by Prescription ID</h2>
		<hr class="my-4">
		<form action="findprescriptions" method="post">
			<p>
				<label for="prescriptionId">Prescription ID: </label> <input id="prescriptionId"
					name="prescriptionId" value="${param.prescriptionId}" placeholder="123456"
					required>
			</p>
			<p>
				<button class="btn btn-primary" type="submit">Search</button>
			</p>
		</form>
	</div>
	<br />

	<div>
		<h2 class="display-5">Results</h2>
		<c:if test="${success != null}">
		<br>
		<div class="alert alert-success" id="successMessage">
			${success}</div>
		<br>
		</c:if>
		<table class="table" border="1">
			<thead>
				<tr class="table-striped table-dark">
					<th>Prescription ID</th>
					<th>Customer UserName</th>
					<th>Drug ID</th>
					<th>Fill Date</th>
					<th>DoctorUserName</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${prescription!=null}">
					<tr class="table-striped table-dark">
						<td>${prescription.getPrescriptionId()}</td>
						<td>${prescription.getCustomerUserName()}</td>
						<td>${prescription.getDrugId()}</td>
						<td>${prescription.getFillDate()}</td>
						<td>${prescription.getDoctorUserName()}</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	</section>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>
