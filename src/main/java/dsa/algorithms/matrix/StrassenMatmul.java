package dsa.algorithms.matrix;

import static dsa.algorithms.Utils.deepCopy2D;

public class StrassenMatmul {
    public final static int MINIMAL_SIZE_FOR_RECURSION = 128;

    /**
     * Splits a square matrix of size 2^n x 2^n into four equally sized sub-matrices (quadrants).
     * The result contains: top-left (A11), top-right (A12), bottom-left (A21), and bottom-right (A22).
     *
     * @param matrix the input square matrix to be split
     * @return an array containing the 4 sub-matrices: {A11, A12, A21, A22}
     * @throws IllegalArgumentException if the input matrix is not square or its size is not a power of 2
     */
    public static double[][][] split(final double[][] matrix) {
        int n = matrix.length;

        if (n != matrix[0].length || (n & (n - 1)) != 0) {
            throw new IllegalArgumentException(
                    String.format("Matrix split error: matrix must be square and size must be a power of 2 (got %d x %d).",
                            n, matrix[0].length)
            );
        }

        int mid = n / 2;

        double[][] A11 = new double[mid][mid];
        double[][] A12 = new double[mid][mid];
        double[][] A21 = new double[mid][mid];
        double[][] A22 = new double[mid][mid];

        for (int i = 0; i < mid; i++) {
            for (int j = 0; j < mid; j++) {
                A11[i][j] = matrix[i][j];                   // top-left, C11
                A12[i][j] = matrix[i][j + mid];             // top-right, C12
                A21[i][j] = matrix[i + mid][j];             // bottom-left, C21
                A22[i][j] = matrix[i + mid][j + mid];       // bottom-right, C22
            }
        }

        return new double[][][]{A11, A12, A21, A22};
    }

    /**
     * Performs matrix addition between two matrices of the same shape.
     * Computes: C = A + B
     *
     * @param A the first matrix
     * @param B the second matrix
     * @return a new matrix representing the element-wise sum of A and B
     * @throws IllegalArgumentException if matrices A and B do not have the same dimensions
     */
    public static double[][] matAdd(final double[][] A, final double[][] B) {
        int rows = A.length;
        int cols = A[0].length;

        // Check if B has the same dimensions
        if (B.length != rows || B[0].length != cols) {
            throw new IllegalArgumentException(
                    String.format("Matrix addition error: incompatible shapes A(%d x %d) and B(%d x %d).",
                            rows, cols, B.length, B[0].length)
            );
        }

        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }

