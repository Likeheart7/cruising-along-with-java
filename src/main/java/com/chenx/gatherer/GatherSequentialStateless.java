package com.chenx.gatherer;


import java.util.function.Consumer;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class GatherSequentialStateless {
    public static void main(String[] args) {
        Stream.of(1, 2, 3, 4, 5)
            .gather(peekInOrder(System.out::println))
            .toList();
    }

    public static <T> Gatherer<T, ?, T> peekInOrder(Consumer<T> consumer) {
        return Gatherer.ofSequential((_, element, downstream) ->
            consumeAndPush(element, consumer, downstream)
        );
    }

    private static <T> boolean consumeAndPush(
        T element,
        Consumer<T> consumer,
        Gatherer.Downstream<? super T> downstream) {
        consumer.accept(element);
        return downstream.push(element);
    }

}
