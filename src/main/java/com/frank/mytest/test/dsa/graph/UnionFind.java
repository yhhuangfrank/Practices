package com.frank.mytest.test.dsa.graph;

import java.util.HashMap;
import java.util.Map;

public class UnionFind {
    Map<Integer, Integer> parent; // 每個節點的 parent
    Map<Integer, Integer> rank; // 每個節點所紀錄的 rank，相當於 tree 的高度

    public UnionFind(int n) { // n 個節點
        this.parent = new HashMap<>();
        this.rank = new HashMap<>();

        for (int i = 0; i < n ; i++) { // 幫節點編號，並初始化 parent, rank Map
            this.parent.put(i, i);
            this.rank.put(i, 0);
        }
    }

    /**
     * 取得某一節點的根節點 (最上層)
     * @param n 某一 graph 上的節點
     * @return 其 parent
     */
    public int find(int n) {
        int p = this.parent.get(n);
        while (p != this.parent.get(p)) { // 父節點不是預設值 (自己)
            this.parent.put(p, this.parent.get(this.parent.get(p))); // 將“父節點的父節點”作為“新的父節點”，以利後續查找的加快 (線性 -> O(1))
            p = this.parent.get(p);
        }
        return p;
    }

    /**
     * 連結兩個節點，構成一組集合
     * @param n1
     * @param n2
     * @return 是否成功
     */
    public boolean union(int n1, int n2) {
        int p1 = this.find(n1);
        int p2 = this.find(n2);

        if (p1 == p2) return false; // 兩個節點已經彼此相連結過了
        // 依照節點的 rank ，決定誰是 parent，達到平衡
        if (this.rank.get(p1) > this.rank.get(p2)) {
            this.parent.put(p2, p1); // p2 作為 p1 的 child
        } else if (this.rank.get(p1) < this.rank.get(p2)) {
            this.parent.put(p1, p2); // p1 作為 p2 的 child
        } else {
            this.parent.put(p2, p1); // 定義相等時，p2 作為 p1 的 child
            this.rank.put(p1, this.rank.get(p1) + 1); // p1 高度遞增
        }
        return true;
    }

    public boolean isSameComponent(int x, int y) {
        return this.find(x) == this.find(y);
    }

    public int getNumComponents() {
        int total = 0;
        for (int i = 0; i < this.parent.size(); i++){
            if (this.parent.get(i) == i) {
                total++;
            }
        }
        return total;
    }
}
