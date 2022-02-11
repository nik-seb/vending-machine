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



}
