package com.cokevendor.cocacola;

import com.restaurants.products.Drink;

public class DietCoke implements Drink {

    public DietCoke() {
        System.out.println("creating " + this);
    }

    @Override
    public String getName() {
        return "Diet Coke";
    }

    @Override
    public int getSize() {
        return 355;
    }
}
