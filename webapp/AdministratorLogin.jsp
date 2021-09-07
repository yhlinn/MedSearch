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
<title>Medical Provider Login</title>
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
<div align="center">
<img src="logo.png" width="20%" height="20%"></img>
<br><br>
<h1 class="display-4">Medical Provider Login</h1>
<p>Not a medical provider? <a href="customerlogin"> Log in as a customer »</a></p>
<hr class="my-4">
<br>
<form action="medproviderlogin" method="post">
<div>
<label for="username">Username: </label>
<input id="username" name="username" value="${fn:escapeXml(param.username)}" required>
</div>
&nbsp;
<div>
<label for="password">Password: </label>
<input type="password" id="password" name="password" value="${fn:escapeXml(param.password)}" required>
</div>
&nbsp;
<div>
<button class="btn btn-primary" type="submit">Login</button>
</div>
</form>
</div>

<c:if test="${fail!=null}">
<br>
<div class="alert alert-success" id="successMessage">
${fail}</div>
<br>
</c:if>



</section>

</body>
</html>