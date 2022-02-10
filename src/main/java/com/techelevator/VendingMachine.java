package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VendingMachine {

    private Map<String, Product> productList;
    private double currentBalance;


    public double getCurrentBalance() {
        return currentBalance;
    }

    public VendingMachine () {
        this.productList = stockMachine();
    }

    public Map<String, Product> stockMachine () {
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
                if (lineArray[3].equalsIgnoreCase("beverage")) {
                    inventoryList.put(slotID, new Beverage(slotID, name, price));
                } else if (lineArray[3].equalsIgnoreCase("candy")) {
                    inventoryList.put(slotID, new Candy(slotID, name, price));
                } else if (lineArray[3].equalsIgnoreCase("chips")) {
                    inventoryList.put(slotID, new Chips(slotID, name, price));
                } else if (lineArray[3].equalsIgnoreCase("gum")) {
                    inventoryList.put(slotID, new Gum(slotID, name, price));
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

    public void returnChange() {

        int numberQuarters = (int)Math.floor(currentBalance / 0.25);
        int numberDimes = (int)Math.floor((currentBalance - (0.25 * numberQuarters)) / 0.10);
        int numberNickels = (int)Math.floor((currentBalance - ((0.25 * numberQuarters) + (0.10 * numberDimes))) / 0.05);

        System.out.println("Your change is: $" + currentBalance + ". You receive: " + numberQuarters + " quarter(s), " + numberDimes + " dime(s), and " + numberNickels + " nickel(s).");
        currentBalance = 0;
    }
}
