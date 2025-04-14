package dsa.sorting.comparative;

import java.util.Random;

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
     * @param left the starting index of the segment to partition
     * @param right the ending index of the segment to partition (pivot)
     * @return the final position of the pivot element
     */
    static public int lomutoPartition(int[] array, int left, int right) {
        int pivot = array[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (array[j] <= pivot) {
                i++;
                Utils.swap(array, i, j);
            }
        }
        Utils.swap(array, i + 1, right);
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
     * @param left the starting index of the segment to partition
     * @param right the ending index of the segment to partition
     * @return the final position of the pivot element
     */
    static public int hoarePartition(int[] array, int left, int right) {
        // todo implement
        return 0;
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
     * @param left the starting index of the segment to partition
     * @param right the ending index of the segment to partition
     * @return the final position of the pivot element
     */
    static public int randomizedLomutoPartition(int[] array, int left, int right) {
        // generate random integer from uniform distribution in range [left, right], therefore inclusive on both sides
        int i = QuickSort.random.nextInt(right - left + 1) + left;
        // swap the element at random position with the last element making it pivot
        Utils.swap(array, i, right);
        // call Lomuto partition
        return lomutoPartition(array, left, right);
    }

    /**
     * <h1>QuickSort Algorithm</h1>
     * <p>
     * Recursively sorts the given array using the QuickSort algorithm and a custom partition function.
     * It divides the array into subarrays around a pivot and sorts each subarray independently.
     *
     * @param array the array to be sorted
     * @param left the starting index of the segment to sort
     * @param right the ending index of the segment to sort
     * @param partitionFunction the partition strategy used to select and position the pivot
     */
    static public void quickSort(int[] array, int left, int right, PartitionFunctionInterface partitionFunction) {
        // if left index is less than right index
        if (left < right) {
            // get pivot by calling the partition function
            int pivot = partitionFunction.partition(array, left, right);
            // recursively call quick-sort on [a(left), ..., a(pivot - 1)]
            quickSort(array, left, pivot - 1, partitionFunction);
            // recursively call quick-sort on [a(pivot), ..., a(right)]
            quickSort(array, pivot, right, partitionFunction);
        }
    }
}
