<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <style>    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
  </style>
  
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">Logo</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="/login">Home</a></li>
        
        <li><a href="/suggestion?ID=<%out.print(session.getAttribute("logged-user")); %>">Suggestion</a></li>
      </ul>
      <!--
      <form class="navbar-form navbar-right" role="search">
        <div class="form-group input-group">
          <input type="text" class="form-control" placeholder="Search..">
          <span class="input-group-btn">
            <button class="btn btn-default" type="button">
              <span class="glyphicon glyphicon-search"></span>
            </button>
          </span>        
        </div>
      </form>
      
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-user"></span> My Account</a></li>
      </ul>
      
        -->
    </div>
  </div>
</nav>
  
<div class="container text-center">    
  <div class="row">
    <div class="col-sm-3 well">
      <div class="well">
        <p><a href="/profile?ID=<%out.print(session.getAttribute("logged-user")); %>">My Profile</a></p>
        <img src="bird.jpg" class="img-circle" height="85" width="85" alt="Avatar"> <br/>
        <div class="subscribe-button"> 
        	<button class="btn btn-primary" name="subscribe" value="Subscribe" onclick="subscribe(this)" >Subscribe</button>
        </div>
      </div>
      <div class="well">
        <p><a href="#">Interests</a></p>
        <p>
          <span class="label label-default">News</span>
          <span class="label label-primary">W3Schools</span>
          <span class="label label-success">Labels</span>
          <span class="label label-info">Football</span>
          <span class="label label-warning">Gaming</span>
          <span class="label label-danger">Friends</span>
        </p>
      </div>
      <!--
      <div class="alert alert-success fade in">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
        <p><strong>Ey!</strong></p>
        People are looking at your profile. Find out who.
      </div>
      -->
      
      <p><a href="/info?ID=<%out.print(session.getAttribute("logged-user")); %>">Info</a></p>
      <p><a href="/subscription?ID=<%out.print(session.getAttribute("logged-user")); %>">Subscription</a></p>
      <p><a href="#">Link</a></p>
    </div>
    
    <c:choose>
    	<c:when test="${Option=='HOME'}"> 
    		 <div class="col-sm-7">
    
      <div class="row postbox">
       	<div class="col-sm-12">
       		
       			<form class="form-horizontal" action="/post" method="post" enctype="multipart/form-data">
					
					<input type="hidden" name="UserID" value="<%out.print(session.getAttribute("logged-user")); %>">
					
					<div class="form-group">
						  
						<input class="form-control" type="text" name="title" placeholder="Enter title here" required>  
					
					</div>
					
					<div class="form-group">
						  
						<input class="form-control" type="text" name="cost" placeholder="Approx. cost" required>  
					
					</div>
					
					<div class="form-group">
						<textarea  class="form-control" rows="4"  name="body" style="resize:none;" placeholder="Write your post" required ></textarea>
					
					</div>	
						
					<div class="form-group submit-button">
						
						<button  type="submit" class="btn btn-info btn-lg">Post </button>	
							
					</div>
					
					
				
				</form>
       		
       	</div>
      </div>
      
      <c:forEach var="post" items="${posts}"  varStatus="status">
      	<div class="row">
        	<div class="col-sm-3">
         		 <div class="well">
          			 <p>${post.getUser().getUsername()}</p>
           			<img src="bird.jpg" class="img-circle" height="55" width="55" alt="Avatar">
          		</div>
        	</div>
        	<div class="col-sm-9">
         		 <div class="well">
         		 	<p><b>Title:</b> ${post.title}</p>
         		 	<p><b>Cost:</b> ${post.cost}</p>
          		 	 <p class="text"> <b> Body: </b> ${post.body}</p>
          		 	 <a href="/read-more?PostID=${post.id}"> Read More</a>
          		 	
          		 	<%!  int flag=0;  %>
          		 	<div class="buttons">
          		 	<c:forEach var="like" items="${likes}" >
          		 		 
          		 	 		<c:choose>
          		 	 			<c:when test="${like.getPost().getId()==post.id && like.getStatus()==1}">
          		 	 				<i id="${post.id}" class="fa fa-thumbs-up like" style="font-size:24px; color:Red;" value="true" onclick="functionLike(this,<% out.print(session.getAttribute("logged-user")); %>,${post.id})"> </i>
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
					
        		  	  <a href="/comment?PostID=${post.id}" class="icon"> <i class="fa fa-comment-o" style="font-size:24px;" onclick="functionComment()" ></i><span></span></a>
        		  	
        			</div>
          		 
          		 	<!--<p> </p> --> 
          		 	
        		  </div>
        		  
        		  
        		 
        	</div>
      	</div>
        	
      </c:forEach>
      <!--  
      <div class="row">
        <div class="col-sm-3">
          <div class="well">
           <p>John</p>
           <img src="bird.jpg" class="img-circle" height="55" width="55" alt="Avatar">
          </div>
        </div>
        <div class="col-sm-9">
          <div class="well">
            <p>Just Forgot that I had to mention something about someone to someone about how I forgot something, but now I forgot it. Ahh, forget it! Or wait. I remember.... no I don't.</p>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-3">
          <div class="well">
           <p>Bo</p>
           <img src="bandmember.jpg" class="img-circle" height="55" width="55" alt="Avatar">
          </div>
        </div>
        <div class="col-sm-9">
          <div class="well">
            <p>Just Forgot that I had to mention something about someone to someone about how I forgot something, but now I forgot it. Ahh, forget it! Or wait. I remember.... no I don't.</p>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-3">
          <div class="well">
           <p>Jane</p>
           <img src="bandmember.jpg" class="img-circle" height="55" width="55" alt="Avatar">
          </div>
        </div>
        <div class="col-sm-9">
          <div class="well">
            <p>Just Forgot that I had to mention something about someone to someone about how I forgot something, but now I forgot it. Ahh, forget it! Or wait. I remember.... no I don't.</p>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-3">
          <div class="well">
           <p>Anja</p>
           <img src="bird.jpg" class="img-circle" height="55" width="55" alt="Avatar">
          </div>
        </div>
        <div class="col-sm-9">
          <div class="well">
            <p>Just Forgot that I had to mention something about someone to someone about how I forgot something, but now I forgot it. Ahh, forget it! Or wait. I remember.... no I don't.</p>
          </div>
        </div>
      </div>     
    </div>
    	-->
    	</c:when>
    	
    	
    	<c:when test="${Option=='INFO'}">
    		<div class="col-sm-6 info-form">
    			<h2> Personal Information </h2>
    			<br>
    			<table class="table">
    				<tr>
    					<td>  User Name: </td>
    					<td> ${user.username} </td>
    				</tr>
    				<tr>
    					<td> Email: </td>
    					<td> ${user.email} </td>
    				</tr>
    				<tr>
    					<td>  Age: </td>
    					<td> ${user.age} </td>
    				</tr>
    				
    				<tr>
    					<td>  Password: </td>
    					<td> ${user.password} </td>
    				</tr>
    				
    				<tr>
    					<td> Bio: </td>
    					<td> ${user.bio} </td>
    				</tr>
    				
    				
    				
    				<tr>
    					<td>  Interest: </td>
    					<td> ${user.interest} </td>
    				</tr>
    			
    				<tr>
    					<td colspan="2"> <a href="/edit-info?ID=<% out.print(session.getAttribute("logged-user"));%>">
    					<button class="btn btn-success" class="edit" > Edit</button> </a> </td>
    				</tr>
    			</table>
				
    		</div>
    		
    		
    	</c:when>
    	
    	<c:when test="${Option=='EDIT-INFO'}">
    		<div class="col-sm-6 info-form">
    			<form class="form-horizontal" action="/update" method="post" >
							
							
						
							
							<input type="hidden" name="ID" value="<% out.print(session.getAttribute("logged-user")); %>">
							
							<div class="form-group">
								<label class="control-label col-sm-2" for="username">User Name:</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="username"
										placeholder="Enter user name" name="username" value="${user.username}" required>
								</div>
							</div>
							
							
							<div class="form-group">
								<label class="control-label col-sm-2" for="email">Email:</label>
								<div class="col-sm-10">
									<input type="email" class="form-control" id="email"
										placeholder="Enter email" name="email" value="${user.email}" required>
								</div>
							</div>
							
	
							<div class="form-group">
								<label class="control-label col-sm-2" for="age">Age:</label>
								<div class="col-sm-10">
									<input type="number" class="form-control" id="age"
										placeholder="Enter age" name="age" value="${user.age}" min="15" max="90" required>
								</div>
							</div>


							<div class="form-group">
								<label class="control-label col-sm-2" for="pwd">Password:</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="password"
										placeholder="Enter password" name="password" value="${user.password}" required>
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-sm-2" for="bio">Bio:</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="bio"
										 name="bio" value="${user.bio}">
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-sm-2" for="bio">Interest:</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="interest"
										 name="interest" value="${user.interest}">
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-success btn-lg">Update</button>
								</div>
							</div>

							
						</form>
    		</div>
    		
    		
    	</c:when>
    	
    	<c:when test="${Option=='SUGGESTION'}">
			<div class="col-sm-7">
				<h3> All Users </h3>
				<table  class="table table-striped">
					<c:forEach var="user" items="${users}">
						<tr> 
					
							<td> ${user.username} </td>
							<td> <button class="btn btn-primary" name="subscribe" value="Subscribe" onclick="subscribe(this,<%out.print(session.getAttribute("logged-user")); %>,${user.ID})" >Subscribe</button></td>
						</tr>
		
					</c:forEach>
				</table>
	
			</div>
		</c:when>
		
		<c:when test="${Option=='SUBSCRIPTION'}">
			<div class="col-sm-7">
				<h3> Subscription </h3>
				<table  class="table table-striped">
					<c:forEach var="subscriber" items="${subscribers}">
						<tr> 
					
							<td> ${subscriber.getUsername()} </td>
							<td> <button class="btn btn-primary" name="unsubscribe" value="Unsubscribe" onclick="subscribe(this,<%out.print(session.getAttribute("logged-user")); %>,${subscriber.ID})" >Unsubscribe</button></td>
						</tr>
		
					</c:forEach>
				</table>
	
			</div>
		</c:when>
		<c:when test="${Option=='POST'}">
    		<div class="col-sm-6 info-form">
    			<h2>  ${post.title} </h2>
    			<br>
    			
    			
    			<p> <b> Cost:  </b> ${post.cost}</p>
    			<p> ${post.body}</p>
				<c:choose>
					<c:when test="${like==null}"> <p> <b>Likes:</b> 0 </p> </c:when>
					<c:when test="${like!=null}"> <p> Likes:<b>Likes:</b> ${like} </p> </c:when>
				</c:choose>
				
				<a href="/comment?PostID=${post.id}"> See Comments</a>
    		</div>
    		
    		
    	</c:when>
		
			<c:when test="${Option=='COMMENTS'}">
    		<div class="col-sm-6 info-form">
    			<h2>  ${post.title} </h2>
    			<br>
    			
    			
    			<p> <b> Cost:  </b> ${post.cost}</p>
    			<p> ${post.body}</p>
				<c:choose>
					<c:when test="${like==null}"> <p> <b>Likes:</b> 0 </p> </c:when>
					<c:when test="${like!=null}"> <p> Likes:<b>Likes:</b> ${like} </p> </c:when>
				</c:choose>
				
				<c:forEach var="comment" items="${comments}">
					<div>
						
						<p> <b> ${comment.getUser().getUsername()} </b>>>> ${comment.comment} </p>
					</div>	
				</c:forEach>
				
				<form class="form-horizontal" action="/add-comment" method="post" enctype="multipart/form-data">
					<input type="hidden" name="UserID" value="<%out.print(session.getAttribute("logged-user")); %>">
					<input type="hidden" name="PostID" value="${post.id}">
					
					<div class="form-group"> 
						<textarea rows="2" cols="50" style="resize:none;" name="comment"></textarea>	
					</div>
					<div class="form-group"> 
						<input type="submit" class="btn btn-primary"  name="submitComment" value="Add Comment">
					</div>
					
				</form>
				
				
    		</div>
    		
    		
    	</c:when>
		
		
    </c:choose>
   <!--  
    <div class="col-sm-2 well">
      <div class="thumbnail">
        <p>Upcoming Events:</p>
        <img src="paris.jpg" alt="Paris" width="400" height="300">
        <p><strong>Paris</strong></p>
        <p>Fri. 27 November 2015</p>
        <button class="btn btn-primary">Info</button>
      </div>      
      <div class="well">
        <p>ADS</p>
      </div>
      <div class="well">
        <p>ADS</p>
      </div>
    </div>
    -->
  </div>
</div>

<footer class="container-fluid text-center">
  <p>Footer Text</p>
</footer>

	<script type="text/javascript"
		src="js/main.js"></script>

</body>
</html>