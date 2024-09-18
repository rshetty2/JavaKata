package leetcode;

// Leetcode 121 : Best Time to Buy and Sell Stock
public class BestTimeToBuySell {

    public static int maxProfit(int[] prices) {
        if(prices == null || prices.length ==0 ) return 0;

        int lowestPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for(int price:prices) {
            if(price < lowestPrice) lowestPrice = price;

            int profit = price - lowestPrice;

            if(profit > maxProfit)
                maxProfit = profit;
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[] {7, 1, 5, 3, 6, 4}));
    }
}
