package dsa.algorithms.sorting.comparative;

import java.util.Random;

import static dsa.algorithms.Utils.swap;

public class QuickSort {
    // random object for randomized algorithms
    static Random random = new Random();

    /**
     * <h1>Lomuto Partition Scheme</h1>
     * <p>
     * Partitions the array using the Lomuto partitioning algorithm.
     * It selects the last element as the pivot and rearranges the array such that
     * elements less than or equal to the pivot come before it, and greater elements after.
     *
     * @param array the array to partition
     * @param left  the starting index of the segment to partition
     * @param right the ending index of the segment to partition (pivot)
     * @return the final position of the pivot element
     */
    static public <T extends Comparable<T>> int lomutoPartition(T[] array, int left, int right) {
        T pivot = array[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, right);
        return i + 1;
    }

    /**
     * <h1>Hoare Partition Scheme</h1>
     * <p>
     * Partitions the array using the Hoare partitioning algorithm.
     * Typically uses the first element as the pivot and partitions the array into
     * two parts: elements less than the pivot and elements greater than the pivot.
     *
     * @param array the array to partition
     * @param left  the starting index of the segment to partition
     * @param right the ending index of the segment to partition
     * @return the final position of the pivot element
     */
    static public <T extends Comparable<T>> int hoarePartition(T[] array, int left, int right) {
        T pivot = array[left];
        int i = left - 1, j = right + 1;

        while (true) {
            // Find leftmost element greater
            // than or equal to pivot
            do {
                i++;
            } while (array[i].compareTo(pivot) < 0);

            // Find rightmost element smaller
            // than or equal to pivot
            do {
                j--;
            } while (array[j].compareTo(pivot) > 0);

            // If two pointers met.
            if (i >= j) return j;
            swap(array, i, j);
        }
    }

    /**
     * <h1>Randomized Lomuto Partition</h1>
     * <p>
     * Partitions the array using the Lomuto scheme with a randomized pivot.
     * A random element from the given range is selected and swapped with the last element,
     * effectively making it the pivot. This helps improve performance by reducing the chance
     * of worst-case scenarios (e.g., already sorted arrays) in quicksort.
     * The method then proceeds with the standard Lomuto partitioning.
     *
     * @param array the array to partition
     * @param left  the starting index of the segment to partition
     * @param right the ending index of the segment to partition
     * @return the final position of the pivot element
     */
    static public <T extends Comparable<T>> int randomizedLomutoPartition(T[] array, int left, int right) {
        // generate random integer from uniform distribution in range [left, right], therefore inclusive on both sides
        int i = QuickSort.random.nextInt(right - left + 1) + left;
        // swap the element at random position with the last element making it pivot
        swap(array, i, right);
        // call Lomuto partition
        return lomutoPartition(array, left, right);
    }

    /**
     * <h1>QuickSort Algorithm (Lomuto Partition)</h1>
     * <p>
     * Recursively sorts the given array using the QuickSort algorithm with Lomuto partition scheme.
     * It selects a pivot element and partitions the array into elements less than and greater than the pivot,
     * then recursively sorts the subarrays.
     *
     * @param array the array to be sorted
     * @param left  the starting index of the segment to sort
     * @param right the ending index of the segment to sort
     */
    static public <T extends Comparable<T>> void quickSortLomuto(T[] array, int left, int right) {
        // if left index is less than right index
        if (left < right) {
            // get pivot by calling the partition function
            int pivot = lomutoPartition(array, left, right);
            // recursively call quick-sort on [a(left), ..., a(pivot - 1)]
            quickSortLomuto(array, left, pivot - 1);
            // recursively call quick-sort on [a(pivot + 1), ..., a(right)]
            quickSortLomuto(array, pivot + 1, right);
        }
    }

    /**
     * <h1>Randomized QuickSort (Lomuto Partition)</h1>
     * <p>
     * Recursively sorts the given array using QuickSort and a randomized version of the Lomuto partition scheme.
     * The pivot is chosen at random to improve performance on certain inputs.
     *
     * @param array the array to be sorted
     * @param left  the starting index of the segment to sort
     * @param right the ending index of the segment to sort
     */
    static public <T extends Comparable<T>> void randomizedQuickSortLomuto(T[] array, int left, int right) {
        // if left index is less than right index
        if (left < right) {
            // get pivot by calling the partition function
            int pivot = randomizedLomutoPartition(array, left, right);
            // recursively call quick-sort on [a(left), ..., a(pivot - 1)]
            quickSortLomuto(array, left, pivot - 1);
            // recursively call quick-sort on [a(pivot + 1), ..., a(right)]
            quickSortLomuto(array, pivot + 1, right);
        }
    }

    /**
     * <h1>QuickSort Algorithm (Hoare Partition)</h1>
     * <p>
     * Recursively sorts the given array using the QuickSort algorithm with Hoare partition scheme.
     * It partitions the array into two parts where all elements on the left are less than or equal to the pivot
     * and all elements on the right are greater than or equal to the pivot, then recursively sorts each subarray.
     *
     * @param array the array to be sorted
     * @param left  the starting index of the segment to sort
     * @param right the ending index of the segment to sort
     */
    static public <T extends Comparable<T>> void quickSortHoare(T[] array, int left, int right) {
        // if left index is less than right index
        if (left < right) {
            // get pivot by calling the partition function
            int pivot = hoarePartition(array, left, right);
            // recursively call quick-sort on [a(left), ..., a(pivot)]
            quickSortHoare(array, left, pivot);
            // recursively call quick-sort on [a(pivot + 1), ..., a(right)]
            quickSortHoare(array, pivot + 1, right);
        }
    }

    // todo, tends to be faster than classical quicksort for large datasets
    static public void dualPivotQuickSort() {}
}
