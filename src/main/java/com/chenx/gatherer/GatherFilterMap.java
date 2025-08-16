package com.chenx.gatherer;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class GatherFilterMap {
    public static void main(String[] args) {
        /*
        Gather有三个行为：
        1. 对给定元素执行自定义操作
        2. 将元素传递给下游downstream（可选）
        3. 通知上游需要传递更多数据
         */
        Stream.of(1, 2, 3, 4, 5)
            .gather(gatherFilter(e -> e < 4))
            .gather(gatherMap(e -> e * 4))
            .forEach(System.out::println);
    }

    private static <T> Gatherer<T, Void, T> gatherFilter(Predicate<T> predicate) {
        return Gatherer.ofSequential((_, element, downstream) -> {
            if (predicate.test(element)) {
                return downstream.push(element);
            }
            return true;
        });
    }

    private static <T, R> Gatherer<T, Void, R> gatherMap(Function<T, R> function) {
        return Gatherer.ofSequential((_, element, downstream) ->
            downstream.push(function.apply(element))
        );
    }
}
