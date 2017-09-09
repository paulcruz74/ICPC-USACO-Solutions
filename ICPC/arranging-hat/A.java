import java.io.*;
import java.util.*;

public class A {
  static int minDiff = Integer.MAX_VALUE;
  static int[][] bestTrial;
  public static void main(String [] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    String[] nums = new String[n];
    for(int i = 0;i < n;i++) {
      nums[i] = in.next();
    }
    solve(n, m, nums);
  }

  public static void solve(int n, int m, String[] nums) {
    int[][] trial = new int[n][m];
    find(n, m, nums, trial, 0);
    for(int i = 0;i < n;i++) {
      for(int j = 0;j < m;j++) {
        System.out.print(bestTrial[i][j]);
      }
      System.out.println();
    }
  }

  public static void find(int n, int m, String[] nums, int[][] trial, int filled) {
    if (filled == n*m && isSorted(n, m, trial)) {
      int diff = 0;
      for(int i = 0;i < n;i++) {
        for(int j = 0;j < m;j++) {
          if (trial[i][j] != (nums[i].charAt(j) - '0')) {
            diff++;
          }
        }
      }
      if (diff < minDiff) {
        minDiff = diff;
        bestTrial = trial;
      }
    }
    else if (filled < n*m) {
      for(int i = 0;i < 10;i++) {
        trial[filled/m][filled%m] = i;
        find(n, m, nums, trial, filled+1);
      }
    }
  }

  public static boolean isSorted(int n, int m, int[][] trial) {
    String[] newNums = new String[n];
    for(int i = 0;i < n;i++) {
      String s = "";
      for(int j = 0;j < m;j++) {
        s += trial[i][j];
      }
      newNums[i] = s;
    }
    for(int i = 0;i < n - 1;i++) {
      String s1 = newNums[i];
      String s2 = newNums[i+1];
      if (s1.compareTo(s2) > 0) {
        return false;
      }
    }
    return true;
  }

}