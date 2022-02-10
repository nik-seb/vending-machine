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
        while (true) {
            boolean validMainMenuResponse = false;
            while (!validMainMenuResponse) {
                printMainMenu();
                int mainResponse = Integer.parseInt(inputScanner.nextLine());
                if (mainResponse == 1) {
                    // does not set valid to true; still needs to allow user to select purchase menu or exit
                    displayProducts();
                } else if (mainResponse == 2) {
                    validMainMenuResponse = true;
                } else if (mainResponse == 3) {
                    // ANYTHING THAT NEEDS TO BE WRAPPED UP HERE? FLUSH LOGS ETC?
                    System.exit(0);
                } else {
                    System.out.println("Please enter a valid option number.");
                }
            }

            boolean purchaseInProgress = true;

            while (purchaseInProgress) {
                printPurchaseMenu();
                int purchaseResponse = Integer.parseInt(inputScanner.nextLine());
                if (purchaseResponse == 1) {
                    // RUN FEED MONEY METHOD HERE
                } else if (purchaseResponse == 2) {
                    System.out.println("Please enter the slot ID of the product you want to purchase:");
                    displayProducts();
                    String productID = inputScanner.nextLine();
                    if (vendingMachine.getProductList().containsKey(productID)) {
                        // CHECK IF SOLD OUT - IF SO, ALERT AND RESUME LOOP
                        // CHECK BALANCE AGAINST PRODUCT COST - IF INADEQUATE, ALERT AND RESUME LOOP
                        // ELSE
                        // UPDATE BALANCE
                        Product product = vendingMachine.getProductList().get(productID);
                        System.out.println("You have purchased " + product.getNameOfProduct() + " for $" + product.getPrice() + " and have $" + "BALANCE HERE" + " remaining.");
                        System.out.println(product.getDispenseMessage());
                        // does not change purchaseInProgress boolean; returns to purchase menu
                    } else {
                        System.out.println("Sorry, that's not a valid slot ID.");
                    }
                } else if (purchaseResponse == 3) {
                    vendingMachine.returnChange();
                    purchaseInProgress = false;
                } else {
                    System.out.println("Please enter a valid option number.");
                }
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
        for (Map.Entry<String, Product> kv : vendingMachine.getProductList().entrySet()) {
            Product thisProduct = kv.getValue();
            String infoString = thisProduct.getSlotIdentifier() + " " + thisProduct.getNameOfProduct() + " " + thisProduct.getPrice();
            if (thisProduct.isSoldOut()) {
                infoString += " - SOLD OUT";
            }
            System.out.println(infoString);
        }
    }

}
