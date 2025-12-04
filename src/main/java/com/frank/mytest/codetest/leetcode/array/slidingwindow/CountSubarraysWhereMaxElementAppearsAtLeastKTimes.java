package com.frank.mytest.codetest.leetcode.array.slidingwindow;

public class CountSubarraysWhereMaxElementAppearsAtLeastKTimes {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countSubarrays(new int[]{1, 3, 2, 3, 3}, 2)); // 6
        System.out.println(solution.countSubarrays(new int[]{1, 4, 2, 1}, 3)); // 0
    }

    static class Solution {
        public long countSubarrays(int[] nums, int k) {
            int n = nums.length;
            int max = nums[0];
            for (int num : nums) {
                max = Math.max(max, num);
            }

            long res = 0; // 有效 subArray 數量
            int count = 0; // 目前共有多少個數，值等於 max
            int l = 0;
            for (int r = 0; r < n; r++) {
                if (nums[r] == max) {
                    count++;
                }
                while (r - l + 1 >= k && count >= k) {
                    if (nums[l] == max) {
                        count--;
                    }
                    l++;
                }
                res += l; // 跳出循環時， 0 ~ l - 1 都是有效的左邊界範圍，共 l 個
            }
            return res;
        }
    }
}
