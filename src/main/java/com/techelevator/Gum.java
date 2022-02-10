package com.techelevator;

public class Gum extends Product {

    private String dispenseMessage = "Chew Chew, Yum!";

    public Gum(String slotIdentifier, String nameOfProduct, double price) {
        super(slotIdentifier, nameOfProduct, price);
    }

    public String getDispenseMessage() {
        return dispenseMessage;
    }
}
