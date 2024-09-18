package ProducerConsumer;

import java.security.SecureRandom;

public class Producer implements Runnable{
    private final Buffer blockingBuffer;
    private static final SecureRandom generator = new SecureRandom();

    public Producer(Buffer blockingBuffer) {
        this.blockingBuffer = blockingBuffer;
    }


    @Override
    public void run() {
        try {
            for(int i = 1;i<=10;i++)
                blockingBuffer.produce(i);
                Thread.sleep(generator.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
