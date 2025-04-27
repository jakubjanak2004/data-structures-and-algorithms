package dataStructures.table;

import dsa.dataStructures.set.openAddressingHashSet.LinearProbingHashSet;
import dsa.dataStructures.table.HashTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    private HashTable<String, Integer> table;

    @BeforeEach
    void setUp() {
        table = new HashTable<>(new LinearProbingHashSet<>());
    }

    @Test
    void testPutAndContains() {
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

    @Test
    void testGetExistingKey() {
        table.put("dog", 5);
        table.put("cat", 7);

        assertEquals(5, table.get("dog"));
        assertEquals(7, table.get("cat"));
    }

    @Test
    void testGetNonExistingKey() {
        table.put("house", 10);

        assertNull(table.get("tree"));
        assertNull(table.get("car"));
    }

    @Test
    void testOverwriteValue() {
        table.put("key", 10);
        assertTrue(table.containsKey("key"));
        assertEquals(10, table.get("key"));
        assertEquals(1, table.size());

        table.put("key", 20); // Overwrite old value
        assertTrue(table.containsKey("key"));
        assertEquals(20, table.get("key")); // New value must be returned
        assertEquals(1, table.size()); // Size must NOT increase
    }

    @Test
    void testRemoveExistingKey() {
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

    @Test
    void testRemoveNonExistingKey() {
        table.put("existing", 123);

        assertTrue(table.containsKey("existing"));
        assertEquals(1, table.size());

        table.remove("nonexistent"); // Should not crash or change anything

        assertTrue(table.containsKey("existing"));
        assertEquals(1, table.size());
    }

    @Test
    void testMultipleOperations() {
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
