package com.techelevator;

public class Candy extends Product {

    private String dispenseMessage = "Munch Munch, Yum!";

    public Candy(String slotIdentifier, String nameOfProduct, double price) {
        super(slotIdentifier, nameOfProduct, price);
    }

    public String getDispenseMessage() {
        return dispenseMessage;
    }
}
