package dsa.algorithms.sorting.comparative;

import dsa.algorithms.sorting.SortingAlgo;

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
    private static <T extends Comparable<T>> int lomutoPartition(T[] array, int left, int right) {
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
    public static <T extends Comparable<T>> int hoarePartition(T[] array, int left, int right) {
        T pivot = array[left];
        int i = left, j = right;

        while (true) {
            // Find leftmost element greater
            // than or equal to pivot
            while (array[i].compareTo(pivot) < 0) i++;

            // Find rightmost element smaller
            // than or equal to pivot
            while (array[j].compareTo(pivot) > 0) j--;

            // If two pointers met.
            if (i >= j) return j;

            if (array[i].equals(array[j])) {
                i++;
                j--;
                continue;
            }

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
    private static <T extends Comparable<T>> int randomizedLomutoPartition(T[] array, int left, int right) {
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
     * then recursively sorts the sub-arrays.
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

    @SortingAlgo
    static public <T extends Comparable<T>> void quickSortLomuto(T[] array) {
        quickSortLomuto(array, 0, array.length - 1);
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

    @SortingAlgo
    static public <T extends Comparable<T>> void randomizedQuickSortLomuto(T[] array) {
        randomizedQuickSortLomuto(array, 0, array.length - 1);
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

    @SortingAlgo
    static public <T extends Comparable<T>> void quickSortHoare(T[] array) {
        quickSortHoare(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> int[] dualPivotPartition(T[] array, int left, int right) {
        // if first element is bigger than last swap them
        // now the first element is smaller than the last
        if (array[left].compareTo(array[right]) > 0) swap(array, left, right);

        // where '< p' region begins
        int j = left + 1;
        // where '> q' region ends
        int g = right - 1;
        // current scanning index
        int k = left + 1;
        // left pivot
        T p = array[left];
        // right pivot
        T q = array[right];

        // while the flags k and g are not swapped
        while (k <= g) {

            // if elements are less than the left pivot
            if (array[k].compareTo(p) < 0) {
                swap(array, k, j);
                j++;
            }

            // if elements are greater than or equal
            // to the right pivot
            else if (array[k].compareTo(q) >= 0) {
                // shift g to the first element that is <= than q
                while (array[g].compareTo(q) > 0 && k < g) g--;

                swap(array, k, g);
                g--;

                // if swapped element is less than p swap it
                if (array[k].compareTo(p) < 0) {
                    swap(array, k, j);
                    j++;
                }
            }
            k++;
        }
        // decrement j and g to match pivot positions
        j--;
        g++;

        // bring pivots to their appropriate positions
        swap(array, left, j);
        swap(array, right, g);

        // return the indexes of the pivots in an array of length 2
        return new int[]{j, g};
    }

    static public <T extends Comparable<T>> void dualPivotQuickSort(T[] array, int left, int right) {
        if (left < right) {
            int[] pivots = dualPivotPartition(array, left, right);

            dualPivotQuickSort(array, left, pivots[0] - 1);
            dualPivotQuickSort(array, pivots[0], pivots[1] - 1);
            dualPivotQuickSort(array, pivots[1], right);
        }
    }

    @SortingAlgo
    static public <T extends Comparable<T>> void dualPivotQuickSort(T[] array) {
        dualPivotQuickSort(array, 0, array.length - 1);
    }
}
