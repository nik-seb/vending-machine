package com.techelevator;

import java.util.Map;

public class Application {

	public static void main(String[] args) {

		VendingMachine vendingMachine = new VendingMachine();

		// store this in a UI class later, for menu option 1: Display Vending Machine Items
		for (Map.Entry<String, Product> kv : vendingMachine.getProductList().entrySet()) {
			Product thisProduct = kv.getValue();
			System.out.println(thisProduct.getSlotIdentifier() + " " + thisProduct.getNameOfProduct() + " " + thisProduct.getPrice());
		}
	}
}
