package dsa.algorithms.sorting.linear;

import java.util.ArrayList;
import java.util.List;

public class BucketSort {
    /**
     * Performs insertion sort on a given list of numbers.
     * Used to sort individual buckets after distribution.
     *
     * @param bucket The list of numbers to be sorted.
     * @param <T>    A numeric type that extends Number.
     */
    public static <T extends Number> void insertionSort(List<T> bucket) {
        int n = bucket.size();

        for (int i = 1; i < n; i++) {
            T key = bucket.get(i);
            int j = i - 1;
            while (j >= 0 && bucket.get(j).doubleValue() > key.doubleValue()) {
                bucket.set(j + 1, bucket.get(j));
                j--;
            }
            bucket.set(j + 1, key);
        }
    }

    /**
     * Sorts an array of numbers using the bucket sort algorithm.
     * Elements are distributed into buckets based on their normalized value relative to max.
     * Each bucket is sorted individually using insertion sort.
     *
     * @param array   The array to be sorted.
     * @param buckets An array of empty ArrayLists to be used as buckets. Each element will be initialized.
     * @param max     The maximum value in the input array, used for normalization.
     * @param <T>     A numeric type that extends Number.
     */
    public static <T extends Number> void bucketSort(T[] array, ArrayList<T>[] buckets, T max) {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (T value : array) {
            int bucketIndex = (int) Math.round(((buckets.length - 1) * (value.doubleValue() / max.doubleValue())));
            buckets[bucketIndex].add(value);
        }

        for (ArrayList<T> ts : buckets) {
            insertionSort(ts);
        }

        int index = 0;
        for (ArrayList<T> bucket : buckets) {
            for (T t : bucket) {
                array[index++] = t;
            }
        }
    }
}
