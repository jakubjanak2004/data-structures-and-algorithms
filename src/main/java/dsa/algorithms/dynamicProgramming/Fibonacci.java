package dsa.algorithms.dynamicProgramming;

public class Fibonacci {
    public static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static int topDownFibonacci(int n) {
        int[] memo = new int[n + 1];
        return fibAux(n, memo);
    }

    private static int fibAux(int n, int[] memo) {
        if (n <= 1) {
            memo[n] = n;
            return n;
        }
        if (memo[n] > 0) {
            return memo[n];
        }
        memo[n] = fibAux(n - 1, memo) + fibAux(n - 2, memo);
        return memo[n];
    }

    public static int bottomUpFibonacci(int n) {
        if (n <= 1) return n;
        int[] f = new int[n + 1];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }
}
