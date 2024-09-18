package lowlatency;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class StockPriceAggregator {
    private static class StockData {
        final LongAdder totalPrice= new LongAdder();
        final LongAdder count = new LongAdder();
    }

    private final ConcurrentHashMap<String, StockData> stockData = new ConcurrentHashMap<>();

    public void addPrice(String symbol, int price) {
        stockData.computeIfAbsent(symbol, k -> new StockData()).totalPrice.add(price);
        stockData.get(symbol).count.increment();
        //StockData data =  stockData.get(symbol);
    }

    public double getAveragePrice(String symbol) {
        StockData data = stockData.get(symbol);
        return (data == null  || data.count.sum() ==0) ? 0 : data.totalPrice.doubleValue() / data.count.sum();
    }

    public static void main(String[] args) {
        StockPriceAggregator aggr = new StockPriceAggregator();
        aggr.addPrice("APPL",150);
        aggr.addPrice("APPL",155);
        System.out.println("Average price of APPL = " + aggr.getAveragePrice("APPL"));
    }

}
