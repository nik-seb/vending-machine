package com.techelevator;

import java.util.Map;
import java.util.Scanner;

public class UI {
    private VendingMachine vendingMachine;
    private Scanner inputScanner = new Scanner(System.in);

    public UI (VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        interaction();
    }

    public void interaction () {
        printMainMenu();
        boolean validMainMenuResponse = false;
        while (!validMainMenuResponse){
            int mainResponse = Integer.parseInt(inputScanner.nextLine());
            if (mainResponse == 1) {
                // does not set valid to true; still needs to allow user to select purchase menu or exit
                displayProducts();
            } else if (mainResponse == 2) {
                validMainMenuResponse = true;
                printPurchaseMenu();
            } else if (mainResponse == 3) {
                // ANYTHING THAT NEEDS TO BE WRAPPED UP HERE? FLUSH LOGS ETC?
                System.exit(0);
            } else {
                System.out.println("Please enter a valid option number.");
            }
        }


    }

    private void printMainMenu () {
        System.out.println("(1) Display Vending Machine Items \n" + "(2) Purchase\n" + "(3) Exit");
    }

    private void printPurchaseMenu () {
        System.out.println("(1) Feed Money\n" + "(2) Select Product\n" + "(3) Finish Transaction");
    }

    private void displayProducts () {
        // NEEDS ADDITIONAL LOGIC: A product that has run out must indicate that it is SOLD OUT
        for (Map.Entry<String, Product> kv : vendingMachine.getProductList().entrySet()) {
            Product thisProduct = kv.getValue();
            System.out.println(thisProduct.getSlotIdentifier() + " " + thisProduct.getNameOfProduct() + " " + thisProduct.getPrice());
        }
    }

}
