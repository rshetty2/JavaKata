package caching;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@NoArgsConstructor(force = true)
public class CacheEntry<V> {
    @Getter
    private final V value;
    private long timeToLive;
    private final LocalDateTime creationTime;


    CacheEntry(V value,long timeToLive) {
        this.value = value;
        this.timeToLive = timeToLive;
        this.creationTime = LocalDateTime.now();
    }

    public boolean isExpired() {
        return creationTime.plusSeconds(timeToLive).isBefore(LocalDateTime.now());
    }
}
