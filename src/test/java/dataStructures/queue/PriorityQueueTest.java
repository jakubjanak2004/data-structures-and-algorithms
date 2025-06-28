package dataStructures.queue;

import dsa.dataStructures.queue.PriorityQueue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class PriorityQueueTest {
    private final Random random = new Random();
    @Test
    public void priorityQueueRetrievesElementsSorted() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        List<Integer> randomInputs = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int randomInt = random.nextInt(100);
            randomInputs.add(randomInt);
            priorityQueue.put(randomInt);
        }

        // sort in reversed order
        randomInputs.sort((a, b) -> Integer.compare(b, a));

        for (Integer randomInput : randomInputs) {
            assertEquals(randomInput, priorityQueue.get());
            priorityQueue.dequeue();
        }
    }
}
