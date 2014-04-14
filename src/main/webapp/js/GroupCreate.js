brite.registerView("GroupCreate", {emptyParent:false}, {
	create: function(data,config){
		if(typeof data == "undefined") {
			var group = [];
		} else {
			var group = data.group;
		}
	
		return render("tmpl-GroupCreate", {group : group});
   },
   
   postDisplay: function(){
	    var groupCreate = this;
   },
   
   events: {
       "click; .form-actions .add": function(event){
    	   var groupCreate = this;
    	   var groupName = groupCreate.$el.find("#groupName").val()
    	   var groupDao = brite.dao("Group");
    	   
    	   if(groupName.length > 0) {
    		   groupCreate.$el.find("#groupHelpText").html("");
    		   groupDao.create(groupName).pipe(function(group) {
    			   brite.display("MainView",$mainview);
    			   console.log(group);
    		   });
    		   this.$el.remove();
    		   
    	   } else {
    		   groupCreate.$el.find("#groupHelpText").html("<p class='text-error'>unavailable value</p>");
    	   }
    	  
       },
       
       "click; .form-actions .cancel": function(event){
    	   this.$el.remove();
       },
       
       "click; .form-actions .update": function(event){
    	   var groupCreate = this;
    	   var groupName = groupCreate.$el.find("#groupName").val();
    	   var groupId = groupCreate.$el.find("#groupName").attr("groupId");
    	   var groupDao = brite.dao("Group");
    	   
    	   if(groupName.length > 0) {
    		   groupCreate.$el.find("#groupHelpText").html("");
    		   console.log(groupId + ":" +groupName);
    		   groupDao.update(groupId, groupName).pipe(function(group) {
    			   brite.display("MainView",$mainview);
    		   });
    		   this.$el.remove();
    		   
    	   } else {
    		   groupCreate.$el.find("#groupHelpText").html("<p class='text-error'>unavailable value</p>");
    	   }
       }
       
   }
   
 });


