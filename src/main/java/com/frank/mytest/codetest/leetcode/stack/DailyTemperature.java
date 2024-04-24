package com.frank.mytest.codetest.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 給定一 array 表示每天的溫度，回傳一 array 表示第 i 天遇到下一次更溫暖的日子需要幾天，若沒有則填入 0
 * Example 1:
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 */
public class DailyTemperature {
    public static void main(String[] args) {
        int[] temps = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(dailyTemperatures(temps)));
    }

    public static int[] dailyTemperatures(int[] temps) {
        int[] res = new int[temps.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < temps.length; i++) {
            while (!stack.isEmpty() && temps[stack.peek()] < temps[i]) {
                Integer pop = stack.pop();
                res[pop] = i - pop;
            }
            stack.push(i);
        }
        return res;
    }
}
