package com.techelevator;

public class Chips extends Product {

    private String dispenseMessage = "Crunch Crunch, Yum!";

    public Chips(String slotIdentifier, String nameOfProduct, double price) {
        super(slotIdentifier, nameOfProduct, price);
    }

    public String getDispenseMessage() {
        return dispenseMessage;
    }
}
