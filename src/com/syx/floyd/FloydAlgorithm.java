package com.syx.floyd;

import java.util.Arrays;

public class FloydAlgorithm {
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
        graph.floyd();
        graph.show();
    }

}

class Graph {
    private char[] vertrix;
    private int[][] matrix;
    private int[][] pre;

    public Graph(char[] vertrix, int[][] matrix) {
        this.vertrix = vertrix;
        this.matrix = matrix;
        pre = new int[vertrix.length][vertrix.length];
        for (int i = 0; i < pre.length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    public void show() {
        for (int i = 0; i < vertrix.length; i++) {
            for (int j = 0; j < vertrix.length; j++) {
                System.out.print(vertrix[pre[i][j]] + "\t");
            }
            System.out.println();
            for (int j = 0; j < vertrix.length; j++) {
                System.out.print(vertrix[i] + "到" + vertrix[j] + "的距离" + matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void floyd() {
        int len;
        for (int k = 0; k < vertrix.length; k++) {
            for (int i = 0; i < vertrix.length; i++) {
                for (int j = 0; j < vertrix.length; j++) {
                    len = matrix[i][k] + matrix[k][j];
                    if (len < matrix[i][j]) {
                        matrix[i][j] = len;
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
}