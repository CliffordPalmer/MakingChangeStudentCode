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
        long combinations = tabulateWays(target, coins);
//        for(int i = 0; i < coins.length; i++) {
//            combinations += countWaysRecur(target, coins, coins[i], 0);
//        }
        return combinations;
    }

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

    public static long memoizeWays(int target, int[] coins){
        long[][] ways = new long[coins.length][target + 1];
        // Initialize first column of ways to 1
        for(int i = 0; i < ways.length; i++){
            ways[i][0] = 1;
        }
        return ways[coins.length - 1][target];
    }
    public static long memoizeWays(int target, int[] coins, )
//    public static long countWaysRecur(int target, int[] coins, int coinUsed, int debug) {
//        target = target - coinUsed;
//        if(target < 0) {
//            return 0;
//        }
//        if(target == 0){
//            return 1;
//        }
//        int combinations = 0;
//        for(int i = 0; i < coins.length; i++) {
//            combinations += countWaysRecur(target, coins, coins[i], debug + 1);
//        }
//        return combinations;
//    }
//    public static int count(int target, int[] coins){
//    }
}
