package com.techelevator;

import java.io.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditLog {

    File auditFile = new File("Log.txt");

    public void feedMoneyLogEntry (double cashInserted, double updatedBalance) {
        String timeStamp = getTimeStamp();
        try(FileOutputStream outputStream = new FileOutputStream(auditFile, true);
            PrintWriter writer = new PrintWriter(outputStream)) {
            writer.println(">" + timeStamp + " FEED MONEY: " + NumberFormat.getCurrencyInstance().format(cashInserted) + " " + NumberFormat.getCurrencyInstance().format(updatedBalance));
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    public void dispenseProductLogEntry (Product product, double updatedBalance) {
        String timeStamp = getTimeStamp();
        try (FileOutputStream outputStream = new FileOutputStream(auditFile, true);
             PrintWriter writer = new PrintWriter(outputStream)) {
            writer.println(">" + timeStamp + " " + product.getNameOfProduct() + " " + product.getSlotIdentifier() + " " + NumberFormat.getCurrencyInstance().format(updatedBalance + product.getPrice()) + " " + NumberFormat.getCurrencyInstance().format(updatedBalance));
        } catch (IOException e) {
            System.out.println("File not found.");
        } catch (NullPointerException e) {
            System.out.println("Try again.");
        }
    }

    public void returnChangeLogEntry (double startingBalance) {
        String timeStamp = getTimeStamp();
        try (FileOutputStream outputStream = new FileOutputStream(auditFile, true);
             PrintWriter writer = new PrintWriter(outputStream)) {
            writer.println(">" + timeStamp + " GIVE CHANGE: " + NumberFormat.getCurrencyInstance().format(startingBalance) + " $0.00");
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    private String getTimeStamp () {
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        return date.format(new Date());
    }

}
