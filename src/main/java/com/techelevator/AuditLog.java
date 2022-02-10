package com.techelevator;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditLog {

    File auditFile = new File("Log.txt");

    public void feedMoneyLogEntry (double cashInserted, double currentBalance) {
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        String timeStamp = date.format(new Date());
        try(FileOutputStream outputStream = new FileOutputStream(auditFile, true);
            PrintWriter writer = new PrintWriter(outputStream)) {
            writer.println(">" + timeStamp + " FEED MONEY: $" + cashInserted + " $" + currentBalance);
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    public void dispenseProductLogEntry (Product product, double currentBalance) {
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        String timeStamp = date.format(new Date());
        try (FileOutputStream outputStream = new FileOutputStream(auditFile, true);
             PrintWriter writer = new PrintWriter(outputStream)) {
            writer.println(">" + timeStamp + " " + product.getNameOfProduct() + " " + product.getSlotIdentifier() + " $" + (currentBalance + product.getPrice()) + " $" + currentBalance);
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    

}
