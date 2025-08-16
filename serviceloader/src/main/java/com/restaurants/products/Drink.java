package com.restaurants.products;

public interface Drink {
    String getName();
    int getSize();
    default String getInfo() {
        return "%s [%d]ml".formatted(getName(), getSize());
    }
}
