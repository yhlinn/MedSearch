<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<title>Find User</title>
</head>
<body>
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
		<div class="container-fluid">
		<img src="logo.png" alt="" width="30" height="24"> &nbsp;
			<a class="navbar-brand" href="./">MedSearch</a>
			<div class="collapse navbar-collapse">
				<ul class="navbar-nav me-auto mb-2 mb-sm-0">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdown"
						data-bs-toggle="dropdown">Administrators</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="reactionscreate">Create
									a New Interaction</a></li>
							<li><a class="dropdown-item" href="reactionsupdate">Update
									an Existing Interaction</a></li>
							<li><a class="dropdown-item" href="prescriptionsupdate">Update
									Prescription</a></li>
							<li><a class="dropdown-item" href="findcustomer">Find
									Customer</a></li>
						</ul></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdown1"
						data-bs-toggle="dropdown">Users</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="finddrugs">Find Drugs</a></li>
							<li><a class="dropdown-item" href="finddrugsbykeyword">Find Drugs by Keyword</a></li>
							<li><a class="dropdown-item" href="findprescriptions">Find
									Prescriptions</a></li>
							<li><a class="dropdown-item" href="findinteractions">Find
									Drug Interactions</a></li>
							<li><a class="dropdown-item" href="finddrugprices">Find
									Drug Prices</a></li>
							<li><a class="dropdown-item" href="finddoctorlastname">
									Find Doctor(Last name)</a></li>
							<li><a class="dropdown-item" href="findpharmacy">Find
									Pharmacy</a></li>
							<li><a class="dropdown-item" href="userdelete">Delete
									User </a></li>
							<li><a class="dropdown-item" href="createcustomer">Create
									Customer </a></li>
						</ul></li>

					<li class="nav-item"><a class="nav-link active" href="reference">Reference</a>
					</li>
				</ul>

			</div>
		</div>
	</nav>
<section class="mt-4 px-5">
	<div align="center">
		<h1 class="display-4">MedSearch: Find Pharmacy</h1>
		<h2 class="lead">Look Up Pharmacy by Pharmacy Name</h2>
		<hr class="my-4">
		
		
		<form action="findpharmacy" method="post">
			<p>
				<label for="PharmacyName">Pharmacy Name: </label> <input id="1"
					name="username" value="${param.username}" placeholder="name"
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
				<tr class="table-striped table-dark">
					<th>Pharmacy Name</th>
					<th>User Name</th>
					<th>Open Time</th>
					<th>Close Time</th>
					<th>Phone</th>
					<th>Street 1</th>
					<th>Street 2</th>
					<th>City</th>
					<th>State</th>
					<th>Zip</th>
					
				</tr>

	<c:forEach items="${pharmacies}" var="pharmacy" >  
		  <tr class="table-striped table-dark">
	      	<td><c:out value="${pharmacy.getPharmacyName()}" /></td>
	      	<td><c:out value="${pharmacy.getUserName()}" /></td>
	      	<td><fmt:formatDate value="${pharmacy.getOpenTime()}" pattern="hh:mm a"></fmt:formatDate></td>
		  	<td><fmt:formatDate value="${pharmacy.getCloseTime()}" pattern="hh:mm a"></fmt:formatDate></td>
		  	<td><c:out value="${pharmacy.getPhone()}" /></td>
		  	<td> <c:out value="${pharmacy.getStreet1()}" /></td>
		  	<td><c:out value="${pharmacy.getStreet2()}" /></td>
		  	<td><c:out value="${pharmacy.getCity()}" /></td>
		  	<td><c:out value="${pharmacy.getState()}" /></td>
		  	<td><c:out value="${pharmacy.getZipcode()}" /></td>
		  </tr>
	
    </c:forEach>
  
			 
			
		</table>
	</div>
	</section>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>