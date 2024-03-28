package com.frank.mytest.test.leetcode.math;

import java.util.HashMap;
import java.util.Map;

/**
 * 在一數列中，找尋佔最多的 element (已知有一數過半)
 * 根據 摩爾投票算法的想法，在已經有過半的前提下，任意取兩個元素移除投票資格，不會改變過半的元素
 */
public class MajorityElement {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 1, 1, 1, 2, 2};
        int[] nums2 = new int[]{3, 2, 3};
        System.out.println(majorityElementV2(nums));
        System.out.println(majorityElementV2(nums2));

    }


    /**
     * 假設 nums[0] 是 majorityElement, 並紀錄 count = 1
     * 當遇到下一個數為不同數時，將 count 遞減，並且若 count = 0 時，將那個不同的數設為 majorityElement
     * 最終留下的即為答案 (因為不會被抵銷到 0)
     */
    public static int majorityElement(int[] nums) {
        int majorityElement = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (majorityElement == nums[i]) {
                count++;
                continue;
            }
            count--; // 若遇到不相同的數遞減
            if (count == 0) {
                // 重新設定目前元素為 majorityElement，重置個數
                count = 1;
                majorityElement = nums[i];
            }
        }
        return majorityElement;
    }

    public static int majorityElementV2(int[] nums) {
        Integer majorityElement = null;
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.merge(num, 1, Integer::sum);
        }
        System.out.println(count);
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (majorityElement == null || value > count.get(majorityElement)) {
                majorityElement = key;
            }
        }
        return majorityElement;
    }
}
