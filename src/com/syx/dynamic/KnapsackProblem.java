package com.syx.dynamic;

/**
 * 01背包问题
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] weight = {1, 4, 3};
        int[] val = {1500, 3000, 2000};
        int m = 4;
        int n = val.length;

        int[][] v = new int[n + 1][m + 1];
        int[][] path = new int[n + 1][m + 1];

        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < (m + 1); j++) {
                /*if (weight[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    if (v[i - 1][j] > (val[i - 1] + v[i - 1][j - weight[i - 1]])) {
                        v[i][j] = v[i - 1][j];
                    } else {
                        v[i][j] = val[i - 1] + v[i - 1][j - weight[i - 1]];
                        path[i][j] = 1;
                    }
                }*/
                if (weight[i - 1] < j &&
                    v[i - 1][j] < (val[i - 1] + v[i - 1][j - weight[i - 1]])) {
                    v[i][j] = val[i - 1] + v[i - 1][j - weight[i - 1]];
                    path[i][j] = 1;
                } else {
                    v[i][j] = v[i - 1][j];
                }
            }
        }

        for (int[] arr : v) {
            for (int i : arr) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }

        int i = n, j = m;
        while (i > 0 && j > 0) {
            if (path[i][j] > 0) {
                System.out.printf("第%d个物品放入背包\n", i);
                j -= weight[i - 1];
            }
            i--;
        }
    }
}
