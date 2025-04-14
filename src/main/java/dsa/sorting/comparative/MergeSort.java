package dsa.sorting.comparative;

import java.util.Arrays;

public class MergeSort {
    /**
     * <h1>Merge method</h1>
     * <p>
     * merge method to combine sorted arrays into sorted array
     *
     * @param left array to be combined
     * @param right array to be combined
     * @return sorted array containing elements of left and right arrays
     */
    static int[] merge(int[] left, int[] right) {
        // prepare result array
        int[] result = new int[left.length + right.length];

        // prepare variables for iteration i(left array iteration) and j(right array iteration)
        int i = 0, j = 0;

        // iterate through arrays left and right picking the smallest of the two(merging nto sorted array)
        while (i < left.length && j < right.length) {
            // if the iterated element of the left array is smaller than iterated element of the right array
            if (left[i] < right[j]) {
                // append iterated element of the left array to the result
                result[i + j] = left[i];
                // increment i
                i++;
            } else {
                // append iterated element of the right array to the result
                result[i + j] = right[j];
                // increment j
                j++;
            }
        }

        // add the remaining elements of left array
        for (; i < left.length; i++) {
            result[i + j] = left[i];
        }

        // add the remaining elements of right array
        for (; j < right.length; j++) {
            result[i + j] = right[j];
        }

        // return sorted result
        return result;
    }

    /**
     * <h1>Merge Sort method</h1>
     * <p>
     * sorts using the merge sort in O(n log(n)) time
     *
     * @param array input array sorted in place
     * @return sorted array of integers
     */
    public static int[] mergeSort(int[] array) {
        // base case: if array length is one just return the array
        if (array.length <= 1) {
            return array;
        }

        // split the array [a1, a2, ..., an] by mid: floor(n / 2) into left: [a1, ..., a(mid - 1)] and right: [a(mid), ..., an]
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);

        // call recursively merge sort on left and right arrays
        int[] sortedLeft = mergeSort(left);
        int[] sortedRight = mergeSort(right);

        // return merged(sorted) arrays left and right
        return merge(sortedLeft, sortedRight);
    }
}
