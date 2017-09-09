import java.io.*;
import java.util.*;

public class JupiterOrbiter {
  public static void main(String [] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int q = in.nextInt();
    int s = in.nextInt();
    int [] queueMap = new int[s];
    for(int i = 0;i < s;i++) {
      queueMap[i] = in.nextInt() - 1;
    }
    int[] queueSize = new int[q];
    for(int i = 0;i < q;i++) {
      queueSize[i] = in.nextInt();
    }
    boolean works = true;
    for(int i = 0;i < n;i++) {
      int total = 0;
      int d = in.nextInt(); /* downlink size */
      int[] measuredSizes = new int[q];
      for(int j = 0;j < s;j++) {
        int x = in.nextInt();
        total += x;
        measuredSizes[queueMap[j]] += x;
      }
      for(int j = 0;j < q;j++) {
        if (measuredSizes[j] > queueSize[j]) {
          works = false;
        }
      }
      if (total > d) {
        works = false;
      }
    }
    if (works) {
      System.out.println("possible");
    }
    else {
      System.out.println("impossible");
    }
  }
}