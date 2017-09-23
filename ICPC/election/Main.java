import java.io.*;
import java.util.*;

public class Main {
  public static double[] f;
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int numTest = in.nextInt();
    f = new double[60];
    f[0] = 1;
    f[1] = 1;
    for (int i = 2; i < f.length; i++) {
      f[i] = i * f[i-1];
    }
    for (int i = 0; i < numTest; i++) {
      int n = in.nextInt();
      int v1 = in.nextInt();
      int v2 = in.nextInt();
      int w = in.nextInt();
      int m = v1 + v2;
      int r = n - m;
      double win = 0, lose = 0, tie = 0;
      for (int j = 0; j <= r; j++) {
        if (v1 + j > v2 + r - j) {
          win += choose(r, j);
        }
        else if (v1 + j < v2 + r - j) {
          lose += choose(r, j);
        }
        else {
          tie += choose(r, j);
        }
      }
      double p = win*100/(win + lose + tie);
      // System.out.println();
      // System.out.println(p);
      // System.out.println(win + " " + lose + " " +tie);
      if (p > w && Math.abs(p-w) > 0.000001) {
        System.out.println("GET A CRATE OF CHAMPAGNE FROM THE BASEMENT!");
      }
      else if (p < 0.000001) {
        System.out.println("RECOUNT!");
      }
      else {
        System.out.println("PATIENCE, EVERYONE!");
      }
    }
  }
  public static double choose(int a, int b) {
    return f[a]/f[b]/f[a-b];
  }
}