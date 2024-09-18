package lowlatency;

import java.lang.invoke.VolatileCallSite;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;

public class MessageQueue {

    private final ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

    public void enqueue(int value) {
        queue.offer(value);
    }

    public Optional<Integer> dequeue() {
        return Optional.ofNullable(queue.poll());
    }

    private boolean isEndOfQueue() {
        return queue.isEmpty();
    }

    public static void main(String[] args) throws InterruptedException {
        MessageQueue messageQueue = new MessageQueue();

        AtomicInteger prodNumber = new AtomicInteger(0);
        AtomicInteger consumerNumber = new AtomicInteger(0);

        Supplier<String> producer = () -> {
            AtomicInteger pN = prodNumber;
            for (int i = 1; i < 101; i++) {
                messageQueue.enqueue(pN.get()*100 + i);
                System.out.printf("Produced by %s, Message id %d \n ",pN, pN.get()*100 + i);
            }
            return "Completed Producing by " + pN;
        };

        Supplier<String> consumer = () -> {
            AtomicInteger cN = consumerNumber;
            do {
                Optional<Integer> val = messageQueue.dequeue();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                val.ifPresentOrElse((item) -> {
                    System.out.printf("Consumed by %s, Message id %d \n ", cN, item);
                }, () -> {
                    System.out.println("No data, quitting");
                });
            } while (!messageQueue.isEndOfQueue());
            return "Completed Consuming by " + cN;
        };

        Executor executor = Executors.newFixedThreadPool(4);
        for(int ii=1; ii<3; ii++) {
            prodNumber.set(ii-1);
            CompletableFuture.supplyAsync(producer, executor).thenAccept(System.out::println);
        }

        for(int jj=1; jj<3; jj++) {
            consumerNumber.set(jj-1);
            CompletableFuture.supplyAsync(consumer, executor).thenAccept(System.out::println);;
        }

       // CompletableFuture.runAsync(consumer,executor);

//        Thread t1 = new Thread(producer);
//        Thread t2 = new Thread(consumer);

//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join(500);
        System.out.println("** IN MAIN***");
    }


}