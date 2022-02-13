package com.techelevator;

import java.text.NumberFormat;
import java.util.Map;
import java.util.Scanner;

public class UI {
    private VendingMachine vendingMachine;
    private Scanner inputScanner = new Scanner(System.in);

    AuditLog auditLog = new AuditLog();



    public UI (VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        interaction();
    }

    private void interaction () {
        while (true) {
            boolean validMainMenuResponse = false;
            while (!validMainMenuResponse) {
                printMainMenu();
                String mainResponse = inputScanner.nextLine();
                switch (mainResponse) {
                    case "1": // Display Vending Machine Items
                        // does not set valid to true; still needs to allow user to select purchase menu or exit
                        displayProducts();
                        break;
                    case "2": // Purchase
                        validMainMenuResponse = true;
                        // exits loop and moves on to purchaseInProgress loop
                        break;
                    case "3": // Exit
                        System.exit(0);
                    case "4": // Hidden option to generate sales report
                        SalesReport salesReport = new SalesReport(vendingMachine.getItemsAndQuantities());
                        salesReport.printSalesReport();
                        break;
                    default:
                        System.out.println("Please enter a valid option number.");
                        break;
                }
            }

            boolean purchaseInProgress = true;

            while (purchaseInProgress) {
                printPurchaseMenu();
                String purchaseResponse = inputScanner.nextLine();
                switch (purchaseResponse) {
                    case "1": // Feed Money
                        System.out.println("Please enter a whole number of dollars:");
                        int cash = validateNumberSelection(inputScanner.nextLine());
                        if (cash == 0) {
                            System.out.println("Transaction has failed.");
                        } else {
                            vendingMachine.addMoney(cash);
                            System.out.println("Your new balance is " + NumberFormat.getCurrencyInstance().format(vendingMachine.getCurrentBalance()));
                            auditLog.feedMoneyLogEntry(cash, vendingMachine.getCurrentBalance());
                        }
                        break;
                    case "2": // Select Product
                        System.out.println("Please enter the slot ID of the product you want to purchase:");
                        displayProducts();
                        String productID = inputScanner.nextLine().toUpperCase();
                        System.out.println(vendingMachine.dispenseProduct(productID));
                        if (vendingMachine.getProductList().get(productID).getPrice() <= vendingMachine.getCurrentBalance()) {
                            auditLog.dispenseProductLogEntry(vendingMachine.getProductList().get(productID), vendingMachine.getCurrentBalance());
                        }
                        break;
                    case "3": // Finish Transaction
                        auditLog.returnChangeLogEntry(vendingMachine.getCurrentBalance());
                        System.out.println(vendingMachine.returnChange());
                        purchaseInProgress = false;
                        // exits purchase loop and returns to main menu
                        break;
                    default:
                        System.out.println("Please enter a valid option number.");
                        break;
                }
            }
        }
    }

    private void printMainMenu () {
        System.out.println("(1) Display Vending Machine Items \n" + "(2) Purchase\n" + "(3) Exit");
    }

    private void printPurchaseMenu () {
        System.out.println("(1) Feed Money\n" + "(2) Select Product\n" + "(3) Finish Transaction");
        System.out.println("Current Money Provided: " + NumberFormat.getCurrencyInstance().format(vendingMachine.getCurrentBalance()));

    }

    private void displayProducts () {
        for (Map.Entry<String, Product> kv : vendingMachine.getProductList().entrySet()) {
            Product thisProduct = kv.getValue();
            String infoString = thisProduct.getSlotIdentifier() + " " + thisProduct.getNameOfProduct() + " " + NumberFormat.getCurrencyInstance().format(thisProduct.getPrice());
            if (thisProduct.isSoldOut()) {
                infoString += " - SOLD OUT";
            }
            System.out.println(infoString);
        }
    }

    private int validateNumberSelection (String userInput) {
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.println("You have entered an invalid number.");
        }
        return 0;
    }

}
