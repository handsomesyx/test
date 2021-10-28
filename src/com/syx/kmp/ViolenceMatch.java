package com.syx.kmp;

public class ViolenceMatch {
    public static void main(String[] args) {
        System.out.println(violenceMatch("硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好", "尚硅谷你尚硅你"));
    }

    public static int violenceMatch(String str1, String str2) {
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();

        int arr1Len = arr1.length;
        int arr2Len = arr2.length;

        int i = 0, j = 0;
        while (i < arr1Len && j < arr2Len) {
            if (arr1[i] == arr2[j]) {
                i++;
                j++;
            } else {
                i -= j - 1;
                j = 0;
            }
        }

        if (j == arr2Len) {
            return i - j;
        }

        return -1;
    }
}
