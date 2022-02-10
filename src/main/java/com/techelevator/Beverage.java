package com.techelevator;

public class Beverage extends Product {

    private String dispenseMessage = "Glug Glug, Yum!";

    public Beverage(String slotIdentifier, String nameOfProduct, double price) {
        super(slotIdentifier, nameOfProduct, price);
    }

    public String getDispenseMessage() {
        return dispenseMessage;
    }

}
