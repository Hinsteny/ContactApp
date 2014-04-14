brite.viewDefaultConfig.loadTmpl = true;
brite.viewDefaultConfig.loadCss = true;

Handlebars.templates = Handlebars.templates || {};  
function render(templateName,data){
  var tmpl = Handlebars.templates[templateName];
  if (!tmpl){
    tmpl = Handlebars.compile($("#" + templateName).html());
    Handlebars.templates[templateName] = tmpl;
  }
  return tmpl(data);
}

brite.registerView("MainView", {emptyParent:true}, {
	create: function(data,config){
       return render("tmpl-MainView");
   },
   postDisplay: function(){
	    var mainView = this;
	    
	    var contactDao = brite.dao("Contact");
	    var groupDao = brite.dao("Group");
	    var userDao = brite.dao("User");
	    
    	contactDao.list().done(function(contactList) {
    		brite.display("ContactView", mainView.$el.find(".MainView-right"), { contactList : contactList});
    	});
		
    	groupDao.list().done(function(groupList) {
    		brite.display("GroupView", mainView.$el.find(".MainView-left"), { groupList : groupList});
    	});
	    
    	userDao.list().done(function(userList) {
    		brite.display("UserView", mainView.$el.find(".MainView-bottom"), { userList : userList});
    	});
   }
 });



