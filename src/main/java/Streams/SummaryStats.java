package Streams;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.LongSummaryStatistics;
import java.util.stream.LongStream;

public class SummaryStats {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        long[] ar = random.longs(10_00_0000,1,1000).toArray();
        Instant start = Instant.now();
        LongStream longStream = Arrays.stream(ar);
        LongSummaryStatistics summaryStats = longStream.summaryStatistics();
        Instant end = Instant.now();

        System.out.printf("Min  %2d%n%n",summaryStats.getMin());
        System.out.printf("Max  %2d%n%n",summaryStats.getMax());
        System.out.printf("Average  %f%n%n",summaryStats.getAverage());
        System.out.printf("Time taken  %2d%n%n",Duration.between(start,end).toMillis());
    }
}
