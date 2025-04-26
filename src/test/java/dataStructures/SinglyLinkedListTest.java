package dataStructures;

import dsa.dataStructures.list.LinkedList;
import dsa.dataStructures.list.SinglyLinkedList;
import org.junit.jupiter.api.*;

import static org.junit.Assert.*;

class SinglyLinkedListTest {

    private LinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new SinglyLinkedList<>();
    }

    @Test
    void testEmptyList() {
        assertTrue(list.empty());
        assertEquals(0, list.length());
        assertNull(list.read());
    }

    @Test
    void testInsertFirstElement() {
        list.insert(10);
        assertFalse(list.empty());
        assertEquals(1, list.length());
        assertEquals(10, (int) list.first());
    }

    @Test
    void testInsertMultipleElements() {
        list.insert(1);
        list.insert(2);
        list.insert(3);

        Assertions.assertEquals(3, list.length());

        list.first();
        Assertions.assertEquals(1, (int) list.read());

        list.next();
        Assertions.assertEquals(2, (int) list.read());

        list.next();
        Assertions.assertEquals(3, (int) list.read());

        list.next();

        assertNull(list.read());
    }

    @Test
    void testDeleteMiddleElement() {
        list.insert(1);
        list.insert(2);
        list.insert(3);

        list.first();    // Move to 1
        list.next();     // Move to 2

        list.delete();   // Delete 2

        assertEquals(2, list.length());

        list.first();
        assertEquals(1, (int) list.read());
        list.next();
        assertEquals(3, (int) list.read());
        list.next();
        assertNull(list.read());
    }

    @Test
    void testDeleteLastElement() {
        list.insert(1);
        list.insert(2);
        list.insert(3);

        list.first();
        list.next();
        list.next();  // Move to 3

        list.delete();  // Delete last

        assertEquals(2, list.length());

        list.first();
        list.next();
        assertEquals(2, (int) list.read());
        list.next();
        assertNull(list.read());
    }

    @Test
    void testDeleteOnlyElement() {
        list.insert(42);

        list.first();
        list.delete();

        assertTrue(list.empty());
        assertEquals(0, list.length());
    }

    @Test
    void testPrevMethod() {
        list.insert(1);
        list.insert(2);
        list.insert(3);

        list.first();   // 1
        list.next();    // 2
        list.next();    // 3

        list.prev();    // back to 2
        assertEquals(2, (int) list.read());

        list.prev();    // back to 1
        assertEquals(1, (int) list.read());
    }

    @Test
    void testFirstAndLast() {
        list.insert(5);
        list.insert(10);
        list.insert(15);

        assertEquals(5, (int) list.first());
        assertEquals(15, (int) list.last());
    }
}
