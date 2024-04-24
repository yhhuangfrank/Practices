package com.frank.mytest.codetest.leetcode.greedy;

/**
 * 給定一個 gas array 與 cost array，代表各個 gas station 與到達下一個 station 所需的 cost
 * 問從哪個 station 開始可以油充足且依序走完一圈
 * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * Output: 3
 */
public class GasStation {

    public static void main(String[] args) {

        int[] gas = new int[]{5, 1, 2, 3, 4};
        int[] cost = new int[]{2, 3, 4, 5, 1};
        int[] gas2 = new int[]{2, 0, 0, 0, 0};
        int[] cost2 = new int[]{0, 1, 0, 0, 0};
        System.out.println(canCompleteCircuit(gas, cost));
        System.out.println(canCompleteCircuit(gas2, cost2));
        System.out.println(canCompleteCircuitV2(gas, cost));
        System.out.println(canCompleteCircuitV2(gas2, cost2));
    }

    /**
     * 當站油量剩餘量為負的不可能為起點，若油量剩餘量一路扣到負的表示起點錯誤，需要重新找
     * 若總加油量 < 總消耗量，則得不到解
     */
    private static int canCompleteCircuitV2(int[] gas, int[] cost) {
        int surplus = 0;
        int start = 0;
        int total_surplus = 0;
        for (int i = 0; i < gas.length; i++) {
            total_surplus += gas[i] - cost[i];
            surplus += gas[i] - cost[i];
            if (surplus < 0) {
                surplus = 0;
                start = i + 1; // 從下一個 index 作為起點
            }
        }
        if (total_surplus >= 0) {
            return start;
        }
        return -1;
    }

    /**
     * cost[i] 代表從 i 到 i + 1 的 station
     * 要跑完全程 -> 每個 cost 都消耗
     * 假定起始點(start)在尾站，結束點(end)在 index 0，油箱留存量為 gas[start] - cost[start]
     * 當 start 和 end 還沒有相遇時：
     * 1) 留存量 >= 0: 在 end 位置加油 -> 更新油箱留存量 -> end 往後推
     * 2) 留存量 < 0 : 表示在 start 出發會有問題，start 往前推，更新油箱留存量
     * 當 start, end 相遇時，檢查油箱留存量是否為負的，是的話則表示沒有答案
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int start = gas.length - 1;
        int end = 0;
        int remainedGas = gas[start] - cost[start];
        while (end < start) {
            if (remainedGas >= 0) {
                remainedGas += gas[end]; // 加油
                remainedGas -= cost[end]; // 更新留存量
                end++; // end 往後推移
            } else {
                start--;
                remainedGas += gas[start];
                remainedGas -= cost[start];
            }
        }
        if (remainedGas < 0) {
            return -1;
        }
        return start;
    }
}
