package com.example.contactapp.entity;

import java.util.ArrayList;

public class Contact {

    private Long   id;    
    private String firstName;
    private String lastName;
    private ArrayList<Group> groups;
    
    public ArrayList<Group> getGroups() {
		return groups;
	}
    
	public void setGroups(ArrayList<Group> groups) {
		this.groups = groups;
	}
	
	public Long getId() {
        return id;
    }
	
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    
}
