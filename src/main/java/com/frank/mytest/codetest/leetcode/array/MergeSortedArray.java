package com.frank.mytest.codetest.leetcode.array;

import java.util.Arrays;

/**
 * Example 1:
 * <p>
 * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * Output: [1,2,2,3,5,6]
 * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
 * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
 * Example 2:
 * <p>
 * Input: nums1 = [1], m = 1, nums2 = [], n = 0
 * Output: [1]
 * Explanation: The arrays we are merging are [1] and [].
 * The result of the merge is [1].
 * Example 3:
 * <p>
 * Input: nums1 = [0], m = 0, nums2 = [1], n = 1
 * Output: [1]
 * Explanation: The arrays we are merging are [] and [1].
 * The result of the merge is [1].
 * Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
 */
public class MergeSortedArray {
    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] arr2 = new int[]{2, 5, 6};
        merge(arr1, 3, arr2, 3);
        System.out.println(Arrays.toString(arr1));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // Time Complexity: O(n), Space Complexity: O(1)
        int temp = nums1.length - 1;
        int l = m - 1;
        int r = nums2.length - 1;

        while (l >= 0 && r >= 0) {
            if (nums1[l] >= nums2[r]) {
                nums1[temp] = nums1[l];
                l--;
            } else {
                nums1[temp] = nums2[r];
                r--;
            }
            temp--;
        }
        while (r >= 0) {
            nums1[temp] = nums2[r];
            r--;
            temp--;
        }
    }
}
