package com.chenx.switchs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PatternMatching {
    private static final Logger log = LoggerFactory.getLogger(PatternMatching.class);

    public static void main(String[] args) {
        past();
        now();
        System.out.println(isBlank(""));
        Trade goog = new Buy("GOOG", 2001);
//        patternMatchingWithSwitch(goog);
//        patternMatchingWithSwitch(null);
        patternMatchingWithPrimitiveType(); // 预览特性
    }


    private static void past() {
        CharSequence obj = "hello";
        if (obj instanceof String) {
            String str = (String) obj; // 必须，否则Cannot resolve method 'trim' in 'CharSequence'
            System.out.println("String is: " + str.trim());
        }
    }

    private static void now() {
        CharSequence obj = "hello";
        if (obj instanceof String str) {
            System.out.println("String is: " + str.trim());
        }
    }

    private static String isBlank(CharSequence data) {
        return data instanceof String str && str.isBlank() ? "yep" : "nope";
    }

    private static boolean patternMatchingWithSwitch(Trade goog) {
        return switch (goog) {
            // guarded pattern
            case Buy buy when buy.quantity() > 2000 && buy.ticker().equals("GOOG") -> {
                log.info("buy when buy.quantity > 2000");
                yield  ProcessTrade.performPurchase(buy.ticker(), buy.quantity());
            }
            // 对null的匹配支持
//            case null -> {
//                log.error("we got null and it's a smell.");
//                throw new TradeException("we got null and it's a smell.");
//            }
            // switch + pattern matching更简洁，无需instanceof
            // switch 可以直接匹配类型
            case Buy buy -> ProcessTrade.performPurchase(buy.ticker(), buy.quantity());
            case Sell sell -> ProcessTrade.performPurchase(sell.ticker(), sell.quantity());
            // 如果Trade是sealed permits Buy, Sell的，甚至只需要前两个case
//            case Trade unexpected -> throw new TradeException("invalid trade"); // 所有都会匹配，所以不用default
            // 可以将null和default放在一起，null' must be first and 'default' must be second
            case null, default -> throw new TradeException("invalid trade");
        };
    }

    /**
     * 预览特性
     * 这对switch匹配规则适用，无损失的情况可以匹配
     */
    private static void patternMatchingWithPrimitiveType() {
        int max = 1000;
        System.out.println(max instanceof double); // true
        double radium226HalfLifeYears = 1600.00;
        System.out.println(radium226HalfLifeYears instanceof int); // true, 无精度损失
        double radium228HalfLifeYears = 6.70;
        System.out.println(radium228HalfLifeYears instanceof int); // false, 有精度损失
        double ageOfUniverse = 13700000000.00;
        System.out.println(ageOfUniverse instanceof int); // false， 超出范围
        System.out.println(ageOfUniverse instanceof long); // true， 无精度损失

        switch (radium226HalfLifeYears) {
            case int num when num == 1600 -> log.info("radium226HalfLifeYears is matched to int");
            default -> throw new RuntimeException("invalid input");
        }
    }
}
