package com.bhargo.domain.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Traveller {
	
	public int tripCount;
	
	private Location source;
	private Location destination;
	private Location currLocation;
	
	public Traveller(Location source, Location destination) {
		this.source = source;
		this.destination = destination;
		this.currLocation = source;
	}
	
	public Location getCurrLocation() {
		return currLocation;
	}

	public void setCurrLocation(Location currLocation) {
		this.currLocation = currLocation;
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

	public void cross(Location from, Location to, boolean alone, Set<String> resources) {
		if(!alone) {
			Iterator<String> itr = resources.iterator();
			String str = null;
			ArrayList<String> resourceListSource = null;
			ArrayList<String> resourceListDest = null;
			while(itr.hasNext()) {
				str = itr.next();
				resourceListSource = from.getResources();
				resourceListSource.remove(str);
				resourceListDest = to.getResources();
				resourceListDest.add(str);
			}
			from.setResources(resourceListSource);
			to.setResources(resourceListDest);
			for (String string : resources) {
				System.out.print(string + " ");
			}
			System.out.println("(from shore " + from.toString() + " to " + to.toString() + ")");
		}
		else {
			System.out.println("Empty (from shore " + from.toString() + " to " + to.toString() + ")");
		}
		setCurrLocation(to);
		
		/*Iterator<String> itr1 = getSource().getResources().iterator();
		System.out.print("source resources ");
		while(itr1.hasNext()) {
			System.out.print(itr1.next());
		}
		System.out.println();
		System.out.print("destination resources ");
		Iterator<String> itr2 = getDestination().getResources().iterator();
		while(itr2.hasNext()) {
			System.out.print(itr2.next());
		}
		System.out.println();*/
		tripCount++;
	}
	
	public Location getOtherLocation(Location currLocationParam) {
		if(currLocationParam.equals(source)) {
			return destination;
		} else {
			return source;
		}		
	}
}
