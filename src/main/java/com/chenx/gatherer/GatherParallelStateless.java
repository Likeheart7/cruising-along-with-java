package com.chenx.gatherer;

import java.util.function.Predicate;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

///  使用Gatherer.of() 创建可并行的自定义gatherer
public class GatherParallelStateless {
    public static void main(String[] args) {
        Stream.of(10, 11, 15, 12, 11, 44, 67, 83, 23, 12, 34, 12, 55)
            .parallel()
            .gather(GatherParallelStateless.takeAnyoneMatching(e -> e > 25))
            .map(e -> e * 10)
            .forEach(System.out::println);
    }

    private static Gatherer<Integer, ?, Integer> takeAnyoneMatching(Predicate<Integer> predicate) {
        return Gatherer.of((_, element, downstream) -> pushIfMatch(predicate, element, downstream));
    }

    private static boolean pushIfMatch(Predicate<Integer> predicate, Integer element, Gatherer.Downstream<? super Integer> downstream) {
        if (predicate.test(element)) {
            downstream.push(element);
            return false; // 返回false，不要下个元素了
        }
        return true;
    }
}
