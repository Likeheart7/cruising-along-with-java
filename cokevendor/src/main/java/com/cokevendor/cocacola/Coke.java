package com.cokevendor.cocacola;

import com.restaurants.products.Drink;

public class Coke implements Drink {
    // ServiceLoader需要依赖无参构造器或provider方法
//    public Coke() {
//        System.out.println("creating " + this);
//    }

    private final int size;
    private Coke(int size) {
        this.size = size;
    }
    public static Coke provider() {
        return new Coke(550);
    }
    @Override
    public String getName() {
        return "Coke";
    }

    @Override
    public int getSize() {
        return size;
    }
}
