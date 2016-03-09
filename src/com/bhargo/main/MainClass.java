package com.bhargo.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.bhargo.domain.model.Location;
import com.bhargo.domain.model.Traveller;

public class MainClass {

	//n,[res1,res2,res3],[res1_arr],[res2_arr],[res3_arr]
	public static void main(String[] args) {
		Map<String, List<String>> incompatMap = createFinalDataSet(args);
		
		//all resources are here when starting
		Location sourceA = new Location();
		sourceA.setResources(getResources(args[1]));
		
		//no resources here when starting
		Location destinationB = new Location();
		
		Traveller traveller = new Traveller(sourceA, destinationB);
		
		//assume 1 resource along with the traveller
		int allowedTravellers = Integer.parseInt(args[0]);
		
		Set<String> resourceSet = incompatMap.keySet();
		Iterator<String> itr = resourceSet.iterator();
		
		while(Integer.compare(destinationB.getResources().size(), getResources(args[1]).size()) != 0) {
			if(first) {
				//highest incompat pass as first param
				traveller.cross(sourceA, destinationB, false, new String[]{pickMostIncompat(incompatMap)});
				first = false;
			} else {
				if(sourceA.getResources().size() >= destinationB.getResources().size()) {
					traveller.cross(sourceA, destinationB, true, null);
					if(checkResources(sourceA, destinationB, incompatMap)) {
						
					}
				} else {
					
				}
				
			}
		}
		
		
	}
	
	static String pickMostIncompat(Map<String, List<String>> incompatMap) {
		int max = 0;
		String str = "";
		Iterator<Map.Entry<String, List<String>>> itr = incompatMap.entrySet().iterator();
		while(itr.hasNext()) {
			Map.Entry<String,List<String>> entry = itr.next();
			int size = entry.getValue().size();
			if(size > max) {
				str = entry.getKey();
				max = size;
			}
		}
		return str;
	}
	
	static boolean first = true;
	
	static boolean checkResources(Location source, Location destination,Map<String, List<String>> incompatMap) {
		if(source.getResources().size() == incompatMap.keySet().size()) {
			first = false;
			return true;
		}
		List<String> tempList = null;
		List<String> incomList = null;
		List<String> sourceResources = source.getResources();
		List<String> destinationResources = destination.getResources();
		for (String string : sourceResources) {
			tempList = removeCurrResource(sourceResources, string);
			incomList = incompatMap.get(string);
			for(int i =0;i<incomList.size();i++) {
				String incom = incomList.get(i); 
				for(int j =0;j<tempList.size();j++) {
					if(tempList.get(j).equals(incom)) {
						return false;
					}
				}
			}
		}
		for (String string : destinationResources) {
			tempList = removeCurrResource(sourceResources, string);
			incomList = incompatMap.get(string);
			for(int i =0;i<incomList.size();i++) {
				String incom = incomList.get(i); 
				for(int j =0;j<tempList.size();j++) {
					if(tempList.get(j).equals(incom)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	static List<String> removeCurrResource(List<String> list, String resource) {
		return list.stream().filter(n -> !n.equals(resource)).collect(Collectors.toList());
	}
	
	static List<String> getResources(String resources) {
		return Arrays.asList(resources.replaceFirst("\\[", "").replaceFirst("\\]", "").split(","));
	}
	
	static Map<String, List<String>> createFinalDataSet(String[] args ) {

		// TODO Auto-generated method stub
		
		List<String> resources = Arrays.asList(args[1].replaceFirst("\\[", "").replaceFirst("\\]", "").split(","));
		Map<String, List<String>> incompatMap = new HashMap<String,List<String>>();
		for(int i =2;i<args.length;i++) {
			String[] tempArr = args[i].replaceAll("\\[", "").replaceAll("\\]", "").split(",");
			List<String> incompatList = new ArrayList<>();
			for (int j = 0; j < tempArr.length; j++) {
				if(tempArr[j].equals("x")) {
					incompatList.add(resources.get(j));
				}
			}
			incompatMap.put(resources.get(i-2), incompatList);			
		}
		
		Set<Map.Entry<String, List<String>>> entrySet = incompatMap.entrySet();
		for (Map.Entry<String, List<String>> entry : entrySet) {
			String key = entry.getKey();
			System.out.println("key " + key);
			entry.getValue().forEach(System.out::println);
		}
		return incompatMap;
	}

}
