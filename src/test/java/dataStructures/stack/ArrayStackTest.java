package dataStructures.stack;

import dsa.dataStructures.stack.ArrayStack;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ArrayStackTest {
    @Test
    public void testPushToFullStackThrows() {
        ArrayStack<String> stack = new ArrayStack<>(1);
        stack.push("a");
        assertThrows(RuntimeException.class, () -> stack.push("b"));
    }
}
