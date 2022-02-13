package com.techelevator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalesReport {

    Map<Product, Integer> itemsAndQuantities = new HashMap<>();

    public SalesReport(Map<Product, Integer> itemsAndQuantities) {
        this.itemsAndQuantities = itemsAndQuantities;
    }

    public void printSalesReport () {
        try(PrintWriter writer = new PrintWriter("salesReport_" + getTimeStamp() + ".txt")) {

            double grossSales = 0;
            for (Product product : itemsAndQuantities.keySet()) {
                grossSales += product.getPrice() * itemsAndQuantities.get(product);
                writer.println(">" + product.getNameOfProduct() + " | " + itemsAndQuantities.get(product));
            }
            writer.println("\n$"+ grossSales);

            System.out.println("A sales report has been generated.");

        } catch (FileNotFoundException e) {
            System.out.println("File not found. " + e.getMessage());
        }
    }

    private String getTimeStamp () {
        SimpleDateFormat date = new SimpleDateFormat("MM-dd-yyyy_hh-mm-ss_a");
        return date.format(new Date());
    }

}
