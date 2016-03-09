package com.bhargo.domain.model;

import java.util.ArrayList;

public class Location {
	private String name;
	private ArrayList<String> resources;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getResources() {
		return resources;
	}

	public void setResources(ArrayList<String> resources) {
		this.resources = resources;
	}
	
	public String toString() {
		return name;
	}
	
	

}
