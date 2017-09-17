import java.io.*;
import java.util.*;

public class A {
  public static void main(String [] args) {
    Scanner in = new Scanner(System.in);
    String s = in.nextLine();
    while(!s.equals("0")) {
      System.out.println(solve(s));
      s = in.nextLine();
    }
  }
  public static long solve(String s) {
    long[] f = new long[s.length()+1];
    f[s.length()] = 1;
    if (Integer.parseInt(s.substring(s.length()-1)) != 0) {
      f[s.length() - 1] = 1;
    }
    else {
      f[s.length() - 1] = 0;
    }
    for(int i = s.length() - 2;i >= 0;i--){
      int a = Integer.parseInt(s.substring(i, i+2));
      int b = a/10;
      int c = a%10;
      if (a <= 26 && a >= 10) {
        f[i] += f[i+2];
      }
      if (c != 0 && b != 0) {
        f[i] += f[i+1];
      }
    }
    return f[0];
  }
}