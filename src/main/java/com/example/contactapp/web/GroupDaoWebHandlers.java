package com.example.contactapp.web;

import java.util.List;
import javax.inject.Inject;
import com.britesnow.snow.web.handler.annotation.WebActionHandler;
import com.britesnow.snow.web.param.annotation.WebParam;
import com.example.contactapp.dao.ContactDao;
import com.example.contactapp.dao.GroupDao;
import com.example.contactapp.entity.Group;
import com.google.inject.Singleton;


@Singleton
public class GroupDaoWebHandlers {

    @Inject
    private GroupDao groupDao;
    
    @Inject
    private ContactDao contactDao;


    @WebActionHandler
    public Group daoGroupGet(@WebParam("obj_id")Long id){
    	System.out.println("������һ��Group����Group_id"+id);
    	Group group = groupDao.get(id);
    	return group;
    }
    
    @WebActionHandler
    public List<Group> daoGroupList(){
    	System.out.println("������ȫ��Group����Group_id");
    	return groupDao.list();
    }
    
    
    @WebActionHandler
    public Group daoGroupCreate(@WebParam("groupName")String groupName){
    	System.out.println("��������һ���µ�Group����Group_Name"+groupName);
    	Group group = groupDao.create(groupName);
    	return group;
    }
    
    
    @WebActionHandler
    public Group daoGroupUpdate(@WebParam("groupId")Long groupId, @WebParam("groupName")String groupName){
    	System.out.println("�������һ���µ�Group����Group_Name"+groupName);
    	Group group = groupDao.update(groupId, groupName);
    	return group;
    }
    
    @WebActionHandler
    public Group daoGroupDelete(@WebParam("groupId")Long groupId){
    	Group group = groupDao.delete(groupId);
    	contactDao.deleteGroup(groupId);
    	return group;
    }
    
}
