package Streams;

import java.util.*;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.summingDouble;
import static java.util.stream.Collectors.maxBy;

import static java.util.stream.Collectors.collectingAndThen;
import static org.junit.Assert.assertThat;

public class CollectorsStream {
    public static void main(String[] args) {
        List<String> givenList = Arrays.asList("raj","shetty");
        List<String> result = givenList.stream().collect(Collectors.toList());

        Set<String> mySet = givenList.stream().collect(Collectors.toSet());

        //List<String> newList = givenList.stream().collect(collectingAndThen(toList(), List::copyOf));
        String joined = givenList.stream().collect(Collectors.joining(","));
        System.out.println(joined);
        Double sum = givenList.stream().collect(summingDouble(String::length));

        //givenList.stream().collect(Collectors.groupingBy(Function.identity(),))

        Optional<String> maxString = givenList.stream().collect(maxBy(Comparator.naturalOrder()));
        System.out.println(maxString);

        givenList.forEach((n) -> System.out.println(n));

        //Consumer<String> consumer = (x) -> return x;
        //givenList.forEach(consumer);

    }

}
