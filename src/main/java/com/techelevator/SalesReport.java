package com.techelevator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class SalesReport {

    Map<String, Integer> itemsAndQuantities = new HashMap<>();

    public SalesReport(Map<String, Integer> itemsAndQuantities) {
        this.itemsAndQuantities = itemsAndQuantities;
    }

    public void printSalesReport () {
        try(PrintWriter writer = new PrintWriter("salesReport.txt")) {

            for (String productName : itemsAndQuantities.keySet()) {
                writer.println(productName + " | " + itemsAndQuantities.get(productName));
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found. " + e.getMessage());
        }
    }

    public Map<String, Integer> getItemsAndQuantities() {
        return itemsAndQuantities;
    }

    public void setItemsAndQuantities(Map<String, Integer> itemsAndQuantities) {
        this.itemsAndQuantities = itemsAndQuantities;
    }

    
// for integrating into dispense Product method in VendingMachine class:

//    Map<String, Integer> itemsAndQuantities = new HashMap<>(); //
//    SalesReport salesReport = new SalesReport(itemsAndQuantities); //
//
//    if (itemsAndQuantities.containsKey(product.getNameOfProduct())) {
//        itemsAndQuantities.put(product.getNameOfProduct(), itemsAndQuantities.get(product.getNameOfProduct() + 1));
//    }


}
