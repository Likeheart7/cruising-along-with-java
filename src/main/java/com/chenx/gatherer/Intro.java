package com.chenx.gatherer;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Gatherer;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class Intro {
    public static void main(String[] args) {
//        Stream.of(1, 2, 3, 4, 5)
//            .takeWhile(e -> e < 4)
//            .gather(redundantMap(e -> e * 2))
//            .forEach(System.out::println);
//       fold();
//        scan();
//        windowFixed();
        windowSliding();
    }

    private static void windowFixed() {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .takeWhile(e -> e < 5)
            // 固定窗口，固定收集n个元素
            .gather(Gatherers.windowFixed(3))
            .forEach(System.out::print); // [1, 2, 3][4]
    }

    private static void windowSliding() {
        Stream.of(1, 2, 3, 4, 5)
            // [1, 2, 3][2, 3, 4][3, 4, 5]
            // 每次移动一位，n个组成一组
            .gather(Gatherers.windowSliding(3))
            .forEach(System.out::print);
    }

    private static void scan() {
        Stream.of(1, 2, 3, 4, 5, 6, 7)
            .filter(e -> e % 2 == 0)
            // 相对于fold，每次计算的结果都传给downstream而不是只传最终结果
            .gather(Gatherers.scan(() -> 0, Integer::sum))
            .forEach(System.out::println);
    }

    private static void fold() {
        List.of(1, 2, 3, 4, 5, 6).stream()
            .filter(e -> e % 2 == 0)
            // fold，reduce的intermediate operation版本
            // 可以实现让reduce不中断继续向下流的功能
            .gather(Gatherers.fold(() -> 0, Integer::sum))
            .map(e -> e * 10)
            .forEach(System.out::println);
    }

    private static Gatherer<? super Integer, ?, Object> redundantMap(Function<Integer, Integer> mapper) {
        return Gatherer.of((_, element, downstream) ->
            downstream.push(mapper.apply(element))
        );
    }
}
