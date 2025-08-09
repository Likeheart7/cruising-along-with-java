package com.chenx.switchs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 可以在switch模式匹配 + record类情况下使用解构
public class PatternMatchingDestructuringWithRecord {
    private static final Logger log = LoggerFactory.getLogger(PatternMatchingDestructuringWithRecord.class);

    public static void main(String[] args) {
        Plane ceair = new Plane("CEAIR", 300);
        withRecord(ceair);
    }

    private static void withRecord(Vehicle vehicle) {
        switch (vehicle) {
            // 可以在解构赋值使用类型推断var
            case Plane(var name, var capacity) -> log.info("The plane from {} with capacity {}.", name, capacity);
            // 此处允许使用_表示不使用的值
            case Bus(var name, var _) -> log.info("The bus from {}.", name);
        }
    }
}

sealed interface Vehicle permits Bus, Plane {
}

record Bus(String name, int capacity) implements Vehicle {
}

record Plane(String name, int capacity) implements Vehicle {
}
