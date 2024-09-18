package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 *
 */
public class LongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        int longest = 0;
        int i=0;
        int j=0;
        Set<Character> subStr = new HashSet<>(s.length());

        while(j < s.length()) {
                if(!subStr.contains(s.charAt(j))) {
                    subStr.add(s.charAt(j));
                    longest = Math.max(longest,subStr.size());
                    ++j;
                } else {
                    subStr.remove(s.charAt(i));
                    ++i;
                }
            }
        return longest;
    }


    public static void main(String[] args) {
        int longestLength = LongestSubstring.lengthOfLongestSubstring("abcabcbb");
        System.out.println(longestLength);
    }
}
