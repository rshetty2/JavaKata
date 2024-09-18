package JustTest;

import java.util.HashMap;
import java.util.Map;

/**
 * Space compliexty is O(1) because HashMap can only contain upto 26 characters (Finite low number).
 * Time complexity is O(n)
 */

public class AnagramTest {
    public static void main(String[] args) {
       System.out.printf("The strings are anagram %b", AnagramTest.isAnagram("Rajeev","eevraj"));
    }

    public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        Map<Character, Integer> countMap = new HashMap<>();

        for(char c:s.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c,0)+1);
        }

        for(char c:t.toCharArray()) {
            if(!countMap.containsKey(c)) return false;
            countMap.put(c, countMap.get(c)-1);
            if(countMap.get(c) < 0) return false;
        }

        return true;
    }

}
