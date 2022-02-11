package com.techelevator;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditLog {

    File auditFile = new File("Log.txt");

    public void feedMoneyLogEntry (double cashInserted, double updatedBalance) {
        String timeStamp = getTimeStamp();
        try(FileOutputStream outputStream = new FileOutputStream(auditFile, true);
            PrintWriter writer = new PrintWriter(outputStream)) {
            writer.println(">" + timeStamp + " FEED MONEY: $" + cashInserted + " $" + updatedBalance);
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    public void dispenseProductLogEntry (Product product, double updatedBalance) {
        String timeStamp = getTimeStamp();
        try (FileOutputStream outputStream = new FileOutputStream(auditFile, true);
             PrintWriter writer = new PrintWriter(outputStream)) {
            writer.println(">" + timeStamp + " " + product.getNameOfProduct() + " " + product.getSlotIdentifier() + " $" + (updatedBalance + product.getPrice()) + " $" + updatedBalance);
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    public void returnChangeLogEntry (double startingBalance) {
        String timeStamp = getTimeStamp();
        try (FileOutputStream outputStream = new FileOutputStream(auditFile, true);
             PrintWriter writer = new PrintWriter(outputStream)) {
            writer.println(">" + timeStamp + " GIVE CHANGE: $" + startingBalance + " $0.00");
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    private String getTimeStamp () {
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        return date.format(new Date());
    }

}
