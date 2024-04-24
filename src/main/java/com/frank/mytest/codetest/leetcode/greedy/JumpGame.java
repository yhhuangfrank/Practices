package com.frank.mytest.codetest.leetcode.greedy;

/**
 * 給定一組非負數的整數 array，每個元素代表可以移動的最大位移量
 * 問是否有可能從 0 index 到最後一個 index
 */
public class JumpGame {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 5, 0, 0};
        int[] arr2 = new int[]{3, 2, 1, 2, 0, 4};
        System.out.println(canJump(arr));
        System.out.println(canJumpV2(arr));
        System.out.println(canJump(arr2));
        System.out.println(canJumpV2(arr2));
    }

    /**
     * 當終點前的某一位置 i 有一個坑 (nums[i] == 0)
     * 第一種方式：
     * 在 i 位置之前必須要有一個位置，移動距離可以超過 i，這樣才有機會碰到終點
     * 因此若在 i 之前可以抵達的最長距離，仍無法超過 i 的話，即為 false
     * <p>
     * 第二種方式：
     * 從尾往頭看，當看到一個坑時，將 gap 設為 1 (要有 nums[i] > 1)
     * 往前找，當 nums[i] 仍無法越過 gap 時， gap 遞增、i 遞減
     * 若找到可越過的 i 時，將 gap 重置，繼續往前找是否有坑
     * 若找到頭 (index = 0) 還是無法越過 gap ，代表無法抵達終點
     */
    public static boolean canJump(int[] nums) {
        int farest = 0; // 初始化能夠到達的最遠的 index 位置
        for (int i = 0; i < nums.length - 1; i++) {
            if (i > farest) return false;
            farest = Math.max(farest, i + nums[i]);
        }
        return true;
    }

    public static boolean canJumpV2(int[] nums) {
        for (int i = nums.length - 2; i >= 0 ; i--) {
            if (nums[i] == 0) {
                int gap = 1;
                int j = i - 1;
                if (j < 0) return nums.length == 1; // 走到 index 為 0 的位置
                while (gap > nums[j]) {
                    gap++;
                    j--;
                    if (j < 0) return false;
                }
            }
        }
        return true;
    }

}
