package com.frank.mytest.codetest.dsa.algo.search;

public class InsertValueSearch {
    public static void main(String[] args) {

        // 使用 1-100 的數列做查找
//        int[] arr = new int[100];
//        for (int i = 0; i < 100; i++) {
//            arr[i] = i + 1;
//        }
        int[] arr = {0, 0, 0};

        int index =  insertValueSearch(arr, 0);
        System.out.println(index);

    }

    private static int insertValueSearch(int[] array, int findValue) {

        if (array.length == 0 || findValue < array[0] || findValue > array[array.length - 1]) return -1;
        return searching(array, 0, array.length - 1, findValue);
    }

    private static int searching(int[] array, int left, int right, int findValue) {
        System.out.println("查找了");
        if (left > right) return -1;
        // 使用插值算法定義 mid
        int mid;
        if (array[right] - array[left] == 0) {
            if (array[left] == findValue) {
                return left;
            } else if (array[left] > findValue) {
                mid = Math.max(left - 1, 0);
            } else {
                mid = Math.min(right + 1, array.length - 1);
            }
        } else {
            mid = left + (right - left) * (findValue - array[left]) / (array[right] - array[left]);
        }

        if (array[mid] == findValue) {
            return mid;
        } else if (array[mid] > findValue) {
            return searching(array, left, mid - 1, findValue);
        } else {
            return searching(array, mid + 1, right, findValue);
        }
    }
}
