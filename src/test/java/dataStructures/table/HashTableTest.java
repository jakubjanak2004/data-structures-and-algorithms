package dataStructures.table;

import dsa.dataStructures.set.HashSet;
import dsa.dataStructures.table.HashTable;
import dsa.dataStructures.table.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static dataStructures.TestHelpers.getInstantiatedChildrenForClass;
import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    public static Stream<Arguments> loadAllHashTableChildren() {
        return getInstantiatedChildrenForClass("dsa.dataStructures.set", dsa.dataStructures.set.HashSet.class);
    }

    @ParameterizedTest(name = "testPutAndContains with hashSet: {1}")
    @MethodSource("loadAllHashTableChildren")
    void testPutAndContains(HashSet<Pair<String, Integer>> hashSet, String insertedHashSetName) {
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
    @MethodSource("loadAllHashTableChildren")
    void testGetExistingKey(HashSet<Pair<String, Integer>> hashSet, String insertedHashSetName) {
        HashTable<String, Integer> table = new HashTable<>(hashSet);
        table.put("dog", 5);
        table.put("cat", 7);

        assertEquals(5, table.get("dog"));
        assertEquals(7, table.get("cat"));
    }

    @ParameterizedTest(name = "testGetNonExistingKey with hashSet: {1}")
    @MethodSource("loadAllHashTableChildren")
    void testGetNonExistingKey(HashSet<Pair<String, Integer>> hashSet, String insertedHashSetName) {
        HashTable<String, Integer> table = new HashTable<>(hashSet);

        table.put("house", 10);

        assertNull(table.get("tree"));
        assertNull(table.get("car"));
    }

    @ParameterizedTest(name = "testOverwriteValue with hashSet: {1}")
    @MethodSource("loadAllHashTableChildren")
    void testOverwriteValue(HashSet<Pair<String, Integer>> hashSet, String insertedHashSetName) {
        HashTable<String, Integer> table = new HashTable<>(hashSet);

        table.put("key", 10);
        assertTrue(table.containsKey("key"));
        assertEquals(10, table.get("key"));
        assertEquals(1, table.size());

        table.put("key", 20); // Overwrite old value
        assertTrue(table.containsKey("key"));
        assertEquals(20, table.get("key")); // New value must be returned
        assertEquals(1, table.size()); // Size must NOT increase
    }

    @ParameterizedTest(name = "testRemoveExistingKey with hashSet: {1}")
    @MethodSource("loadAllHashTableChildren")
    void testRemoveExistingKey(HashSet<Pair<String, Integer>> hashSet, String insertedHashSetName) {
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
    @MethodSource("loadAllHashTableChildren")
    void testRemoveNonExistingKey(HashSet<Pair<String, Integer>> hashSet, String insertedHashSetName) {
        HashTable<String, Integer> table = new HashTable<>(hashSet);

        table.put("existing", 123);

        assertTrue(table.containsKey("existing"));
        assertEquals(1, table.size());

        table.remove("nonexistent"); // Should not crash or change anything

        assertTrue(table.containsKey("existing"));
        assertEquals(1, table.size());
    }

    @ParameterizedTest(name = "testMultipleOperations with hashSet: {1}")
    @MethodSource("loadAllHashTableChildren")
    void testMultipleOperations(HashSet<Pair<String, Integer>> hashSet, String insertedHashSetName) {
        HashTable<String, Integer> table = new HashTable<>(hashSet);

        table.put("a", 1);
        table.put("b", 2);
        table.put("c", 3);

        assertEquals(3, table.size());

        assertTrue(table.containsKey("a"));
        assertTrue(table.containsKey("b"));
        assertTrue(table.containsKey("c"));

        table.remove("b");
        assertFalse(table.containsKey("b"));
        assertEquals(2, table.size());

        table.put("d", 4);
        assertTrue(table.containsKey("d"));
        assertEquals(3, table.size());
    }
}
