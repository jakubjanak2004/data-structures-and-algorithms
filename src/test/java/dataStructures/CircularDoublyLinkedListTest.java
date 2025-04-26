package dataStructures;

import dsa.dataStructures.list.CircularDoublyLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CircularDoublyLinkedListTest {

    private CircularDoublyLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new CircularDoublyLinkedList<>();
    }

    @Test
    public void testEmptyList() {
        assertEquals(0, list.length());
        assertNull(list.first());
        assertNull(list.last());
    }

    @Test
    public void testSingleInsert() {
        list.insert(5);
        assertEquals(1, list.length());
        assertEquals(5, list.first());
        assertEquals(5, list.last());
    }

    @Test
    public void testMultipleInsert() {
        list.insert(1);
        list.insert(2);
        list.insert(3);

        assertEquals(3, list.length());
        assertEquals(1, list.first());
        assertEquals(3, list.last());
    }

    @Test
    public void testNextAndPrev() {
        list.insert(10);
        list.insert(20);
        list.insert(30);

        list.first(); // Move to 10
        assertEquals(10, list.read());

        list.next(); // Move to 20
        assertEquals(20, list.read());

        list.next(); // Move to 30
        assertEquals(30, list.read());

        list.next(); // Should go back to start being 10
        assertEquals(10, list.read());

        list.prev(); // Back to 30
        assertEquals(30, list.read());

        list.prev(); // Back to 20
        assertEquals(20, list.read());
    }

    @Test
    public void testDeleteMiddleElement() {
        list.insert(100);
        list.insert(200);
        list.insert(300);

        list.first(); // Move to 100
        list.next(); // Move to 200
        list.delete(); // Delete 200

        assertEquals(2, list.length());

        list.first(); // Move to 100
        list.next(); // Should now be 300
        assertEquals(300, list.read());
    }

    @Test
    public void testDeleteFirstElement() {
        list.insert(5);
        list.insert(10);
        list.insert(15);

        list.first(); // Move to 5
        list.delete(); // Delete 5

        assertEquals(2, list.length());
        assertEquals(10, list.first());
    }

    @Test
    public void testDeleteLastElement() {
        list.insert(11);
        list.insert(22);
        list.insert(33);

        list.first(); // Move to 11
        list.next(); // 22
        list.next(); // 33
        list.delete(); // Delete 33

        assertEquals(2, list.length());
        assertEquals(22, list.last());
    }

    @Test
    public void testCircularNavigation() {
        list.insert(1);
        list.insert(2);
        list.insert(3);

        list.first(); // 1
        list.next(); // 2
        list.next(); // 3
        list.next(); // 1 again
        list.next(); // 2 again

        assertEquals(2, list.read());
    }
}
