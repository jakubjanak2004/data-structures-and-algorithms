package dsa.algorithms.dynamicProgramming;

public class Knapsack {
    public static int knapsackNaive(int weight, int[] values, int[] weights) {
        return knapsackNaive(weight, values, weights, values.length);
    }

    private static int knapsackNaive(int weight, int[] values, int[] weights, int n) {
        if (n == 0 || weight == 0) return 0;

        int pick = 0;

        if (weights[n - 1] <= weight) {
            pick = values[n - 1] + knapsackNaive(weight - weights[n - 1], values, weights, n - 1);
        }

        int notPick = knapsackNaive(weight, values, weights, n - 1);

        return Math.max(pick, notPick);
    }

    public static int topDownKnapsack(int weight, int[] values, int[] weights) {
        int[][] memo = new int[values.length + 1][weight + 1];

        for (int i = 0; i <= values.length; i++) {
            for (int j = 0; j <= weight; j++) {
                memo[i][j] = -1;
            }
        }

        return topDownKnapsack(weight, values, weights, values.length, memo);
    }

    private static int topDownKnapsack(int weight, int[] values, int[] weights, int n, int[][] memo) {
        if (n == 0 || weight == 0) return 0;

        if (memo[n][weight] != -1) return memo[n][weight];

        int pick = 0;

        if (weights[n - 1] <= weight) {
            pick = values[n - 1] + topDownKnapsack(weight - weights[n - 1], values, weights, n - 1, memo);
        }

        int notPick = topDownKnapsack(weight, values, weights, n - 1, memo);

        return memo[n][weight] = Math.max(pick, notPick);
    }

    public static int bottomUpKnapsack(int weight, int[] values, int[] weights) {
        int[][] dp = new int[weights.length + 1][weight + 1];

        for (int i = 0; i <= weights.length; i++) {
            for (int j = 0; j <= weight; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    int pick = 0;

                    if (weights[i - 1] <= j) {
                        pick = values[i - 1] + dp[i - 1][j - weights[i - 1]];
                    }

                    int notPick = dp[i - 1][j];

                    dp[i][j] = Math.max(pick, notPick);
                }
            }
        }
        return dp[weights.length][weight];
    }
}
