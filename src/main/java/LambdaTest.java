import java.util.ArrayList;

import java.util.function.Consumer;
import java.util.stream.Stream;

interface StringFunction {
    String run(String str);
}

public class LambdaTest {
    public static void main(String[] args) {
        StringFunction exclaim = (s) -> s + "!";

        System.out.println(exclaim.run("Hello"));

        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(5);
        numbers.add(10);
        Stream numberStream = numbers.stream().sorted();

        Consumer<Integer> method = (n) -> {
            System.out.println(n);
        };
        numbers.forEach(method);
    }
}
