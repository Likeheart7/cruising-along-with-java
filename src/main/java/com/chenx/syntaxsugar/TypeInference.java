package com.chenx.syntaxsugar;


import java.util.ArrayList;
import java.util.List;

public class TypeInference {
    public static void main(String[] args) {
        // 泛型类型推断
        List<String> list = new ArrayList<>();
        // 局部变量类型推断
        // 代码会更简洁，但是可能对阅读代码不利。
        var word = "hello";
        // lambda类型推断
        Runnable func = () -> System.out.println(word);
        func.run();
    }
}
