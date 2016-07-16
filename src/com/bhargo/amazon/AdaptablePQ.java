package com.bhargo.amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import com.bhargo.amazon.model.Page;

public class AdaptablePQ extends PriorityQueue<Page> {

	// private Page page;
	private static AdaptablePQ instance;

	public static AdaptablePQ getInstance() {
		if (instance == null) {
			instance = new AdaptablePQ();
			return instance;
		} else {
			return instance;
		}
	}

	public void updateVisitedCOunt(Page page) {
		Page tempPage = null;
		int minSeeks = 0;
		while (true) {
			tempPage = this.poll();
			minSeeks++;
			if (tempPage == null)
				break;
			if (tempPage.equals(page)) {
				break;
			} else {
				if (minSeeks <= this.size()) {
					this.add(tempPage);
				} else {
					break;
				}
			}
		}
		if (tempPage != null) {
			tempPage.setVisitedCount(increment(tempPage.getVisitedCount()));
			this.add(tempPage);
		}
	}

	public boolean removePage(Page page) {
		Page tempPage = null;
		int minSeeks = 0, maxSeeks = this.size();
		List<Page> pageList = new ArrayList<>();
		while (true) {
			tempPage = this.poll();
			minSeeks++;
			if (tempPage == null) {
				break;
			} else {
				if (tempPage.getUrl().equals(page.getUrl())) {
					pageList.stream().forEach(n -> this.add(n));
					return true;
				} else {
					if (minSeeks <= maxSeeks) {
						pageList.add(tempPage);
					} else {
						break;
					}
				}
			}
		}
		pageList.stream().forEach(n -> this.add(n));
		return false;
	}

	public Page getPage(String url) {
		List<Page> pageList = new ArrayList<>();
		Page tempPage = null;
		int minSeeks = 0, maxSeeks = this.size();
		while (true) {
			tempPage = this.poll();
			minSeeks++;
			if (tempPage == null)
				break;
			else {
				if (tempPage.getUrl().equals(url)) {
					break;
				} else {
					if (minSeeks <= maxSeeks) {
						pageList.add(tempPage);
					} else {
						// System.out.println("The requested page with the url "
						// + url + " has not been found");
						break;
					}
				}
			}
		}
		if (tempPage != null) {
			this.add(tempPage);
		}
		pageList.stream().forEach(n -> this.add(n));
		// System.out.println("Page not found");
		return tempPage;
	}

	private Integer increment(Integer count) {
		return count + 1;
	}

}
