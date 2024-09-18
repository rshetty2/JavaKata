package ProducerConsumer;

public interface Buffer {
    public void produce(int value) throws InterruptedException;
    public int consumer() throws InterruptedException;
}
