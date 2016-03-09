package com.bhargo.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.bhargo.domain.model.Location;
import com.bhargo.domain.model.Traveller;

public class MainClass {

	//n,[res1,res2,res3],[res1_arr],[res2_arr],[res3_arr]
	/*Atleast 1 resource is incompatible with all the other resources i.e. critical resource
	 * */
	public static void main(String[] args) {
		Map<String, List<String>> incompatMap = createFinalDataSet(args);
		
		//all resources are here when starting
		Location sourceA = new Location();
		sourceA.setName("A");
		sourceA.setResources(getResources(args[1]));
		
		//no resources here when starting
		Location destinationB = new Location();
		destinationB.setName("B");
		destinationB.setResources(new ArrayList<String>());
		
		Traveller traveller = new Traveller(sourceA, destinationB);
		
		//assume 1 resource along with the traveller
		int numOfAllowedItems = Integer.parseInt(args[0]);
		
		Set<Set<String>> alwaysTogether = new HashSet<Set<String>>();
		
		//group resources based on incompatibility matrix
		Map<Set<String>,List<String>> groupedData = groupData(incompatMap);
		//System.out.println(groupedData);

		//populate always together
		togetherResources(groupedData, alwaysTogether);
		//System.out.println(alwaysTogether);
	
		boolean solve = false;
		Iterator<Set<String>> itr = alwaysTogether.iterator();
		while(itr.hasNext()) {
			Set<String> together = itr.next();
			if(together.size() == numOfAllowedItems) {
				//System.out.println("can be solved"); 
				solve = true;
				break;
			}
		}
		
		if(solve) {
			solve(numOfAllowedItems, groupedData, traveller, alwaysTogether, incompatMap);
		}
		//System.out.println(traveller.tripCount);
	}
	
	public static void solve(int carried,Map<Set<String>,List<String>> groupedData, 
			Traveller traveller, Set<Set<String>> alwaysTogether,Map<String, List<String>> incompatMap) {
		int totalResources = traveller.getSource().getResources().size();
		boolean firstTripDone = false;
		Set<String> critical = new HashSet<String>();
		//figure out the critical resource group
		Iterator<Set<String>> itr = alwaysTogether.iterator();
		while(itr.hasNext()) {
			Set<String> together = itr.next();
			if(together.size() == carried) {
				critical.addAll(together);
				break;
			}
		}
		
		boolean resourceStatus = false;
		while(traveller.getDestination().getResources().size() != totalResources) {
			//first trip means transfer critical resources
			if(!firstTripDone) {
				traveller.cross(traveller.getSource(), traveller.getDestination(), false, critical);
				firstTripDone = true; 
			} else {
				//figure out the resource groups at both locations
				resourceStatus = checkResources(traveller.getCurrLocation(), traveller.getOtherLocation(traveller.getCurrLocation()), incompatMap);
				if(resourceStatus) {
					//if at peace, check curr location
					if(!traveller.getCurrLocation().equals(traveller.getSource())) {
						//if not source, return empty
						traveller.cross(traveller.getCurrLocation(), traveller.getOtherLocation(traveller.getCurrLocation()), true, null);
					}
					// move resources
					else {
						List<String> currResources = traveller.getCurrLocation().getResources();
						traveller.cross(traveller.getCurrLocation(), traveller.getOtherLocation(traveller.getCurrLocation()),
								false, currResources.stream().limit(carried).collect(Collectors.toSet()));
					}
				} else {
					//else move resources

					List<String> currResources = traveller.getCurrLocation().getResources();
					traveller.cross(traveller.getCurrLocation(), traveller.getOtherLocation(traveller.getCurrLocation()),
							false, currResources.stream().limit(carried).collect(Collectors.toSet()));					
				}				
			}
			
		}
		
	}
	
	static void togetherResources(Map<Set<String>,List<String>> groupedData,Set<Set<String>> alwaysTogether) {
		alwaysTogether.addAll(groupedData.keySet());
	}
	
	
	static Map<Set<String>,List<String>> groupData(Map<String, List<String>> incompatMap) {
		Map<String, List<String>> tempMap = new HashMap<>();
		tempMap.putAll(incompatMap);
		Set<String> keySet = incompatMap.keySet();
		Iterator<String> itr = keySet.iterator();
		Set<String> groupedResources = null;
		Map<Set<String>,List<String>> groupedData = new HashMap<Set<String>, List<String>>();
		while(itr.hasNext()) {
			groupedResources = new HashSet<String>();
			String key = itr.next();
			List<String> incomList = incompatMap.get(key);
			Iterator<String> itr2 = tempMap.keySet().iterator();
			if(!groupedResources.contains(key)) {
				while(itr2.hasNext()) {
					String key2 = itr2.next();
					if(!key2.equals(key)) {
						if(tempMap.get(key2).containsAll(incomList)) {
							groupedResources.add(key2);
							groupedResources.add(key);
							//System.out.println("added " + key + " " + key2);
						}
					} else {
						groupedResources.add(key);
					}
				}
				//groupedResources.forEach(System.out::println);
				if(groupedResources.size() > 0) {
					groupedData.put(groupedResources, incomList);
				}
								
			}
		}
		//System.out.println(groupedData.keySet().size());
		Iterator<Set<String>> itr3 = groupedData.keySet().iterator();
		while(itr3.hasNext()) {
			Set<String> set = itr3.next();
			for (String string : set) {
				//System.out.println("values in keyset" + string);
			}
			//groupedData.get(set).forEach(System.out::println);
		}
		return groupedData;
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
	
	static boolean checkResources(Location source, Location destination,Map<String, List<String>> incompatMap) {
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
			tempList = removeCurrResource(destinationResources, string);
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
	
	static ArrayList<String> getResources(String resources) {
		ArrayList<String> list = new ArrayList<String>();
		list.addAll(Arrays.asList(resources.replaceFirst("\\[", "").replaceFirst("\\]", "").split(",")));
		return list;
	}
	
	static Map<String, List<String>> createFinalDataSet(String[] args ) {		
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
		
		/*Set<Map.Entry<String, List<String>>> entrySet = incompatMap.entrySet();
		for (Map.Entry<String, List<String>> entry : entrySet) {
			//String key = entry.getKey();
			//System.out.println("key " + key);
			//entry.getValue().forEach(System.out::println);
		}*/
		return incompatMap;
	}

}
