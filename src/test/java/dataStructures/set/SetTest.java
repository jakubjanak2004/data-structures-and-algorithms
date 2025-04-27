package dataStructures.set;

import dsa.dataStructures.set.Set;
import dsa.dataStructures.set.hashSet.HashSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static dataStructures.TestHelpers.getInstantiatedChildrenForClass;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetTest {
    public static Stream<Arguments> loadAllHashTableChildren() {
        return getInstantiatedChildrenForClass("dsa.dataStructures.set", Set.class);
    }

    @ParameterizedTest(name = "testEmptySet on: {1}")
    @MethodSource("loadAllHashTableChildren")
    public void testEmptySet(Set<Double> doubleHashSet, String testedClassName) {
        Assertions.assertEquals(0, doubleHashSet.size());
        Assertions.assertNull(doubleHashSet.get(0.5));
        Assertions.assertFalse(doubleHashSet.contains(1.5));
        doubleHashSet.remove(2.5);
    }

    @ParameterizedTest(name = "testAddAndContains on: {1}")
    @MethodSource("loadAllHashTableChildren")
    public void testAddAndContains(Set<Double> doubleHashSet, String testedClassName) {
        assertEquals(0, doubleHashSet.size());
        assertFalse(doubleHashSet.contains(5.5));

        doubleHashSet.add(5.5);
        assertTrue(doubleHashSet.contains(5.5));
        assertEquals(1, doubleHashSet.size());

        doubleHashSet.add(10.5);
        assertTrue(doubleHashSet.contains(10.5));
        assertEquals(2, doubleHashSet.size());

        doubleHashSet.add(5.5); // duplicate add
        assertTrue(doubleHashSet.contains(5.5));
        assertEquals(2, doubleHashSet.size());
    }

    @ParameterizedTest(name = "testRemove on: {1}")
    @MethodSource("loadAllHashTableChildren")
    public void testRemove(Set<Double> doubleHashSet, String testedClassName) {
        doubleHashSet.add(42.5);
        assertTrue(doubleHashSet.contains(42.5));
        assertEquals(1, doubleHashSet.size());

        doubleHashSet.remove(42.5);
        assertFalse(doubleHashSet.contains(42.5));
        assertEquals(0, doubleHashSet.size());

        doubleHashSet.remove(99.5);
        assertFalse(doubleHashSet.contains(99.5));
        assertEquals(0, doubleHashSet.size());
    }

    @ParameterizedTest(name = "testMultipleElements on: {1}")
    @MethodSource("loadAllHashTableChildren")
    public void testMultipleElements(Set<Double> doubleHashSet, String testedClassName) {
        int maximalElement = 20000;
        for (double i = 1; i <= maximalElement; i++) {
            doubleHashSet.add(i);
        }

        for (double i = 1; i <= maximalElement; i++) {
            assertTrue(doubleHashSet.contains(i));
        }

        doubleHashSet.remove(50D);
        assertFalse(doubleHashSet.contains(50D));
        assertEquals(maximalElement - 1, doubleHashSet.size());
    }

    @ParameterizedTest(name = "testNegativeHashCodes on: {1}")
    @MethodSource("loadAllHashTableChildren")
    public void testNegativeHashCodes(Set<Double> doubleHashSet, String testedClassName) {
        doubleHashSet.add(-123D);
        assertTrue(doubleHashSet.contains(-123D));

        doubleHashSet.remove(-123D);
        assertFalse(doubleHashSet.contains(-123D));
    }
}
