import dsa.matrix.ClassicMatmul;
import org.junit.Test;

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
}
