<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<title>MedSearch: Sign up</title>
</head>
<body>
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
		<div class="container-fluid">
			<a href="./"> <img src="logo.png" alt="" width="30" height="24">
			</a> &nbsp; &nbsp; <a class="navbar-brand" href="./"> MedSearch</a>
			<div class="collapse navbar-collapse">
				<ul class="navbar-nav me-auto mb-2 mb-sm-0">
					<li class="nav-item"><a class="nav-link active" href="about">About</a></li>
					<li class="nav-item"><a class="nav-link active" href="create">Sign
							up</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdown1"
						data-bs-toggle="dropdown">Login</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="customerlogin">Customer
									Login</a></li>
							<li><a class="dropdown-item" href="medproviderlogin">Medical
									Provider Login</a></li>
						</ul></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdown"
						data-bs-toggle="dropdown">Demo</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="finddrugsbykeyword">Search
									for Drugs</a></li>
							<li><a class="dropdown-item" href="findinteractions">Search
									for Drug Interactions</a></li>
						</ul></li>
				</ul>
				<ul class="navbar-nav my-2 my-md-0">
					<li class="nav-item"><a class="nav-link active" href="home">My
							Account »</a></li>
				</ul>

			</div>
		</div>
	</nav>
	
	<section class="mt-4 px-5">
	<div>	
		<h1 align="center" class="display-4">Create Your MedSearch Account</h1>
		<br>
		<div align="center">
		<img src="logo.png" width="20%" height="20%"></img>
		</div>
		<hr class="my-4">
		<div class="mx-auto" style="width: 70%;">
		<form action="create" method="post">
			<div class="row g-3">
				<div>
					<label class="form-label" for="user">I am registering as a: </label> <select
						class="form-select" id="user" name="user">
						<option value="">--Please choose an option--</option>
						<option value="customer">Patient/Customer</option>
						<option value="doctor">Doctor</option>
						<option value="pharmacy">Pharmacy</option>
					</select>
				</div>
				<div>
				<button class="btn btn-primary" role="submit">Next</button>
				</div>
	
			</div>
		</form>
		</div>
	</div>

	<br />

	<c:if test="${fail != null}">
		<br>
		<div class="alert alert-danger" id="failMessage">${fail}</div>
		<br>
	</c:if>

	</section>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>