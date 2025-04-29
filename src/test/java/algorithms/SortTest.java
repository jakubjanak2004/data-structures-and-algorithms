package algorithms;

import dsa.algorithms.sorting.comparative.HeapSort;
import dsa.algorithms.sorting.comparative.InsertionSort;
import dsa.algorithms.sorting.comparative.MergeSort;
import dsa.algorithms.sorting.comparative.QuickSort;
import dsa.algorithms.sorting.linear.CountingSort;
import dsa.algorithms.sorting.linear.RadixSort;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public class SortTest {
    private static int min = 0;
    private static int max = 10;
    private static int numberOfElements = 1000;

    private static int[] getIntArray(){
        Random random = new Random();
        return random.ints(numberOfElements, min, max+1).toArray();
    }

    public static Integer[] getArray() {
        int[] array = getIntArray();
        Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i]; // auto-boxing
        }
        return result;
    }

    @Test
    public void testRadix() {
        int[] original = getIntArray();

        int[] expected = original.clone();
        int[] actual = original.clone();

        // sort using native sorting
        Arrays.sort(expected);

        // sort using my sort
        RadixSort.radixSort(actual, max, 0, actual.length);

        // assert the results
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testCounting() {
        int[] original = getIntArray();

        int[] expected = original.clone();
        int[] actual = original.clone();

        // sort using native sorting
        Arrays.sort(expected);

        // sort using my sort
        CountingSort.countingSort(actual, max);

        // assert the results
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testInsertion() {
        Integer[] original = getArray();

        Integer[] expected = original.clone();
        Integer[] actual = original.clone();

        // sort using native sorting
        Arrays.sort(expected);

        // sort using my sort
        InsertionSort.insertionSort(actual);

        // assert the results
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMerge() {
        Integer[] original = getArray();

        Integer[] expected = original.clone();
        Integer[] actual = original.clone();

        // sort using native sorting
        Arrays.sort(expected);

        // sort using my sort
        actual = MergeSort.mergeSort(actual);

        // assert the results
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testHeap() {
        Integer[] original = getArray();

        Integer[] expected = original.clone();
        Integer[] actual = original.clone();

        // sort using native sorting
        Arrays.sort(expected);

        // sort using my sort
        HeapSort.heapSort(actual);

        // assert the results
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testRandomizedQuickWithLomuto() {
        Integer[] original = getArray();

        Integer[] expected = original.clone();
        Integer[] actual = original.clone();

        // sort using native sorting
        Arrays.sort(expected);

        // sort using my sort
        QuickSort.randomizedQuickSortLomuto(actual, 0, actual.length - 1);

        // assert the results
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testQuickWithLomuto() {
        Integer[] original = getArray();

        Integer[] expected = original.clone();
        Integer[] actual = original.clone();

        // sort using native sorting
        Arrays.sort(expected);

        // sort using my sort
        QuickSort.quickSortLomuto(actual, 0, actual.length - 1);

        // assert the results
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testQuickWithHoare() {
        Integer[] original = getArray();

        Integer[] expected = original.clone();
        Integer[] actual = original.clone();

        // sort using native sorting
        Arrays.sort(expected);

        // sort using my sort
        QuickSort.quickSortHoare(actual, 0, actual.length - 1);

        // assert the results
        assertArrayEquals(expected, actual);
    }
}
