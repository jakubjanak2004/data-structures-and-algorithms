package algorithms;

import dsa.algorithms.sorting.comparative.HeapSort;
import dsa.algorithms.sorting.comparative.InsertionSort;
import dsa.algorithms.sorting.comparative.MergeSort;
import dsa.algorithms.sorting.comparative.QuickSort;
import dsa.algorithms.sorting.linear.BucketSort;
import dsa.algorithms.sorting.linear.CountingSort;
import dsa.algorithms.sorting.linear.RadixSort;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public class SortTest {
    private static final int MIN = 0;
    private static final int MAX = 10;
    private static final int NUMBER_OF_ELEMENTS = 1000;

    private static int[] getIntArray() {
        Random random = new Random();
        return random.ints(NUMBER_OF_ELEMENTS, MIN, MAX + 1).toArray();
    }

    public static double[] getDoubleArray() {
        Random random = new Random();
        return random.doubles(NUMBER_OF_ELEMENTS, MIN, MAX).toArray();
    }

    public static Integer[] getIntegerObjectArray() {
        int[] array = getIntArray();
        Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i]; // auto-boxing
        }
        return result;
    }

    public static Double[] getDoubleObjectArray() {
        double[] array = getDoubleArray();
        Double[] result = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
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
        RadixSort.radixSort(actual, MAX, 0, actual.length);

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
        CountingSort.countingSort(actual, MAX);

        // assert the results
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testBucketSort() {
        Double[] original = getDoubleObjectArray();

        Double[] expected = original.clone();
        Double[] actual = original.clone();

        // sort using native sorting
        Arrays.sort(expected);

        // sort using my sort
        BucketSort.bucketSort(actual, new ArrayList[original.length], MAX);

        // assert the results
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testInsertion() {
        Integer[] original = getIntegerObjectArray();

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
        Integer[] original = getIntegerObjectArray();

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
    public void testInPlaceMerge() {
        Integer[] original = getIntegerObjectArray();

        Integer[] expected = original.clone();
        Integer[] actual = original.clone();

        // sort using native sorting
        Arrays.sort(expected);

        // sort using my sort
        MergeSort.inPlaceMergeSort(actual, actual.clone(), 0, actual.length - 1);

        // assert the results
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testHeap() {
        Integer[] original = getIntegerObjectArray();

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
        Integer[] original = getIntegerObjectArray();

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
        Integer[] original = getIntegerObjectArray();

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
        Integer[] original = getIntegerObjectArray();

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
