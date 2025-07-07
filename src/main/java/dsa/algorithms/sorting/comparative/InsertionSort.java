package dsa.algorithms.sorting.comparative;

import dsa.algorithms.search.BinarySearch;
import dsa.algorithms.sorting.SortingAlgo;

public class InsertionSort {
    /**
     * <h1>Insertion Sort Algorithm</h1>
     * <p>
     * Sorts the given array in ascending order using the insertion sort algorithm.
     * The algorithm builds the sorted portion of the array one element at a time by
     * inserting each element into its correct position relative to the sorted portion.
     *
     * @param array the array to be sorted in place
     */
    static public <T extends Comparable<T>> void insertionSort(T[] array, int low, int high) {
        // loop from the second element of the array to the last
        for (int i = low; i <= high; i++) {
            // set key to the element at the iterated position
            T key = array[i];
            // j will be the index of element before key
            int j = i - 1;
            // while the element before key is smaller
            while (j >= low && array[j].compareTo(key) > 0) {
                // shift the element at j forward
                array[j + 1] = array[j];
                // decrement j by one
                j--;
            }
            // set key before all shifted elements
            array[j + 1] = key;
        }
    }

    @SortingAlgo
    static public <T extends Comparable<T>> void insertionSort(T[] array) {
        insertionSort(array, 0, array.length - 1);
    }

    static public <T extends Comparable<T>> void binarySort(T[] array, int low, int high) {
        for (int i = low; i <= high; i++) {
            T key = array[i];

            int j = BinarySearch.iterativeBinarySearch(array, key, low, i);

            System.arraycopy(array, j, array, j + 1, i - j);
            array[j] = key;
        }
    }

    @SortingAlgo
    static public <T extends Comparable<T>> void binarySort(T[] array) {
        binarySort(array, 0, array.length - 1);
    }
}
