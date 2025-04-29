package dsa.algorithms;

public class Utils {
    static public void swap(int[] array, int i1, int i2) {
        int temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }

    static public <T extends Comparable<T>> void swap(T[] array, int i1, int i2) {
        T temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }

    public static double[][] deepCopy2D(double[][] matrix) {
        return java.util.Arrays.stream(matrix)
                .map(double[]::clone)
                .toArray(double[][]::new);
    }
}
