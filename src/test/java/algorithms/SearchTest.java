package algorithms;

import dsa.algorithms.search.BinarySearch;
import dsa.algorithms.search.InterpolationSearch;
import dsa.algorithms.search.SearchAlgo;
import dsa.algorithms.search.SequentialSearch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class SearchTest {
    private static final int numberOfValues = 1000_000;
    private static final int NUMBER_OF_INSTANCES = 10;
    static Integer[] searchArray;
    static Integer[] sortedSearchArray;
    Reflections reflections = new Reflections("dsa.algorithms.search", new MethodAnnotationsScanner());

    @BeforeAll
    static void initArrays() {
        // Create sorted array
        sortedSearchArray = new Integer[numberOfValues];
        for (int i = 0; i < numberOfValues; i++) {
            sortedSearchArray[i] = i;
        }

        // Shuffle and convert back to array
        List<Integer> shuffled = new ArrayList<>(Arrays.asList(sortedSearchArray));
        Collections.shuffle(shuffled);
        searchArray = shuffled.toArray(new Integer[0]);
    }

    @TestFactory
    Stream<DynamicTest> testSearchAlgorithms() {
        return reflections.getMethodsAnnotatedWith(SearchAlgo.class).stream()
                .flatMap(this::testSearchFindsElementThatIsPresent);
    }

    private Stream<DynamicTest> testSearchFindsElementThatIsPresent(Method method) {
        int step = sortedSearchArray.length / NUMBER_OF_INSTANCES;

        return IntStream.range(0, NUMBER_OF_INSTANCES)
                .map(i -> i * step)
                .mapToObj(i -> {
                    int element = sortedSearchArray[i];

                    return DynamicTest.dynamicTest(
                            "Searching using method " + method.getName() + " in array of length " + sortedSearchArray.length + " at index " + i,
                            () -> {
                                try {
                                    Object result = method.invoke(null, sortedSearchArray.clone(), element);
                                    Assertions.assertEquals(i, result);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    );
                });
    }

    @Test
    void sequentialSearchReturnsMinus1WhenElementNotPresent() {
        Integer[] array = new Integer[]{1, 2, 3};
        Assertions.assertEquals(-1, SequentialSearch.sequentialSearch(array, 4));
    }

    @Test
    void sequentialSentinelSearchReturnsMinus1WhenElementNotPresent() {
        Integer[] array = new Integer[]{1, 2, 3};
        Assertions.assertEquals(-1, SequentialSearch.sequentialSentinelSearch(array, 4));
    }

    @Test
    void InterpolationSearchReturnsMinus1WhenElementNotPresent() {
        Integer[] array = new Integer[]{1, 2, 3};
        Assertions.assertEquals(-1, InterpolationSearch.interpolationSearch(array, 4));
    }

    @Test
    void recursiveBinarySearchReturnsOptimalPlaceForElementIfNotPresent() {
        Integer[] array = new Integer[]{1, 2, 4};
        Assertions.assertEquals(2, BinarySearch.recursiveBinarySearch(array, 3));
    }

    @Test
    void iterativeBinarySearchReturnsOptimalPlaceForElementIfNotPresent() {
        Integer[] array = new Integer[]{1, 2, 4};
        Assertions.assertEquals(2, BinarySearch.iterativeBinarySearch(array, 3));
    }
}
