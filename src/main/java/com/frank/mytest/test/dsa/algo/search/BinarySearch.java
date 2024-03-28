package com.frank.mytest.test.dsa.algo.search;

import java.util.ArrayList;
import java.util.List;

// 注意，數列必須是有序的
public class BinarySearch {

    public static void main(String[] args) {

        int[] arr = {1, 8, 10, 89, 1000, 1000,1234};
        System.out.println(binarySearchRecursively(arr, 0, arr.length - 1, 15));
        System.out.println(binarySearchWithDuplicate(arr, 0, arr.length - 1, 1000));
        System.out.println(binarySearchIteratively(arr, 10));
    }

    public static int binarySearchRecursively(int[] arr, int left, int right, int findVal) {
        if (arr.length == 0 || left > right) return -1;
        if (arr.length == 1) return arr[0];

        int mid = (left + right) / 2;
        if (arr[mid] == findVal) return mid;

        if (arr[mid] > findVal) {
            return binarySearchRecursively(arr, left, mid - 1, findVal);
        } else {
            return binarySearchRecursively(arr, mid + 1, right, findVal);
        }
    }

    public static int binarySearchIteratively(int[] arr, int findVal) {
        if (arr.length == 0) return -1;
        if (arr.length == 1) return arr[0];

        int left = 0;
        int right = arr.length - 1;
        int mid;

        while (left <= right) {
            mid = (left + right) >> 2;
            if (arr[mid] == findVal) return mid;

            if (arr[mid] < findVal) {
                left += 1;
            } else {
                right -= 1;
            }
        }
        return -1;
    }

    // 延伸：找到所有重複數字的 index

    /**
     * 1. 在找到 mid 索引值時，不馬上返回
     * 2. 向 mid 索引左邊掃描，將所有滿足條件的 index 加入到集合中
     * 3. 同理也向 mid 右側掃描
     *
     */
    public static List<Integer> binarySearchWithDuplicate(int[] arr, int left, int right, int findVal) {
        if (arr.length == 0 || left > right) return new ArrayList<>();
        if (arr.length == 1) return List.of(arr[0]);

        int mid = (left + right) / 2;

        if (arr[mid] > findVal) {
            return binarySearchWithDuplicate(arr, left, mid - 1, findVal);
        } else if (arr[mid] < findVal){
            return binarySearchWithDuplicate(arr, mid + 1, right, findVal);
        } else {
            List<Integer> indexList = new ArrayList<>();
            // 向左掃描
            int temp = mid - 1;
            while (temp >= 0 && arr[temp] == findVal) {
                indexList.add(temp);
                temp -= 1;
            }
            indexList.add(mid);

            // 向右掃描
            temp = mid + 1;
            while (temp <= arr.length - 1 && arr[temp] == findVal) {
                indexList.add(temp);
                temp += 1;
            }

            return indexList;
        }
    }
}
