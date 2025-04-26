package algorithms;

import dsa.algorithms.matrix.ClassicMatmul;
import dsa.algorithms.matrix.StrassenMatmul;
import org.junit.Test;

import static dsa.algorithms.Utils.deepCopy2D;
import static dsa.algorithms.matrix.StrassenMatmul.scalarMul;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class MatmulTest {

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
    public void testValidMultiplication() {
        double[][] A = {
                {1, 2},
                {3, 4}
        };

        double[][] B = {
                {5, 6},
                {7, 8}
        };

        double[][] expected = {
                {19, 22},
                {43, 50}
        };

        double[][] result = ClassicMatmul.matmul(A, B);

        assertEquals(expected.length, result.length);
        assertEquals(expected[0].length, result[0].length);

        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[0].length; j++) {
                assertEquals(expected[i][j], result[i][j], 1e-9);
            }
        }
    }

    @Test
    public void testValidMultiplicationForStrassen() {
        double[][] A = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 8, 7, 6},
                {5, 4, 3, 2}
        };

        double[][] B = {
                {1, 0, 2, 1},
                {0, 1, 2, 0},
                {1, 0, 1, 2},
                {0, 2, 3, 1}
        };

        double[][] expected = ClassicMatmul.matmul(A, B);
        double[][] result = StrassenMatmul.strassenMatmul(A, B);

        assertEquals(expected.length, result.length);
        assertEquals(expected[0].length, result[0].length);

        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[0].length; j++) {
                assertEquals(expected[i][j], result[i][j], 1e-9);
            }
        }
    }

    @Test
    public void testValidMultiplicationForDivideConquer() {
        double[][] A = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 8, 7, 6},
                {5, 4, 3, 2}
        };

        double[][] B = {
                {1, 0, 2, 1},
                {0, 1, 2, 0},
                {1, 0, 1, 2},
                {0, 2, 3, 1}
        };

        double[][] expected = ClassicMatmul.matmul(A, B);
        double[][] result = StrassenMatmul.divideAndConquerMatmul(A, B);

        assertEquals(expected.length, result.length);
        assertEquals(expected[0].length, result[0].length);

        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[0].length; j++) {
                assertEquals(expected[i][j], result[i][j], 1e-9);
            }
        }
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