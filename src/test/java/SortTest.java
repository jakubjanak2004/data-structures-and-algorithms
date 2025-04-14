import dsa.sorting.comparative.HeapSort;
import dsa.sorting.comparative.InsertionSort;
import dsa.sorting.comparative.MergeSort;
import dsa.sorting.comparative.QuickSort;
import dsa.sorting.linear.CountingSort;
import dsa.sorting.linear.RadixSort;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public class SortTest {
    private int min = 0;
    private int max = 1000;
    private int numberOfElements = 10;

    private int[] getArray(){
        Random random = new Random();
        return random.ints(numberOfElements, min, max+1).toArray();
    }

    @Test
    public void testRadix() {
        int[] original = getArray();

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
        int[] original = getArray();

        int[] expected = original.clone();
        int[] actual = original.clone();

        // sort using native sorting
        Arrays.sort(expected);

        // sort using my sort
        CountingSort.countingSort(actual, max, 0, actual.length - 1);

        // assert the results
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testInsertion() {
        int[] original = getArray();

        int[] expected = original.clone();
        int[] actual = original.clone();

        // sort using native sorting
        Arrays.sort(expected);

        // sort using my sort
        InsertionSort.insertionSort(actual);

        // assert the results
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMerge() {
        int[] original = getArray();

        int[] expected = original.clone();
        int[] actual = original.clone();

        // sort using native sorting
        Arrays.sort(expected);

        // sort using my sort
        actual = MergeSort.mergeSort(actual);

        // assert the results
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testHeap() {
        int[] original = getArray();

        int[] expected = original.clone();
        int[] actual = original.clone();

        // sort using native sorting
        Arrays.sort(expected);

        // sort using my sort
        HeapSort.heapSort(actual);

        // assert the results
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testRandomizedQuickWithLomuto() {
        int[] original = getArray();

        int[] expected = original.clone();
        int[] actual = original.clone();

        // sort using native sorting
        Arrays.sort(expected);

        // sort using my sort
        QuickSort.quickSort(actual, 0, actual.length - 1, QuickSort::randomizedLomutoPartition);

        // assert the results
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testQuickWithLomuto() {
        int[] original = getArray();

        int[] expected = original.clone();
        int[] actual = original.clone();

        // sort using native sorting
        Arrays.sort(expected);

        // sort using my sort
        QuickSort.quickSort(actual, 0, actual.length - 1, QuickSort::lomutoPartition);

        // assert the results
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testQuickWithHoare() {
        int[] original = getArray();

        int[] expected = original.clone();
        int[] actual = original.clone();

        // sort using native sorting
        Arrays.sort(expected);

        // sort using my sort
        QuickSort.quickSort(actual, 0, actual.length - 1, QuickSort::hoarePartition);

        // assert the results
        assertArrayEquals(expected, actual);
    }
}
