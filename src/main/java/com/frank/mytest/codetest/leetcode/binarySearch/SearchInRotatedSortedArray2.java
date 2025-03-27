package com.frank.mytest.codetest.leetcode.binarySearch;

/**
 * 給定一個可能經過 rotate 的升序 array (值可能重複)
 * rotate (右移某單位), [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * 問某一 target 是否存在於此 可能 rotate 過的 array 中
 */
public class SearchInRotatedSortedArray2 {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 6, 7, 7, 7, 7, 0, 1, 2};
        System.out.println(search(arr, 0)); // true
        System.out.println(search(arr, 3)); // false
    }

    public static boolean search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) return true;
            if (nums[m] > nums[l]) { // left portion
                if (target > nums[m] || target < nums[l]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            } else if (nums[m] < nums[l]) { // right portion
                if (target < nums[m] || target > nums[r]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            } else { // duplicate
                l += 1;
            }
        }
        return false;
    }
}
