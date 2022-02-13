package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.*;

public class VendingMachine {

    private Map<String, Product> productList;
    private double currentBalance;
    private Map<Product, Integer> itemsAndQuantities = new HashMap<>();


    public double getCurrentBalance() {
        return currentBalance;
    }

    // need to write into methods
    public double addMoney (double amountToAdd) {
        currentBalance = currentBalance + amountToAdd;
        return currentBalance;
    }

    // need to write into methods
    public double subtractMoney (double amountToSubtract) {
        currentBalance = currentBalance - amountToSubtract;
        return currentBalance;
    }

    public VendingMachine () {
        this.productList = stockMachine();
    }

    private Map<String, Product> stockMachine () {
        Map<String, Product> inventoryList = new LinkedHashMap<>();
        File inventoryFile = new File ("vendingmachine.csv");
        if (!inventoryFile.exists() || !inventoryFile.isFile() || !inventoryFile.canRead()) {
            System.out.println("can't find it");
        }
        try (Scanner scanner = new Scanner(inventoryFile)) {
            while (scanner.hasNextLine()) {
                // lines are in format: A1|Potato Crisps|3.05|Chip
                String currentLine = scanner.nextLine();
                String[] lineArray = currentLine.split("\\|");
                String slotID = lineArray[0];
                String name = lineArray[1];
                double price = Double.parseDouble(lineArray[2]);
                if (lineArray[3].equalsIgnoreCase("drink")) {
                    Beverage beverage = new Beverage(slotID, name, price);
                    inventoryList.put(slotID, beverage);
                    itemsAndQuantities.put(beverage, 0);
                } else if (lineArray[3].equalsIgnoreCase("candy")) {
                    Candy candy = new Candy(slotID, name, price);
                    inventoryList.put(slotID, candy);
                    itemsAndQuantities.put(candy, 0);
                } else if (lineArray[3].equalsIgnoreCase("chip")) {
                    Chips chips = new Chips(slotID, name, price);
                    inventoryList.put(slotID, chips);
                    itemsAndQuantities.put(chips, 0);
                } else if (lineArray[3].equalsIgnoreCase("gum")) {
                    Gum gum = new Gum(slotID, name, price);
                    inventoryList.put(slotID, gum);
                    itemsAndQuantities.put(gum, 0);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return inventoryList;
    }

    public Map<String, Product> getProductList () {
        return productList;
    }

    public Map<Product, Integer> getItemsAndQuantities () {
        return itemsAndQuantities; // should return new object, and find occurrences elsewhere too
    }

    public String returnChange() {

        int numberQuarters = (int)Math.floor(currentBalance / 0.25);
        int numberDimes = (int)Math.floor((currentBalance - (0.25 * numberQuarters)) / 0.10);
        int numberNickels = (int)Math.round((currentBalance - ((0.25 * numberQuarters) + (0.10 * numberDimes))) / 0.05);

        String returnString = "Your change is: " + NumberFormat.getCurrencyInstance().format(currentBalance) + ". You receive: " + numberQuarters + " quarter(s), " + numberDimes + " dime(s), and " + numberNickels + " nickel(s).";
        currentBalance = 0;
        return returnString;
    }

    public String dispenseProduct (String productID) {
        if (productList.containsKey(productID)) {
            Product product = productList.get(productID);
            if (product.isSoldOut()) {
                return "Sorry, that product is sold out.";
            } else if (product.getPrice() > currentBalance) {
                return "Sorry, you have not put in enough money to purchase that product.";
            } else {
                product.removeProduct();
                subtractMoney(product.getPrice());

                if (itemsAndQuantities.containsKey(product)) {
                   itemsAndQuantities.put(product, itemsAndQuantities.get(product) + 1);
                }

                return "You have purchased " + product.getNameOfProduct() + " for " + NumberFormat.getCurrencyInstance().format(product.getPrice()) + " and have " + NumberFormat.getCurrencyInstance().format(currentBalance) + " remaining. \n" + product.getDispenseMessage();
                // does not change purchaseInProgress boolean; returns to purchase menu
            }
        } else {
            return "Sorry, that's not a valid slot ID.";
        }
    }


}
