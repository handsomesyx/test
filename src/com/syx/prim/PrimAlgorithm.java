package com.syx.prim;

import java.util.Arrays;

public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        int[][] weight = new int[][] {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };
        MGraph mGraph = new MGraph(verxs);
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, verxs, data, weight);
        minTree.printGraph(weight);
        minTree.prim(mGraph, 0);
    }

}

class MGraph {
    int verxs;
    char[] data;
    int[][] weight;

    public MGraph(int verxs) {
        this.verxs = verxs;
        this.data = new char[verxs];
        this.weight = new int[verxs][verxs];
    }
}

class MinTree {

    public void createGraph(MGraph mGraph, int verxs, char[] data, int[][] weight) {
        for (int i = 0; i < verxs; i++) {
            mGraph.data[i] = data[i];
            for (int j = 0; j < verxs; j++) {
                mGraph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void printGraph(int[][] weight) {
        for (int[] arr : weight) {
            System.out.println(Arrays.toString(arr));
        }
    }

    public void prim(MGraph mGraph,int index) {
        int[] visited = new int[mGraph.verxs];
        visited[index] = 1;


        int h1 = -1, h2 = -1, minWeight = 10000;
        for (int i = 1; i < mGraph.verxs; i++) {

            for (int j = 0; j < mGraph.verxs; j++) {
                for (int k = 0; k < mGraph.verxs; k++) {
                    if (visited[j] == 1 && visited[k] == 0 && mGraph.weight[j][k] < minWeight) {
                        h1 = j;
                        h2 = k;
                        minWeight = mGraph.weight[j][k];
                    }
                }
            }

            System.out.printf("%s and %s , power = %d \n", mGraph.data[h1], mGraph.data[h2], minWeight);
            visited[h2] = 1;
            minWeight = 10000;
        }
    }
}
