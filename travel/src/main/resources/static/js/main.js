function subscribe(btn,UserID,SubscriberID){
	
	
	
	if(btn.value=="Subscribe"){
		$.ajax({
			
			
			
			method:"post",
			url:"/subscribe?UserID="+UserID+"&SubscriberID="+SubscriberID,
			success: function(){
				btn.value="Unsubscribe";
				btn.innerHTML="Unsubscribe";
				
			}
		});
	}
	else{
		$.ajax({
			
			
			
			method:"post",
			url:"/unsubscribe?UserID="+UserID+"&SubscriberID="+SubscriberID,
			success: function(){
				btn.value="Subscribe";
				btn.innerHTML="Subscribe";
				
			}
		});
	}
	

}


function functionLike(btn,UserID,PostID){
	
	
	if(btn.getAttribute("value")=="false"){
		
		$.ajax({
			
			
			
			method:"post",
			url:"/like?UserID="+UserID+"&PostID="+PostID,
			success: function(){
				btn=document.getElementById(""+PostID);
				
				btn.style.color="Red";
				btn.setAttribute("value","true");
				
			}
		});
		
	}
	else{
	$.ajax({
			
			
			
			method:"post",
			url:"/unlike?UserID="+UserID+"&PostID="+PostID,
			success: function(){
				btn.style.color="Black";
				btn.setAttribute("value","false");
				
			}
		});
		
	}
	

}


function functionIntro(){
	var x=document.getElementById("intro");
	var y=document.getElementById("introForm");
	
	if(x.style.display=="block"){
		x.style.display="none";
		y.style.display="block";
	}
	
	else{
		y.style.display="none";
		x.style.display="block";
	}
	

}
function functionSearch(){
	var x=document.forms["searchForm"]["option"].value;
	var y=document.forms["searchForm"]["searchText"].value;
	
	if(y!=''){
		document.getElementById("searchForm").submit();
	}
	else{
		alert("Enter some text");
	}
}