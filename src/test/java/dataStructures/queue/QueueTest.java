package dataStructures.queue;

import dsa.dataStructures.queue.Queue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.Assert.assertThrows;
import static utils.TestHelpers.getInstantiatedChildrenForClass;

public class QueueTest {
    public static Stream<Arguments> loadAllQueueImplementations() {
        return getInstantiatedChildrenForClass("dsa.dataStructures.queue", dsa.dataStructures.queue.Queue.class);
    }

    @ParameterizedTest(name = "testPutGetDequeueOrder on {0}")
    @MethodSource("loadAllQueueImplementations")
    void testPutGetDequeueOrder(Queue<Integer> queue) {
        queue.put(3);
        queue.put(2);
        queue.put(1);

        Assertions.assertEquals(3, (int) queue.get());
        queue.dequeue();

        Assertions.assertEquals(2, (int) queue.get());
        queue.dequeue();

        Assertions.assertEquals(1, (int) queue.get());
    }

    @ParameterizedTest(name = "testDequeueFromEmptyQueueThrows on {0}")
    @MethodSource("loadAllQueueImplementations")
    void testDequeueFromEmptyQueueThrows(Queue<String> queue) {
        assertThrows(RuntimeException.class, queue::dequeue);
    }
}
