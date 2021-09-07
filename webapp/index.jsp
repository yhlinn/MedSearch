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

	<div
		class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center">
		<img src="logo.png" width="35%" height="35%"></img>
		<div class="col-md-5 p-lg-5 mx-auto">
<!-- 			<h1 class="display-4 fw-normal">MedSearch</h1> -->
			<p class="lead fw-normal">MedSearch is a medications application
				that provides details about medication such as brand name, generic
				name, side effects, and potential drug interactions for pharmacists,
				pharmacy staff, prescribers and healthcare workers who are unhappy
				with using costly, proprietary applications and/or decentralized
				databases.</p> <br>
			<a class="btn btn-outline-secondary" href="customerlogin">Customer Login</a> <br><br>
			<a class="btn btn-outline-secondary" href="medproviderlogin">Medical Provider Login</a>
		</div>
	</div>

	<!-- secondary text -->
	<div class="p-3 p-md-5 m-md-3 bg-light">
	
	<div class="container marketing">

		<!-- Three columns of text below the carousel -->
		<div class="row">
			<div class="col-lg-4">
				<svg class="bd-placeholder-img rounded-circle" width="140"
					height="140" xmlns="http://www.w3.org/2000/svg" role="img"
					aria-label="Placeholder: 140x140"
					preserveAspectRatio="xMidYMid slice" focusable="false">
					<title>Placeholder</title><image href="signUp.png" width="100%"
						height="100%"></image></svg>

				<h2>Sign Up</h2>
				<p>Join MedSearch now!</p>
				<p>
					<a class="btn btn-secondary" href="create">Sign up now »</a> <br><br>
					
				</p>
			</div>

			<div class="col-lg-4">
				<svg class="bd-placeholder-img rounded-circle" width="140"
					height="140" xmlns="http://www.w3.org/2000/svg" role="img"
					aria-label="Placeholder: 140x140"
					preserveAspectRatio="xMidYMid slice" focusable="false">
					<image href="medicines.png" width="100%" height="100%"></image></svg>
				<h2>Find Drugs</h2>
				<p>Look up drugs with keyword.</p>
				<p>
				<a class="btn btn-secondary" href="finddrugsbykeyword">Find Drugs »</a>
				</p>
			</div>
			<!-- /.col-lg-4 -->
			<div class="col-lg-4">
				<svg class="bd-placeholder-img rounded-circle" width="140"
					height="140" xmlns="http://www.w3.org/2000/svg" role="img"
					aria-label="Placeholder: 140x140"
					preserveAspectRatio="xMidYMid slice" focusable="false">
					<title>Placeholder</title><image href="search.png" width="100%"
						height="100%"></image></svg>

				<h2>Interactions Lookup</h2>
				<p>Search for drug-drug interactions.</p>
				<p>
					<a class="btn btn-secondary" href="findinteractions">Search
						Interactions »</a>
				</p>
			</div>
		</div>
	</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>
