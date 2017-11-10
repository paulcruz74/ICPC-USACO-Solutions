import java.io.*;
import java.util.*;

public class B {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int walks = 0;
    int total = 0;
    for (int i = 0; i < n; i++) {
      int val = in.nextInt();
      if (val == -1) walks++;
      else total += val;
    }
    System.out.println((double)total/(n-walks));
  }
}