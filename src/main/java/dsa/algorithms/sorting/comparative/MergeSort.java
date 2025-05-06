package dsa.algorithms.sorting.comparative;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MergeSort {
    /**
     * <h1>Merge method</h1>
     * <p>
     * merge method to combine sorted arrays into sorted array
     *
     * @param left  array to be combined
     * @param right array to be combined
     * @return sorted array containing elements of left and right arrays
     */
    static <T extends Comparable<T>> T[] merge(T[] left, T[] right) {
        // prepare result array
        @SuppressWarnings("unchecked")
        T[] result = (T[]) Array.newInstance(left.getClass().getComponentType(), left.length + right.length);

        // prepare variables for iteration i(left array iteration) and j(right array iteration)
        int i = 0, j = 0;

        // iterate through arrays left and right picking the smallest of the two(merging nto sorted array)
        while (i < left.length && j < right.length) {
            // if the iterated element of the left array is smaller than iterated element of the right array
            if (left[i].compareTo(right[j]) < 0) {
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

    static <T extends Comparable<T>> void inPlaceMerge(T[] array, T[] aux, int low, int high) {
        int mid = (low + high) / 2;
        int i1 = low;
        int i2 = mid + 1;
        int j = low;

        while ((i1 <= mid) && (i2 <= high)) {
            if (array[i1].compareTo(array[i2]) <= 0) {
                aux[j] = array[i1];
                i1++;
            } else {
                aux[j] = array[i2];
                i2++;
            }
            j++;
        }

        while (i1 <= mid) {
            aux[j] = array[i1];
            i1++;
            j++;
        }
        while (i2 <= high) {
            aux[j] = array[i2];
            i2++;
            j++;
        }
    }

    /**
     * <h1>Merge Sort method</h1>
     * <p>
     * sorts using the merge sort in O(n log(n)) time
     *
     * @param array input array sorted in place
     * @return sorted array of integers
     */
    public static <T extends Comparable<T>> T[] mergeSort(T[] array) {
        // base case: if array length is one just return the array
        if (array.length <= 1) {
            return array;
        }

        // split the array [a1, a2, ..., an] by mid: floor(n / 2) into left: [a1, ..., a(mid - 1)] and right: [a(mid), ..., an]
        int mid = array.length / 2;
        T[] left = Arrays.copyOfRange(array, 0, mid);
        T[] right = Arrays.copyOfRange(array, mid, array.length);

        // call recursively merge sort on left and right arrays
        T[] sortedLeft = mergeSort(left);
        T[] sortedRight = mergeSort(right);

        // return merged(sorted) arrays left and right
        return merge(sortedLeft, sortedRight);
    }

    public static <T extends Comparable<T>> void inPlaceMergeSort(T[] array, T[] aux, int low, int high) {
        // base case: if the low and high are the same we stop the recursion
        if (low >= high) return;

        //  split the array [a1, a2, ..., an] by mid: floor(n / 2) into left: [a1, ..., a(mid)] and right: [a(mid + 1), ..., an]
        int mid = (low + high) / 2;

        // call recursively merge sort on left and right arrays
        inPlaceMergeSort(array, aux, low, mid);
        inPlaceMergeSort(array, aux, mid + 1, high);

        // merge sorted sub-arrays into the aux array
        inPlaceMerge(array, aux, low, high);

        // copying aux to array from low to high
        // optimised copying
        System.arraycopy(aux, low, array, low, high + 1 - low);
    }
}
