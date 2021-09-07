<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>MedSearch: Delete Account</title>
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
	
<main class="mt-4 px-5">
	<div align="center">
		<h1 class="display-4">Delete Account</h1>
		<br>
		<div align="center">
		<svg class="bd-placeholder-img rounded-circle" width="200"
			height="200" xmlns="http://www.w3.org/2000/svg" role="img"
			preserveAspectRatio="xMidYMid slice" focusable="false">
			<image href="signUp.png" width="100%" height="100%"></image></svg>
		</div>
		
		<hr class="my-4">
		
		<div class="mx-auto" style="width: 40%;">	
			<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<div class="alert alert-warning">
				<br>
				<p>Please enter your username if you are certain you would like to delete
				this account. <br> Note that this deletion cannot be undone.</p>
				</div>
				
				<p>
				Username to be Deleted: <c:if test="${admin!=null}"> ${admin.getUserName()}</c:if>
				<c:if test="${customer!=null}"> ${customer.getUserName()}</c:if>
				</p>
				
				<form action="userdelete" method="post">
					<p> <label for="username">UserName:</label> <input
						id="username" name="username"
						value="${fn:escapeXml(param.username)}"> 
					</p>
					<p>
						<span id="submitButton">
							<input type="submit" value="Delete" class="btn btn-primary">
						</span>
					</p>
				</form>
			</div> 
		</div>
		<br> <br>
		</div>

	<c:if test="${messages.fail != null}">
		<br>
		<div align="center" class="alert alert-danger" id="failMessage">${messages.fail}</div>
		<br>
	</c:if>

	<c:if test="${messages.success != null}">
		<br>
		<div align="center" class="alert alert-success" id="successMessage">
		<p>${messages.success}</p>
		<a class="btn btn-secondary" href="./">Go back to Home Page »</a>
		</div>
		<br>
	</c:if>
	
	</main>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>