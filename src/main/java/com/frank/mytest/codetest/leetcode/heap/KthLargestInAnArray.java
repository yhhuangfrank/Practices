package com.frank.mytest.codetest.leetcode.heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KthLargestInAnArray {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int[] nums2 = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println(sol.findKthLargest(nums, 2)); // 5
        System.out.println(sol.findKthLargest(nums2, 4)); // 4
        System.out.println(sol.findKthLargestV2(nums, 2)); // 5
        System.out.println(sol.findKthLargestV2(nums2, 4)); // 4
    }

}

//Example 1:
//Input: nums = [3,2,1,5,6,4], k = 2
//Output: 5
//Example 2:
//Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
//Output: 4

class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int n : nums) {
            minHeap.add(n);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    // By sorting
    public int findKthLargestV2(int[] nums, int k) {
        Integer[] arr = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = nums[i];
        }
        Arrays.sort(arr, Collections.reverseOrder());
        return arr[k - 1];
    }
}
