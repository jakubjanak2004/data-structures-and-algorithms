package dsa.algorithms.search;

public class InterpolationSearch {
    public static <T extends Number> int interpolationSearch(T[] array, T element, int first, int last) {
        // element not present
        if (first > last) return -1;
        int mid = (int) Math.round((first + (element.doubleValue() - array[first].doubleValue()) * (last - first) / (array[last].doubleValue() - array[first].doubleValue())));
        // if mid out of range return not found
        if (mid < first || mid > last) return -1;
        if (array[mid].doubleValue() < element.doubleValue()) return interpolationSearch(array, element, mid + 1, last);
        if (array[mid].doubleValue() > element.doubleValue()) return interpolationSearch(array, element, first, mid - 1);
        // found element
        return mid;
    }
}
