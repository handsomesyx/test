package com.syx.dijkstra;

import java.util.Arrays;

public class DijkstraAlgorithm {
    private static final int N = 65535;
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {N, 5, 7, N, N, N, 2},
                {5, N, N, 9, N, N, 3},
                {7, N, N, N, 8, N, N},
                {N, 9, N, N, N, 4, N},
                {N, N, 8, N, N, 5, 4},
                {N, N, N, 4, 5, N, 6},
                {2, 3, N, N, 4, 6, N}
        };
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.dijkstra(2);
        graph.show();
    }


}

class Graph {
    private char[] vertex;
    private int[][] matrix;
    private VisitedVertex vv;


    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int[] arr : matrix) {
            System.out.println(Arrays.toString(arr));

        }
    }

    public void dijkstra(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index);
        for (int i = 1; i < vertex.length; i++) {
            int j = vv.updateArr();
            update(j);
        }
    }

    public void update(int index) {
        int len;
        for (int i = 0; i < vertex.length; i++) {
            len = vv.getDis(index) + matrix[index][i];
            if (!vv.in(i) && vv.getDis(i) > len) {
                vv.updatePre(i, index);
                vv.updateDis(i, len);
            }
        }
    }

    public void show() {
        for (int i = 0; i < vertex.length; i++) {
            System.out.println(vertex[i] + "\t" + (vv.getDis(i) == 0 ? "N" : vv.getDis(i)));
        }
    }

}

class VisitedVertex {
    private int[] alreadyArr;
    private int[] preVisited;
    private int[] dis;

    public VisitedVertex(int length, int index) {
        alreadyArr = new int[length];
        preVisited = new int[length];
        dis = new int[length];

        Arrays.fill(dis, 65535);
        alreadyArr[index] = 1;
        dis[index] = 0;
    }

    public boolean in(int index) {
        return alreadyArr[index] == 1;
    }

    public void updateDis(int index, int val) {
        dis[index] = val;
    }

    public void updatePre(int index, int val) {
        preVisited[index] = val;
    }

    public int getDis(int index) {
        return dis[index];
    }

    public int updateArr() {
        int index = 0, min = 65535;
        for (int i = 0; i < alreadyArr.length; i++) {
            if (alreadyArr[i] == 0 && dis[i] < min) {
                index = i;
                min = dis[i];
            }
        }
        alreadyArr[index] = 1;

        return index;
    }
}
