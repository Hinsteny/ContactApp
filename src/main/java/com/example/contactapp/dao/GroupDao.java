package com.example.contactapp.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.example.contactapp.entity.Group;
import com.google.inject.Singleton;

@Singleton
public class GroupDao {

    private long idseq = 0;
    private Map<Long,Group> groupStore = new ConcurrentHashMap<Long, Group>();
    private Comparator<Group> groupComparator =new Comparator<Group>(){

		@Override
		public int compare(Group o1, Group o2) {
			// TODO Auto-generated method stub
			return (int) (o1.getId() - o2.getId()) ;
		}
        
    };
    
    
    public Map<Long, Group> getGroupStore() {
		return groupStore;
	}

	public GroupDao(){
        create("Friend");
        create("Relative");
    }

    
    synchronized private long newId(){
        return idseq++;
    }
    
    
    public Group create(String groupName){
    	Group group = new Group();
    	group.setId(newId());
    	group.setGroupName(groupName);
    	groupStore.put(group.getId(), group);
        return group;
    }
    
    public Group update(Long id, String groupName) {
    	//get the group ,then update groupName 
    	groupStore.get(id).setGroupName(groupName);
    	return groupStore.get(id);
    }
    
    public Group get(Long id){
    	return groupStore.get(id);
    }
    
    public ArrayList<Long> getGroupIds(){
    	ArrayList<Long> ids = new ArrayList<Long>();
    	 List<Group> groupList = list();
    	 for (int i = 0; i < groupList.size(); i++) {
			ids.add(groupList.get(i).getId());
		}
    	return ids;
    }
    
    public List<Group> list(){
        List<Group> groupList = new ArrayList<Group>(groupStore.values());
        Collections.sort(groupList, groupComparator);
        return groupList;
    }

    
    public Group delete(Long groupId){
        return groupStore.remove(groupId);
    }

    
    
}
