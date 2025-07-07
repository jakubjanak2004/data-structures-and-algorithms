package dsa.algorithms.sorting.comparative.parallel;

import dsa.algorithms.sorting.SortingAlgo;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import static dsa.algorithms.Utils.swap;

public class BitonicSort {
    private static final int MIN_ARRAY_SIZE = 16;

    private static <T extends Comparable<T>> void bitonicMerge(T[] arr, int left, int count, boolean direction) {
        if (count <= 1) return;
        int k = count / 2;
        for (int i = left; i < left + k; i++) {
            if (direction == arr[i].compareTo(arr[i + k]) > 0) {
                swap(arr, i, i + k);
            }
        }
        bitonicMerge(arr, left, k, direction);
        bitonicMerge(arr, left + k, k, direction);
    }

    public static <T extends Comparable<T>> void bitonicSort(T[] arr, int left, int count, boolean direction) {
        if (count <= 1) return;
        int k = count / 2;
        bitonicSort(arr, left, k, true);
        bitonicSort(arr, left + k, k, false);
        bitonicMerge(arr, left, count, direction);
    }

    @SortingAlgo
    public static <T extends Comparable<T>> void bitonicSort(T[] arr) {
        if (Integer.bitCount(arr.length) != 1) {
            throw new IllegalArgumentException("The array length must be an exponent of 2");
        }
        bitonicSort(arr, 0, arr.length, true);
    }

    @SortingAlgo
    public static <T extends Comparable<T>> void parallelBitonicSort(T[] arr) {
        if (Integer.bitCount(arr.length) != 1) {
            throw new IllegalArgumentException("The array length must be an exponent of 2");
        }
        try (ForkJoinPool pool = ForkJoinPool.commonPool()) {
            pool.invoke(new BitonicSortTask<>(arr, 0, arr.length, true));
        }
    }

    private static class BitonicSortTask<T extends Comparable<T>> extends RecursiveAction {
        private final T[] arr;
        private final int left;
        private final int count;
        private final boolean direction;

        public BitonicSortTask(T[] arr, int left, int count, boolean direction) {
            this.arr = arr;
            this.left = left;
            this.count = count;
            this.direction = direction;
        }

        @Override
        protected void compute() {
            if (count <= 1) return;

            int k = count / 2;
            if (count <= MIN_ARRAY_SIZE) {
                // sequential sorting
                bitonicSort(arr, left, k, true);
                bitonicSort(arr, left + k, k, false);
                bitonicMerge(arr, left, count, direction);
            } else {
                // fork sorting
                BitonicSortTask<T> task1 = new BitonicSortTask<>(arr, left, k, true);
                BitonicSortTask<T> task2 = new BitonicSortTask<>(arr, left + k, k, false);
                invokeAll(task1, task2);
                invokeAll(new BitonicMergeTask<>(arr, left, count, direction));
            }
        }
    }

    private static class BitonicMergeTask<T extends Comparable<T>> extends RecursiveAction {
        private final T[] arr;
        private final int left;
        private final int count;
        private final boolean direction;

        public BitonicMergeTask(T[] arr, int left, int count, boolean direction) {
            this.arr = arr;
            this.left = left;
            this.count = count;
            this.direction = direction;
        }

        @Override
        protected void compute() {
            if (count <= 1) return;

            int k = count / 2;
            for (int i = left; i < left + k; i++) {
                if ((arr[i].compareTo(arr[i + k]) > 0) == direction) {
                    swap(arr, i, i + k);
                }
            }

            if (count <= MIN_ARRAY_SIZE) {
                bitonicMerge(arr, left, k, direction);
                bitonicMerge(arr, left + k, k, direction);
            } else {
                invokeAll(
                    new BitonicMergeTask<>(arr, left, k, direction),
                    new BitonicMergeTask<>(arr, left + k, k, direction)
                );
            }
        }
    }
}
