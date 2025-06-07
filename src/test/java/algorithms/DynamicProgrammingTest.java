package algorithms;

import dsa.algorithms.dynamicProgramming.Fibonacci;
import dsa.algorithms.dynamicProgramming.Knapsack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static utils.TestHelpers.getIntArray;

public class DynamicProgrammingTest {
    private static int NUMBER_OF_ELEMENTS = 20;
    private static int MIN = 1;
    private static int MAX = 10;

    @Test
    public void testNaiveFibonacci() {
        Assertions.assertEquals(0, Fibonacci.fibonacci(0));
        Assertions.assertEquals(1, Fibonacci.fibonacci(1));
        Assertions.assertEquals(1, Fibonacci.fibonacci(2));
        Assertions.assertEquals(2, Fibonacci.fibonacci(3));
        Assertions.assertEquals(3, Fibonacci.fibonacci(4));
        Assertions.assertEquals(5, Fibonacci.fibonacci(5));
        Assertions.assertEquals(8, Fibonacci.fibonacci(6));
        Assertions.assertEquals(13, Fibonacci.fibonacci(7));
        Assertions.assertEquals(21, Fibonacci.fibonacci(8));
    }

    @Test
    public void testTopDownMatchesNaive() {
        for (int i = 0; i <= 30; i++) {
            Assertions.assertEquals(
                    Fibonacci.fibonacci(i),
                    Fibonacci.topDownFibonacci(i),
                    "Top-down mismatch at i = " + i
            );
        }
    }

    @Test
    public void testBottomUpMatchesNaive() {
        for (int i = 0; i <= 30; i++) {
            Assertions.assertEquals(
                    Fibonacci.fibonacci(i),
                    Fibonacci.bottomUpFibonacci(i),
                    "Bottom-up mismatch at i = " + i
            );
        }
    }

    @Test
    public void testNaiveKnapsack() {
        int[] val = {1, 2, 3};
        int[] wt = {4, 5, 1};
        int W = 5;

        Assertions.assertEquals(4, Knapsack.knapsackNaive(W, val, wt));
    }

    @Test
    public void testTopDownKnapsack() {
        int[] val = getIntArray(NUMBER_OF_ELEMENTS, MIN, MAX);
        int[] wt = getIntArray(NUMBER_OF_ELEMENTS, MIN, MAX);
        int W = NUMBER_OF_ELEMENTS * (MIN + MAX / 2);

        Assertions.assertEquals(Knapsack.knapsackNaive(W, val, wt), Knapsack.topDownKnapsack(W, val, wt));
    }

    @Test
    public void testBottomUpKnapsack() {
        int[] val = getIntArray(NUMBER_OF_ELEMENTS, MIN, MAX);
        int[] wt = getIntArray(NUMBER_OF_ELEMENTS, MIN, MAX);
        int W = NUMBER_OF_ELEMENTS * (MIN + MAX / 2);

        Assertions.assertEquals(Knapsack.knapsackNaive(W, val, wt), Knapsack.bottomUpKnapsack(W, val, wt));
    }
}
