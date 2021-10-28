package com.syx.kruskal;

import java.util.Arrays;

public class KruskalCase {
    private int edgeNum;
    private char[] vertexs;
    private int[][] matrix;
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A','B','C','D','E','F','G'};
        int[][] matrix = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };

        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        kruskalCase.print();
        EData[] edges = kruskalCase.getEdges();
        System.out.println(Arrays.toString(edges));
        kruskalCase.kruskal();

    }

    public KruskalCase(char[] vertexs, int[][] matrix) {
        this.vertexs = new char[vertexs.length];
        this.matrix = new int[vertexs.length][vertexs.length];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
            for (int j = 0; j < vertexs.length; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (j > i && matrix[i][j] != INF) edgeNum++;
            }
        }
    }

    public void print() {
        for (int[] arr : matrix) {
            for (int i : arr) {
                System.out.printf("%12d\t", i);
            }
            System.out.println();
        }
    }

    public int getPosition(char c) {
        for (int i = 0; i < vertexs.length; i++) {
            if (c == vertexs[i]) {
                return i;
            }
        }

        return -1;
    }

    public void bubbleSort(EData[] eData) {
        EData temp;
        for (int i = 1; i < eData.length; i++) {
            for (int j = 0; j < eData.length - i; j++) {
                if (eData[j].weight > eData[j + 1].weight) {
                    temp = eData[j];
                    eData[j] = eData[j + 1];
                    eData[j + 1] = temp;
                }
            }
        }
    }

    public EData[] getEdges() {
        int index = 0;
        EData[] eData = new EData[this.edgeNum];
        for (int i = 0; i < this.vertexs.length; i++) {
            for (int j = i + 1; j < this.vertexs.length; j++) {
                if (this.matrix[i][j] != INF) {
                    eData[index++] = new EData(this.vertexs[i], this.vertexs[j], this.matrix[i][j]);
                }
            }
        }

        return eData;
    }

    //如果说他现在做的更高一点的话，我丝毫不怀疑上帝更偏爱我
    public int getEnds(int[] ends, int index) {
        while (ends[index] != 0) {
            index = ends[index];
        }

        return index;
    }

    public void kruskal() {
        int index = 0;
        int[] ends = new int[vertexs.length];
        EData[] res = new EData[vertexs.length - 1];

        EData[] edges = getEdges();

        bubbleSort(edges);

        for (int i = 0; i < edgeNum; i++) {
            int startIndex = getPosition(edges[i].start);
            int endIndex = getPosition(edges[i].end);

            int m = getEnds(ends, startIndex);
            int n = getEnds(ends, endIndex);

            if (m != n) {
                ends[m] = n;
                res[index++] = edges[i];
            }
        }

        for (EData eData : res) {
            System.out.println(eData);
        }
    }

}

class EData {
    char start;
    char end;
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "<" + start +
                "," + end +
                ">=" + weight;
    }
}
