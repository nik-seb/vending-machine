package com.techelevator;

public abstract class Product {

    private static final int MAX_QUANTITY = 5;
    private String slotIdentifier;
    private String nameOfProduct;
    private double price;
    private int quantityInStock = MAX_QUANTITY;

    public Product(String slotIdentifier, String nameOfProduct, double price) {
        this.slotIdentifier = slotIdentifier;
        this.nameOfProduct = nameOfProduct;
        this.price = price;
    }

    public String getSlotIdentifier() {
        return slotIdentifier;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public boolean isSoldOut() {
        return quantityInStock < 1;
    }

}
