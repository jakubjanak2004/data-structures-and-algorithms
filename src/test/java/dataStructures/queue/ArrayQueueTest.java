package dataStructures.queue;

import dsa.dataStructures.queue.CircularArrayQueue;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ArrayQueueTest {

    @Test
    public void testGetFromEmptyQueueThrows() {
        CircularArrayQueue<String> queue = new CircularArrayQueue<>(2);
        assertThrows(RuntimeException.class, queue::get);
    }

    @Test
    public void testPutToFullQueueThrows() {
        CircularArrayQueue<String> queue = new CircularArrayQueue<>(2);
        queue.put("a");
        queue.put("b");
        assertThrows(RuntimeException.class, () -> queue.put("c"));
    }

    @Test
    public void testWrapAroundBehavior() {
        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(3);
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
