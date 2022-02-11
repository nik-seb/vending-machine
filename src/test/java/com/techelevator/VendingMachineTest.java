package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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



}
