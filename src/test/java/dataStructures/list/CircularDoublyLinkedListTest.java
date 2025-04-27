package dataStructures.list;

import dsa.dataStructures.list.circularLinkedList.CircularDoublyLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CircularDoublyLinkedListTest {

    private CircularDoublyLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new CircularDoublyLinkedList<>();
    }

    @Test
    public void testCircularBehavior() {
        list.insert(10);
        list.insert(20);
        list.insert(30);

        list.first(); // Move to 10
        assertEquals(10, list.read());

        list.next(); // Move to 20
        assertEquals(20, list.read());

        list.next(); // Move to 30
        assertEquals(30, list.read());

        list.next(); // Should go back to start (10)
        assertEquals(10, list.read());

        list.prev(); // Back to 30
        assertEquals(30, list.read());

        list.prev(); // Back to 20
        assertEquals(20, list.read());
    }

    @Test
    public void testCircularNavigation() {
        list.insert(1);
        list.insert(2);
        list.insert(3);

        list.first(); // 1
        assertEquals(1, list.read());

        list.prev(); // 3
        assertEquals(3, list.read());

        list.prev(); // 2
        assertEquals(2, list.read());

        list.prev(); // 1 again
        assertEquals(1, list.read());

        list.prev(); // 3 again
        assertEquals(3, list.read());
    }
}
