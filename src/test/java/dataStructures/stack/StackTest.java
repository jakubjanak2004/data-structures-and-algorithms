package dataStructures.stack;

import dsa.dataStructures.stack.Stack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static dataStructures.TestHelpers.getInstantiatedChildrenForClass;
import static org.junit.Assert.assertThrows;

public class StackTest {
    public static Stream<Arguments> loadAllStackImplementations() {
        return getInstantiatedChildrenForClass("dsa.dataStructures.stack", Stack.class);
    }

    @ParameterizedTest(name = "testPushAndTopOrder on: {1}")
    @MethodSource("loadAllStackImplementations")
    public void testPushAndTopOrder(Stack<Integer> stack, String testedClassName) {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        Assertions.assertEquals(30, (int) stack.top());
        stack.pop();
        Assertions.assertEquals(20, (int) stack.top());
        stack.pop();
        Assertions.assertEquals(10, (int) stack.top());
    }

    @ParameterizedTest(name = "testPopFromEmptyStackThrows on: {1}")
    @MethodSource("loadAllStackImplementations")
    public void testPopFromEmptyStackThrows(Stack<Integer> stack, String testedClassName) {
        assertThrows(RuntimeException.class, stack::pop);
    }

    @ParameterizedTest(name = "testTopFromEmptyStackThrows on: {1}")
    @MethodSource("loadAllStackImplementations")
    public void testTopFromEmptyStackThrows(Stack<Integer> stack, String testedClassName) {
        assertThrows(RuntimeException.class, stack::top);
    }

    @ParameterizedTest(name = "testTopFromEmptyStackThrows on: {1}")
    @MethodSource("loadAllStackImplementations")
    public void testLengthAfterPushAndPop(Stack<String> stack, String testedClassName) {;
        Assertions.assertEquals(0, stack.length());

        stack.push("a");
        Assertions.assertEquals(1, stack.length());

        stack.push("b");
        stack.push("c");
        Assertions.assertEquals(3, stack.length());

        stack.pop();
        Assertions.assertEquals(2, stack.length());

        stack.pop();
        stack.pop();
        Assertions.assertEquals(0, stack.length());
    }
}
