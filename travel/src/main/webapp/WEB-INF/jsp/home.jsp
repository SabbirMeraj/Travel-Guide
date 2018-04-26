<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Let's Travel</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<script type="text/javascript"
		src="js/main.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
		<c:url value="/css/main.css" var="jstlCss" />
		<link href="${jstlCss}" rel="stylesheet" />
	</head>
	
	<body>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12">
					<div class="header">
						<nav class="navbar navbar-default">
							<div class="container-fluid">
								<div class="navbar-header">
									<a class="navbar-brand" href="/login">Let's Travel</a>
								</div>
								<ul class="nav navbar-nav">
									<li ><a href="/login">Home</a></li>
									<li><a href="/profile?ID=<%out.print(session.getAttribute("logged-user")); %>">Profile</a></li>
									<li><a href="/info?ID=<%out.print(session.getAttribute("logged-user")); %>">About me</a></li>
									<li><a href="/suggestion?ID=<%out.print(session.getAttribute("logged-user")); %>">Suggestion</a></li>
									<li> <a href="/subscription?ID=<%out.print(session.getAttribute("logged-user")); %>">Subscription</a></li>
									<li><a href="/logout">Logout</a></li>
								</ul>
							</div>
						</nav>
					</div>
				</div>
			</div>
			<c:choose>
				<c:when test="${Page=='TIMELINE'}">
					<div class="row">
						<div class="col-sm-3">
							<div class="sidebar">
								<div class="well text-center">
									<a href="uploadPic?ID=<%out.print(session.getAttribute("logged-user")); %>"> 
										
										
										<c:choose>
											<c:when test="${empty profilePic}">
												<img height="100px" width="100px" class="img-circle profilepic" alt="No pro pic" src="pp.png"/> 
											
											</c:when>
										
										
											<c:when test="${not empty profilePic}">
												<img height="100px" width="100px" class="img-circle profilepic" alt="No Image Found" src="data:image/jpg;base64,${profilePic}"/>				
											
											</c:when>
										</c:choose>
									</a> <br>
									<!-- <img src="bird.jpg"  height="100px" width="100px" class="img-circle profilepic" alt="No Image Found"> <br> -->
									<a href="/profile?ID=<%out.print(session.getAttribute("logged-user")); %>"> ${loggedUser.username} </a>
								</div>
						
						
								<div class="well">
									 <p class="text-center">
										<span class="label label-default" style="font-size:20px;"> Intro </span>
									</p>
									
									<div id="intro">
									
										
         								<p> <span class="label label-primary" style="font-size:15px;">  ${loggedUser.getIntro1()} </span> </p>
        								<p> <span class="label label-success" style="font-size:15px;"> ${loggedUser.getIntro2()}</span> </p>
         								<p>  <span class="label label-warning" style="font-size:15px;">${loggedUser.getIntro3()}</span> </p>			
									</div>
									<!--  
									<div id="introForm" style="display:none;">
										<form class="form-horizontal" action="/intro" method="post">
											<div class="form-group">
												<input class="form-control" type="text" name="intro1" >  
											</div>
											
											<div class="form-group">
												<input class="form-control" type="text" name="intro2">  
											</div>
											<div class="form-group">
												<input class="form-control" type="text" name="intro3">  
											</div>
											<div class="form-group">
												<input class="form-control" type="text" name="intro4">  
											</div>
											<div class="form-group">
												<input class="form-control" type="text" name="intro5">  
											</div>
											<div class="form-group">
												<input class="form-control" type="text" name="intro6">  
											</div>
											<div class="form-group">
												<input class="form-control" type="submit" name="add" value="Add">  
											</div>		
													
										</form> 
									</div>
									-->
        
									
									
								</div>
						
								<div class="well">
									<p class="text-center">
								
										<button type="button" class="btn btn-info" data-toggle="collapse" data-target=".demo">Search</button>
										<div id="" class="collapse demo">
											<div class="container-fluid">
  
												<form id="searchForm" name="searchForm" action="/search" method="post">
													<div class="form-group">
														<label for="sel1">Select list (select one):</label>
														<select class="form-control" id="sel1" name="option">
															<option>Place</option>
															<option>Cost</option>
															<option>People</option>
											
														</select>
													</div>
											
													<div class="form-group">
														<input type="text" class="search" placeholder="Search.." name="searchText">
														<i class="fa fa-search" style="font-size:24px;" onClick="functionSearch()"></i>
													</div>
												</form>
											</div>
									
										</div>
								 
									</p>
								</div>
							</div>
						</div>
						
						
						<c:choose>
							<c:when test="${Option=='HOME'}"> 
								<div class="col-sm-9">
									<div class="mainArea">
										
										<div class="row postbox">
										<h2 class="postboxHeading"> Share your experience!</h2>
											<div class="col-sm-10">
												<form class="form-horizontal" action="/post" method="post" enctype="multipart/form-data">
													<input type="hidden" name="UserID" value="<%out.print(session.getAttribute("logged-user")); %>">
													<div class="form-group">
														<input class="form-control" type="text" name="title" placeholder="Enter title here" required>  
													</div>
													<div class="form-group">
														<input class="form-control" type="text" name="cost" placeholder="Approx. cost" required>  
													</div>
													
													<div class="form-group">
														<input class="form-control" type="text" name="place" placeholder="Place" required>  
													</div>
													<div class="form-group">
														<textarea  class="form-control" rows="2"  name="body" style="resize:none; background-color :#eff3f7;" placeholder="Write your post" required ></textarea>
													</div>	
													<div class="form-group submit-button">
														<button  type="submit" class="btn btn-info btn-lg">Post </button>	
													</div>
												</form>
											</div>
										</div>
										<c:forEach var="post" items="${posts}"  varStatus="status">
											<div class="row posts">
												<div class="col-sm-10">
													<div class="fullpost">	
														<h4><small>RECENT POSTS</small></h4>
														<hr>
														<h2> ${post.title}</h2>
														<h5><span class="glyphicon glyphicon-time"></span> Post by <span>${post.getUser().getUsername()}, Sep 24, 2015</span> .</h5>
														
														<p class="text">${post.body}</p>
														<a href="/read-more?PostID=${post.id}"> Read more.. </a>
														
														<!--  
														<div class="buttons">
															<i class="fa fa-thumbs-up" style="font-size:24px;" onclick="functionLike()" ></i>
															 <i class="fa fa-thumbs-down" style="font-size:24px;" onclick="functionUnlike()" ></i> 
														</div>
														-->
														<%!  int flag=0;  %>
          		 										<div class="buttons">
          		 											<c:forEach var="like" items="${likes}" >
          		 		 
          		 	 											<c:choose>
          		 	 												<c:when test="${like.getPost().getId()==post.id && like.getStatus()==1}">
          		 	 													<i id="${post.id}" class="fa fa-thumbs-up like" style="font-size:24px; color:#581af4;" value="true" onclick="functionLike(this,<% out.print(session.getAttribute("logged-user")); %>,${post.id})"> </i>
          		 	 													<% flag=1;%>
          		 	 												</c:when>
          		 	 		
          		 	 												<c:when test="${like.getPost().getId()==post.id && like.getStatus()==0}">
          		 	 													<i id="${post.id}" class="fa fa-thumbs-up like" style="font-size:24px; color:Black;" value="false" onclick="functionLike(this,<% out.print(session.getAttribute("logged-user")); %>,${post.id})"> </i>
          		 	 													<% flag=1;%>
          		 	 												</c:when>
          		 	 											</c:choose>
          		 	 		
          		 	 										</c:forEach>		
          		 	
          		 											<% if(flag==0) {%>
   															<i id="${post.id}" class="fa fa-thumbs-up like" style="font-size:24px; color:Black;" value="false" onclick="functionLike(this,<% out.print(session.getAttribute("logged-user")); %>,${post.id})"> </i>
															<%} flag=0; //out.print(flag); %>
														</div>
														
														<hr>
									
														<button type="button" class="btn btn-info" data-toggle="collapse" data-target="#Comment${post.id}">Show comments</button>
														<div id="Comment${post.id}" class="collapse">
															
															<c:forEach var="c" items="${post.getComment()}">
															<div class="comment">
																<p><span class="label label-danger" style="font-size:15px;">	 ${c.getUser().getUsername()}</span></p>
																<p style="font-size:15px;"><span class="label" style="font-size:15px; color:black;"> ${c.getComment()} </span> </p> 
																<hr>
															</div>
															</c:forEach>
															
															
															
															<p><span class="label label-danger" style="font-size:15px;" id="usernameSpan${post.id}"></span></p>
															<p style="font-size:15px;"><span class="label" style="font-size:15px; color:black;" id="commentSpan${post.id}"></span> </p> 
															
															
															<h4>Leave a Comment:</h4>
															<form class="form-horizontal" action="/add-comment" method="post" enctype="multipart/form-data">
																<input type="hidden" name="UserID" value="<%out.print(session.getAttribute("logged-user")); %>">
																<input type="hidden" name="PostID" value="${post.id}">
																<div class="form-group">
																	<textarea class="form-control" rows="2" style="resize:none; background-color :#eff3f7;" name="comment" ></textarea>
																</div>
																
																<button type="submit" class="btn btn-success" name="addComment">Submit</button>
															</form>
															<br><br>
														</div>
													</div>
								
												</div>
											</div>
										</c:forEach>

									</div>
								</div>
							</c:when>
							
							<c:when test="${Option=='INFO'}">
								<div class="col-sm-9">
									<div class="mainArea">
					
										<div class="well">
											<h1> Personal Information </h1>
											<hr>
											<table class="table table-responsive" style ="word-break:break-all;">
												<tr class="success">
													<td> User Name: </td>
													<td> ${user.username} </td>
												</tr>
												<tr class="danger">
													<td> Email: </td>
													<td> ${user.email} </td>
												</tr>
												<tr class="info">
													<td>  Age: </td>
													<td>  ${user.age} </td>
												</tr>
    								
												<tr class="warning">
													<td>  Password: </td>
													<td>  ${user.password} </td>
												</tr>
    				
												<tr class="active">
													<td> Bio: </td>
													<td>  ${user.bio} </td>
												</tr>
											
												<tr class="success">
													<td>  Interest: </td>
													<td>  ${user.interest} </td>
												</tr>
    											
    											<tr class="info">
    												<td rowspan="3"> Intro </td>
    												<td> ${user.intro1} </td>
    											</tr>
    											
    											<tr class="info">
    												<td>${user.intro2} </td>
    											</tr>
    											<tr class="info">
    												<td>${user.intro3} </td>
    											</tr>
    											
												<tr>
													<td colspan="2"> <a href="/edit-info?ID=<% out.print(session.getAttribute("logged-user"));%>">
													<button class="btn btn-success btn-lg edit-btn"  > Edit</button> </a> </td>
												</tr>
												
											
												
												
												
											</table>
											
										</div>
									</div>
								</div>
							</c:when>
							
							<c:when test="${Option=='EDIT-INFO'}">
								<div class="col-sm-9">
									<div class="mainArea">
					
										<div class="well">
											<h1> Personal Information </h1>
											<hr>
											<form class="form-horizontal" action="/update" method="post" enctype="multipart/form-data">
												<input type="hidden" name="ID" value="<% out.print(session.getAttribute("logged-user")); %>">
							
												<div class="form-group">
													<label class="control-label col-sm-2" for="username">User Name:</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" id="username" name="username" value="${user.username}" required>
													</div>
												</div>
							
							
												<div class="form-group">
													<label class="control-label col-sm-2" for="email">Email:</label>
													<div class="col-sm-10">
														<input type="email" class="form-control" id="email" name="email" value="${user.email}" required>
													</div>
												</div>
							
	
												<div class="form-group">
													<label class="control-label col-sm-2" for="age">Age:</label>
													<div class="col-sm-10">
														<input type="number" class="form-control" id="age" name="age" value="${user.age}" min="15" max="90" required>
													</div>
												</div>


												<div class="form-group">
													<label class="control-label col-sm-2" for="pwd">Password:</label>
													<div class="col-sm-10">
														<input type="password" class="form-control" id="password" name="password" value="${user.password}" required>
													</div>
												</div>
							
												<div class="form-group">
													<label class="control-label col-sm-2" for="bio">Bio:</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" id="bio" name="bio" value="${user.bio}">
													</div>
												</div>
							
												<div class="form-group">
													<label class="control-label col-sm-2" for="bio">Interest:</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" id="interest" name="interest" value="${user.interest}">
													</div>
												</div>
												
												
												
												<div class="form-group">
													<label class="control-label col-sm-2" for="bio">Intro:</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" id="intro1" name="intro1" value="${user.intro1}">
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-2" for="bio"></label>
													<div class="col-sm-10">
														<input type="text" class="form-control" id="intro2" name="intro2" value="${user.intro2}">
													</div>
												</div>
												
												<div class="form-group">
													<label class="control-label col-sm-2" for="bio"></label>
													<div class="col-sm-10">
														<input type="text" class="form-control" id="intro3" name="intro3" value="${user.intro3}">
													</div>
												</div>
												
												<div class="form-group">
													<div class="col-sm-offset-2 col-sm-10">
														<button type="submit" class="btn btn-success btn-lg">Update</button>
													</div>
												</div>
												
												
											</form>
										</div>
									</div>
								</div>
										
							</c:when>
							
							<c:when test="${Option=='UPLOAD'}">
								
								<div class="col-sm-9">
									<div class="mainArea">
					
										<div class="well">
											<h1> Upload Picture </h1>
											<hr>
											<form class="form-horizontal" action="/upload" method="post" enctype="multipart/form-data" >
												<input type="hidden" name="ID" value="${loggedUser.getID()}">
												<input type="hidden" name="username" value="${loggedUser.getUsername()}">
												<input type="hidden" name="email" value="${loggedUser.getEmail()}">
												<input type="hidden" name="age" value="${loggedUser.getAge()}">
												<input type="hidden" name="bio" value="${loggedUser.getBio()}">
												<input type="hidden" name="password" value="${loggedUser.getPassword()}">
												<input type="hidden" name="interest" value="${loggedUser.getInterest()}">
												<input type="hidden" name="intro1" value="${loggedUser.getIntro1()}">
												<input type="hidden" name="intro2" value="${loggedUser.getIntro2()}">
												<input type="hidden" name="intro3" value="${loggedUser.getIntro3()}">
									
									
												<div class="form-group">
													<label class="control-label col-sm-2" for="image">Browse Image:</label>
													<div class="col-sm-10">
														<input type="file" class="form-control" id="image" name="file">
													</div>
												</div>
												
												<div class="form-group">
													<div class="col-sm-offset-2 col-sm-10">
														<button type="submit" class="btn btn-success btn-lg">Update</button>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							
							
							</c:when>
						</c:choose>
					</div>
				</c:when>
				
				<c:when test="${Page=='SUGGESTION'}">
					<div class="row">
						<div class="col-sm-4">
							<div class="well">
								<h3> Top posts </h3>
						
								<c:forEach var="post" items="${posts}">
									<span class="label label-primary" style="font-size:15px;"> ${post.getUser().getUsername()}</span>
									<p class="text"> ${post.body} </p>
									<a href="/read-more?PostID=${post.id}"> Read More... </a>
								<hr>
								</c:forEach>
								
							</div>
						</div>
					
						<div class="col-sm-8">
							<div class="well suggestion">
								<table  class="table">
									<c:forEach var="user" items="${users}">
										<tr>
											<td><h4> ${user.username} </h4> </td>
											<td> <button class="btn btn-primary subscribe-btn" name="subscribe" value="Subscribe" onclick="subscribe(this,<%out.print(session.getAttribute("logged-user")); %>,${user.ID})" >Subscribe</button></td>
										</tr>
							
									</c:forEach>
								</table>
							</div>
						</div>
				
					</div>
				</c:when>
				
				<c:when test="${Page=='SUBSCRIPTION'}">
					<div class="row">
						<div class="col-sm-4">
							<div class="well">
								<h3> Top posts </h3>
						
								<c:forEach var="post" items="${posts}">
									<span class="label label-primary" style="font-size:15px;"> ${post.getUser().getUsername()}</span>
									<p class="text"> ${post.body} </p>
									<a href="/read-more?PostID=${post.id}"> Read More... </a>
								<hr>
								</c:forEach>
								
							</div>
						</div>
					
						<div class="col-sm-8">
							<div class="well suggestion">
								<table  class="table">
									<c:forEach var="subscriber" items="${subscribers}">
										<tr> 
					
											<td><h4> ${subscriber.getUsername()}</h4> </td>
											<td> <button class="btn btn-primary" name="unsubscribe" value="Unsubscribe" onclick="subscribe(this,<%out.print(session.getAttribute("logged-user")); %>,${subscriber.ID})" >Unsubscribe</button></td>
										</tr>
		
									</c:forEach>
								</table>
							</div>
						</div>
				
					</div>
				</c:when>
				
				<c:when test="${Page=='READMORE'}">
					<div class="row">
						<div class="col-sm-12 well">
							<div class="postTitle"> <span class="label label-success"> ${post.title}  </span> </div>
					
							
							<div class="postInfo"> <span class="label label-primary"> By  ${post.getUser().getUsername()} </span>
							 
							</div>
							
							<div class="postInfo"> <span class="label label-warning"> Approximate Cost: ${post.cost}   </span> </div>
							
							   
							<c:choose>
								
								<c:when test="${like==null}"> <div class="postInfo">  <p> <span class="label label-info"> <b>Likes:</b> 0 </span> </p> </div> </c:when>
								<c:when test="${like!=null}"> <div class="postInfo"> <p> <span class="label label-info"> <b>Likes:</b> ${like} </span></p> </div> </c:when>
								
							</c:choose>
							<hr>
							<p class="postBody"><b>  ${post.body}</b> </p>
							
						</div>
				
					</div>
					
					
					
					<div class="row">
						<div class="colo-sm-12 well">
							<h4> Comments</h4>
								<c:forEach var="c" items="${post.getComment()}">
									<div class="comment">
										<p><span class="label label-danger" style="font-size:15px;">	 ${c.getUser().getUsername()}</span></p>
										<p style="font-size:15px;"><span class="label" style="font-size:15px; color:black;"> ${c.getComment()} </span> </p> 
										<hr>
									</div>
								</c:forEach>
						</div>
					
					</div>
					
				
				</c:when>
				
				<c:when test="${Page=='SEARCHRESULT'}">
					<%!  int temp=0;  %>
					<c:forEach var="post" items="${posts}">
						<% temp=1; %>
						<div class="row">
						<div class="col-sm-12 well">
							<div class="postTitle"> <span class="label label-success"> ${post.title}  </span> </div>
					
							
							<div class="postInfo"> <span class="label label-primary"> By  ${post.getUser().getUsername()} </span>
							 
							</div>
							
							<div class="postInfo"> <span class="label label-warning"> Approximate Cost: ${post.cost}   </span> </div>
							
							   
							<c:choose>
								
								<c:when test="${like==null}"> <div class="postInfo">  <p> <span class="label label-info"> <b>Likes:</b> 0 </span> </p> </div> </c:when>
								<c:when test="${like!=null}"> <div class="postInfo"> <p> <span class="label label-info"> <b>Likes:</b> ${like} </span></p> </div> </c:when>
								
							</c:choose>
							<hr>
							<p class="postBody"><b>  ${post.body}</b> </p>
							
						</div>
				
					</div>
					
					</c:forEach>
					
					<%if(temp==0){%>
						
						<div class="alert alert-danger">
								<c:out value="No Result Found"></c:out>
						</div>
						
					<% temp=0; } %>
					
				</c:when>
			</c:choose>
		</div>
				
				
	</body>
</html>
	