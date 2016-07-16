package com.bhargo.main;

import java.util.ArrayList;
import java.util.List;

import com.bhargo.amazon.MaximumSum;
import com.bhargo.amazon.WebsiteAnalyzer;
import com.bhargo.amazon.model.Page;

public class MainClass {

	//n,[res1,res2,res3],[res1_arr],[res2_arr],[res3_arr]
	/*critical resource :- resource which is incompatible with all the other resources i.e. critical resource, assuming atleast 1
	 * n - num of resources that can be carried through the bridge 
	 * */
	public static void main(String[] args) {
		//BoatmanBridgeSolution.solve(args);
		//solveMaximum();
		//solveWebsiteChallenge();

	}
	
	static void solveMaximum() {
		List<Integer> inputSet = new ArrayList<>();
		inputSet.add(1);
		inputSet.add(2);
		inputSet.add(4);
		inputSet.add(1);
		inputSet.add(1);
		inputSet.add(7);
		MaximumSum ms = new MaximumSum(inputSet);
		ms.solve();
	}
	
	static void solveWebsiteChallenge() {
		WebsiteAnalyzer websiteAnalyzer = new WebsiteAnalyzer();
		//create pages
		Page  p1 = new Page("p1");
		Page  p2 = new Page("p2");
		Page  p3 = new Page("p3");
		Page  p4 = new Page("p4");
		Page  p5 = new Page("p5");
		Page  p6 = new Page("p6");
		
		
		//add to the queue
		websiteAnalyzer.addToQueue(p1);
		websiteAnalyzer.addToQueue(p2);
		websiteAnalyzer.addToQueue(p3);
		websiteAnalyzer.addToQueue(p4);
		websiteAnalyzer.addToQueue(p4);
		websiteAnalyzer.addToQueue(p2);
		websiteAnalyzer.addToQueue(p1);
		websiteAnalyzer.addToQueue(p1);
		websiteAnalyzer.addToQueue(p1);
		websiteAnalyzer.addToQueue(p1);
		websiteAnalyzer.addToQueue(p1);
		websiteAnalyzer.addToQueue(p5);
		websiteAnalyzer.addToQueue(p6);
		
		//System.out.println("original queue");
		// to check the queue, populate again after using this method
		//websiteAnalyzer.printQueue();
		
		
		List<Page> resultList = websiteAnalyzer.getTopNPages(3);
		resultList.stream().forEach(System.out::println);
		
		websiteAnalyzer.reportPageAccess("p4");
		
		//System.out.println("after original queue");
		//websiteAnalyzer.printQueue();
	}
}
