package com.chenx.gatherer;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Gatherer;

/**
 * 实现可并行的stateful需要四个参数，分别针对不同阶段
 * 1. 初始化：初始外部状态容器
 * 2. 集成：对元素执行的操作
 * 3. 合并：合并各线程执行结果
 * 4. 结束：可能放入额外的东西
 */
public class GatherParallelStateful {
    public static void main(String[] args) {
        var people = List.of(
            new Person("Jill", 21), new Person("Jake", 8),
            new Person("Bill", 21), new Person("Nancy", 22), new Person("Mark", 9),
            new Person("Sara", 18), new Person("Paul", 15), new Person("Sam", 28)
        );
        people.stream()
//            .gather(distinctBy(Person::ageGroup))
            .gather(distinctByParallel(Person::ageGroup))
            .forEach(System.out::println);
    }

    private static <T, C extends Comparable<C>> Gatherer<? super T, ?, T> distinctByParallel(Function<T, C> criteria) {
        return Gatherer.of(
            DistinctValues<T>::new, // 初始化状态
            (state, element, _) -> state.addIfDistinct(criteria, element), // 向state添加元素，不传给下游，等合并后清除重复数据后传递
            (state1, state2) -> state1.combineDistinct(criteria, state2), // 合并每个线程的state
            DistinctValues::pushEachValueDownstream); // 将最终结果的每个元素传给下游
    }

    static class DistinctValues<T> {
        private final Set<T> distinctElements = new HashSet<>();

        public <C extends Comparable<C>> boolean addIfDistinct(Function<T, C> criteria, T element) {
            if (distinctElements.stream().noneMatch(existing ->
                criteria.apply(existing).compareTo(criteria.apply(element)) == 0)
            ) {
                distinctElements.add(element);
            }
            return true;
        }

        public <C extends Comparable<C>> DistinctValues<T> combineDistinct(Function<T, C> criteria, DistinctValues<T> toCombine) {
            for (var item : toCombine.distinctElements) {
                addIfDistinct(criteria, item);
            }
            return this;
        }

        public void pushEachValueDownstream(Gatherer.Downstream<? super T> downstream) {
            for (var element : distinctElements) {
                if (!downstream.push(element)) {
                    break;
                }
            }
        }
    }


//    private static <T, C extends Comparable<C>> Gatherer<? super T, ?, T> distinctBy(Function<T, C> criteria) {
//        return Gatherer.ofSequential(HashSet<C>::new,
//            (state, element, downstream) ->
//                !state.add(criteria.apply(element)) || downstream.push(element));
//    }

    record Person(String name, int age) {
        public int ageGroup() {
            return age / 10 * 10;
        }
    }
}
