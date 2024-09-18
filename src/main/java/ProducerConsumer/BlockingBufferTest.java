package ProducerConsumer;
import java.util.concurrent.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class BlockingBufferTest {
    public static void main(String[] args) throws InterruptedException {
        Buffer blockingBuffer = new BlockingBuffer();
        Producer producer = new Producer(blockingBuffer);
        Consumer consumer = new Consumer(blockingBuffer);

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(producer);
        executor.execute(consumer);

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}
