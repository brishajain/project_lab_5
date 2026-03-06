package edu.grinnell.csc207.sorts;

import java.lang.reflect.Array;

/**
 * A collection of sorting algorithms over generic arrays.
 */
public class Sorts {
    /**
     * Swaps indices <code>i</code> and <code>j</code> of array <code>arr</code>.
     * 
     * @param <T> the carrier type of the array
     * @param arr the array to swap
     * @param i   the first index to swap
     * @param j   the second index to swap
     */
    public static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * Sorts the array according to the bubble sort algorithm:
     * 
     * <pre>
     * [ unprocessed | i largest elements in order ]
     * </pre>
     * 
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void bubbleSort(T[] arr) {
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; i < length - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0)
                    swap(arr, j, j + 1);
            }
        }
    }

    /**
     * Sorts the array according to the selection sort algorithm:
     * 
     * <pre>
     * [ i smallest elements in order | unprocessed ]
     * </pre>
     * 
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void selectionSort(T[] arr) {
        for (int j = 0; j < arr.length; j++) {
            int tracker = j; // tracks largest element in unsorted region
            for (int i = j + 1; i < arr.length; i++) {
                if (arr[tracker].compareTo(arr[i]) > 0) {
                    tracker = i; // store new largest element in unsorted region
                }
            }
            swap(arr, tracker, j);
        }
    }

    /**
     * Sorts the array according to the insertion sort algorithm:
     * 
     * <pre>
     * [ i elements in order | unprocessed ]
     * </pre>
     * 
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void insertionSort(T[] arr) {
        for (int j = 0; j < arr.length; j++) {
            int i = 0;
            for (; i < j; i++) {
                if (arr[i].compareTo(arr[j]) > 0)
                    break;
            }
            T hold = arr[j];
            for (int w = j; w > i; w--) // shifts all elements between i and j to the right
            {
                swap(arr, w, w - 1);
            }
            arr[i] = hold; // moves element at j to i
        }
    }

    /**
     * Sorts the array according to the merge sort algorithm:
     * <ol>
     * <li>Sort the left half of the array recursively.</li>
     * <li>Sort the right half of the array recursively.</li>
     * <li>Merge the two sorted halves into a sorted whole.</li>
     * </ol>
     * 
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void mergeSort(T[] arr) {
        T[] shadow = (T[]) (new Object[arr.length]);
        mergehelp(arr, shadow, 0, arr.length);
    }

    public static <T extends Comparable<? super T>> void mergehelp(T[] arr, T[] shadow, int beg, int end) {
        int mid = (beg + end) / 2;
        if (end - beg > 1) {
            mergehelp(arr, shadow, beg, mid);
            mergehelp(arr, shadow, mid, end);
        } else {
            int leftIn = beg;
            int rightIn = mid;
            int shadowIn = beg;

            while (shadowIn < end) {

                if (rightIn >= end) {
                    shadow[shadowIn++] = arr[leftIn++];
                } else if (leftIn >= mid) {
                    shadow[shadowIn++] = arr[rightIn++];
                } else if (arr[leftIn].compareTo(arr[rightIn]) > 0) {
                    shadow[shadowIn++] = arr[leftIn++];
                } else {
                    shadow[shadowIn++] = arr[rightIn++];
                }
            }

            for (int i = beg; i < end; i++) {
                arr[i] = shadow[i];
            }
        }
    }

    /**
     * Sorts the array according to the quick sort algorithm:
     * <ol>
     * <li>Choose a pivot value and partition the array according to the pivot.</li>
     * <li>Sort the left half of the array recursively.</li>
     * <li>Sort the right half of the array recursively.</li>
     * </ol>
     * 
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] arr) {
        quickSort_help(arr, 0, arr.length - 1);
    }

    public static <T extends Comparable<? super T>> void quickSort_help(T[] arr, int beg, int end) {
        if (beg >= end) {
            int mid = (beg + end) / 2;
            int leftpoint = beg;
            int rightpoint = end - 1;

            swap(arr, mid, end);

            while (leftpoint < rightpoint) {
                if (arr[leftpoint].compareTo(arr[end]) < 0)
                    leftpoint++;
                else if (arr[rightpoint].compareTo(arr[end]) < 0)
                    rightpoint--;
                else {
                    swap(arr, leftpoint++, rightpoint--);
                }
            }
            swap(arr, leftpoint, end);

            quickSort_help(arr, beg, leftpoint - 1);
            quickSort_help(arr, leftpoint + 1, end);
        }
    }
}
