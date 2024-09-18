import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class ThreadTest1 {

    public static void main(String[] args) {
        PrintTask task1 = new PrintTask();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(task1);
        service.shutdown();;
    }

    private void process() throws InterruptedException {
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> getName());

        boolean isReadable = false;
        boolean isWritable = true;
        Lock lock = new ReentrantLock();

        //ReentrantReadWriteLock r = new ReentrantReadWriteLock(true);
        //ReentrantReadWriteLock.WriteLock wr = r.writeLock();
        //ReentrantReadWriteLock.ReadLock rl = r.readLock();

        //wr.lockInterruptibly();
        
        Condition readCondition = lock.newCondition();
        Condition writeCondition = lock.newCondition();

        lock.lock();
        while(!isWritable) {
            System.out.println("Waiting to write");
            writeCondition.await();
        }
        writeCondition.signalAll();
        lock.unlock();

        Runnable first = () -> {
            System.out.println("print this");
        };


    }

    public String getName() {
        return "Rajeev";
    }

}
