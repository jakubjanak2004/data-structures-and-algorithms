package dsa.algorithms.sorting.linear;

import dsa.algorithms.sorting.MaxValue;
import dsa.algorithms.sorting.SortingAlgo;

// todo finish commenting the radix sort
public class RadixSort {
    static void countingSortForRadix(Integer[] arr, int start, int size, int exp) {
        // create output array
        int[] output = new int[size];
        // declare index i
        int i;
        // count represents the digits 0-9
        int[] count = new int[10];

        // iterate from start to size - 1
        for (i = start; i < size; i++)
            //
            count[(arr[i] / exp) % 10]++;

        // iterate from second element after start to 9
        for (i = start + 1; i < 10; i++)
            // increase count at iterated position by count at the position before
            count[i] += count[i - 1];

        // iterate from last element to the start element
        for (i = size - 1; i >= start; i--) {
            //
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            //
            count[(arr[i] / exp) % 10]--;
        }

        //
        for (i = start; i < size; i++)
            arr[i] = output[i];
    }

    // todo implement radix for other number systems(binary,...)
    static public void radixSort(Integer[] arr, int max, int start, int size) {
        // iterate exp from 1 to the power of 10 eih condition that floor(max / exp) > 0
        for (int exp = 1; max / exp > 0; exp *= 10)
            // call counting sort for radix with exp
            countingSortForRadix(arr, start, size, exp);
    }

    @SortingAlgo(onlyInteger = true)
    static public void radixSort(Integer[] arr, @MaxValue int max) {
        radixSort(arr, max, 0, arr.length);
    }
}
