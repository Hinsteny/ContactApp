package com.example.contactapp.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.britesnow.snow.web.handler.annotation.WebActionHandler;
import com.britesnow.snow.web.param.annotation.WebParam;
import com.example.contactapp.dao.ContactDao;
import com.example.contactapp.dao.GroupDao;
import com.example.contactapp.entity.Contact;
import com.example.contactapp.entity.Group;
import com.google.inject.Singleton;


@Singleton
public class ContactDaoWebHandlers {

    @Inject
    private ContactDao contactDao;
    
    @Inject
    private GroupDao groupDao;
    
    
    private Comparator<Group> groupComparator =new Comparator<Group>(){

		@Override
		public int compare(Group o1, Group o2) {
			// TODO Auto-generated method stub
			return (int) (o1.getId() - o2.getId()) ;
		}
        
    };

    @WebActionHandler
    public Contact daoContactGet(@WebParam("contactId")Long id){
    	System.out.println("contactId:" + id);
    	Contact contact = contactDao.get(id);
    	return contact;
    }
    
    @WebActionHandler
    public List<Group> daoContactGetGroups(@WebParam("contactId")Long id){
    	Contact contact = contactDao.get(id);
    	ArrayList<Group> includeGroups = contact.getGroups();
    	ArrayList<Long> allGroupIds = groupDao.getGroupIds();
    	List<Group> returnGroups = new ArrayList<Group>();
    	Map<Long,Group> nochoseGroups = new HashMap<Long, Group>();
    	
    	if(includeGroups.size() > 0 ) {
    		
    		for (int j = 0; j < allGroupIds.size(); j++) {
    			boolean flag = false;
    			Long aid = allGroupIds.get(j);
    			for (int i = 0; i < includeGroups.size(); i++) {
        			Long iid = includeGroups.get(i).getId();
        			if(aid == iid) {
        				flag = true;
        			}
    				System.out.println(includeGroups.get(i).getId());
        		}
    			if(!flag) {
    				nochoseGroups.put(aid, groupDao.get(aid));
    			}
			}
    		returnGroups = new ArrayList<Group>(nochoseGroups.values());
    		Collections.sort(returnGroups, groupComparator);
    	} else {
    		returnGroups = groupDao.list();
    	}
    	
    	return returnGroups;
    }
    
    @WebActionHandler
    public List<Contact> daoContactList(){
    	return contactDao.list();
    }
    
    @WebActionHandler
    public Contact daoContactCreate(@WebParam("firstName")String firstName, @WebParam("lastName")String lastName){
    	Contact contact = contactDao.create(firstName, lastName);
    	return contact;
    }
    
    @WebActionHandler
    public Contact daoContactUpdate(@WebParam("contactId")Long contactId, @WebParam("firstName")String firstName, 
    		@WebParam("lastName")String lastName){
    	
    	Contact contact = contactDao.update(contactId, firstName, lastName);
    	return contact;
    }
    
    @WebActionHandler
    public Contact daoContactDelete(@WebParam("contactId")Long contactId){
    	Contact contact = contactDao.delete(contactId);
    	return contact;
    }
    
    @WebActionHandler
    public Contact daoContactSetGroup(@WebParam("contactId")Long contactId, @WebParam("groupIds")String groupIds){
    	if(groupIds != null) {
    		String stringarray[] = groupIds.split("\\|");
    		ArrayList<Group> groups = new ArrayList<Group>();
        	for (int i = 0; i < stringarray.length; i++) {
        		Long id = Long.valueOf(stringarray[i]);
        		groups.add(groupDao.get(id));
    		}
        	
        	contactDao.setGroups(contactId, groups);
    	} else {
    		contactDao.setGroups(contactId, null);
    	}
    	
    	return contactDao.get(contactId);
    }
    
    @WebActionHandler
    public Contact daoContactAddGroup(@WebParam("contactId")Long contactId, @WebParam("groupIds")String groupIds){
    	if(groupIds != null) {
    		String stringarray[] = groupIds.split("\\|");
    		ArrayList<Group> groups = new ArrayList<Group>();
        	for (int i = 0; i < stringarray.length; i++) {
        		Long id = Long.valueOf(stringarray[i]);
        		contactDao.getContactStore().get(contactId).getGroups().add(groupDao.get(id));
        		//groups.add(groupDao.get(id));
    		}
        	
        	//contactDao.addGroups(contactId, groups);
    	} else {
    		//contactDao.addGroups(contactId, null);
    	}
    	
    	return contactDao.get(contactId);
    }


}
