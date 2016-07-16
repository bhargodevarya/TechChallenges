package com.bhargo.amazon.model;

import java.util.List;

public class sumToElements {
	
	private int sum;
	private List<Integer> indexes;
	public sumToElements(int sum, List<Integer> indexes) {
		super();
		this.sum = sum;
		this.indexes = indexes;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public List<Integer> getIndexes() {
		return indexes;
	}
	public void setIndexes(List<Integer> indexes) {
		this.indexes = indexes;
	}
	
	
	

}
