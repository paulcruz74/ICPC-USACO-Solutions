import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    for (int i = 0; i < n; i++) {
      int x = in.nextInt();
      int y = in.nextInt();
      int l = in.nextInt();
      for (int j = 1; j <= l; j++) {
        if (j % x == 0 && j % y == 0) {
          System.out.println("FizzBuzz");
        }
        else if (j % x == 0) {
          System.out.println("Fizz");
        }
        else if (j % y == 0) {
          System.out.println("Buzz");
        }
        else {
          System.out.println(j);
        }
      }
    }
  }
}