package dsa.sorting.linear;

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
     * @param array the array to be sorted (in place)
     * @param max the maximum expected value in the input range
     * @param start the starting index (inclusive) of the segment to sort
     * @param end the ending index (inclusive) of the segment to sort
     */
    public static void countingSort(int[] array, int max, int start, int end) {
        // create array with each index representing an integer from <0, 1, ..., max>
        int[] countArray = new int[max + 1];

        // iterate from start to end of array
        for (int k = start; k <= end; k++) {
            // count array at index of the value at array should be increased by one
            countArray[array[k]]++;
        }

        // initialise index at first element
        int index = 0;
        // iterate from first element to the last element of the count array
        for (int i = 0; i < countArray.length; i++) {
            // iterate count array at i times
            for (int j = 0; j < countArray[i]; j++) {
                // array at index will be equal to i(iteration through count array)
                array[index] = i;
                // increase index by one
                index++;
            }
        }
    }
}
