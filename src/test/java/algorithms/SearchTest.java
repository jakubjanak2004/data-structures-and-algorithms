package algorithms;

import dsa.algorithms.search.BinarySearch;
import dsa.algorithms.search.InterpolationSearch;
import dsa.algorithms.search.SequentialSearch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

public class SearchTest {
    private static final int numberOfValues = 50000;
    private static final int maximalValue = 1000;
    private static Double[] searchArray;
    private static Double[] sortedSearchArray;

    @BeforeAll
    static void initArrays() {
        Random rand = new Random();
        searchArray = new Double[numberOfValues];
        sortedSearchArray = new Double[numberOfValues];
        for (int i = 0; i < numberOfValues; i++) {
            double newDouble;
            boolean exists;
            // the new double cant be present in the search array
            do {
                newDouble = rand.nextDouble(maximalValue);
                exists = false;
                for (int j = 0; j < i; j++) {
                    if (searchArray[j].equals(newDouble)) {
                        exists = true;
                        break;
                    }
                }
            } while (exists);
            searchArray[i] = newDouble;
        }
        System.arraycopy(searchArray, 0, sortedSearchArray, 0, searchArray.length);

        // TODO: use your own sorting algorithm instead of Arrays.sort
        Arrays.sort(sortedSearchArray);
    }

    @Test
    public void testSequentialSearch_found() {
        Integer[] array = {1, 2, 3, 4, 5};
        int index = SequentialSearch.sequentialSearch(array, 3);
        Assertions.assertEquals(2, index); // 3 is at index 2
    }

    @Test
    public void testSequentialSearch_notFound() {
        Integer[] array = {1, 2, 3, 4, 5};
        int index = SequentialSearch.sequentialSearch(array, 6);
        Assertions.assertEquals(-1, index);
    }

    @Test
    public void testSequentialSentinelSearch_found() {
        Integer[] array = {1, 2, 3, 4, 5, 0}; // extra space for sentinel overwrite
        int index = SequentialSearch.sequentialSentinelSearch(array, 3);
        Assertions.assertEquals(2, index); // 3 is at index 2
    }

    // todo make sentinel return -1 for missing
    @Test
    public void testSequentialSentinelSearch_notFound() {
        Integer[] array = {1, 2, 3, 4, 5, 0}; // extra space for sentinel overwrite
        int index = SequentialSearch.sequentialSentinelSearch(array, 6);
        Assertions.assertEquals(-1, index);
    }

    @Test
    public void testRecursiveBinarySearch_found() {
        Integer[] array = {1, 3, 5, 7, 9};
        int index = BinarySearch.recursiveBinarySearch(array, 5, 0, array.length - 1);
        Assertions.assertEquals(2, index); // 5 is at index 2
    }

    @Test
    public void testRecursiveBinarySearch_notFound() {
        Integer[] array = {1, 3, 5, 7, 9};
        int index = BinarySearch.recursiveBinarySearch(array, 8, 0, array.length - 1);
        Assertions.assertEquals(-1, index);
    }

    @Test
    public void testIterativeBinarySearch_found() {
        Integer[] array = {1, 3, 5, 7, 9};
        int index = BinarySearch.iterativeBinarySearch(array, 7, 0, array.length - 1);
        Assertions.assertEquals(3, index); // 7 is at index 3
    }

    @Test
    public void testIterativeBinarySearch_notFound() {
        Integer[] array = {1, 3, 5, 7, 9};
        int index = BinarySearch.iterativeBinarySearch(array, 6, 0, array.length - 1);
        Assertions.assertEquals(-1, index);
    }

    @Test
    public void testInterpolationSearch_found() {
        Integer[] array = {10, 20, 30, 40, 50, 60};
        int index = InterpolationSearch.interpolationSearch(array, 30, 0, array.length - 1);
        Assertions.assertEquals(2, index); // 30 is at index 2
    }

    @Test
    public void testInterpolationSearch_notFound() {
        Integer[] array = {10, 20, 30, 40, 50, 60};
        int index = InterpolationSearch.interpolationSearch(array, 35, 0, array.length - 1);
        Assertions.assertEquals(-1, index);
    }

    // ----------------------------------------- PERFORMANCE TESTS -----------------------------------------

    @Test
    public void performance_sequentialSearch() {
        for (int i = 0; i < searchArray.length; i++) {
            Double element = searchArray[i];
            Assertions.assertEquals(i, SequentialSearch.sequentialSearch(searchArray, element));
        }
    }

    @Test
    public void performance_sequentialSentinelSearch() {
        for (int i = 0; i < searchArray.length; i++) {
            Double element = searchArray[i];
            Assertions.assertEquals(i, SequentialSearch.sequentialSentinelSearch(searchArray, element));
        }
    }

    @Test
    public void performance_binarySearch() {
        for (int i = 0; i < sortedSearchArray.length; i++) {
            Double element = sortedSearchArray[i];
            Assertions.assertEquals(i, BinarySearch.recursiveBinarySearch(sortedSearchArray, element, 0, sortedSearchArray.length - 1));
        }
    }

    @Test
    public void performance_interpolationSearch() {
        for (int i = 0; i < sortedSearchArray.length; i++) {
            Double element = sortedSearchArray[i];
            Assertions.assertEquals(i, InterpolationSearch.interpolationSearch(sortedSearchArray, element, 0, sortedSearchArray.length - 1));
        }
    }
}
