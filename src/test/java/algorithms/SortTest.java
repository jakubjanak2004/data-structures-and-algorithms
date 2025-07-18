package algorithms;

import dsa.algorithms.sorting.MaxValue;
import dsa.algorithms.sorting.SortingAlgo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static utils.TestHelpers.getIntegerObjectArray;

public class SortTest {
    private static final int MIN_NUM_ELEMENTS = 8;
    private static final int MAX_NUM_ELEMENTS = 1 << 14;
    private static final int ELEMENT_MIN = 1;
    private static final int ELEMENT_MAX = 1000;
    Reflections reflections = new Reflections("dsa.algorithms.sorting", new MethodAnnotationsScanner());
    private final List<Integer[]> sortingProblemsList = new ArrayList<>();

    @BeforeEach
    void setup() {
        for (int i = MIN_NUM_ELEMENTS; i <= MAX_NUM_ELEMENTS; i *= 2) {
            sortingProblemsList.add(getIntegerObjectArray(i, ELEMENT_MIN, ELEMENT_MAX));
        }
    }

    @TestFactory
    Stream<DynamicTest> inPlaceSortTests() {
        return reflections.getMethodsAnnotatedWith(SortingAlgo.class).stream()
                .filter(method -> method.getAnnotation(SortingAlgo.class).inplace())
                .flatMap(this::inPlaceSortTest);
    }

    @TestFactory
    Stream<DynamicTest> notInPlaceSortTests() {
        return reflections.getMethodsAnnotatedWith(SortingAlgo.class).stream()
                .filter(method -> !method.getAnnotation(SortingAlgo.class).inplace())
                .flatMap(this::notInPlaceSortTest);
    }

    private Stream<DynamicTest> inPlaceSortTest(Method method) {
        return sortingProblemsList.stream()
                .map(problem -> dynamicTest("[" + method.getName() + " with array of length " + problem.length + " ]", () -> {
                    Integer[] expected = problem.clone();
                    Integer[] actual = problem.clone();

                    // sort using native sorting
                    Arrays.sort(expected);

                    // sort using my sort
                    try {
                        method.invoke(null, buildArgumentsForMethod(method, actual));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }

                    // assert the results
                    Assertions.assertArrayEquals(expected, actual);
                }));
    }

    private Stream<DynamicTest> notInPlaceSortTest(Method method) {
        return sortingProblemsList.stream()
                .map(problem -> dynamicTest("[" + method.getName() + " with array of length " + problem.length + " ]", () -> {
                    Integer[] expected = problem.clone();
                    Integer[] actual = problem.clone();

                    // sort using native sorting
                    Arrays.sort(expected);

                    // sort using my sort
                    try {
                        actual = (Integer[]) method.invoke(null, buildArgumentsForMethod(method, actual));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }

                    // assert the results
                    Assertions.assertArrayEquals(expected, actual);
                }));
    }

    private Object[] buildArgumentsForMethod(Method method, Object[] actual) {
        Parameter[] parameters = method.getParameters();
        List<Object> args = new ArrayList<>();
        for (Parameter param : parameters) {
            if (param.isAnnotationPresent(MaxValue.class)) {
                args.add(ELEMENT_MAX);
            } else if (param.getType().isArray()) {
                args.add(actual);
            } else {
                throw new IllegalArgumentException("Unsupported parameter: " + param);
            }
        }
        return args.toArray();
    }
}
