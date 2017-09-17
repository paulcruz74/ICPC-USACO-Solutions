import java.io.*;
import java.util.*;

public class E {
  public static void main(String [] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    while(n != 0) {
      int[][] f = new int[n+1][10000];
      f[0][0] = 1;
      for(int i = 1;i <= n;i++) {
        int faces = in.nextInt();
        for(int j = 0;j <faces;j++) {
          int fVal = in.nextInt();
          for(int k = 0;k < 10000;k++) {
            if (k+fVal < 10000) {
              f[i][k+fVal] += f[i-1][k];
            }
          }
        }
      }
      int r = in.nextInt();
      int m = in.nextInt();
      int[][] goal = new int[m][2];
      for(int i = 0;i < m;i++) {
        goal[i][0] = in.nextInt();
        goal[i][1] = in.nextInt();
      }
      int[] vals = new int[r];
      int [] curr = new int[10000];
      if(!solve(curr, f[n], goal, vals, 0, r)) {
        System.out.println("Impossible");
      }
      n = in.nextInt();
    }
  }
  public static boolean solve(int[] curr, int[] f, int[][] goal, int[] vals, 
                              int filled, int r) {
    if (filled == r) {
      // System.out.println(Arrays.toString(vals));
      boolean works = true;
      for(int i = 0;i < goal.length;i++) {
        if (curr[goal[i][0]] != goal[i][1]) {
          // System.out.println("wanted : " +goal[i][0] + " " +goal[i][1]);
          // System.out.println("instead got: "+ curr[goal[i][0]]);
          works = false;
        }
      }
      if (works) {
        System.out.print("Final die face values are ");
        for(int i = 0;i < r;i++) {
          if (i == r-1) {
            System.out.println(vals[i]);
          }
          else {
            System.out.print(vals[i] + " ");
          }
        }
      }
      return works;
    }
    else {
      for(int i = 1;i <= 50;i++) {
        for(int j = 0;j < f.length;j++) {
          if (i+j < 10000) {
            curr[i+j] += f[j];
          }
        }
        vals[filled] = i;
        if (solve(curr, f, goal, vals, filled+1, r)) {
          return true;
        }
        vals[filled] = 0;
        for(int j = 0;j < f.length;j++) {
          if (i+j < 10000) {
            curr[i+j] -= f[j];
          }
        }
      }
      return false;
    }
  }
}