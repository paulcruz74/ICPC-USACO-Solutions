import java.io.*;
import java.util.*;

public class I { 
  public static void main(String [] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    for (int i = 0; i < n; i++) {
      int g = in.nextInt();
      int prev = Integer.MIN_VALUE;
      boolean solve = false;
      for (int j = 0;j < g; j++) {
        int next = in.nextInt();
        if (j >= 1 && j < g-1 && next != prev + 1 && !solve) {
          System.out.println(j+1);
          solve = true;
        }
        prev = next;
      }
    }
  }
}