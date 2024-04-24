package com.frank.mytest.codetest.leetcode.dp;


public class HouseRobber2 {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 2};
        int[] nums2 = new int[]{1, 2, 3, 1};
        System.out.println(rob(nums));
        System.out.println(rob(nums2));
    }

    /**
     * 依照 nums 陣列，不能連續偷到相鄰的房子。
     * 與第一題不同的是：第一間與最後一間視為連續的房子 (即環狀排列)。
     * 在此情況下計算能獲取到的最大價值。
     * 將問題拆解為：
     * 1) 從第一間到倒數第二間
     * 2) 從第二間到最後一間
     * 拆解後，問題就與第一題相同，在這兩者之間找到最大的即是答案
     *
     * @param nums 代表每間房子內擁有的價值
     * @return 返回有 nums 間房子下，所能獲取到的最大價值
     */
    public static int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[0];
        if (len == 2) return Math.max(nums[0], nums[1]);
        int[] group1 = new int[len - 1];
        int[] group2 = new int[len - 1];
        System.arraycopy(nums, 0, group1, 0, group1.length); // 0 to n-1
        System.arraycopy(nums, 1, group2, 0, group2.length); // 1 to n
        return Math.max(calculateMaxValuesByDP(group1), calculateMaxValuesByDP(group2));
    }

    public static int calculateMaxValuesByDP(int[] nums) {
        int l = nums.length;
        int[] rob = new int[l];
        // edge cases
        if (l == 0) return 0;
        if (l == 1) return nums[0];
        // 1) init condition
        rob[0] = nums[0];
        rob[1] = Math.max(rob[0], nums[1]);

        // 2) follow formula : rob[i] = Max(rob[i-2]+nums[i], rob[i-1]), do DP
        for (int i = 2; i < l; i++) {
            rob[i] = Math.max(rob[i - 2] + nums[i], rob[i - 1]);
        }

        return rob[l - 1];
    }
}
