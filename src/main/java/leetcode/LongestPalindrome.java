package leetcode;

//https://leetcode.com/problems/longest-palindromic-substring/description/

public class LongestPalindrome {

    public static String longestPalindrom(String s) {
        if(s == null || s.length() ==0) return "";
        String longest = "";
        for(int i=0;i<s.length();i++) {
            String p1 = expandAroundCenter(s,i,i);
            if (p1.length() > longest.length()) longest = p1;

            String p2 = expandAroundCenter(s,i,i+1);
            if(p2.length() > longest.length()) longest = p2;
        }
        return longest;
    }

    private static String expandAroundCenter(String s, int left, int right) {
        while(left>=0 && right < s.length() && Character.toUpperCase(s.charAt(left)) == Character.toUpperCase(s.charAt(right))) {
            left--;
            right++;
        }
        return s.substring(left+1, right);
    }

    public static void main(String[] args) {
        String longestString = longestPalindrom("Saippuakivikauppias");
        System.out.println("Longest Palindrom = " + longestString);
    }
}
