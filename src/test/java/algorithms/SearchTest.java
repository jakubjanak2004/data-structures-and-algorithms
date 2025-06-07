package algorithms;

import dsa.algorithms.search.SearchAlgo;
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

// todo implement the different search for algorithms- that do not need sorted array
public class SearchTest {
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
                .flatMap(this::searchTest);
    }

    private Stream<DynamicTest> searchTest(Method method) {
        int step = sortedSearchArray.length / NUMBER_OF_INSTANCES;

        return IntStream.range(0, NUMBER_OF_INSTANCES)
                .map(i -> i * step)
                .mapToObj(i -> {
                    int element = sortedSearchArray[i];

                    return DynamicTest.dynamicTest(
                            "Searching using method " + method.getName() + " in array of length " + sortedSearchArray.length + " at index " + i,
                            () -> {
                                try {
                                    // todo, the sentinel search should make its own copy of array instead of changing the global one
                                    Object result = method.invoke(null, sortedSearchArray.clone(), element);
                                    Assertions.assertEquals(i, result);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    );
                });
    }

}
