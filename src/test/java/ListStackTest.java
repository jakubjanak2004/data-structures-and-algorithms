import dsa.dataStructures.stack.ListStack;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ListStackTest {
    @Test
    public void testPushTopPopOrder() {
        ListStack<Integer> stack = new ListStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, (int) stack.top());
        stack.pop();
        assertEquals(2, (int) stack.top());
        stack.pop();
        assertEquals(1, (int) stack.top());
    }

    @Test
    public void testPopFromEmptyStackThrows() {
        ListStack<String> stack = new ListStack<>();
        assertThrows(RuntimeException.class, stack::pop);
    }

    @Test
    public void testTopFromEmptyStackThrows() {
        ListStack<String> stack = new ListStack<>();
        assertThrows(RuntimeException.class, stack::top);
    }

    @Test
    public void testLengthAfterPushAndPop() {
        ListStack<String> stack = new ListStack<>();
        assertEquals(0, stack.length());

        stack.push("a");
        assertEquals(1, stack.length());

        stack.push("b");
        stack.push("c");
        assertEquals(3, stack.length());

        stack.pop();
        assertEquals(2, stack.length());

        stack.pop();
        stack.pop();
        assertEquals(0, stack.length());
    }
}
