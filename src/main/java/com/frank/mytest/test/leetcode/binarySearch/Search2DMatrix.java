package com.frank.mytest.test.leetcode.binarySearch;

/**
 * 給定一個大小為 m x n 陣列，以及一個 target
 * mxn 陣列每一 row 都是由小排到大，並且每個 row 第一個元素會比前一個 row 的最後一個元素還大
 * 找出 target 是否在陣列當中。並使用 O(log(m*n)) 解法
 */
public class Search2DMatrix {

    public static void main(String[] args) {
        int m = 2;
        int n = 3;
        int[][] matrix = new int[m][n];
        int[] a = new int[]{1, 3, 5, 7};
        int[] b = new int[]{10, 11, 16, 20};
        matrix[0] = a;
        matrix[1] = b;

        System.out.println(searchMatrix(matrix, 16));
        System.out.println(searchMatrix(matrix, 3));
        System.out.println(searchMatrix(matrix, -1));
        System.out.println(searchMatrix(matrix, 10));
        System.out.println(searchMatrix(matrix, 20));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        // 從每個列的開頭進行 binary search
        int leftIndex = 0;
        int rightIndex = matrix.length - 1;
        int midArrayIndex = (leftIndex + rightIndex) >> 1;

        while (leftIndex <= rightIndex) {
            midArrayIndex = (leftIndex + rightIndex) >> 1;
            if (target == matrix[midArrayIndex][0]) {
                return true;
            } else if (target > matrix[midArrayIndex][0]) {
                leftIndex = midArrayIndex + 1;
            } else {
                rightIndex = midArrayIndex - 1;
            }
        }

        // leave loop 時代表 target 可能在 matrix[midArrayIndex - 1] 或 matrix[midArrayIndex] 的陣列中
        if (target < matrix[midArrayIndex][0]) {
            midArrayIndex--;
            if (midArrayIndex < 0) return false; // 不可越界
        }

        // 在一維陣列繼續進行 binary search
        leftIndex = 0;
        rightIndex = matrix[midArrayIndex].length - 1;
        int midIndex;

        while (leftIndex <= rightIndex) {
            midIndex = (leftIndex + rightIndex) >> 1;
            if (target == matrix[midArrayIndex][midIndex]) {
                return true;
            } else if (target > matrix[midArrayIndex][midIndex]) {
                leftIndex = midIndex + 1;
            } else {
                rightIndex = midIndex - 1;
            }
        }
        return false;
    }

}
