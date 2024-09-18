import java.util.concurrent.Callable;

public class PrintTask implements Runnable {
    public void run(){
        try {
            System.out.printf("sleeping for %d", 2000);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Thread %s", Thread.currentThread().getName());

    }
}
