package dataStructures.list;

import dsa.dataStructures.list.List;
import dsa.dataStructures.list.circularLinkedList.CircularDoublyLinkedList;
import dsa.dataStructures.list.LinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static dataStructures.TestHelpers.getInstantiatedChildrenForClass;

public class ListTest {

    public static Stream<Arguments> loadAllLinkedListChildren() {
        return getInstantiatedChildrenForClass("dsa.dataStructures.list", List.class);
    }

    @ParameterizedTest(name = "testEmptyList on: {1}")
    @MethodSource("loadAllLinkedListChildren")
    void testEmptyList(LinkedList<Integer> linkedList, String testedClassName) {
        Assertions.assertTrue(linkedList.empty(), linkedList.getClass().getSimpleName());
        Assertions.assertEquals(0, linkedList.size(), linkedList.getClass().getSimpleName());
        Assertions.assertNull(linkedList.read(), linkedList.getClass().getSimpleName());
    }

    @ParameterizedTest(name = "testInsertFirstElement on: {1}")
    @MethodSource("loadAllLinkedListChildren")
    void testInsertFirstElement(LinkedList<Integer> linkedList, String testedClassName) {
        linkedList.insert(10);
        Assertions.assertFalse(linkedList.empty());
        Assertions.assertEquals(1, linkedList.size(), linkedList.getClass().getSimpleName());
        Assertions.assertEquals(10, (int) linkedList.first(), linkedList.getClass().getSimpleName());
    }

    @ParameterizedTest(name = "testInsertMultipleElements on: {1}")
    @MethodSource("loadAllLinkedListChildren")
    void testInsertMultipleElements(LinkedList<Integer> linkedList, String testedClassName) {
        linkedList.insert(1);
        linkedList.insert(2);
        linkedList.insert(3);

        Assertions.assertEquals(3, linkedList.size(), linkedList.getClass().getSimpleName());

        linkedList.first();
        Assertions.assertEquals(1, (int) linkedList.read(), linkedList.getClass().getSimpleName());

        linkedList.next();
        Assertions.assertEquals(2, (int) linkedList.read(), linkedList.getClass().getSimpleName());

        linkedList.next();
        Assertions.assertEquals(3, (int) linkedList.read(), linkedList.getClass().getSimpleName());

        linkedList.next();

        if (linkedList instanceof CircularDoublyLinkedList) {
            Assertions.assertEquals(1, (int) linkedList.read(), linkedList.getClass().getSimpleName());
        } else {
            Assertions.assertNull(linkedList.read(), linkedList.getClass().getSimpleName());
        }
    }

    @ParameterizedTest(name = "testDeleteMiddleElement on: {1}")
    @MethodSource("loadAllLinkedListChildren")
    void testDeleteMiddleElement(LinkedList<Integer> linkedList, String testedClassName) {
        linkedList.insert(1);
        linkedList.insert(2);
        linkedList.insert(3);

        linkedList.first();    // Move to 1
        linkedList.next();     // Move to 2
        linkedList.delete();   // Delete 2

        Assertions.assertEquals(2, linkedList.size(), linkedList.getClass().getSimpleName());

        linkedList.first();
        Assertions.assertEquals(1, (int) linkedList.read(), linkedList.getClass().getSimpleName());
        linkedList.next();
        Assertions.assertEquals(3, (int) linkedList.read(), linkedList.getClass().getSimpleName());

        linkedList.next();

        if (linkedList instanceof CircularDoublyLinkedList) {
            Assertions.assertEquals(1, (int) linkedList.read(), linkedList.getClass().getSimpleName());
        } else {
            Assertions.assertNull(linkedList.read(), linkedList.getClass().getSimpleName());
        }
    }

    @ParameterizedTest(name = "testDeleteLastElement on: {1}")
    @MethodSource("loadAllLinkedListChildren")
    void testDeleteLastElement(LinkedList<Integer> linkedList, String testedClassName) {
        linkedList.insert(1);
        linkedList.insert(2);
        linkedList.insert(3);

        linkedList.first();
        linkedList.next();
        linkedList.next();  // Move to 3
        linkedList.delete();  // Delete last

        Assertions.assertEquals(2, linkedList.size(), linkedList.getClass().getSimpleName());

        linkedList.first();
        linkedList.next();
        Assertions.assertEquals(2, (int) linkedList.read(), linkedList.getClass().getSimpleName());

        linkedList.next();

        if (linkedList instanceof CircularDoublyLinkedList) {
            Assertions.assertEquals(1, (int) linkedList.read(), linkedList.getClass().getSimpleName());
        } else {
            Assertions.assertNull(linkedList.read(), linkedList.getClass().getSimpleName());
        }
    }

    @ParameterizedTest(name = "testDeleteOnlyElement on: {1}")
    @MethodSource("loadAllLinkedListChildren")
    void testDeleteOnlyElement(LinkedList<Integer> linkedList, String testedClassName) {
        linkedList.insert(42);

        linkedList.first();
        linkedList.delete();

        Assertions.assertTrue(linkedList.empty(), linkedList.getClass().getSimpleName());
        Assertions.assertEquals(0, linkedList.size(), linkedList.getClass().getSimpleName());
    }

    @ParameterizedTest(name = "testPrevMethod on: {1}")
    @MethodSource("loadAllLinkedListChildren")
    void testPrevMethod(LinkedList<Integer> linkedList, String testedClassName) {
        linkedList.insert(1);
        linkedList.insert(2);
        linkedList.insert(3);

        linkedList.first();   // 1
        linkedList.next();    // 2
        linkedList.next();    // 3

        linkedList.prev();    // back to 2
        Assertions.assertEquals(2, (int) linkedList.read(), linkedList.getClass().getSimpleName());

        linkedList.prev();    // back to 1
        Assertions.assertEquals(1, (int) linkedList.read(), linkedList.getClass().getSimpleName());
    }

    @ParameterizedTest(name = "testFirstAndLast on: {1}")
    @MethodSource("loadAllLinkedListChildren")
    void testFirstAndLast(LinkedList<Integer> linkedList, String testedClassName) {
        linkedList.insert(5);
        linkedList.insert(10);
        linkedList.insert(15);

        Assertions.assertEquals(5, (int) linkedList.first(), linkedList.getClass().getSimpleName());
        Assertions.assertEquals(15, (int) linkedList.last(), linkedList.getClass().getSimpleName());
    }

    @ParameterizedTest(name = "testLinkedListIterator on: {1}")
    @MethodSource("loadAllLinkedListChildren")
    void testLinkedListIterator(LinkedList<Integer> linkedList, String testedClassName) {
        int[] values = {1, 2, 3, 4};
        for (int value: values) {
            linkedList.insert(value);
        }

        int index = 0;
        for (int value : linkedList) {
            Assertions.assertEquals(values[index], value);
            index++;
        }
        Assertions.assertEquals(index, values.length);
    }
}
