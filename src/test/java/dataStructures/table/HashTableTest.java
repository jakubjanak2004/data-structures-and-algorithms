package dataStructures.table;

import dsa.dataStructures.set.hashSet.HashSet;
import dsa.dataStructures.set.sortedSet.TreeSet;
import dsa.dataStructures.table.HashTable;
import dsa.dataStructures.table.Table;
import dsa.dataStructures.table.TreeTable;
import dsa.dataStructures.tree.Tree;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static utils.TestHelpers.findAllNonAbstractChildrenForClass;
import static utils.TestHelpers.getInstantiatedChildrenForClass;

class HashTableTest {
    static Reflections reflections = new Reflections("dsa.dataStructures");
    public static Stream<Arguments> loadAllHashSetChildren() {
        return getInstantiatedChildrenForClass("dsa.dataStructures.set", HashSet.class);
    }

    @ParameterizedTest(name = "testPutAndContains with hashSet: {1}")
    @MethodSource("loadAllHashSetChildren")
    void testPutAndContains(HashSet<Table.Pair<String, Integer>> hashSet, String insertedHashSetName) {
        HashTable<String, Integer> table = new HashTable<>(hashSet);
        assertFalse(table.containsKey("apple"));
        assertEquals(0, table.size());

        table.put("apple", 1);
        assertTrue(table.containsKey("apple"));
        assertEquals(1, table.size());

        table.put("banana", 2);
        assertTrue(table.containsKey("banana"));
        assertEquals(2, table.size());

        assertFalse(table.containsKey("cherry"));
    }

    @ParameterizedTest(name = "testGetExistingKey with hashSet: {1}")
    @MethodSource("loadAllHashSetChildren")
    void testGetExistingKey(HashSet<Table.Pair<String, Integer>> hashSet, String insertedHashSetName) {
        HashTable<String, Integer> table = new HashTable<>(hashSet);
        table.put("dog", 5);
        table.put("cat", 7);

        assertEquals(5, table.getValue("dog"));
        assertEquals(7, table.getValue("cat"));
    }

    @ParameterizedTest(name = "testGetNonExistingKey with hashSet: {1}")
    @MethodSource("loadAllHashSetChildren")
    void testGetNonExistingKey(HashSet<Table.Pair<String, Integer>> hashSet, String insertedHashSetName) {
        HashTable<String, Integer> table = new HashTable<>(hashSet);

        table.put("house", 10);

        assertNull(table.getValue("tree"));
        assertNull(table.getValue("car"));
    }

    @ParameterizedTest(name = "testOverwriteValue with hashSet: {1}")
    @MethodSource("loadAllHashSetChildren")
    void testOverwriteValue(HashSet<Table.Pair<String, Integer>> hashSet, String insertedHashSetName) {
        HashTable<String, Integer> table = new HashTable<>(hashSet);

        table.put("key", 10);
        assertTrue(table.containsKey("key"));
        assertEquals(10, table.getValue("key"));
        assertEquals(1, table.size());

        table.put("key", 20); // Overwrite old value
        assertTrue(table.containsKey("key"));
        assertEquals(20, table.getValue("key")); // New value must be returned
        assertEquals(1, table.size()); // Size must NOT increase
    }

    @ParameterizedTest(name = "testRemoveExistingKey with hashSet: {1}")
    @MethodSource("loadAllHashSetChildren")
    void testRemoveExistingKey(HashSet<Table.Pair<String, Integer>> hashSet, String insertedHashSetName) {
        HashTable<String, Integer> table = new HashTable<>(hashSet);

        table.put("one", 1);
        table.put("two", 2);

        assertTrue(table.containsKey("one"));
        assertTrue(table.containsKey("two"));
        assertEquals(2, table.size());

        table.remove("one");

        assertFalse(table.containsKey("one"));
        assertTrue(table.containsKey("two"));
        assertEquals(1, table.size());
    }

    @ParameterizedTest(name = "testRemoveNonExistingKey with hashSet: {1}")
    @MethodSource("loadAllHashSetChildren")
    void testRemoveNonExistingKey(HashSet<Table.Pair<String, Integer>> hashSet, String insertedHashSetName) {
        HashTable<String, Integer> table = new HashTable<>(hashSet);

        table.put("existing", 123);

        assertTrue(table.containsKey("existing"));
        assertEquals(1, table.size());

        table.remove("nonexistent"); // Should not crash or change anything

        assertTrue(table.containsKey("existing"));
        assertEquals(1, table.size());
    }

    @ParameterizedTest(name = "testMultipleOperations with hashSet: {1}")
    @MethodSource("loadAllHashSetChildren")
    void testMultipleOperations(HashSet<Table.Pair<String, Integer>> hashSet, String insertedHashSetName) {
        HashTable<String, Integer> table = new HashTable<>(hashSet);

        table.put("a", 1);
        table.put("b", 2);
        table.put("c", 3);

        // check size
        assertEquals(3, table.size());
        // check that all keys are contained
        assertTrue(table.containsKey("a"));
        assertTrue(table.containsKey("b"));
        assertTrue(table.containsKey("c"));

        // remove one key
        table.remove("b");
        assertFalse(table.containsKey("b"));
        assertEquals(2, table.size());
        // add different key
        table.put("d", 4);
        assertTrue(table.containsKey("d"));
        assertEquals(3, table.size());
    }

    @ParameterizedTest(name = "testBigInsertions with hashSet: {1}")
    @MethodSource("loadAllHashSetChildren")
    void testPerformanceForBigInsertions(HashSet<Table.Pair<Integer, Integer>> hashSet, String insertedHashSetName) {
        HashTable<Integer, Integer> table = new HashTable<>(hashSet);
        int maximalElement = 1000000;
        for (int i = 0; i < maximalElement; i++) {
            table.put(i, i+1);
        }

        assertEquals(maximalElement, table.size());
        for (int i = 0; i < maximalElement; i++) {
            assertEquals(i+1, table.getValue(i));
        }
    }
}
