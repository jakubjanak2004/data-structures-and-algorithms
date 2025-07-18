package dataStructures.table;

import dsa.dataStructures.set.sortedSet.TreeSet;
import dsa.dataStructures.table.Table;
import dsa.dataStructures.table.TreeTable;
import dsa.dataStructures.tree.Tree;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.TestHelpers.getInstantiatedChildrenForClass;

public class TreeTableTest {
    public static Stream<Arguments> loadAllTreeChildren() {
        return getInstantiatedChildrenForClass("dsa.dataStructures.tree", Tree.class);
    }

    @ParameterizedTest(name = "testPutAndContains with TreeTable with: {1}")
    @MethodSource("loadAllTreeChildren")
    void testPutAndContains(Tree<TreeTable.TreePair<String, Integer>> tree, String insertedHashSetName) {
        Table<String, Integer> table = new TreeTable<>(
                new TreeSet<>(tree)
        );
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
    @MethodSource("loadAllTreeChildren")
    void testGetExistingKey(Tree<TreeTable.TreePair<String, Integer>> tree, String insertedHashSetName) {
        Table<String, Integer> table = new TreeTable<>(
                new TreeSet<>(tree)
        );
        table.put("dog", 5);
        table.put("cat", 7);

        assertEquals(5, table.getValue("dog"));
        assertEquals(7, table.getValue("cat"));
    }

    @ParameterizedTest(name = "testGetNonExistingKey with hashSet: {1}")
    @MethodSource("loadAllTreeChildren")
    void testGetNonExistingKey(Tree<TreeTable.TreePair<String, Integer>> tree, String insertedHashSetName) {
        Table<String, Integer> table = new TreeTable<>(
                new TreeSet<>(tree)
        );

        table.put("house", 10);

        assertNull(table.getValue("tree"));
        assertNull(table.getValue("car"));
    }

    @ParameterizedTest(name = "testOverwriteValue with hashSet: {1}")
    @MethodSource("loadAllTreeChildren")
    void testOverwriteValue(Tree<TreeTable.TreePair<String, Integer>> tree, String insertedHashSetName) {
        Table<String, Integer> table = new TreeTable<>(
                new TreeSet<>(tree)
        );

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
    @MethodSource("loadAllTreeChildren")
    void testRemoveExistingKey(Tree<TreeTable.TreePair<String, Integer>> tree, String insertedHashSetName) {
        Table<String, Integer> table = new TreeTable<>(
                new TreeSet<>(tree)
        );

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
    @MethodSource("loadAllTreeChildren")
    void testRemoveNonExistingKey(Tree<TreeTable.TreePair<String, Integer>> tree, String insertedHashSetName) {
        Table<String, Integer> table = new TreeTable<>(
                new TreeSet<>(tree)
        );

        table.put("existing", 123);

        assertTrue(table.containsKey("existing"));
        assertEquals(1, table.size());

        table.remove("nonexistent"); // Should not crash or change anything

        assertTrue(table.containsKey("existing"));
        assertEquals(1, table.size());
    }

    @ParameterizedTest(name = "testMultipleOperations with hashSet: {1}")
    @MethodSource("loadAllTreeChildren")
    void testMultipleOperations(Tree<TreeTable.TreePair<String, Integer>> tree, String insertedHashSetName) {
        Table<String, Integer> table = new TreeTable<>(
                new TreeSet<>(tree)
        );

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
    @MethodSource("loadAllTreeChildren")
    void testPerformanceForBigInsertions(Tree<TreeTable.TreePair<Integer, Integer>> tree, String insertedHashSetName) {
        Table<Integer, Integer> table = new TreeTable<>(
                new TreeSet<>(tree)
        );
        int maximalElement = 10_000;
        for (int i = 0; i < maximalElement; i++) {
            table.put(i, i+1);
        }

        assertEquals(maximalElement, table.size());
        for (int i = 0; i < maximalElement; i++) {
            assertEquals(i+1, table.getValue(i));
        }
    }
}
