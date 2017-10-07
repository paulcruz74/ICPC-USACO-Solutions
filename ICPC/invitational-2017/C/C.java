import java.io.*;
import java.util.*;

public class C {
  public static final int INF = Integer.MAX_VALUE/2;
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[] c = new int[n];
    for (int i = 0; i < n; i++) {
      c[i] = in.nextInt();
    }
    int max = c[c.length-1] + c[c.length-2] + 1;
    int[] greedy = new int[max];
    greedy[0] = 0;
    int idx = 0;
    for (int i = 1; i < max; i++) {
      if (idx != c.length-1 && i == c[idx+1]) {
        idx++;
      } 
      greedy[i] = greedy[i-c[idx]] + 1;
    }
    // System.out.println(Arrays.toString(greedy));
    int[][] optimal = new int[n+1][max];
    for (int i = 0; i < max; i++) {
      optimal[0][i] = INF;
    }
    optimal[0][0] = 0;
    for (int i = 1; i < n+1; i++) {
      for (int j = 0; j < max; j++) {
        if (j < c[i-1]) {
          optimal[i][j] = optimal[i-1][j];
        }
        else {
          optimal[i][j] = Math.min(optimal[i-1][j], optimal[i][j-c[i-1]]+1);
        }
      }
    }
    boolean ans = true;
    for (int i = 0; i < max; i++) {
      if (optimal[n][i] != greedy[i]) {
        System.out.println("non-canonical");
        ans = false;
        break;
      }
    }
    // System.out.println(Arrays.toString(optimal[n]));
    if (ans) {
      System.out.println("canonical");
    }
  }
}