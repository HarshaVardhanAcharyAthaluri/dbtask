package com.tasktwo.dbtask.dto;

import java.util.List;


import com.tasktwo.dbtask.model.UserDetails;


public class UserDeatailInfo {
	
	private String name;
	private List<SubClasses> subclasses;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SubClasses> getSubclasses() {
		return subclasses;
	}
	public void setSubclasses(List<SubClasses> subclasses) {
		this.subclasses = subclasses;
	}
	
	
	
	
	
	
}
