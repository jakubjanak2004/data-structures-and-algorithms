package algorithms;

import dsa.algorithms.matmul.ClassicMatmul;
import dsa.algorithms.matmul.MatMul;
import dsa.algorithms.matmul.StrassenMatmul;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static dsa.algorithms.Utils.deepCopy2D;
import static dsa.algorithms.matmul.StrassenMatmul.scalarMul;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class MatmulTest {
    static final double MIN_VALUE = 0;
    static final double MAX_VALUE = 100.0;
    private static final int MAX_MATRIX_SIZE = (int) Math.pow(2, 9);
    private static final ArrayList<double[][]> matricesA = new ArrayList<>();
    private static final ArrayList<double[][]> matricesB = new ArrayList<>();
    Reflections reflections = new Reflections("dsa.algorithms.matrix", new MethodAnnotationsScanner());

    @BeforeAll
    static void setup() {
        for (int i = 2; i <= MAX_MATRIX_SIZE; i *= 2) {
            double[][] newMatrixA = new double[i][i];
            double[][] newMatrixB = new double[i][i];

            for (int row = 0; row < i; row++) {
                for (int col = 0; col < i; col++) {
                    newMatrixA[row][col] = (int) (MIN_VALUE + (MAX_VALUE - MIN_VALUE) * Math.random());
                    newMatrixB[row][col] = (int) (MIN_VALUE + (MAX_VALUE - MIN_VALUE) * Math.random());
                }
            }
            matricesA.add(newMatrixA);
            matricesB.add(newMatrixB);
        }
    }

    @TestFactory
    Stream<DynamicTest> testRecursiveMatmul() {
        return reflections.getMethodsAnnotatedWith(MatMul.class).stream()
                .filter(method -> !method.getAnnotation(MatMul.class).def())
                .flatMap(this::matmulTest);
    }

    private Stream<DynamicTest> matmulTest(Method method) {
        List<DynamicTest> tests = new ArrayList<>();

        for (int i = 0; i < matricesA.size(); i++) {
            double[][] A = matricesA.get(i);
            double[][] B = matricesB.get(i);
            double[][] expected = ClassicMatmul.matmul(A, B);

            DynamicTest test = DynamicTest.dynamicTest(
                    "Matmul test with matrices of size: " + A.length + " using " + method.getName(),
                    () -> {
                        try {
                            double[][] actual = (double[][]) method.invoke(null, A, B);
                            assertArrayEquals(expected, actual);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );

            tests.add(test);
        }

        return tests.stream();
    }

    @Test
    public void testIncompatibleShapesThrowsException() {
        // A = 2 x 2
        double[][] A = {
                {1, 2},
                {3, 4}
        };

        // B = 3 x 2
        double[][] B = {
                {5, 6},
                {7, 8},
                {9, 10}
        };

        Exception exception = assertThrows(IllegalArgumentException.class, () -> ClassicMatmul.matmul(A, B));

        String expectedMessage = "Matrix multiplication error";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testScalarMultiplication() {
        double[][] original = {
                {1, 2},
                {3, 4}
        };

        double[][] copy = deepCopy2D(original);
        double scalar = 2;

        double[][] expected = {
                {2, 4},
                {6, 8}
        };

        double[][] result = scalarMul(scalar, original);

        assertArrayEquals(expected, result);
        assertArrayEquals(copy, original); // Ensure input was not modified
    }

    @Test
    public void testMatrixAddition() {
        double[][] A = {
                {1, 2},
                {3, 4}
        };

        double[][] B = {
                {5, 6},
                {7, 8}
        };

        double[][] expected = {
                {6, 8},
                {10, 12}
        };

        double[][] copyA = deepCopy2D(A);
        double[][] copyB = deepCopy2D(B);

        double[][] result = StrassenMatmul.matAdd(A, B);

        assertArrayEquals(expected, result);
        assertArrayEquals(copyA, A); // Ensure A was not modified
        assertArrayEquals(copyB, B); // Ensure B was not modified
    }
}