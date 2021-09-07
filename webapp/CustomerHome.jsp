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
<title>MedSearch</title>
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-light bg-light">
		<div class="container-fluid">
		<a href="./">
      			<img src="logo.png" alt="" width="30" height="24">
      	</a> &nbsp; &nbsp;
			<a class="navbar-brand" href="./">MedSearch</a>
			<div class="collapse navbar-collapse">
				<ul class="navbar-nav me-auto mb-2 mb-sm-0">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdown"
						data-bs-toggle="dropdown">Look Up Drug Information</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="finddrugs">Find Drugs by ID</a></li>
							<li><a class="dropdown-item" href="finddrugsbykeyword">Find Drugs by Keyword</a></li>
							<li><a class="dropdown-item" href="findinteractions">Find Drug-Drug Interactions</a></li>
							<li><a class="dropdown-item" href="finddrugprices">Find Drug Prices</a></li>
						</ul></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdown1"
						data-bs-toggle="dropdown">Doctors & Pharmacies</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="finddoctorlastname">Find Doctors</a></li>
							<li><a class="dropdown-item" href="findpharmacy">Find Pharmacies</a></li>
						</ul></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdown2"
						data-bs-toggle="dropdown">My Prescriptions</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="findprescriptions">Look Up Prescriptions</a></li>
							<li><a class="dropdown-item" href="prescriptionsupdate">Update Prescriptions</a></li>
						</ul></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdown3"
						data-bs-toggle="dropdown">Account Management</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="userdelete">Delete My Account</a></li>
						</ul></li>
				</ul>
				<ul class="navbar-nav my-2 my-md-0">
				<li class="nav-item"><a class="nav-link active" href="home">My Account »</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
	<section class="mt-4 px-5">
		<div class="jumbotron p-3 p-md-5 text-white rounded bg-dark">
			<div class="col-md-6 px-0">
				<h1 class="display-4">
					Welcome, <i>${customer.getFirstName()}</i> !
				</h1>
				<p class="lead mb-0">
					<a href="updatecustomer" class="text-white font-weight-bold">Edit My Profile</a> &nbsp;
					<a href="customerlogout" class="text-white font-weight-bold">Logout</a>
				</p>
			</div>
		</div>


		<br> <br>

		<div
			class="bg-light mr-md-3 pt-3 px-3 pt-md-5 px-md-5 text-center overflow-hidden">
			<div class="my-3 py-3">
				<h2 class="display-5">Look up Drug Information</h2>
				<p class="lead">Search our database for information on 500,000+ drugs.</p>
			</div>
			<div>
				<div align="center">
					<a class="btn btn-info" href="finddrugsbykeyword">Find Drugs by Keyword</a> &nbsp; &nbsp;
					<a class="btn btn-info" href="finddrugs">Find Drugs by ID</a> <br>
					<br>
					<a class="btn btn-warning" href="findinteractions">Find
						Drug Interactions</a> <br>
					<br> <a class="btn btn-primary" href="finddrugprices">Find Drug Prices</a>
				</div>
				<br> <br>
			</div>

		</div>

		<div
			class="bg-dark mr-md-3 pt-3 px-3 pt-md-5 px-md-5 text-center text-white  overflow-hidden">
			<div class="my-3 p-3">
				<h2 class="display-5">Doctors & Pharmacies</h2>
				<p class="lead">Search for medical providers within the
					MedSearch network.</p>
			</div>
			<div align="center">
				<a class="btn btn-info" href="finddoctorlastname">Find Doctors</a> <br>
				<br> <a class="btn btn-warning" href="findpharmacy">Find
					Pharmacies</a>
			</div>
			<br> <br>
		</div>
		
		<div
			class="bg-light mr-md-3 pt-3 px-3 pt-md-5 px-md-5 text-center overflow-hidden">
			<div class="my-3 py-3">
				<h2 class="display-5">My Prescriptions</h2>
				<p class="lead">Look up or make changes to your past prescriptions.</p>
			</div>
			<div>
				<div align="center">
					<a class="btn btn-info" href="findprescriptions">Look up Prescription</a> <br>
					<br> <a class="btn btn-warning" href="prescriptionsupdate">Update Prescription</a> 
				</div>
				<br> <br>
			</div>

		</div>
		
		<div
			class="bg-dark mr-md-3 pt-3 px-3 pt-md-5 px-md-5 text-center text-white  overflow-hidden">
			<div class="my-3 p-3">
				<h2 class="display-5">Account Management</h2>
				<p class="lead">Update your profile.</p>
			</div>
			<div align="center">
				<a class="btn btn-info" href="userdelete">Delete My Account</a>
			</div>
			<br> <br>
		</div>
		
	<br><br>
	
	</section>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>