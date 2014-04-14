brite.registerView("UserView", {emptyParent:true}, {
	create: function(data,config){
	   var userList = data.userList;
       return render("tmpl-UserView", {userList:userList});
   },
   
   postDisplay: function(){
	    var view = this;
   },
   
   events: {
       "click; button.add": function(event){
         brite.display("UserCreate",$mainview);
       },
       
       "click; button.edit": function(event){
    	   var userId = $(event.target).closest("tr").attr("data-user-id");
    	   var userDao = brite.dao("User");
    	   
    	   userDao.get(userId).pipe(function(user) {
    		   brite.display("UserCreate",$mainview,{user : user});
    	   })
       },
         
       "click; button.delete": function(event){
    	   var userId = $(event.target).closest("tr").attr("data-user-id");
    	   var userDao = brite.dao("User");
    	   
    	   userDao.delete(userId).pipe(function(user) {
    		   brite.display("MainView",$mainview);
    	   })
       },
       
   }
 
 });