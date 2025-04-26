package dataStructures.buffers;

import dsa.dataStructures.queue.ArrayQueue;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ArrayQueueTest {

    @Test
    public void testPutGetDequeueOrder() {
        ArrayQueue<Integer> queue = new ArrayQueue<>(3);
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
        ArrayQueue<String> queue = new ArrayQueue<>(2);
        assertThrows(RuntimeException.class, queue::dequeue);
    }

    @Test
    public void testGetFromEmptyQueueThrows() {
        ArrayQueue<String> queue = new ArrayQueue<>(2);
        assertThrows(RuntimeException.class, queue::get);
    }

    @Test
    public void testPutToFullQueueThrows() {
        ArrayQueue<String> queue = new ArrayQueue<>(2);
        queue.put("a");
        queue.put("b");
        assertThrows(RuntimeException.class, () -> queue.put("c"));
    }

    @Test
    public void testWrapAroundBehavior() {
        ArrayQueue<Integer> queue = new ArrayQueue<>(3);
        queue.put(10);
        queue.put(20);
        queue.put(30);

        queue.dequeue(); // remove 10
        queue.put(40);   // should wrap around to position 0

        assertEquals(20, (int) queue.get());
        queue.dequeue();
        assertEquals(30, (int) queue.get());
        queue.dequeue();
        assertEquals(40, (int) queue.get());
    }
}
