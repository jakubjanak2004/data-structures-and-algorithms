package dsa.algorithms.search;

import java.util.Objects;

public class SequentialSearch {
    public static <T> int sequentialSearch(T[] array, T element) {
        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(element, array[i])) {
                return i;
            }
        }
        return -1;
    }

    // todo come back to this as this doesnt make sense for me
    public static <T> int sequentialSentinelSearch(T[] array, T element) {
        if (Objects.equals(element, array[array.length - 1])) {
            return array.length - 1;
        }
        array[array.length - 1] = element;
        int index = 0;
        while (true) {
            if (Objects.equals(element, array[index])) {
                return index;
            }
            index++;
        }
    }
}
