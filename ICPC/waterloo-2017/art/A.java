import java.io.*;
import java.util.*;

public class A {
  public static void main(String [] args) {
    Scanner in = new Scanner(System.in);
    int[] ints = new int[5];
    for (int i = 0; i < 5; i++) 
      ints[i] = in.nextInt();
    int total = 0;
    for (int i = 0; i < 5; i++) {
      for (int j = i+1; j < 5; j++) {
        for (int k = j+1; k < 5; k++) {
          int s1 = ints[i];
          int s2 = ints[j];
          int s3 = ints[k];
          if (works(s1, s2, s3))
            total++;
        }
      }
    }
    System.out.println(total);
  }
  public static boolean works(int a, int b, int c) {
    return (a+b > c && a+c > b && b+c > a);
  }
}