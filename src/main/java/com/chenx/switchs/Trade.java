package com.chenx.switchs;

public interface Trade {
}

record Buy(String ticker, int quantity) implements Trade {
}

record Sell(String ticker, int quantity) implements Trade {
}

class TradeException extends RuntimeException{
    public TradeException(String message){
        super(message);
    }
}

class ProcessTrade {
    public static boolean performPurchase(String ticker, int quantity) {
        System.out.println("performing a purchase operation for " + ticker);
        return true;
    }
    public static boolean performSell(String ticker, int quantity) {
        System.out.println("performing a sell operation for " + ticker);
        return true;
    }
}