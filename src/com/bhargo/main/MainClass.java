package com.bhargo.main;

import com.bhargo.solutions.BoatmanBridgeSolution;

public class MainClass {

	//n,[res1,res2,res3],[res1_arr],[res2_arr],[res3_arr]
	/*critical resource :- resource which is incompatible with all the other resources i.e. critical resource, assuming atleast 1
	 * n - num of resources that can be carried through the bridge 
	 * */
	public static void main(String[] args) {
		BoatmanBridgeSolution.solve(args);
	}
}
