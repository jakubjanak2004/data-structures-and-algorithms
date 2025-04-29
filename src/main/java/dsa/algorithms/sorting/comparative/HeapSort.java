package dsa.algorithms.sorting.comparative;

import static dsa.algorithms.Utils.swap;

public class HeapSort {
    /**
     * <h1>Left Child Index</h1>
     * <p>
     * Calculates the index of the left child of a given node index in a binary heap.
     *
     * @param i the index of the parent node
     * @return the index of the left child
     */
    static int left(int i) {
        return 2 * i + 1;
    }

    /**
     * <h1>Right Child Index</h1>
     * <p>
     * Calculates the index of the right child of a given node index in a binary heap.
     *
     * @param i the index of the parent node
     * @return the index of the right child
     */
    static int right(int i) {
        return 2 * i + 2;
    }

    /**
     * <h1>Parent Index</h1>
     * <p>
     * Calculates the index of the parent of a given node index in a binary heap.
     *
     * @param i the index of the child node
     * @return the index of the parent node
     */
    static int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * <h1>Max Heapify Method</h1>
     * <p>
     * Ensures the max-heap property for a subtree rooted at the given start index within the array.
     * If the subtree violates the max-heap condition, elements are swapped accordingly and the method
     * is called recursively to fix the affected subtree.
     *
     * @param array the array representing the heap
     * @param start the index of the root of the subtree
     * @param end   the last index of the valid heap segment within the array
     */
    static <T extends Comparable<T>> void maxHeapify(T[] array, int start, int end) {
        // setting left, right index variables
        int l = left(start);
        int r = right(start);
        // setting the largest variable to the start of the array
        int largest = start;

        // determining the largest of the i, left(start) and right(start)
        // left(start) is bigger than array[start]
        if (l <= end && array[l].compareTo(array[start]) > 0) {
            largest = l;
        }
        // right(start) is bigger than array[start]
        if (r <= end && array[r].compareTo(array[largest]) > 0) {
            largest = r;
        }
        // if start isn't the biggest of the tree we swap it with the largest
        // and call recursively max-heapify on the largest element
        if (largest != start) {
            swap(array, largest, start);
            maxHeapify(array, largest, end);
        }
    }

    /**
     * <h1>Build Max Heap method</h1>
     * <p>
     * method builds max-heap from given array.
     *
     * @param array elements are swapped to ensure max-heap property
     */
    static <T extends Comparable<T>> void buildMaxHeap(T[] array) {
        // set n to the length of the given array
        int n = array.length;

        // iterate from floor((n-1) / 2) to 0
        // note when indexing from 1 the calculation of the starting index of iteration is floor(n/2)
        for (int i = (n - 1) / 2; i >= 0; i--) {
            // calling max-heapify on the iterated element and ending at the last element of the array
            maxHeapify(array, i, n - 1);
        }
    }

    /**
     * <h1>Heap sort method</h1>
     * <p>
     * heap sort method
     *
     * @param array array to be sorted in place
     */
    static public <T extends Comparable<T>> void heapSort(T[] array) {
        // build max-heap
        buildMaxHeap(array);

        // iterate form the last element of the heap array to the second element of the array
        for (int i = array.length - 1; i >= 1; i--) {
            // swapping first element and the iterated element
            swap(array, 0, i);
            // calling max-heapify on the [a1, a2, ..., a(iterated - 1)] sub array
            maxHeapify(array, 0, i - 1);
        }
    }
}
