/**
 * The class Making Change solves a classic problem:
 * given a set of coins, how many ways can you make change for a target amount?
 *
 * @author Zach Blick
 * @author [YOUR NAME HERE]
 */

public class MakingChange {
    /**
     * TODO: Complete this function, countWays(), to return the number of ways to make change
     *  for any given total with any given set of coins.
     */
    public static long countWays(int target, int[] coins) {
        // Tabulation approach:
        return tabulateWays(target, coins);
        // Memoization approach:
        // return memoizeWays(target, coins);
    }

    // A tabulation approach to countWays
    public static long tabulateWays(int target, int[] coins){
        long[][] ways = new long[coins.length][target + 1];
        // Initialize first column of ways to 1
        for(int i = 0; i < ways.length; i++){
            ways[i][0] = 1;
        }
        // Fill in first row at the beginning to avoid going out of bounds
        // Zero ways until target >= coin
        for(int i = 1; i < coins[0]; i++){
            ways[0][i] = 0;
        }
        // Build remaining ways from previously tabulated ways
        for(int i = coins[0]; i < ways[0].length; i++){
            ways[0][i] = ways[0][i - coins[0]];
        }
        // Fill in the rest of the table
        for(int i = 1; i < ways.length; i++){
            // Until target >= coin, new coin will add zero new ways, so default to previous coin
            for(int j = 1; j < coins[i]; j++){
                ways[i][j] = ways[i-1][j];
            }
            // Build remaining ways from previously tabulated ways
            for (int j = coins[i]; j < ways[0].length; j++){
                ways[i][j] = ways[i-1][j] + ways[i][j-coins[i]];
            }
        }
        return ways[coins.length - 1][target];
    }

    // A memoization approach to countWays
    public static long memoizeWays(int target, int[] coins){
        long[][] ways = new long[coins.length][target + 1];
        // Initialize first column of ways to 1
        for(int i = 0; i < ways.length; i++){
            ways[i][0] = 1;
        }
        // Call recursive memoization approach with proper parameters
        memoizeWays(target, coins, coins.length - 1, ways);
        return ways[coins.length - 1][target];
    }
    // Recursive memoization method
    public static long memoizeWays(int target, int[] coins, int coinsIndex, long[][] ways){
        // Base case keeps everything in-bounds and deals with filling the first row and
        // first few entries in each row until coin >= target
        if(coinsIndex == -1 || target < 0){
            return 0;
        }
        // If this case has already been recorded, return the recorded value
        if(ways[coinsIndex][target] != 0){
            return ways[coinsIndex][target];
        }
        // Recursively call the exclude and include states and record the result for future use
        ways[coinsIndex][target] = memoizeWays(target, coins, coinsIndex - 1, ways) + memoizeWays(target - coins[coinsIndex], coins, coinsIndex, ways);
        // Return this count
        return ways[coinsIndex][target];
    }
}
