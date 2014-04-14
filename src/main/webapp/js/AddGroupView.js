brite.registerView("AddGroupView", {emptyParent:false}, {
	create: function(data,config){
		var groupList = data.groupList;
		var contactId = data.contactId;
		return render("tmpl-AddGroupView", {groupList:groupList , contactId : contactId});
	},
   
	postDisplay: function(data,config){
		var groupView = this;
		var contactDao = brite.dao("Contact");
		var contactId = data.contactId;
		
		var contactDao = brite.dao("Contact");
		contactDao.get(contactId).pipe(function(contact) {
			console.log(contact);
			var selectedGroups = contact.groups;
			if(selectedGroups.length > 0) {
				for(var i = 0; i < selectedGroups.length; i++) {
					var groupId = selectedGroups[i].id;
					$("[groupid=" + groupId +"]")[0].checked = true;
				}
			}
				
		});
	    
   },
   
   events: {
       "click; button.setGroup": function(event){
    	  var contactId = this.$el.find("legend").attr("data-contact-id");
    	  var checkboxs = this.$el.find("input[type='checkbox']");
    	  var groupIds = [];
    	  var contactDao = brite.dao("Contact"); 
    		  
    	  checkboxs.each(function(index) {
    		  var checkbox = checkboxs[index];
    		  if(checkbox.checked) {
    			  var groupId = $(checkbox).attr("groupId");
    			  groupIds.push(groupId);
    		  }
    	  });
    	  
    	  contactDao.setGroup(contactId,groupIds);
    	  this.$el.remove();
    	  brite.display("MainView",$mainview);
       },
       
       "click; button.cancel": function(event){
    	   this.$el.remove();
       }
       
   }
   
});


