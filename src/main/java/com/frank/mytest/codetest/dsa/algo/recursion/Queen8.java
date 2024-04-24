package com.frank.mytest.codetest.dsa.algo.recursion;

public class Queen8 {

    int max = 8; // 表示共有多少皇后
    // 定義一維 array 保存皇后放置位置的結果 (按照列依次放)
    // 比如 arr = {0, 4, 7, 5, 2, 6, 1, 3}
    // arr[i] = val, val 表示實際上第 i + 1 個皇后，放在第 i + 1 列的第  val + 1 行。
    int[] arr = new int[max];
    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {
        // 從第 0 個開始放
        Queen8 queen8 = new Queen8();
        queen8.placeNthQueen(0);
        System.out.println("共有 " + count + " 個解");
        System.out.println("共判斷了 " + judgeCount + " 次");
    }

    /**
     * 查看當放置第 n 個，檢查該皇后是否和前面已放置的皇后衝突
     * 斜線衝突：
     * 若 n = 1, i = 0, arr[0] = 0, arr[n] = 1，表示放 n = 1 的皇后在第 1 列第 1 行，n = 0 的皇后在第 0 列第 0 行
     * 若 n = 2, i = 1, arr[1] = 3, arr[n] = 2，表示放 n = 2 的皇后在第 2 列第 2 行，n = 1 的皇后在第 1 列第 3 行
     * 判斷條件 -> Math.abs(n - i) 相等於 Math.abs(arr[n] - arr[i])
     * @param n
     * @return
     */
    private boolean judgeIsOK(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            boolean isOnSameColumn = (arr[i] == arr[n]);
            boolean isOnCrossLine = (Math.abs(n - i) == Math.abs(arr[n] - arr[i]));
            if (isOnSameColumn || isOnCrossLine) {
                return false;
            }
        }
        return true;
    }

    // 將皇后位置輸出
    private void print() {
        count++;
        for (int index : arr) {
            System.out.print(index + " ");
        }
        System.out.println();
    }

    // 放置第 n 個皇后
    private void placeNthQueen(int nThQueen) {
        if (nThQueen == max) {
            // nThQueen == 8，表示放置完成
            print();
            return;
        }
        // 先把目前的第 nThQueen 個皇后放置在該列的第 1 行，依序往後
        for (int i = 0; i < max ; i++) {
            arr[nThQueen] = i;
            // 判斷是否衝突
            if (judgeIsOK(nThQueen)) {
                placeNthQueen(nThQueen + 1); // 此位置可放置就繼續往後放
            }
        }
    }
}
