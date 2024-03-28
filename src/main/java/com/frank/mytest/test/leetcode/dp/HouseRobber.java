package com.frank.mytest.test.leetcode.dp;

public class HouseRobber {
    public static void main(String[] args) {
        int[] nums = new int[]{2,7,9,3,1};
        System.out.println(rob(nums));
    }

    /**
     * 依照 nums 陣列，不能連續偷到相鄰的房子。
     * 計算能獲取到的最大價值
     * rob[0] = nums[0] => 只有偷一間
     * rob[1] = Max(rob[0],nums[1]) 偷第二間與第一間中找尋較大的
     * rob[2] = Max(rob[0] + nums[2], rob[1]) => 可求得通式 rob[i] = Max(rob[i-2]+nums[i], rob[i-1])
     * 因此使用 dp 算出最終結果，rob[nums.length-1] 即是在有 nums 間房子下能獲得的最大價值
     *
     * @param nums 代表每間房子內擁有的價值
     * @return 返回有 nums 間房子下，所能獲取到的最大價值
     */
    public static int rob(int[] nums) {
        int len = nums.length;
        int[] maxValues = new int[len];
        // edge cases
        if (len == 0) return 0;
        if (len == 1) return nums[0];

        // 1) init condition
        maxValues[0] = nums[0];
        maxValues[1] = Math.max(maxValues[0], nums[1]);

        if (len == 2) return maxValues[1];

        // 2) follow formula : rob[i] = Max(rob[i-2]+nums[i], rob[i-1]), do DP
        for (int i = 2; i < len; i++) {
            maxValues[i] = Math.max(maxValues[i - 2] + nums[i], maxValues[i - 1]);
        }

        return maxValues[len - 1];
    }
}
