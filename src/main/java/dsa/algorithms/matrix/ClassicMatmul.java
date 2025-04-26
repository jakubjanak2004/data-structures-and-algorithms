package dsa.algorithms.matrix;

public class ClassicMatmul {
    /**
     * Multiplies two matrices A and B using the classic matrix multiplication algorithm.
     * <p>
     * The operation performed is: C = A @ B
     * <br>
     * A is of shape (m x n), B is of shape (n x p), and the resulting matrix C is of shape (m x p).
     * Each element C[i][j] is computed as the dot product of the i-th row of A and the j-th column of B.
     *
     * @param A the left matrix (m x n)
     * @param B the right matrix (n x p)
     * @return the resulting matrix C after multiplication (m x p)
     * @throws IllegalArgumentException if the number of columns in A does not match the number of rows in B
     */
    public static double[][] matmul(double[][] A, double[][] B) {
        // m is the number of rows in A
        int m = A.length;
        // n is the number of columns in A
        int n = A[0].length;
        // n must be equal to the number of rows in B
        if (B.length != n) {
            // throw formatted message
            throw new IllegalArgumentException(
                    String.format("Matrix multiplication error: incompatible shapes A(%d x %d) and B(%d x %d). Columns of A must match rows of B.",
                            m, n, B.length, B[0].length)
            );
        }
        // p is the number of columns in B
        int p = B[0].length;

        // the result matrix C is of size m x p
        double[][] C = new double[m][p];

        // standard matrix multiplication iteration
        // C[i][j] = A[i][1]*B[1][j] + A[i][2]*B[2][j] + ... + A[i][n]*B[n][j]
        // i = 1, 2, ..., m
        // j = 1, 2, ..., p
        // Note: in our case i -> row and j -> col
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < p; col++) {
                for (int k = 0; k < n; k++) {
                    C[row][col] += A[row][k] * B[k][col];
                }
            }
        }

        // returning computed matrix C
        return C;
    }
}
