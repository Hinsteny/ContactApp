package com.example.contactapp.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.example.contactapp.entity.Contact;
import com.example.contactapp.entity.Group;
import com.google.inject.Singleton;

@Singleton
public class ContactDao {

    private long idseq = 0;
    private Map<Long,Contact> contactStore = new ConcurrentHashMap<Long, Contact>();
    private Comparator<Contact> contactComparator =new Comparator<Contact>(){
        @Override
        public int compare(Contact c1, Contact c2) {
            return (int) (c1.getId() - c2.getId()) ;
        }
        
    };
    
    
    
    public Map<Long, Contact> getContactStore() {
		return contactStore;
	}

	public ContactDao(){
        create("Bill","Gates");
        create("Larry","Page");
        create("Tim","Cook");
    }

    
    synchronized private long newId(){
        return idseq++;
    }
    
    
    public Contact create(String firstName, String lastName){
        Contact contact = new Contact();
        contact.setId(newId());
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setGroups(new ArrayList<Group>());
        
        contactStore.put(contact.getId(), contact);
        return contact;
    }
    
    public Contact get(Long id){
        return contactStore.get(id);
    }
    
    public List<Contact> list(){
        List<Contact> contactList = new ArrayList(contactStore.values());
        Collections.sort(contactList, contactComparator);
        return contactList;
    }
    
    public Contact update(Long id, String firstName, String lastName) {
    	//get the contact ,then update  
    	contactStore.get(id).setFirstName(firstName);
    	contactStore.get(id).setLastName(lastName);
    	return contactStore.get(id);
    }
    
    public void deleteGroup(Long groupId) {
    	Set<Long> ids = contactStore.keySet();
        for (Long id : ids) {
        	Contact contact = get(id);
        	ArrayList<Group> groups = contact.getGroups();
        	
        	if(groups.size() > 0) {
        		boolean flag = false;
            	int i;
            	for ( i = 0; i < groups.size(); i++) {
    				Group group = groups.get(i);
    				if(group.getId() == groupId) {
    					flag = true;
    					break;
    				}
    			}
            	
            	if(flag) {
            		groups.remove(i);
            	}
            	
        	}
        	
        }
    }
    
    public Contact setGroups(Long contactId, ArrayList<Group> groups) {
    	if(groups != null) {
    		contactStore.get(contactId).setGroups(groups);
    	} else {
    		contactStore.get(contactId).getGroups().clear();
    	}
    	return contactStore.get(contactId);
    }

    
    public Contact delete(Long contactId){
        return contactStore.remove(contactId);
    }

    
    
}
