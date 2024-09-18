package lowlatency;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class RingBuffer {
    private final int[] buffer;
    private final int capacity;
    private AtomicInteger writeIndex= new AtomicInteger(0);
    private AtomicInteger readIndex= new AtomicInteger(0);


    public RingBuffer(int capacity) {
        this.buffer = new int[capacity];
        this.capacity = capacity;
    }

    public boolean add(int val) {
        int nextWrite = (writeIndex.get() + 1) % capacity;
        if(nextWrite == readIndex.get()) {
            return false;
        }
        buffer[writeIndex.get()] = val;
        writeIndex.set(nextWrite);
        return true;
    }

    public Integer remove() {
        if(readIndex.get() == writeIndex.get()) {
            return null;
        }

        int val = buffer[readIndex.get()];
        readIndex.set((readIndex.get() + 1) %capacity);
        return val;
    }

    public static void main(String[] args) {
        RingBuffer ringBuffer = new RingBuffer(3);
        ringBuffer.add(1);
        ringBuffer.add(2);
        ringBuffer.add(3);
        ringBuffer.add(4);
        System.out.println("Ring Buffer = " + Arrays.toString(ringBuffer.buffer));

        System.out.println("Removed: " + ringBuffer.remove());
        System.out.println("Removed: " + ringBuffer.remove());

    }
}
