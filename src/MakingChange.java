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
        int combinations = 0;
        for(int i = 0; i < coins.length; i++) {
            combinations += countWaysRecur(target, coins, coins[i], 0);
        }
        return combinations;
    }

    public static long countWaysRecur(int target, int[] coins, int coinUsed, int debug) {
        target = target - coinUsed;
        if(target < 0) {
            return 0;
        }
        if(target == 0){
            return 1;
        }
        int combinations = 0;
        for(int i = 0; i < coins.length; i++) {
            combinations += countWaysRecur(target, coins, coins[i], debug + 1);
        }
        return combinations;
    }
}
