package dsa.algorithms.sorting.comparative.parallel;

import dsa.algorithms.sorting.SortingAlgo;
import dsa.algorithms.sorting.comparative.InsertionSort;
import dsa.algorithms.sorting.comparative.MergeSort;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class ParallelMergeSort {
    public static class MergeSortTask<T extends Comparable<T>> extends RecursiveAction {
        private final T[] array;
        private final T[] aux;
        private final int low;
        private final int high;

        public MergeSortTask(T[] array, T[] aux, int low, int high) {
            this.array = array;
            this.aux = aux;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (high - low <= MergeSort.MIN_ARRAY_SIZE) {
                InsertionSort.binarySort(array, low, high);
                return;
            }

            int mid = low + (high - low) / 2;
            MergeSortTask<T> left = new MergeSortTask<>(array, aux, low, mid);
            MergeSortTask<T> right = new MergeSortTask<>(array, aux, mid + 1, high);

            // fork and join left and right merge-sort
            invokeAll(left, right);

            MergeSort.inPlaceMerge(array, aux, low, high);
            System.arraycopy(aux, low, array, low, high - low + 1);
        }
    }

    @SortingAlgo
    public static <T extends Comparable<T>> void parallelMergeSort(T[] array) {
        T[] aux = Arrays.copyOf(array, array.length);
        try (ForkJoinPool pool = ForkJoinPool.commonPool()) {
            pool.invoke(new MergeSortTask<>(array, aux, 0, array.length - 1));
        }
    }
}
