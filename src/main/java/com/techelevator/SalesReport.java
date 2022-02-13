package com.techelevator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class SalesReport {

    Map<Product, Integer> itemsAndQuantities = new HashMap<>();

    public SalesReport(Map<Product, Integer> itemsAndQuantities) {
        this.itemsAndQuantities = itemsAndQuantities;
    }

    public void printSalesReport () {
        try(PrintWriter writer = new PrintWriter("salesReport.txt")) {

            double grossSales = 0;
            for (Product product : itemsAndQuantities.keySet()) {
                grossSales += product.getPrice() * itemsAndQuantities.get(product);
                writer.println(">" + product.getNameOfProduct() + " | " + itemsAndQuantities.get(product));
            }
            writer.println("\n$"+ grossSales);

        } catch (FileNotFoundException e) {
            System.out.println("File not found. " + e.getMessage());
        }
    }

    public Map<Product, Integer> getItemsAndQuantities() {
        return itemsAndQuantities;
    }

    public void setItemsAndQuantities(Map<Product, Integer> itemsAndQuantities) {
        this.itemsAndQuantities = itemsAndQuantities;
    }


}
