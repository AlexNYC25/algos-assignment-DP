
/**
 * Glass Falling by Alexis Montes
 */

public class GlassFalling {

  // Do not change the parameters!
  /*
   * For the values (27,2) the program compiles and runs fine, but for the larger
   * value pair (100,3) the program complies but takes a long time to compute, due
   * probably the larger amount of subproblems and no memorization
   */

  public int glassFallingRecur(int floors, int sheets) {
    /*
     * if the num of floors = 1 0r 0 then return the amount of floors left for the
     * first base case
     */

    if (floors == 1 || floors == 0)
      return floors;

    /*
     * if we just have one sheet left then we return the number of floors, since we
     * would have to test every floor to find the optimal floor
     */
    if (sheets == 1)
      return floors;

    /*
     * In the RodCutting problem from chapter 15 the used a infinty value to compare
     * with the results of the recursion, but in java we would just use the biggest
     * possible value from the built in integer class
     */
    int min = Integer.MAX_VALUE;

    // Consider all falls from
    // 1st floor to kth floor
    for (int x = 1; x <= floors; x++) {
      // instead of building a helper function to compare values, we can use the built
      // in math lib
      int res = Math.max(glassFallingRecur(x - 1, sheets - 1), glassFallingRecur(floors - x, sheets));
      if (res < min)
        min = res;
    }

    // return the end result + 1 for the initial drop
    return min + 1;
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  /*
   * uses best parts of recursion and bottom up from recursion used basic
   * algorithm structure with bottom up memorization
   */
  public int glassFallingMemoized(int floors, int sheets) {
    // created 2d arr for mem
    int sheetDrops[][] = new int[sheets + 1][floors + 1];

    // initialized base cases in the table
    for (int x = 0; x <= sheets; x++) {
      sheetDrops[x][1] = 1;
      sheetDrops[x][0] = 0;
    }

    // set up the rest of base cases
    for (int x = 1; x <= floors; x++) {
      sheetDrops[1][x] = x;
    }

    // val to be returned
    int finalResult = 0;

    for (int x = 2; x <= sheets; x++) {
      for (int y = 2; y <= floors; y++) {
        sheetDrops[x][y] = Integer.MAX_VALUE;
        for (int z = 1; z <= y; z++) {
          finalResult = 1 + Math.max(sheetDrops[x - 1][z - 1], sheetDrops[x][y - z]);
          if (finalResult < sheetDrops[x][y]) {
            sheetDrops[x][y] = finalResult;
          }
        }
      }
    }

    // Fill in here and change the return
    // return in now equivalent to the last calculated value in 2d arr
    return sheetDrops[sheets][floors];
  }

  /*
   * For the bottom up we need a 2d array to keep track of previous results
   * 
   */

  // Do not change the parameters!
  public int glassFallingBottomUp(int floors, int sheets) {
    // Fill in here and change the return
    // initialized the array with the sizes of para + 1
    int sheetDrops[][] = new int[sheets + 1][floors + 1];

    // set up base cases in arr for when floors are 0 and 1
    for (int x = 1; x <= sheets; x++) {
      sheetDrops[x][0] = 0;
      sheetDrops[x][1] = 1;
    }

    // set up when there is just one sheet left in the algorithm
    for (int x = 1; x <= floors; x++) {
      sheetDrops[1][x] = 1;
    }

    /*
     * for the rest of the algorithm we can start at values after 1 since the
     * results for value for 1 and 0 are already set up using the above for loop
     * base case
     */

    for (int x = 2; x <= sheets; x++) {
      for (int y = 2; y <= floors; y++) {
        /*
         * set up all values to be the biggest possible value from the Integer class set
         * ip a temp value to hold the biggest of 2 values when comparing the previous
         * value and next sub problem Then find the least of the temp val and current
         * val in arr
         */
        sheetDrops[x][y] = Integer.MAX_VALUE;
        int temp;

        for (int z = 1; z <= y; z++) {
          temp = 1 + Math.max(sheetDrops[x - 1][y - 1], sheetDrops[x][y - z]);
          sheetDrops[x][y] = Math.min(temp, sheetDrops[x][y]);
        }
      }
    }

    // return the last value that was found
    return sheetDrops[sheets][floors];
  }

  public static void main(String args[]) {
    GlassFalling gf = new GlassFalling();

    // Do not touch the below lines of code, and make sure
    // in your final turned-in copy, these are the only things printed
    int minTrials1Recur = gf.glassFallingRecur(27, 2);
    int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
    // issues with recursion
    // changes were applied to use memorized version
    // dont need the third parameter, the mem arr was not passed every time
    int minTrials2Memo = gf.glassFallingMemoized(100, 3);
    int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
    System.out.println(minTrials1Recur + " " + minTrials1Bottom);
    System.out.println(minTrials2Memo + " " + minTrials2Bottom);
  }
}
