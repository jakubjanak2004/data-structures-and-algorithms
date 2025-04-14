package dsa.sorting;

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
    static public void insertionSort(int[] array) {
        // set n as the length of array
        int n = array.length;

        // loop from the second element of the array to the last
        for (int i = 1; i < n; i++) {
            // set key to the element at the iterated position
            int key = array[i];
            // j will be the index of element before key
            int j = i - 1;
            // while the element before key is smaller
            while (j >= 0 && array[j] > key) {
                // shift the element at j forward
                array[j + 1] = array[j];
                // decrement j by one
                j--;
            }
            // set key before all shifted elements
            array[j + 1] = key;
        }
    }
}
