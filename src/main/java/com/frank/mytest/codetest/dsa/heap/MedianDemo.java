package com.frank.mytest.codetest.dsa.heap;

public class MedianDemo {
    public static void main(String[] args) {

        Median median = new Median();
        int[] arr = new int[]{4, 7, 3, 5, 1};
        for (int v : arr) {
            median.insert(v);
            System.out.printf("加入 %s , ", v);
            System.out.println(median.getMedian());
        }
    }
}
