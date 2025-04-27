package dataStructures.queue;

import dsa.dataStructures.queue.ListQueue;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ListQueueTest {

    @Test
    public void testGetReturnsNullIfEmpty() {
        ListQueue<String> queue = new ListQueue<>();
        assertNull(queue.get());
    }

    @Test
    public void testIsEmptyAfterOperations() {
        ListQueue<Integer> queue = new ListQueue<>();
        assertTrue(queue.isEmpty());

        queue.put(10);
        assertFalse(queue.isEmpty());

        queue.dequeue();
        assertTrue(queue.isEmpty());
    }
}
