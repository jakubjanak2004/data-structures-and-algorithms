package dataStructures.buffers;

import dsa.dataStructures.queue.ListQueue;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ListQueueTest {

    @Test
    public void testPutGetDequeueOrder() {
        ListQueue<Integer> queue = new ListQueue<>();
        queue.put(1);
        queue.put(2);
        queue.put(3);

        assertEquals(1, (int) queue.get());
        queue.dequeue();

        assertEquals(2, (int) queue.get());
        queue.dequeue();

        assertEquals(3, (int) queue.get());
    }

    @Test
    public void testDequeueFromEmptyQueueThrows() {
        ListQueue<String> queue = new ListQueue<>();
        assertThrows(RuntimeException.class, queue::dequeue);
    }

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
