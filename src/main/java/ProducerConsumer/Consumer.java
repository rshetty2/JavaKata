package ProducerConsumer;

import java.security.SecureRandom;

public class Consumer implements Runnable {
    Buffer blockingBuffer;
    private static final SecureRandom generator = new SecureRandom();

    public Consumer(Buffer blockingBuffer) {
        this.blockingBuffer = blockingBuffer;
    }

    @Override
    public void run() {
        int sum = 0;
        try {
            for(int i = 1;i<=10;i++)
                Thread.sleep(generator.nextInt(1000));
                sum+= blockingBuffer.consumer();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
