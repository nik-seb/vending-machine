package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.text.NumberFormat;

public class VendingMachineTest {
    VendingMachine vendingMachine;

    @Before
    public void setup () {
        vendingMachine = new VendingMachine();
    }

    @Test
    public void adding_and_subtracting_from_balance_creates_correct_balance () {
        vendingMachine.addMoney(5);
        vendingMachine.subtractMoney(0.15);
        Assert.assertEquals(4.85, vendingMachine.getCurrentBalance(), 0);
    }

    @Test
    public void vending_machine_instantiation_makes_usable_objects () {
        Product retrievedProduct = vendingMachine.getProductList().get("B3");
        Assert.assertEquals("Wonka Bar", retrievedProduct.getNameOfProduct());
        Assert.assertEquals(1.50, retrievedProduct.getPrice(),0);
        Assert.assertEquals("Munch Munch, Yum!", retrievedProduct.getDispenseMessage());
    }

    @Test
    public void returnChange_returns_correct_coins_given_1_15 () {
        vendingMachine.addMoney(1.15);
        String output = vendingMachine.returnChange();
        String expected = "Your change is: $1.15. You receive: 4 quarter(s), 1 dime(s), and 1 nickel(s).";
        Assert.assertEquals(expected, output);
    }

    @Test
    public void returnChange_returns_correct_coins_given_5_20 () {
        vendingMachine.addMoney(5.20);
        String output = vendingMachine.returnChange();
        String expected = "Your change is: $5.20. You receive: 20 quarter(s), 2 dime(s), and 0 nickel(s).";
        Assert.assertEquals(expected, output);
    }

    @Test
    public void returnChange_returns_correct_coins_given_0 () {
        vendingMachine.addMoney(0);
        String output = vendingMachine.returnChange();
        String expected = "Your change is: $0.00. You receive: 0 quarter(s), 0 dime(s), and 0 nickel(s).";
        Assert.assertEquals(expected, output);
    }

    @Test
    public void dispenseProduct_twice_returns_3_quantity_in_stock () {

        Map<String, Product> productList = new LinkedHashMap<>();
        productList.put("A1", new Chips("A1", "Potato Crisps", 3.05));
        vendingMachine.addMoney(10);
        vendingMachine.dispenseProduct("A1");
        vendingMachine.dispenseProduct("A1");
        int quantityRemaining = vendingMachine.getProductList().get("A1").getQuantityInStock();
        Assert.assertEquals(3, quantityRemaining);

    }

    @Test
    public void dispenseProduct_returns_out_of_stock_message_given_sold_out () {

        Map<String, Product> productList = new LinkedHashMap<>();
        productList.put("A1", new Chips("A1", "Potato Crisps", 3.05));
        vendingMachine.addMoney(20);
        vendingMachine.dispenseProduct("A1");
        vendingMachine.dispenseProduct("A1");
        vendingMachine.dispenseProduct("A1");
        vendingMachine.dispenseProduct("A1");
        vendingMachine.dispenseProduct("A1");
        String output = vendingMachine.dispenseProduct("A1");
        Assert.assertEquals("Sorry, that product is sold out.", output);

    }
    
    @Test
    public void dispenseProduct_decrements_balance () {
        vendingMachine.addMoney(5);
        vendingMachine.dispenseProduct("C2"); // cost is 1.50
        Assert.assertEquals(3.50, vendingMachine.getCurrentBalance(), 0);
    }

    @Test
    public void dispenseProduct_returns_correct_string_for_successful_purchase () {
        vendingMachine.addMoney(5);
        String dispenseMessage = vendingMachine.dispenseProduct("C1");
        Assert.assertEquals("You have purchased Cola for $1.25 and have $3.75 remaining. \nGlug Glug, Yum!", dispenseMessage);
    }

    @Test
    public void purchase_declined_if_funds_insufficient_to_purchase_product () {
        vendingMachine.addMoney(1);
        String dispenseMessage = vendingMachine.dispenseProduct("A1");
        Assert.assertEquals("Sorry, you have not put in enough money to purchase that product.", dispenseMessage);
    }

    @Test
    public void purchase_declined_if_no_cash_has_been_added () {
        String dispenseMessage = vendingMachine.dispenseProduct("B2");
        Assert.assertEquals("Sorry, you have not put in enough money to purchase that product.", dispenseMessage);
    }

}
