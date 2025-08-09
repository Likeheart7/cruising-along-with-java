package com.chenx.syntaxsugar;

public class TextBlock {
    public static String smartIndentation() {
        // 字符串文本会记住缩进
        var message = """
            It is great
                when compilers care about conventions
             Makes our lives easier""";
        return message;
    }

    public static String preserveIndentation() {
        // 可以根据最后一行"""的缩进调整
        String message = """
            If you like
                    you can ask the indentations
            to be preserved, unaltered, like in this example.
    """;
        return message;
    }

    private static void print(String message) {
        System.out.println("--------");
        System.out.print(message);
        System.out.println("--------");
    }


    public static void main(String[] args) {
        print(smartIndentation());
        print(preserveIndentation());
    }

}
