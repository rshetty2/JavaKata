package lowlatency;

import java.util.concurrent.atomic.AtomicLong;

public class RateLimiter {
    private final long maxTokens;
    private final long refillRate;
    private final AtomicLong availableTokens;
    private long lastRefillTime;

    public RateLimiter(long maxTokens, long refillRate) {
        this.maxTokens = maxTokens;
        this.refillRate = refillRate;
        this.availableTokens = new AtomicLong(maxTokens);
        this.lastRefillTime = System.nanoTime();
    }


    private boolean tryAcquire() {
        refillToken();
        long currentTokens = availableTokens.get();
        if(currentTokens > 0) {
            return availableTokens.compareAndSet(currentTokens,currentTokens-1);
        }
        return false;
    }

    private void refillToken(){
        long now = System.nanoTime();
        long tokensToAdd = (now - lastRefillTime) * refillRate /1_000_000_000L;
        if (tokensToAdd > 0) {
            availableTokens.addAndGet(Math.min(maxTokens,tokensToAdd));
            lastRefillTime = now;
        }
    }


    public static void main(String[] args) {
        RateLimiter rateLimiter = new RateLimiter(3,3);

        for (int i = 0; i < 10; i++) {
            System.out.println("Request " + (i +1) + ": " + (rateLimiter.tryAcquire() ? "Acquired." : "Failed to ACQUIRE"));
//            try {
//                Thread.sleep(200);
//            } catch(InterruptedException e) {
//
//            }
        }
    }
}
