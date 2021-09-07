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
<title>Update Drug Interactions</title>
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
		<h1 class="display-4">MedSearch: Update Drug Interactions</h1>
		<h2 class="lead">Update the Description</h2>
		<hr class="my-4">
		<form action="reactionsupdate" method="post">
			<p>
				<label for="reactionId">Reaction ID: </label> <input id="reactionId"
					name="reactionId" value="${fn:escapeXml(param.reactionId)}">
			</p>
			<p>
				<label for="newDescription">New Description: </label> <input
					id="newDescription" name="newDescription"
					value="${fn:escapeXml(param.newDescription)}">
			</p>
			<p>
				<button class="btn btn-primary" type="submit">Update</button>
			</p>
		</form>
	</div>
	<br />
	<c:if test="${messages.fail != null}">
		<br>
		<div class="alert alert-danger" id="failMessage">${messages.fail}</div>
		<br>
	</c:if>
	<c:if test="${messages.success != null}">
		<br>
		<div class="alert alert-success" id="successMessage">${messages.success}</div>
		<br>
	</c:if>
</section>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>
