package dsa.algorithms.sorting.linear;

import dsa.algorithms.sorting.MaxValue;
import dsa.algorithms.sorting.SortingAlgo;

import java.util.Arrays;

public class CountingSort {
    /**
     * <h1>Counting Sort Algorithm</h1>
     * <p>
     * Sorts the specified range of the array using the counting sort algorithm.
     * This non-comparison-based sorting technique counts the occurrences of each integer value
     * in the input range and reconstructs the sorted array based on these counts.
     * <br><br>
     * The method assumes all values in the input range are non-negative and less than or equal to {@code max}.
     *
     * @param input the array to be sorted (in place)
     * @param max the maximum expected value in the input range
     */
    @SortingAlgo(onlyInteger = true)
    public static void countingSort(Integer[] input, @MaxValue int max) {
        // create array holding the number of each element from 0 to max
        Integer[] c = new Integer[max + 1];

        // fill array with zeros
        Arrays.fill(c, 0);

        // for each element of the input increase the c at index of that element
        for (int i : input) {
            c[i] += 1;
        }

        // add previous value to iterated in array c
        for (int i = 1; i < c.length; i++) {
            c[i] += c[i - 1];
        }

        // create new array sorted
        Integer[] sorted = new Integer[input.length];
        // iterate from last to first element of the input array
        for (int i = input.length - 1; i >= 0; i--) {
            // init current as input at iteration
            int current = input[i];
            // sorted at cumulative number of elements (-1 for 0 based indexing) is equal to current
            sorted[c[current] - 1] = current;
            // decrease the number of current in c
            c[current]--;
        }

        // copy the sorted into input
        // as the sorting should be in place
        System.arraycopy(sorted, 0, input, 0, sorted.length);
    }
}
