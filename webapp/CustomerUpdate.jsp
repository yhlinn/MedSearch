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
<title>MedSearch: Update Customer Profile</title>
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
		<h1 align="center" class="display-4">Update Customer Profile</h1>
		<br>
		<div align="center">
		<svg class="bd-placeholder-img rounded-circle" width="200"
			height="200" xmlns="http://www.w3.org/2000/svg" role="img"
			preserveAspectRatio="xMidYMid slice" focusable="false">
			<image href="signUp.png" width="100%" height="100%"></image></svg>
		</div>
		
		<hr class="my-4">
		
		<div class="mx-auto" style="width: 70%;">
		<c:if test="${fail != null}">
		<br>
		<div class="alert alert-danger" id="failMessage">
		${fail}
		</div>
		
		<br>
		</c:if>
		<c:if test="${success != null}">
		<br>
		<div class="alert alert-success" id="successMessage">
		<p>${success}</p>
		<a class="btn btn-secondary" href="customerlogout">Login Again »</a>
		 <i>*Note that changes might not be refreshed until you are re-logged in.</i>
		</div>
		<br>
		</c:if>
		
		<form action="updatecustomer" method="post">
			<div class="row g-3">
				<div>
					<label class="form-label" for="username">Username: </label>
					<p>${username}</p>
					
				</div>

				<div>
					<label class="form-label" for="password">Password: </label> <input
						class="form-control" type="text" id="password" name="password"
						value="${password}">
				</div>

				<div>
					<label class="form-label" for="firstName">First Name: </label> <input
						class="form-control" type="text" id="firstName" name="firstName"
						value="${firstname}">

				</div>
				<div>

					<label for="lastName">Last Name: </label> <input
						class="form-control" type="text" id="lastName" name="lastName"
						value="${lastname}">
				</div>

				<div>
					Sex:
					<div class="form-check">
						<label class="form-check-label" for="male"> Male </label> <input
							class="form-check-input" type="radio" name="sex" id="male"
							value="MALE">
					</div>
					<div class="form-check">
						<label class="form-check-label" for="female"> Female </label> <input
							class="form-check-input" type="radio" name="sex" id="female"
							value="FEMALE">
					</div>
				</div>

				<div>
					<label class="form-label" for="age">Age: </label> <input
						class="form-control" type="text" id="age" name="age"
						value="${age}">

				</div>


				<div>
					<label class="form-label" for="phone">Phone: </label> <input
						class="form-control" type="text" id="phone" name="phone"
						value="${phone}">
				</div>

				<div>
					<label class="form-label" for="street1">Address 1: </label> <input
						class="form-control" type="text" id="street1" name="street1"
						value="${street1}">
				</div>

				<div>
					<label class="form-label" for="street2">Address 2: </label> <input
						class="form-control" type="text" id="street2" name="street2"
						value="${street2}">
				</div>

				<div>
					<label class="form-label" for="city">City: </label> <input
						class="form-control" type="text" id="city" name="city"
						value="${city}">
				</div>
				<div>
					<label class="form-label" for="state">State: </label> <select
						class="form-select" id="state" name="state" value="${state}">
						<option value="">--Please choose an option--</option>
						<option value="AL">Alabama</option>
						<option value="AK">Alaska</option>
						<option value="AZ">Arizona</option>
						<option value="AR">Arkansas</option>
						<option value="CA">California</option>
						<option value="CO">Colorado</option>
						<option value="CT">Connecticut</option>
						<option value="DE">Delaware</option>
						<option value="DC">District Of Columbia</option>
						<option value="FL">Florida</option>
						<option value="GA">Georgia</option>
						<option value="HI">Hawaii</option>
						<option value="ID">Idaho</option>
						<option value="IL">Illinois</option>
						<option value="IN">Indiana</option>
						<option value="IA">Iowa</option>
						<option value="KS">Kansas</option>
						<option value="KY">Kentucky</option>
						<option value="LA">Louisiana</option>
						<option value="ME">Maine</option>
						<option value="MD">Maryland</option>
						<option value="MA">Massachusetts</option>
						<option value="MI">Michigan</option>
						<option value="MN">Minnesota</option>
						<option value="MS">Mississippi</option>
						<option value="MO">Missouri</option>
						<option value="MT">Montana</option>
						<option value="NE">Nebraska</option>
						<option value="NV">Nevada</option>
						<option value="NH">New Hampshire</option>
						<option value="NJ">New Jersey</option>
						<option value="NM">New Mexico</option>
						<option value="NY">New York</option>
						<option value="NC">North Carolina</option>
						<option value="ND">North Dakota</option>
						<option value="OH">Ohio</option>
						<option value="OK">Oklahoma</option>
						<option value="OR">Oregon</option>
						<option value="PA">Pennsylvania</option>
						<option value="RI">Rhode Island</option>
						<option value="SC">South Carolina</option>
						<option value="SD">South Dakota</option>
						<option value="TN">Tennessee</option>
						<option value="TX">Texas</option>
						<option value="UT">Utah</option>
						<option value="VT">Vermont</option>
						<option value="VA">Virginia</option>
						<option value="WA">Washington</option>
						<option value="WV">West Virginia</option>
						<option value="WI">Wisconsin</option>
						<option value="WY">Wyoming</option>
					</select>

				</div>
				<div>
					<label class="form-label" for="zipcode">Zipcode: </label> <input
						class="form-control" type="text" id="zipcode" name="zipcode"
						value="${zipcode}">
				</div>

				<div align="center">
					<button class="btn btn-primary" type="submit">Update</button>
				</div>
			</div>
		</form>
	</div>

	<br />

	</section>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>


</body>
</html>