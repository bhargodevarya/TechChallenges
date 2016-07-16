package com.bhargo.amazon.model;

public class Page implements Comparable<Page> {
	private String url;
	private Integer visitedCount;

		public Page() {

	}

	public Page(String url, Integer visitedCount) {
		super();
		this.url = url;
		this.visitedCount = visitedCount;
	}
	
	public Page(String url) {
		super();
		this.url = url;
		this.visitedCount = 1;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getVisitedCount() {
		return visitedCount;
	}

	public void setVisitedCount(Integer visitedCount) {
		this.visitedCount = visitedCount;
	}

	@Override
	public int compareTo(Page o) {
		// TODO Auto-generated method stub
		return -this.visitedCount.compareTo(o.getVisitedCount());
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Page) {
			return this.getUrl().equals(((Page) obj).getUrl());
		} else {
			return false;
		}
	}
	

	@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "page " + getUrl() + " count " + getVisitedCount();
		}
}
