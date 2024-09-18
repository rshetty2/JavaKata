import ProducerConsumer.CountingTask;
import ProducerConsumer.TreeNode;
import com.google.common.collect.Sets;


import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ScrapTest {
    public static void main(String[] args) throws InterruptedException {
        String number = "23346.1245";
        String[] splitNum = number.split("\\.");
        String integerPart = splitNum[0];
        String decimalPart = splitNum[1];
        int integerPartSum = getSum(integerPart);
        int decimalPartSum = getSum(decimalPart);
        System.out.println(integerPartSum + decimalPartSum/(Math.pow(10,decimalPart.length())));

        // Another sample

        String sentence = "Item1 10 Item2 25 Item3 30 Item4 45";
        //String sentence = "Item3";
        //Integer summm = Arrays.stream(sentence.split("\\s+")).filter(s -> s.matches("\\d+")).mapToInt(Integer::valueOf).sum();
        List<String> numbers = Arrays.stream(sentence.split("\\s+")).filter(s -> s.matches("\\\\^*+(d+)\\$*+")).collect(Collectors.toList());
        System.out.println(numbers);
                //mapToInt(Integer::valueOf).sum();
        //Integer summm = Arrays.stream(sentence.split("\\d+")).mapToInt(Integer::valueOf).sum();
        //System.out.println(summm);

        AtomicInteger ai = new AtomicInteger(1);
        ExecutorService tpe = Executors.newSingleThreadExecutor();
        tpe.submit(() -> ai.set(1));
        tpe.submit(() -> { ai.compareAndSet(2,3);});
        System.out.println(ai.get());
        Thread.sleep(1000);
        System.out.println(ai.get());

        Stream.of("a","b","c").collect(Collectors.toCollection(HashSet::new));

        TreeNode tree = new TreeNode(5,new TreeNode(3), new TreeNode(2, new TreeNode(2), new TreeNode(8)));
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        int Sum = forkJoinPool.invoke(new CountingTask(tree));
        System.out.println(Sum);

        //Collection<Integer> listOfNumbers = Arrays.asList(1,2,3,4,5);
        //BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(listOfNumbers);

        ExecutorService service = Executors.newFixedThreadPool(1);
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        service.submit(() -> {
            try {
                queue.take();
            } catch (InterruptedException ie) {

            }
        });

    }

    private static int getSum(String number) {
        int sum = 0;
        for(int i = number.length() -1;i>=0;i--) {
            int n = Character.getNumericValue(number.charAt(i));
            sum+= (int) (n*Math.pow(10,(number.length()-i)-1));
        }
        return sum;
    }
}
