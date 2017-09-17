import java.io.*;
import java.util.*;

public class B {
  public static boolean[] isPrime;
  public static void main(String [] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    int d = in.nextInt();
    isPrime = new boolean[100000];
    // find primes
    for(int i = 2;i < isPrime.length;i++) {
      boolean p = true;
      for(int j = 2;j <= Math.sqrt(i);j++) {
        if (i % j == 0) {
          p = false;
        }
      }
      isPrime[i] = p;
    }
    while (!(n == 0 && m == 0 && d == 0)) {
      boolean[] used = new boolean[m-n+1];
      int[] vals = new int[m-n+1];
      if (solve(n, m, d, used, vals, 0) == false) {
        System.out.println("No anti-prime sequence exists.");
      }
      n = in.nextInt();
      m = in.nextInt();
      d = in.nextInt();
    }
  }
  public static boolean solve(int n, int m, int d, 
                           boolean[] used, int[] vals, int filled) {
    if(filled == m-n+1) {
      String s = "";
      for(int i = 0;i < m-n+1;i++) {
        if (i != m-n) {
          s += vals[i]+",";
        }
        else {
          s += vals[i];
        }
      }
      System.out.println(s);
      return true;
    }
    for(int i = n;i <= m;i++) {
      if (!used[i-n]) {
        boolean works = true;
        int currSum = i;
        for(int j = 1;j <= Math.min(d-1, filled);j++) {
          currSum += vals[filled - j];
          if (isPrime[currSum]) {
            works = false;
          }
        }
        if (works) {
          used[i-n] = true;
          vals[filled] = i;
          if (solve(n, m, d, used, vals, filled+1)) {
            return true;
          }
          used[i-n] = false;
          vals[filled] = 0;
        }
      }
    }
    return false;
  }
}