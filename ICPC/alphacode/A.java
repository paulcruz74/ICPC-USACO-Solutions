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
    Set<Integer> idx = new HashSet<>();
    for(int i = 0;i < s.length() - 1;i++) {
      if (Integer.parseInt(s.substring(i, i+2)) <= 26) {
        idx.add(i);
      }
    }
    long[] f = new long[s.length()+1];
    f[s.length()] = 1;
    f[s.length() - 1] = 1;
    for(int i = s.length() - 2;i >= 0;i--){
      if (idx.contains(i)) {
        f[i] = f[i+1]+f[i+2];
      }
      else{
        f[i] = f[i+1];
      }
    }
    return f[0];
  }
}