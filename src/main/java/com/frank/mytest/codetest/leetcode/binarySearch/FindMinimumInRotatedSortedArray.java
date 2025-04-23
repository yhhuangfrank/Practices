package com.frank.mytest.codetest.leetcode.binarySearch;

/**
 * 一 sorted array (每個元素唯一) 向右 shift 某單位
 * 向右一單位 ： (arr[0], arr[1]... arr[n - 1], arr[n]) -> arr[n], arr[0], ... arr[n - 1]
 * 問此 array 最小值為何？ 限定 time complexity 需為 O(log n)
 * <p>
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 */
public class FindMinimumInRotatedSortedArray {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 6, 7, 0, 1, 2};
        System.out.println(findMin(arr));
        System.out.println(findMinByDFS(arr));
    }

    public static int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int res = nums[l];
        while (l <= r) {
            if (nums[r] > nums[l]) { // no rotate
                res = Math.min(res, nums[l]);
                break;
            }
            int m = l + (r - l) / 2;
            res = Math.min(res, nums[m]); // 更新可能的最小值
            if (nums[m] >= nums[l]) { // 代表 m 以前的部分不會有最小值
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return res;
    }

    public static int findMinByDFS(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    public static int dfs(int[] nums, int l, int r) {
        if (l >= r) return nums[l];

        int mid = l + (r - l) / 2;
        int prev = Math.max(mid - 1, 0);
        int post = Math.min(mid + 1, nums.length - 1);
        // 若 mid 在 prev 與 post 之間
        if (nums[mid] <= nums[prev] && nums[mid] <= nums[post]) {
            return nums[mid];
        }
        // 遞迴並取最小值
        return Math.min(
                dfs(nums, l, mid - 1),
                dfs(nums, mid + 1, r)
        );
    }
}
