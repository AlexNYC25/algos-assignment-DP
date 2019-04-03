/**
 * Rod cutting problem described in Chapter 15 of textbook
 */
public class RodCutting {

  // Do not change the parameters!
  public int rodCuttingRecur(int rodLength, int[] lengthPrices) {
    // seting up base cases for algorithm
    if (rodLength <= 1) {
      return 0;
    }

    // acts as -infinity
    int max = Integer.MIN_VALUE;

    for (int x = 0; x < rodLength; x++) {
      max = Math.max(max, lengthPrices[x] + rodCuttingRecur(rodLength - x - 1, lengthPrices));
    }

    return max;
  }

  // Do not change the parameters!
  public int rodCuttingBottomUp(int rodLength, int[] lengthPrices) {
    // set up arr for holding past values
    int possibleVal[] = new int[rodLength + 1];

    // base case for when the rod length is 0, then there can be no size made using
    // it
    possibleVal[0] = 0;

    // Set up the least possible value to act as -infinty
    int max = Integer.MIN_VALUE;

    for (int x = 1; x <= rodLength; x++) {

      for (int y = 0; y < x; y++) {
        max = Math.max(max, lengthPrices[y] + possibleVal[x - y - 1]);
      }

      possibleVal[x] = max;
    }

    return possibleVal[rodLength];
  }

  public static void main(String args[]) {
    RodCutting rc = new RodCutting();

    // In your turned in copy, do not touch the below lines of code.
    // Make sure below is your only output.
    int length1 = 7;
    int[] prices1 = { 1, 4, 7, 3, 19, 5, 12 };
    int length2 = 14;
    int[] prices2 = { 2, 5, 1, 6, 11, 15, 17, 12, 13, 9, 10, 22, 18, 26 };
    int maxSell1Recur = rc.rodCuttingRecur(length1, prices1);
    int maxSell1Bottom = rc.rodCuttingBottomUp(length1, prices1);
    int maxSell2Recur = rc.rodCuttingRecur(length2, prices2);
    int maxSell2Bottom = rc.rodCuttingBottomUp(length2, prices2);
    System.out.println(maxSell1Recur + " " + maxSell1Bottom);
    System.out.println(maxSell2Recur + " " + maxSell2Bottom);
  }
}
