brite.registerView("UserCreate", {emptyParent:false}, {
	create: function(data,config){
		if(typeof data == "undefined") {
			var user = [];
		} else {
			var user = data.user;
		}
	
		return render("tmpl-UserCreate", {user : user});
   },
   
   postDisplay: function(){
	    var userCreate = this;
   },
   
   events: {
       "click; .form-actions .add": function(event){
    	   var userCreate = this;
    	   var userName = userCreate.$el.find("#userName").val();
    	   var userPassword = userCreate.$el.find("#userPassword").val();
    	   var userSex = userCreate.$el.find("#userSex").val();
    	   
    	   var userDao = brite.dao("User");
    	   
    	   if(userName.length <= 0) {
    		   userCreate.$el.find("#userNameHelpText").html("<p class='text-error'>unavailable value</p>");
    	   } else {
    		   userCreate.$el.find("#userNameHelpText").html("");
    	   }
    	   
    	   if(userPassword.length <= 0) {
    		   userCreate.$el.find("#userPasswordHelpText").html("<p class='text-error'>unavailable value</p>");
    	   } else {
    		   userCreate.$el.find("#userPasswordHelpText").html("");
    	   }
    	   
    	   if(userSex.length <= 0) {
    		   userCreate.$el.find("#userSexHelpText").html("<p class='text-error'>unavailable value</p>");
    	   } else {
    		   userCreate.$el.find("#userSexHelpText").html("");
    	   }
    	   
    	   if(userName.length > 0 && userPassword.length > 0 && userSex.length > 0) {
    		   userCreate.$el.find("#userNameHelpText").html("");
    		   userCreate.$el.find("#userPasswordHelpText").html("");
    		   userCreate.$el.find("#userSexHelpText").html("");
    		   userDao.create(userName,userPassword,userSex).pipe(function(contact) {
    			   brite.display("MainView",$mainview);
    		   });
    		   this.$el.remove();
    	   }
    	  
       },
       
       "click; .form-actions .cancel": function(event){
    	   this.$el.remove();
       },
       
       "click; .form-actions .update": function(event){
    	   var userCreate = this;
    	   var userName = userCreate.$el.find("#userName").val();
    	   var userPassword = userCreate.$el.find("#userPassword").val();
    	   var userSex = userCreate.$el.find("#userSex").val();
    	   var userId = userCreate.$el.find("#userName").attr("userId");
    	   
    	   var userDao = brite.dao("User");
    	   
    	   if(userName.length <= 0) {
    		   userCreate.$el.find("#userNameHelpText").html("<p class='text-error'>unavailable value</p>");
    	   } else {
    		   userCreate.$el.find("#userNameHelpText").html("");
    	   }
    	   
    	   if(userPassword.length <= 0) {
    		   userCreate.$el.find("#userPasswordHelpText").html("<p class='text-error'>unavailable value</p>");
    	   } else {
    		   userCreate.$el.find("#userPasswordHelpText").html("");
    	   }
    	   
    	   if(userSex.length <= 0) {
    		   userCreate.$el.find("#userSexHelpText").html("<p class='text-error'>unavailable value</p>");
    	   } else {
    		   userCreate.$el.find("#userSexHelpText").html("");
    	   }
    	   
    	   if(userName.length > 0 && userPassword.length > 0 && userSex.length > 0) {
    		   userCreate.$el.find("#userNameHelpText").html("");
    		   userCreate.$el.find("#userPasswordHelpText").html("");
    		   userCreate.$el.find("#userSexHelpText").html("");
    		   userDao.update(userId,userName,userPassword,userSex).pipe(function(user) {
    			   brite.display("MainView",$mainview);
    		   });
    		   this.$el.remove();
    	   }
    	   
       }
       
   }
   
 });


