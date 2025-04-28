package algorithms;

import dsa.algorithms.search.BinarySearch;
import dsa.algorithms.search.InterpolationSearch;
import dsa.algorithms.search.SequentialSearch;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SearchTest {
    @Test
    public void testSequentialSearch_found() {
        Integer[] array = {1, 2, 3, 4, 5};
        int index = SequentialSearch.sequentialSearch(array, 3);
        assertEquals(2, index); // 3 is at index 2
    }

    @Test
    public void testSequentialSearch_notFound() {
        Integer[] array = {1, 2, 3, 4, 5};
        int index = SequentialSearch.sequentialSearch(array, 6);
        assertEquals(-1, index);
    }

    @Test
    public void testSequentialSentinelSearch_found() {
        Integer[] array = {1, 2, 3, 4, 5, 0}; // extra space for sentinel overwrite
        int index = SequentialSearch.sequentialSentinelSearch(array, 3);
        assertEquals(2, index); // 3 is at index 2
    }

    // todo make sentinel return -1 for missing
    @Test
    public void testSequentialSentinelSearch_notFound() {
        Integer[] array = {1, 2, 3, 4, 5, 0}; // extra space for sentinel overwrite
        int index = SequentialSearch.sequentialSentinelSearch(array, 6);
        assertEquals(-1, index);
    }

    @Test
    public void testRecursiveBinarySearch_found() {
        Integer[] array = {1, 3, 5, 7, 9};
        int index = BinarySearch.recursiveBinarySearch(array, 5, 0, array.length - 1);
        assertEquals(2, index); // 5 is at index 2
    }

    @Test
    public void testRecursiveBinarySearch_notFound() {
        Integer[] array = {1, 3, 5, 7, 9};
        int index = BinarySearch.recursiveBinarySearch(array, 8, 0, array.length - 1);
        assertEquals(-1, index);
    }

    @Test
    public void testIterativeBinarySearch_found() {
        Integer[] array = {1, 3, 5, 7, 9};
        int index = BinarySearch.iterativeBinarySearch(array, 7, 0, array.length - 1);
        assertEquals(3, index); // 7 is at index 3
    }

    @Test
    public void testIterativeBinarySearch_notFound() {
        Integer[] array = {1, 3, 5, 7, 9};
        int index = BinarySearch.iterativeBinarySearch(array, 6, 0, array.length - 1);
        assertEquals(-1, index);
    }

    @Test
    public void testInterpolationSearch_found() {
        Integer[] array = {10, 20, 30, 40, 50, 60};
        int index = InterpolationSearch.interpolationSearch(array, 30, 0, array.length - 1);
        assertEquals(2, index); // 30 is at index 2
    }

    @Test
    public void testInterpolationSearch_notFound() {
        Integer[] array = {10, 20, 30, 40, 50, 60};
        int index = InterpolationSearch.interpolationSearch(array, 35, 0, array.length - 1);
        assertEquals(-1, index);
    }
}
