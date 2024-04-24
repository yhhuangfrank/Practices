package com.frank.mytest.codetest.dsa.graph;

import java.util.*;

public class GraphDemo {

    private List<String> vertexList; // 儲存頂點集合
    private int[][] edges; // 儲存對應的連接矩陣
    private int numOfEdges; // edge 的數目
    private boolean[] isVisited; // 紀錄某個頂點是否被訪問過

    public GraphDemo(int n) {
        // 初始化矩陣和 vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    // 得到目前頂點第一個相鄰頂點的 index
    public int getFirstNeighbor(int currentVertexIndex) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[currentVertexIndex][i] > 0) {
                return i;
            }
        }
        return -1; // 說明沒有相鄰
    }

    // 得到以目前頂點的第一個相鄰頂點的下一個相鄰頂點 index
    public int getNextNeighbor(int currentVertexIndex, int neighborVertex) {
        for (int i = neighborVertex + 1; i < vertexList.size(); i++) {
            if (edges[currentVertexIndex][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    // 深度優先
    private void doDfs(boolean[] isVisited, int vertexIndex) {
        // 訪問該節點，並輸出
        System.out.print(getVertexByIndex(vertexIndex) + " -> ");
        // 設置已經訪問過
        isVisited[vertexIndex] = true;

        int n = getFirstNeighbor(vertexIndex);
        while (n != -1) { // 找到相鄰
            if (!isVisited[n]) {
                doDfs(isVisited, n); // 讓相鄰節點為參考點再次進行
            }
            // 如果此相鄰節點被訪問過
            n = getNextNeighbor(vertexIndex, n);
        }
    }

    // 對所有頂點都執行 doDFS
    public void dfs () {
        // 清除遍歷過的紀錄
        int len = isVisited.length;
        isVisited = new boolean[len];
        // 對每個頂點執行
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                doDfs(isVisited, i);
            }
        }
    }

    // 廣度優先
    private void doBfs(boolean[] isVisited, int vertexIndex) {
        Queue<Integer> q = new LinkedList<>();
        int headVertexIndex; // 表示 queue 中頭節點對應 index
        int neighborIndex; // 表示相鄰頂點的 index

        System.out.print(getVertexByIndex(vertexIndex) + "->");
        isVisited[vertexIndex] = true;

        q.add(vertexIndex); // 將起始頂點加入 queue

        while (!q.isEmpty()) {
             // 取出 queue 頭節點
             headVertexIndex = q.poll();
             neighborIndex = getFirstNeighbor(headVertexIndex);

             // 訪問目前頂點的每個相鄰頂點
             while (neighborIndex != -1) {
                 if (!isVisited[neighborIndex]) {
                     System.out.print(getVertexByIndex(neighborIndex) + "->");
                     isVisited[neighborIndex] = true;
                     q.add(neighborIndex); // 紀錄後續新的一輪起始頂點順序
                 }
                 neighborIndex = getNextNeighbor(headVertexIndex, neighborIndex); // 找目前頂點下一個相鄰
             }
        }
    }

    public void bfs() {
        // 清除遍歷過的紀錄
        int len = isVisited.length;
        isVisited = new boolean[len];
        doBfs(isVisited, 0);
    }


    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    // v1 v2 為頂點的 index
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    public int getNumOfVertex() {
        return vertexList.size();
    }

    public String getVertexByIndex(int index) {
        return vertexList.get(index);
    }

    // 取得兩個頂點的 weight;
    public int getWeightBetweenVertexes(int v1, int v2) {
        return edges[v1][v2];
    }

    // 顯示出 edges 矩陣
    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    /**
     *        A
     *      /  \
     *     C ㄧ B
     *        / \
     *       D  E
     */
    public static void main(String[] args) {
        GraphDemo graphDemo = new GraphDemo(5);
        String[] vertexes = {"A", "B", "C", "D", "E"};

        for (String vertex : vertexes) {
            graphDemo.insertVertex(vertex);
        }

        // 添加邊的關係 (A-B, A-C, B-C, B-D, B-E)
        graphDemo.insertEdge(0, 1, 1);
        graphDemo.insertEdge(0, 2, 1);
        graphDemo.insertEdge(1, 2, 1);
        graphDemo.insertEdge(1, 3, 1);
        graphDemo.insertEdge(1, 4, 1);

        graphDemo.showGraph();

        // dfs
//        graphDemo.dfs();
        // bfs
        graphDemo.bfs();
    }

}

