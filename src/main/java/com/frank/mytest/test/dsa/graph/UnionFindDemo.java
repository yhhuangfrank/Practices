package com.frank.mytest.test.dsa.graph;

public class UnionFindDemo {
    public static void main(String[] args) {

        UnionFind unionFind = new UnionFind(4);
        unionFind.union(0,1);
        unionFind.union(2,3);
        System.out.println(unionFind.getNumComponents());
        unionFind.union(0,2);
        System.out.println(unionFind.find(2));
        System.out.println(unionFind.getNumComponents());
    }
}
