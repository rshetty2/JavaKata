package ProducerConsumer;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingBuffer implements Buffer{

    private final ArrayBlockingQueue<Integer> blockingQueue;

    public BlockingBuffer() {
        blockingQueue = new ArrayBlockingQueue<>(1);
    }

    @Override
    public void produce(int value) throws InterruptedException {
        blockingQueue.put(value);
        System.out.printf("%s%2d\t%s%d%n", "Producer writes ",value,"Buffer cell occupied: ", blockingQueue.size());
    }

    @Override
    public int consumer() throws InterruptedException {
        int value = blockingQueue.take();
        System.out.printf("%s%2d\t%s%d%n", "Consumer reds ",value,"Buffer cell occupied: ", blockingQueue.size());
        return value;
    }
}
