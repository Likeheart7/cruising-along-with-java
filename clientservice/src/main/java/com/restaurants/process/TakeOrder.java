package com.restaurants.process;

import com.restaurants.products.Drink;

import java.util.ServiceLoader;

public class TakeOrder {
    public static void main(String[] args) {
        System.out.println("We're ready to take your order");
        System.out.println("What would you like");
        var drinks = ServiceLoader.load(Drink.class);
        for (var drink : drinks) {
            System.out.println(drink.getInfo());
        }
        System.out.println("Please choose from the above.");
    }
}
