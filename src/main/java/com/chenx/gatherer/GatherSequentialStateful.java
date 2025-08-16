package com.chenx.gatherer;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Gatherer;

/// stateful操作会依赖外部状态
public class GatherSequentialStateful {
    public static void main(String[] args) {
        List.of("Tom", "Jerry", "Tyke")
            .parallelStream()
            .filter(name -> name.length() > 3)
            .gather(GatherSequentialStateful.<String, String>mapWithIndex(String::toUpperCase))
            .forEach(System.out::println);
    }

    private static <T, R> Gatherer<? super T, AtomicInteger, ValueWithIndex<R>> mapWithIndex(Function<T, R> mapper) {
        return Gatherer.ofSequential(
            AtomicInteger::new,
            (index, element, downstream) ->
                downstream.push(new ValueWithIndex<>(mapper.apply(element), index.getAndIncrement()))
        );
    }

    record ValueWithIndex<E>(E value, int index) {
        @Override
        public String toString() {
            return "%d, %s".formatted(index, value);
        }
    }
}
