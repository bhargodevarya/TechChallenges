package com.bhargo.domain.model;

import java.util.Arrays;
import java.util.List;

public class Traveller {
	
	private Location source;
	private Location destination;
	
	public Traveller(Location source, Location destination) {
		this.source = source;
		this.destination = destination;
	}

	public Location getSource() {
		return source;
	}

	public void setSource(Location source) {
		this.source = source;
	}

	public Location getDestination() {
		return destination;
	}

	public void setDestination(Location destination) {
		this.destination = destination;
	}

	public void cross(Location from, Location to, boolean alone, String[] resources) {
		if(!alone) {
			from.getResources().removeAll(Arrays.asList(resources));
			to.getResources().addAll(Arrays.asList(resources));
		}		
	}

}
