brite.registerView("ContactCreate", {emptyParent:false}, {
	create: function(data,config){
		if(typeof data == "undefined") {
			var contact = [];
		} else {
			var contact = data.contact;
		}
	
		return render("tmpl-ContactCreate", {contact : contact});
   },
   
   postDisplay: function(){
	    var contactCreate = this;
   },
   
   events: {
       "click; .form-actions .add": function(event){
    	   var contactCreate = this;
    	   var firstName = contactCreate.$el.find("#firstName").val();
    	   var lastName = contactCreate.$el.find("#lastName").val();
    	   
    	   var contactDao = brite.dao("Contact");
    	   
    	   if(firstName.length <= 0) {
    		   contactCreate.$el.find("#firstNameHelpText").html("<p class='text-error'>unavailable value</p>");
    	   } else {
    		   contactCreate.$el.find("#firstNameHelpText").html("");
    	   }
    	   
    	   if(lastName.length <= 0) {
    		   contactCreate.$el.find("#lastNameHelpText").html("<p class='text-error'>unavailable value</p>");
    	   } else {
    		   contactCreate.$el.find("#lastNameHelpText").html("");
    	   }
    	   
    	   if(firstName.length > 0 && lastName.length > 0 ) {
    		   contactCreate.$el.find("#firstNameHelpText").html("");
    		   contactCreate.$el.find("#lastNameHelpText").html("");
    		   contactDao.create(firstName,lastName).pipe(function(contact) {
    			   brite.display("MainView",$mainview);
    		   });
    		   this.$el.remove();
    	   }
    	  
       },
       
       "click; .form-actions .cancel": function(event){
    	   this.$el.remove();
       },
       
       "click; .form-actions .update": function(event){
    	   var contactCreate = this;
    	   var firstName = contactCreate.$el.find("#firstName").val();
    	   var lastName = contactCreate.$el.find("#lastName").val();
    	   var contactId = contactCreate.$el.find("#firstName").attr("contactId");
    	   
    	   var contactDao = brite.dao("Contact");
    	   
    	   if(firstName.length <= 0) {
    		   contactCreate.$el.find("#firstNameHelpText").html("<p class='text-error'>unavailable value</p>");
    	   } else {
    		   contactCreate.$el.find("#firstNameHelpText").html("");
    	   }
    	   
    	   if(lastName.length <= 0) {
    		   contactCreate.$el.find("#lastNameHelpText").html("<p class='text-error'>unavailable value</p>");
    	   } else {
    		   contactCreate.$el.find("#lastNameHelpText").html("");
    	   }
    	   
    	   if(firstName.length > 0 && lastName.length > 0 ) {
    		   contactCreate.$el.find("#firstNameHelpText").html("");
    		   contactCreate.$el.find("#lastNameHelpText").html("");
    		   contactDao.update(contactId,firstName,lastName).pipe(function(contact) {
    			   brite.display("MainView",$mainview);
    		   });
    		   this.$el.remove();
    	   }
    	   
       }
       
   }
   
 });


