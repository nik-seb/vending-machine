package com.techelevator;

public class Application {

	public static void main(String[] args) {

		VendingMachine vendingMachine = new VendingMachine();

		// store this in a UI class later, for menu option 1: Display Vending Machine Items
		for (int i = 0; i < vendingMachine.getProductList().size(); i++) {
			// use getters for vendingMachine.getProductList().get(i) - maybe a toString method?
		}

	}
}