        return result;
    }

    /**
     * Multiplies every element of a matrix by a scalar value.
     * Computes: C = a * A
     *
     * @param a the scalar multiplier
     * @param A the matrix to be scaled
     * @return a new matrix resulting from element-wise multiplication by the scalar
     */
    public static double[][] scalarMul(final double a, final double[][] A) {
        double[][] copiedA = deepCopy2D(A);
        for (int row = 0; row < copiedA.length; row++) {
            for (int col = 0; col < copiedA[row].length; col++) {
                copiedA[row][col] = a * copiedA[row][col];
            }
        }
        return copiedA;
    }

    /**
     * Joins four square submatrices into a single larger matrix.
     * The submatrices must be of equal size and represent quadrants:
     * top-left (C11), top-right (C12), bottom-left (C21), bottom-right (C22).
     *
     * @param C11 top-left quadrant
     * @param C12 top-right quadrant
     * @param C21 bottom-left quadrant
     * @param C22 bottom-right quadrant
     * @return the combined square matrix of size (2n x 2n)
     * @throws IllegalArgumentException if the input matrices are not all square or not of the same size
     */
    public static double[][] joinMatrices(final double[][] C11, final double[][] C12, final double[][] C21, final double[][] C22) {
        int mid = C11.length;

        if (C12.length != mid || C21.length != mid || C22.length != mid ||
                C11[0].length != mid || C12[0].length != mid || C21[0].length != mid || C22[0].length != mid) {
            throw new IllegalArgumentException("All input matrices must be square and of equal dimensions.");
        }

        int n = mid * 2;
        double[][] result = new double[n][n];

        for (int i = 0; i < mid; i++) {
            for (int j = 0; j < mid; j++) {
                result[i][j] = C11[i][j];                   // top-left, C11
                result[i][j + mid] = C12[i][j];             // top-right, C12
                result[i + mid][j] = C21[i][j];             // bottom-left, C21
                result[i + mid][j + mid] = C22[i][j];       // bottom-right, C22
            }
        }

        return result;
    }

    /**
     * Multiplies two square matrices using Strassen's algorithm.
     * <p>
     * This method applies divide-and-conquer matrix multiplication optimized to reduce the number of recursive multiplications
     * from 8 to 7, improving the asymptotic time complexity over the standard cubic approach.
     * Only supports matrices of shape (2^n × 2^n).
     * <p>
     * The time complexity of Strassen's algorithm is approximately O(n^log₂7) ≈ O(n^2.81),
     * which is faster than the classic O(n³) algorithm for large enough matrices.
     *
     * @param A the first square matrix
     * @param B the second square matrix
     * @return the result of multiplying A and B using Strassen's algorithm
     * @throws IllegalArgumentException if matrices are not square or their dimensions are not powers of 2
     */
    @MatMul
    public static double[][] strassenMatmul(final double[][] A, final double[][] B) {
        int n = A.length;
        if (A[0].length != n || B.length != n || B[0].length != n) {
            throw new IllegalArgumentException("Strassen algorithm is implemented for square matrices of shape A(n, n) and B(n, n)");
        }
        if ((n & (n - 1)) != 0) {
            throw new IllegalArgumentException("n must be an exact power of 2, ie. 2^x = n where x is integer");
        }

        // base case
        if (n <= MINIMAL_SIZE_FOR_RECURSION) {
            return ClassicMatmul.matmul(A, B);
        }

        // split the matrices
        double[][][] ASplitResult = split(A);
        double[][][] BSplitResult = split(B);

        // initialise sub matrices, 4 matrices for A and B
        double[][] a = ASplitResult[0], b = ASplitResult[1], c = ASplitResult[2], d = ASplitResult[3];
        double[][] e = BSplitResult[0], f = BSplitResult[1], g = BSplitResult[2], h = BSplitResult[3];

        // compute the matrices from recursion
        double[][] p1 = strassenMatmul(matAdd(a, d), matAdd(e, h));
        double[][] p2 = strassenMatmul(d, matAdd(g, scalarMul(-1, e)));
        double[][] p3 = strassenMatmul(matAdd(a, b), h);
        double[][] p4 = strassenMatmul(matAdd(b, scalarMul(-1, d)), matAdd(g, h));
        double[][] p5 = strassenMatmul(a, matAdd(f, scalarMul(-1, h)));
        double[][] p6 = strassenMatmul(matAdd(c, d), e);
        double[][] p7 = strassenMatmul(matAdd(a, scalarMul(-1, c)), matAdd(e, f));

        // compute the matrices in 4 quadrants
        double[][] C11 = matAdd(matAdd(matAdd(p1, p2), scalarMul(-1, p3)), p4);
        double[][] C12 = matAdd(p5, p3);
        double[][] C21 = matAdd(p6, p2);
        double[][] C22 = matAdd(matAdd(matAdd(p5, p1), scalarMul(-1, p6)), scalarMul(-1, p7));

        // join the sub matrices and return them back
        return joinMatrices(C11, C12, C21, C22);
    }

    /**
     * Multiplies two square matrices using the classic divide-and-conquer matrix multiplication algorithm.
     * <p>
     * The input matrices must be square and have dimensions of size 2^n × 2^n.
     * This algorithm recursively splits the input matrices into 4 sub-matrices each,
     * performs 8 recursive multiplications, and combines the results into the final matrix.
     * <p>
     * Unlike Strassen’s algorithm, this version performs all 8 recursive products and has no optimization
     * for reducing the number of multiplications, resulting in a time complexity of O(n³).
     *
     * @param A the first square matrix
     * @param B the second square matrix
     * @return the resulting matrix of A × B computed via recursive divide-and-conquer
     * @throws IllegalArgumentException if the input matrices are not square or their sizes are not powers of 2
     */

    @MatMul
    public static double[][] divideAndConquerMatmul(final double[][] A, final double[][] B) {
        int n = A.length;
        if (A[0].length != n || B.length != n || B[0].length != n) {
            throw new IllegalArgumentException("divideAndConquerMatmul algorithm is implemented for square matrices of shape A(n, n) and B(n, n)");
        }
        if ((n & (n - 1)) != 0) {
            throw new IllegalArgumentException("n must be an exact power of 2, ie. 2^x = n where x is integer");
        }

        // base case
        if (n <= MINIMAL_SIZE_FOR_RECURSION) {
            return ClassicMatmul.matmul(A, B);
        }

        // split the matrices
        double[][][] ASplitResult = split(A);
        double[][][] BSplitResult = split(B);

        // initialise sub matrices, 4 matrices for A and B
        double[][] a = ASplitResult[0], b = ASplitResult[1], c = ASplitResult[2], d = ASplitResult[3];
        double[][] e = BSplitResult[0], f = BSplitResult[1], g = BSplitResult[2], h = BSplitResult[3];

        // compute the matrices from recursion
        //  happens 8 times therefore having worse complexity than strassen algo
        double[][] p1 = divideAndConquerMatmul(a, e);
        double[][] p2 = divideAndConquerMatmul(b, g);
        double[][] p3 = divideAndConquerMatmul(a, f);
        double[][] p4 = divideAndConquerMatmul(b, h);
        double[][] p5 = divideAndConquerMatmul(c, e);
        double[][] p6 = divideAndConquerMatmul(d, g);
        double[][] p7 = divideAndConquerMatmul(c, f);
        double[][] p8 = divideAndConquerMatmul(d, h);

        // compute the matrices in 4 quadrants
        double[][] C11 = matAdd(p1, p2);
        double[][] C12 = matAdd(p3, p4);
        double[][] C21 = matAdd(p5, p6);
        double[][] C22 = matAdd(p7, p8);

        // join the sub matrices and return them back
        return joinMatrices(C11, C12, C21, C22);
    }
}