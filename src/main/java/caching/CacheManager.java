package caching;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheManager<K,V> {
    private final ConcurrentHashMap<K, CacheEntry<V>> cache = new ConcurrentHashMap<>();

    private final ConcurrentLinkedDeque<K> orderReferences = new ConcurrentLinkedDeque<>();
    private final long cleanUpInterval;
    private final EvictionStrategy evictionStrategy;

    private final int maxSize;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private final ReadWriteLock lock = new ReentrantReadWriteLock(); ;

    public CacheManager(int maxSize, long cleanUpInterval, EvictionStrategy evictionStrategy) {
        this.maxSize = maxSize;
        this.cleanUpInterval = cleanUpInterval;
        this.evictionStrategy = evictionStrategy;
        startExpirationTask();
    }

    public void put(K key, V value, long timeToLive) {
        lock.writeLock().lock();
        try {
            if (cache.size() >= maxSize) {
                //perform operation to remove the key from reference dequeue
                K oldKey = null;
                if (EvictionStrategy.LRU.equals(evictionStrategy)) {
                    oldKey = orderReferences.pollLast();
                    System.out.printf("IN LRU, REMOVED KEY = %s, ADDING KEY = %s\n", oldKey, key);
                }
                if (EvictionStrategy.MRU.equals(evictionStrategy)) {
                    oldKey = orderReferences.pollFirst();
                    System.out.printf("IN MRU, REMOVED KEY = %s, ADDING KEY = %s\n", oldKey, key);
                }
                cache.remove(oldKey);
            }
            CacheEntry<V> entry = new CacheEntry<>(value, timeToLive);
            cache.put(key, entry);
            orderReferences.addFirst(key);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Optional<V> get(K key) {
        lock.readLock().lock();
        try {
            CacheEntry<V> entry = cache.get(key);
            if (entry != null && !entry.isExpired()) {
                orderReferences.remove(key);
                orderReferences.addFirst(key);
            }
            return Optional.ofNullable(entry.getValue());
        } finally {
            lock.readLock().unlock();
        }
    }

    private void startExpirationTask() {
        scheduler.scheduleAtFixedRate(() -> {
            long now = System.currentTimeMillis();
            for (Map.Entry<K,CacheEntry<V>> entry: cache.entrySet()) {
                if(entry.getValue().isExpired()) {
                    cache.remove(entry.getKey());
                    orderReferences.remove(entry.getKey());
                }
            }
        }, cleanUpInterval, cleanUpInterval, TimeUnit.SECONDS);
    }

    public void shutdown() {
        scheduler.shutdown();
        try {
            if(!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException ie) {
            scheduler.shutdownNow();
        }
    }

    private int getCacheSize() {
        return this.cache.size();
    }

    private ConcurrentLinkedDeque<K> getReferenceQueue() {
        return orderReferences;
    }



    public static void main(String[] args) throws InterruptedException{
        CacheManager<String, String> cacheManager = new CacheManager<>(3,2, EvictionStrategy.MRU);
        cacheManager.put("key1","value1", 4);
        cacheManager.put("key2","value2",7);
        cacheManager.put("key3","value3",8);
        cacheManager.put("key4","value4",9);
        cacheManager.put("key5","value5",10);

        //System.out.println("Map size = " + cacheManager.getCacheSize());


        //System.out.println("Key1: " + cacheManager.get("key1"));
        Thread.sleep(3000);
        //System.out.println("Map size = " + cacheManager.getCacheSize());

        //System.out.println("Key1: " + cacheManager.get("key1"));
        //System.out.println("Key2: " + cacheManager.get("key2"));

        //Thread.sleep(5000);

        //System.out.println("Map size = " + cacheManager.getCacheSize());
        cacheManager.getReferenceQueue().stream().forEach(System.out::println);
        cacheManager.shutdown();

    }
}
