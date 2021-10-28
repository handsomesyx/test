package com.syx.sort;

import java.time.Instant;
import java.util.Arrays;

/**
 * 我必须考虑到这是不是我最后的机会
 *
 */
public class SortTest {
    public static void main(String[] args) {
        int[] arr = new int[20];
        for (int i = 0; i < 20; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }

        Instant start = Instant.now();
//        insertSort(arr);
//        shellSort(arr);
//        selectSort(arr);
//        heapSort(arr);
//        quickSort(arr);
//        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
        radioSort(arr);
        Instant end = Instant.now();
        System.out.println(end.toEpochMilli() - start.toEpochMilli());

        System.out.println(Arrays.toString(arr));

    }

    /**
     * 插入排序
     * 时间复杂度：O(n^2)
     * @param arr
     */
    public static void insertSort(int[] arr) {
        int insertIndex, insertVal;
        for (int i = 1; i < arr.length; i++) {
            insertIndex = i - 1;
            insertVal = arr[i];

            while (insertIndex > -1 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            arr[++insertIndex] = insertVal;
        }
    }

    /**
     * 希尔排序
     * 时间复杂度：O(nlogn)
     * @param arr
     */
    public static void shellSort(int[] arr) {
        int insertIndex, insertVal;

        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                insertIndex = i - gap;
                insertVal = arr[i];

                while (insertIndex > -1 && insertVal < arr[insertIndex]) {
                    arr[insertIndex + gap] = arr[insertIndex];
                    insertIndex -= gap;
                }

                arr[insertIndex + gap] = insertVal;
            }
        }
    }

    /**
     * 选择排序
     * 时间复杂度：O(n^2)
     * @param arr
     */
    public static void selectSort(int[] arr) {
        int index;
        for (int i = 0; i < arr.length; i++) {
            index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[index] > arr[j]) {
                    index = j;
                }
            }

            swap(arr, i, index);
        }
    }

    /**
     * 堆排序
     * 时间复杂度：nlogn
     * @param arr
     */
    public static void heapSort(int[] arr) {
        buildHeap(arr);

        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            adjustHeap(arr, 0, i);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void buildHeap(int[] arr) {
        for (int i = (arr.length / 2 - 1); i >= 0 ; i--) {
            adjustHeap(arr, i, arr.length);
        }
    }

    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            if (j + 1 < length && arr[j] < arr[j + 1]) {
                j++;
            }
            if (temp < arr[j]) {
                arr[i] = arr[j];
                i = j;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }

    /**
     * 冒泡排序
     * 时间复杂度：n^2
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        int temp;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 快速排序
     * 时间复杂度：nlogn
     * @param arr
     */
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int l, int r) {
        if (l >= r) return;

        int i = paritition(arr, l, r);

        quickSort(arr, l, i - 1);
        quickSort(arr, i + 1, r);
    }

    public static int paritition(int[] arr, int l, int r) {
        int i = l, j = r;
        while (i < j) {
            while (i < j && arr[j] >= arr[l]) j--;
            while (i < j && arr[i] <= arr[l]) i++;

            swap(arr, i, j);
        }
        swap(arr, i, l);

        return i;
    }

    /**
     * 归并排序
     * 时间复杂度：nlogn
     * @param arr
     * @param l
     * @param r
     */
    public static void mergeSort(int[] arr, int l, int r, int[] temp) {
        if (l < r) {
            int mid = (l + r) / 2;
            mergeSort(arr, l, mid, temp);
            mergeSort(arr, mid + 1, r, temp);

            merge(arr, l, mid, r, temp);
        }
    }

    public static void merge(int[] arr, int l, int mid, int r, int[] temp) {
        int i = l, j = mid + 1, k = 0;
        while (i <= mid && j <= r) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= r) {
            temp[k++] = arr[j++];
        }

        for (int m = 0; m < k; m++) {
            arr[l++] = temp[m];
        }
    }

    /**
     * 基数排序
     * 时间复杂度 n+k
     * @param arr
     */
    public static void radioSort(int[] arr) {
        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCounts = new int[10];

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) max = arr[i];
        }

        int maxlength = 1;
        while ((max = max / 10) > 0) {
            maxlength++;
        }

        for (int i = 0, n = 1; i < maxlength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]++] = arr[j];
            }

            int index = 0;
            for (int j = 0; j < 10; j++) {
                if (bucketElementCounts[j] > 0) {
                    for (int k = 0; k < bucketElementCounts[j]; k++) {
                        arr[index++] = bucket[j][k];
                    }

                    bucketElementCounts[j] = 0;
                }
            }
        }
    }
}
