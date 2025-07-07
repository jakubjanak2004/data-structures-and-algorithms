package dataStructures.set;

import dsa.dataStructures.set.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.TestHelpers.getInstantiatedChildrenForClass;

public class SetTest {
    private static Random random = new Random();
    private static List<Double> randomList = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        int numberOfElements = 20000; // use 50 000 to see the big difference
        int maxValue = 1000;

        for (int i = 0; i < numberOfElements; i++) {
            randomList.add(random.nextDouble(maxValue));
        }
    }

    public static Stream<Arguments> loadAllHashTableChildren() {
        return getInstantiatedChildrenForClass("dsa.dataStructures.set", Set.class);
    }

    @ParameterizedTest(name = "testEmptySet on: {1}")
    @MethodSource("loadAllHashTableChildren")
    public void testEmptySet(Set<Double> set, String testedClassName) {
        Assertions.assertEquals(0, set.size());
        Assertions.assertNull(set.get(0.5));
        Assertions.assertFalse(set.contains(1.5));
        set.remove(2.5);
    }

    @ParameterizedTest(name = "testAddAndContains on: {1}")
    @MethodSource("loadAllHashTableChildren")
    public void testAddAndContains(Set<Double> set, String testedClassName) {
        assertEquals(0, set.size());
        assertFalse(set.contains(5.5));

        set.add(5.5);
        assertTrue(set.contains(5.5));
        assertEquals(1, set.size());

        set.add(10.5);
        assertTrue(set.contains(10.5));
        assertEquals(2, set.size());

        set.add(5.5); // duplicate add
        assertTrue(set.contains(5.5));
        assertEquals(2, set.size());
    }

    @ParameterizedTest(name = "testRemove on: {1}")
    @MethodSource("loadAllHashTableChildren")
    public void testRemove(Set<Double> set, String testedClassName) {
        set.add(42.5);
        assertTrue(set.contains(42.5));
        assertEquals(1, set.size());

        set.remove(42.5);
        assertFalse(set.contains(42.5));
        assertEquals(0, set.size());

        set.remove(99.5);
        assertFalse(set.contains(99.5));
        assertEquals(0, set.size());
    }

    @ParameterizedTest(name = "testNegativeHashCodes on: {1}")
    @MethodSource("loadAllHashTableChildren")
    public void testNegativeHashCodes(Set<Double> set, String testedClassName) {
        set.add(-123D);
        assertTrue(set.contains(-123D));

        set.remove(-123D);
        assertFalse(set.contains(-123D));
    }

    /**
     * Performance test for all Set children
     * @param set
     * @param testedClassName
     */
    @ParameterizedTest(name = "testPerformance on: {1}")
    @MethodSource("loadAllHashTableChildren")
    public void testPerformance(Set<Double> set, String testedClassName) {
        for (Double value : randomList) {
            set.add(value);
        }
        assertEquals(randomList.size(), set.size());

        for (Double value : randomList) {
            assertTrue(set.contains(value));
        }
    }
}
