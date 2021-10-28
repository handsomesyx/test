package com.syx.kmp;

import java.util.Arrays;

public class KmpAlgorithm {
    public static void main(String[] args) {
        int[] next = kmpNext("ABCDABD");
        System.out.println(Arrays.toString(next));

        int index = kmpSearch("BBC ABCDAB ABCDABCDABDE", "ABCDABD", kmpNext("ABCDABD"));
        System.out.println(index);
    }

    public static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            if (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }

            if (j == str2.length()) {
                return i - j + 1;
            }
        }

        return -1;
    }

    public static int[] kmpNext(String str) {
        int[] next = new int[str.length()];
        for (int i = 1, j = 0; i < next.length; i++) {
            if (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = next[j - 1];
            }

            if (str.charAt(i) == str.charAt(j)) {
                j++;
            }

            next[i] = j;
        }

        return next;
    }
}
