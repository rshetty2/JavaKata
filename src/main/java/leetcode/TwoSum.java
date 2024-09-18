package leetcode;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {

    private static int[] findTwoSum(int[] input, int targetSum) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0;i<input.length;i++){
            int diff = targetSum-input[i];
            if(map.containsKey(diff))
                return new int[] {i, map.get(diff)};
            map.put(input[i],i);
        }
        return new int[] {-1,-1};
    }

    public static void main(String[] args) {
        int[] result = TwoSum.findTwoSum(new int[]{12,45,5,7,2,9},15);
        Arrays.stream(result).forEach(System.out::println);
    }
}
