import java.io.*;
import java.util.*;

public class I {
  public static void main(String [] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int k = in.nextInt();
    int[] cc = new int[k+1];
    for (int i = 0; i < n; i++) {
      cc[in.nextInt()]++;
    }
    int total = 0;
    for (int i = 1; i <= k; i++) {
      total += Math.abs(cc[i] - (n / k));
    }
    System.out.println(total / 2);
  }
}