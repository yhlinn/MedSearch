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
<title>About</title>
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
	
	<br>
	<section class="mt-4 px-5">
	<h1 class="display-4">Reference</h1>
	<hr class="my-4">
	<p>
	Wishart DS, Feunang YD, Guo AC, Lo EJ, Marcu A, Grant JR, Sajed T, Johnson D, Li C, Sayeeda Z, Assempour N, Iynkkaran I, Liu Y, Maciejewski A, Gale N, Wilson A, Chin L, Cummings R, Le D, Pon A, Knox C, Wilson M. DrugBank 5.0: a major update to the DrugBank database for 2018. Nucleic Acids Res. 2017 Nov 8. doi: 10.1093/nar/gkx1037.
	</p>
	
	<div class="jumbotron">
	<h2 class="display-6">Legal Disclaimer</h2>
	<p>
	The content provided by this database is for general informational and educational purposes only and does not substitute as medical advice, diagnosis, and treatment. Always seek the advice of your physician or qualified health provider with any questions you may have regarding a medical condition.
	</p>
	<p>
	The DrugBank Database may be used by individuals so long as the use itself or any works derived from the use of the DrugBank Database are not intended to generate sales or profit.
	</p>
	</div>
	
	</section>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	
</body>
</html>
	
	
	
