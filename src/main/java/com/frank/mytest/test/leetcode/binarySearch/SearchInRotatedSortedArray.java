package com.frank.mytest.test.leetcode.binarySearch;

/**
 * 給定一個可能經過 rotate 的升序 array (值不重複)
 * rotate (右移某單位), [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * 問某一 target 是否存在於此 可能 rotate 過的 array 中
 */
public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 6, 7, 0, 1, 2};
        System.out.println(search(arr, 0));
        System.out.println(searchV2(arr, 0));
        System.out.println(search(arr, 3));
        System.out.println(searchV2(arr, 3));
    }

    public static int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int minIndex = 0;
        // find index of min
        while (l <= r) {
            if (nums[r] > nums[l]) {
                minIndex = nums[minIndex] < nums[l] ? minIndex : l;
                break;
            }
            // still a rotated array
            int m = l + (r - l) / 2;
            minIndex = nums[minIndex] < nums[m] ? minIndex : m;
            if (nums[m] >= nums[l]) { // equals for edge case (one element)
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        // minIndex as pivot, do binary search in left/right portion
        int ll = 0;
        int lr = minIndex - 1;
        int rl = minIndex;
        int rr = nums.length - 1;
        while (ll <= lr && lr >= 0) {
            int m = ll + (lr - ll) / 2;
            if (nums[m] == target) {
                return m;
            } else if (nums[m] > target) {
                lr = m - 1;
            } else {
                ll = m + 1;
            }
        }

        while (rl <= rr && rl < nums.length) {
            int m = rl + (rr - rl) / 2;
            if (nums[m] == target) {
                return m;
            } else if (nums[m] > target) {
                rr = m - 1;
            } else {
                rl = m + 1;
            }
        }
        // cannot find target
        return -1;
    }

    // 解法二
    public static int searchV2(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target)
                return m;
            // left portion
            if (nums[m] >= nums[l]) {
                if (target > nums[m] || target < nums[l]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            } else { // right portion
                if (target > nums[r] || target < nums[m]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
        }
        return -1;
    }
}
