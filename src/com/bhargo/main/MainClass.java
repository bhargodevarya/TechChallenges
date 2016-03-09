package com.bhargo.main;

import com.bhargo.solutions.BoatmanBridgeSolution;

public class MainClass {

	//n,[res1,res2,res3],[res1_arr],[res2_arr],[res3_arr]
	/*Atleast 1 resource is incompatible with all the other resources i.e. critical resource
	 * */
	public static void main(String[] args) {
		BoatmanBridgeSolution.solve(args);
	}
}
