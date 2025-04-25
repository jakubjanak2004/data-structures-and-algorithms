import dsa.dataStructures.stack.ArrayStack;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ArrayStackTest {

    @Test
    public void testPushAndTopOrder() {
        ArrayStack<Integer> stack = new ArrayStack<>(3);
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(30, (int) stack.top());
        stack.pop();
        assertEquals(20, (int) stack.top());
        stack.pop();
        assertEquals(10, (int) stack.top());
    }

    @Test
    public void testPopFromEmptyStackThrows() {
        ArrayStack<String> stack = new ArrayStack<>(1);
        assertThrows(RuntimeException.class, stack::pop);
    }

    @Test
    public void testTopFromEmptyStackThrows() {
        ArrayStack<String> stack = new ArrayStack<>(1);
        assertThrows(RuntimeException.class, stack::top);
    }

    @Test
    public void testPushToFullStackThrows() {
        ArrayStack<String> stack = new ArrayStack<>(1);
        stack.push("a");
        assertThrows(RuntimeException.class, () -> stack.push("b"));
    }
}
