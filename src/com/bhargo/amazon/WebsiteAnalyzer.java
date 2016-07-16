package com.bhargo.amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import com.bhargo.amazon.model.Page;

public class WebsiteAnalyzer {

	// you may declare and use other variables or
	// other helper methods you may need
	public void reportPageAccess(String pageUrl) {
		AdaptablePQ adaptableQueue = getTheQueue();
		if (adaptableQueue.size() > 0) {
			Page requestedPage = null;
			requestedPage = adaptableQueue.getPage(pageUrl);
			if (requestedPage != null) {
				System.out.println("The requested page with URL " + requestedPage.getUrl() + " has been visited "
						+ requestedPage.getVisitedCount());
			}
		} else {
			System.out.println("There are no pages");
		}
	}
	
	public void pageUpdate(Page page) {
		AdaptablePQ adaptableQueue = getTheQueue();
		PriorityQueue<Page> pq = (PriorityQueue<Page>) adaptableQueue;
		Page tempPage = null;
		List<Page> pageList = new ArrayList<>();
		if(pq.size() > 0) {
			tempPage = adaptableQueue.getPage(page.getUrl());
			if(tempPage != null) {
				if(adaptableQueue.removePage(tempPage)) {
					tempPage.setVisitedCount(tempPage.getVisitedCount()+1);
					adaptableQueue.add(tempPage);
				}
			} else {
				pq.add(page);
			}
		} else {
			pq.add(page);
		}
		
		
	}

	// the size of the list returned must be n
	@SuppressWarnings("rawtypes")
	public List getTopNPages(int n) {
		// your pre here
		AdaptablePQ adaptableQueue = getTheQueue();
		PriorityQueue<Page> pq = (PriorityQueue<Page>) adaptableQueue;
		Page page = null;
		List<Page> pageList = new ArrayList<>();
		if (pq.size() > 0) {
			if (pq.size() < n) {
				System.out.println("The requested num of pages doesnot exist. The num of pages is " + pq.size());
			}
			while (n > 0) {
				page = pq.poll();
				pageList.add(page);
				n--;
			}
		} else {
			System.out.println("No pages exist");
		}
		pageList.stream().forEach(p -> pq.add(p));
		return pageList;
	}

	public void addToQueue(Page page) {
		//AdaptablePQ adaptableQueue = getTheQueue();
		pageUpdate(page);
		//adaptableQueue.add(page);
	}

	public void printQueue() {
		AdaptablePQ adaptableQueue = AdaptablePQ.getInstance();
		Page polled = null;
		while (true) {
			polled = adaptableQueue.poll();
			if (polled != null) {
				System.out.println(polled);
			} else
				break;
		}
	}

	private AdaptablePQ getTheQueue() {
		return AdaptablePQ.getInstance();
	}

}
