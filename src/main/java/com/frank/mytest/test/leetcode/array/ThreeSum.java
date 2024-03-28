package com.frank.mytest.test.leetcode.array;

import java.util.*;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * <p>
 * Notice that the solution set must not contain duplicate triplets.
 * Example 1:
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not matter.
 * <p>
 * Example 2:
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 * Example 3:
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 */
public class ThreeSum {
    public static void main(String[] args) {
        int[] arr = new int[]{-2, -2, 0, -5, -1, -3, 0, 4, 3, 4, 1, 3, 0, -1, 0, 3};
//        System.out.println(threeSumV2(arr));
        System.out.println(threeSumV3(arr));
//        int[] arr2 = new int[]{-2, -1, 0, 0, 1, 2, 2, 3, 4};
//        System.out.println(twoSum(arr2, 2));
    }

    // 暴力解
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3) return res;

        int left = 0; // 指向 index 0
        int right = 1; // 指向 index 1
        int sum;

        while (left < nums.length - 2) {
            // 往 right pointer 右側遍歷
            for (int i = right + 1; i < nums.length; i++) {
                sum = nums[left] + nums[right] + nums[i];
                if (sum == 0) {
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(nums[left]);
                    triplet.add(nums[right]);
                    triplet.add(nums[i]);
                    if (!checkIfContain(res, triplet)) {
                        res.add(triplet);
                    }
                }
            }
            right++;
            if (right == nums.length - 1) {
                left++;
                right = left + 1;
            }
        }

        return res;
    }

    public static boolean checkIfContain(List<List<Integer>> res, List<Integer> list) {
        if (res.isEmpty()) return false;
        boolean zeroSetExist = false;
        Set<Set<Integer>> setList = new HashSet<>();
        for (List<Integer> ints : res) {
            Set<Integer> set = new HashSet<>(ints);
            setList.add(set);
            if (!zeroSetExist) {
                zeroSetExist = set.size() == 1;
            }
        }

        for (Set<Integer> s : setList) {
            int count = 0;
            // 判斷 list 是否全是 0
            boolean isAllZero;
            for (Integer i : list) {
                if (i == 0) count++;
            }
            isAllZero = count == list.size();
            count = 0;
            for (Integer i : list) {
                if (zeroSetExist && isAllZero) return true; // (0,0,0) 為特殊例子
                if (!zeroSetExist && isAllZero) return false; // (0,0,0) 為特殊例子
                if (s.contains(i)) {
                    count++;
                }
                if (count == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * [-2, -2, 0, -5, -1, -3, 0, 4, 3, 4, 1, 3, 0, -1, 0, 3]
     * => [-5, -3, -2, -2, -1, -1, 0, 0, 0, 0, 0, 1, 3, 3, 3, 4]
     * 想法：若先對數列進行排序，先使用 O(nlogn) 的排序法
     * 從第一個數開始，找到最後一個數 (O(n)) (並且每一輪的數要不同，因為結果集要求要為獨立的)
     * 每個數計算需要找的和 target = 0 - nums[i]
     * 在除了目前的數之外剩餘的數列中，使用兩個 pointer 指向左右兩側(l,r)，進行收縮的迴圈 (O(n))
     * 當找到一組和 (nums[l] + nums[r])等於 target 的組合後，紀錄並保存到結果集 l++, r-- (需跳過相同的數)
     * 若 (nums[l] + nums[r]) < target, 將 l 遞增，否則將 r 遞減
     * 綜合兩層迴圈與排序 O(n^2 + nlogn)
     */
    public static List<List<Integer>> threeSumV2(int[] nums) {
        Arrays.sort(nums); // 先排序 (O(nlogn))
        int l;
        int r;
        int target; // 目標和
        List<List<Integer>> res = new ArrayList<>();
        // 從第一個數到倒數第三個數，檢查一輪 array，每一輪又掃一遍 array => O(n^2)
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (nums[i] != nums[i - 1])) { // 跳過重複的數
                // 定義左右指針
                l = i + 1;
                r = nums.length - 1;
                target = -nums[i];
                while (l < r) {
                    if (target == nums[l] + nums[r]) {
                        List<Integer> list = new ArrayList<>(List.of(nums[i], nums[l], nums[r]));
                        res.add(list);
                        l++;
                        r--;
                        // 跳過重複的組合
                        while (l < r && nums[l] == nums[l - 1]) {
                            l++;
                        }
                        while (l < r && nums[r] == nums[r + 1]) {
                            r--;
                        }
                    } else if (target > nums[l] + nums[r]) { // 找到的和太小
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 當在一個已經排列好的陣列尋找 target 時，題目就變為 two sum 了
     */
    public static List<List<Integer>> threeSumV3(int[] nums) {
        Arrays.sort(nums); // 先排序 (O(nlogn))
        System.out.println(Arrays.toString(nums));
        int target; // 目標和
        List<List<Integer>> res = new ArrayList<>();
        // 從第一個數到倒數第三個數，檢查一輪 array，每一輪又掃一遍 array => O(n^2)
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (nums[i] != nums[i - 1])) { // 跳過重複的數
                target = -nums[i];
                // simplify to two sum
                Map<Integer, Integer> map = new HashMap<>();
                int newTarget;
                int j = i + 1;
                while (j < nums.length) {
                    newTarget = target - nums[j];
                    Integer newTargetIndex = map.get(newTarget);
                    if (newTargetIndex != null) {
                        List<Integer> list = new ArrayList<>(List.of(nums[i], nums[j], nums[newTargetIndex]));
                        res.add(list);
                        while (j < nums.length - 1 && nums[j] == nums[j + 1]) {
                            j++; // 往後走直到確定下一個不重複
                        }
                    } else {
                        map.put(nums[j], j);
                    }
                    j++;
                }
            }
        }
        return res;
    }

    // ex:[-2,-1,0,0,1,2,2,3,4], sum = 2
    public static List<List<Integer>> twoSum(int[] nums, int sum) {
        int target;
        Map<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 跳過重複
            target = sum - nums[i];
            Integer targetIndex = map.get(target);
            if (targetIndex != null) {
                List<Integer> list = new ArrayList<>(List.of(nums[i], nums[targetIndex]));
                res.add(list);
            } else {
                map.put(nums[i], i);
            }
        }
        return res;
    }
}
