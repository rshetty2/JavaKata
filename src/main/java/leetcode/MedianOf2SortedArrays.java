package leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 */
public class MedianOf2SortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] targetArray = new int[nums1.length + nums2.length];
        int first_i = 0;
        int second_j = 0;
        int third_k=0;
        while(first_i < nums1.length && second_j < nums2.length) {
            targetArray[third_k++]= Math.min(nums1[first_i],nums2[second_j]);
            if (nums1[first_i] < nums2[second_j]) ++first_i; else ++second_j;
        }
        while (first_i < nums1.length) targetArray[third_k++] = nums1[first_i++];
        while (second_j < nums2.length) targetArray[third_k++] = nums2[second_j++];



        Arrays.stream(targetArray).forEach(System.out::println);
        //First create a target array by checking smallest of each element
        //if odd elements, then pick the element at mid point, otherwise take avg of total/2 and total/2+1

        return (targetArray.length % 2 !=0) ? targetArray[(targetArray.length/2)] : (targetArray[(targetArray.length/2)-1] + targetArray[targetArray.length/2])/2.0;
    }

    public static void main(String[] args) {
        System.out.println(MedianOf2SortedArrays.findMedianSortedArrays(new int[]{1,3,7,9}, new int[]{2,6,8,10}));
    }
}
