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