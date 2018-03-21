<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />



</head>
<body>
	<div class="container">
		<div class="header">
			<h1>Website</h1>
		</div>
	</div>

	<nav class="navbar navbar-inverse"> </nav>


	<c:choose>

		<c:when test="${Option=='LOGIN'}">
			<div class="container">
				<div class="row">
					<div class="col-sm-6">
					
					</div>
					<div class="col-sm-6 form">
						<form class="form-horizontal" action="/login" method="post">
							<c:if test="${not empty Error}">
								<div class="alert alert-danger">
									<c:out value="${Error}"></c:out>
								</div>
					
							</c:if>
							<div class="form-group">
								<label class="control-label col-sm-2" for="email">Email:</label>
								<div class="col-sm-10">
									<input type="email" class="form-control" id="email"
										placeholder="Enter email" name="email">
								</div>
							</div>


							<div class="form-group">
								<label class="control-label col-sm-2" for="pwd">Password:</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="password"
										placeholder="Enter password" name="password">
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-default">Submit</button>
								</div>
							</div>

							<p>
								Don't have an account? <span class="forgotPassword"> <a
									href="/registration"> Sign up here</a></span>
							</p>
						</form>
					</div>
				</div>
			</div>
		</c:when>
		

		<c:when test="${Option=='REGISTRATION'}">
			<div class="container">
				<div class="row">
					


					<div class="col-sm-12 form">
						<div class="header">
							<h1>Register Here!</h1>
						</div>
						<form class="form-horizontal" action="/register" method="post" >
							
							
						
							
							<input type="hidden" name="ID" >
							
							<div class="form-group">
								<label class="control-label col-sm-2" for="username">User Name:</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="username"
										placeholder="Enter user name" name="username" required>
								</div>
							</div>
							
							
							<div class="form-group">
								<label class="control-label col-sm-2" for="email">Email:</label>
								<div class="col-sm-10">
									<input type="email" class="form-control" id="email"
										placeholder="Enter email" name="email" required>
								</div>
							</div>
							
	
							<div class="form-group">
								<label class="control-label col-sm-2" for="age">Age:</label>
								<div class="col-sm-10">
									<input type="number" class="form-control" id="age"
										placeholder="Enter age" name="age" min="15" max="90" required>
								</div>
							</div>


							<div class="form-group">
								<label class="control-label col-sm-2" for="pwd">Password:</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="password"
										placeholder="Enter password" name="password" required>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-success btn-lg">Submit</button>
								</div>
							</div>

							
						</form>
					</div>
				</div>
			</div>
		
		</c:when>
		
		
	</c:choose>

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>