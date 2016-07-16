package com.bhargo.amazon;

import java.util.ArrayList;
import java.util.List;

import com.bhargo.amazon.model.sumToElements;

public class MaximumSum {
	
	List<Integer> inputSet = new ArrayList<>();
	
	public MaximumSum(List<Integer> inputSet) {
		this.inputSet = inputSet;
	}
	
	public void solve() {
		sumToElements initial = selectIndexes(0,0, inputSet.size());
		
		sumToElements next = selectIndexes(1,1, inputSet.size());
		
		if(initial.getSum() > next.getSum()) {
			initial.getIndexes().stream().forEach(n -> {System.out.println(inputSet.get(n));});
		} else {
			next.getIndexes().stream().forEach(n -> {System.out.print(n + " "); System.out.println(inputSet.get(n));});
		}
		//.out.println(initial + " " + next);
		
	}
	
	public sumToElements selectIndexes(int startIndex,int hop, int size) {
		List<Integer> list = new ArrayList<>();
		while(hop < size) {
			hop +=2;
			if(hop < size)
			list.add(hop);
		}
		int sum = addElementsAt(list, startIndex);
		list.add(startIndex);
		sumToElements ds = new sumToElements(sum, list);
		return ds;
	}
	
	public int addElementsAt(List<Integer> indexList, int startIndex) {
		int sum = inputSet.get(startIndex);
		for (Integer index : indexList) {
			sum = sum + inputSet.get(index);
		}		
	return sum;	
		
	}
	
	private boolean checkDiff(List<Integer> indexList, int sum) {
		if(inputSet.get(indexList.get(0) +1) > inputSet.get(indexList.get(0))) {
			return false;
		} else {
			return true;
		}
	}

}
