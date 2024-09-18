package leetcode;

public class ReverseInteger {

    public static void main(String[] args) {
//        int number = 123;
//        int reversed = reverseIt(number);
        int input = 2147483;
        int reversed = 0;
        int quotient = input;
        
        while(quotient/10 > 1) {
            int remainder = quotient % 10;
            quotient = quotient/10;
            reversed = reversed*10 + remainder;
        }

        if (quotient > 0) reversed = reversed*10 + quotient;

        System.out.println(reversed);
//        String s = String.valueOf(input);
//        char[] ch = s.toCharArray();
//        //char[] ch = Character.toChars(2147483647);
//        System.out.println(ch);
    }

//    private static int reverseIt(int number) {
//        //Integer.MAX_VALUE
//        //Integer.MIN_VALUE
//
//    }


}
