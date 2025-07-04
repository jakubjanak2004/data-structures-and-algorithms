package dsa.algorithms.search;

public class BinarySearch {
    public static <T extends Comparable<T>> int recursiveBinarySearch(T[] sortedArray, T element, int first, int last) {
        // element not present
        if (first > last) return first;
        int mid = (first + last) / 2;
        int comparison = element.compareTo(sortedArray[mid]);
        if (comparison < 0) return recursiveBinarySearch(sortedArray, element, first, mid - 1);
        if (comparison > 0) return recursiveBinarySearch(sortedArray, element, mid + 1, last);
        // found element
        return mid;
    }

    public static <T extends Comparable<T>> int iterativeBinarySearch(T[] sortedArray, T element, int first, int last) {
        while (first <= last) {
            int mid = (first + last) / 2;
            int comparison = element.compareTo(sortedArray[mid]);
            if (comparison > 0) first = mid + 1;
            else if (comparison < 0) last = mid - 1;
                // found element
            else return mid;
        }
        // element not present
        return first;
    }

    @SearchAlgo
    public static <T extends Comparable<T>> int iterativeBinarySearch(T[] sortedArray, T element) {
        return iterativeBinarySearch(sortedArray, element, 0, sortedArray.length - 1);
    }

    @SearchAlgo
    public static <T extends Comparable<T>> int recursiveBinarySearch(T[] sortedArray, T element) {
        return recursiveBinarySearch(sortedArray, element, 0, sortedArray.length - 1);
    }
}
