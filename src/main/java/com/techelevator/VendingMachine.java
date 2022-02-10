package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {

    private List<Product> productList;

    public VendingMachine () {
        this.productList = stockMachine();
    }

    public List<Product> stockMachine () {
        List<Product> inventoryList = new ArrayList<>();
        File inventoryFile = new File ("vendingmachine.csv");
        try (Scanner scanner = new Scanner(inventoryFile)) {
            while (scanner.hasNextLine()) {
                // lines are in format: A1|Potato Crisps|3.05|Chip
                String currentLine = scanner.nextLine();
                String[] lineArray = currentLine.split("//|");
                String slotID = lineArray[0];
                String name = lineArray[1];
                double price = Double.parseDouble(lineArray[2]);
                if (lineArray[3].equalsIgnoreCase("beverage")) {
                    inventoryList.add(new Beverage(slotID, name, price));
                } else if (lineArray[3].equalsIgnoreCase("candy")) {
                    inventoryList.add(new Candy(slotID, name, price));
                } else if (lineArray[3].equalsIgnoreCase("chips")) {
                    inventoryList.add(new Chips(slotID, name, price));
                } else if (lineArray[3].equalsIgnoreCase("gum")) {
                    inventoryList.add(new Gum(slotID, name, price));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return inventoryList;
    }

    public List<Product> getProductList () {
        return productList;
    }
}
