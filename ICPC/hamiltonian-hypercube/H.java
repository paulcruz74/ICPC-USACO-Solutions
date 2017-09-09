import java.io.*;
import java.util.*;

public class H {
  public static void main(String [] args) {
    Scanner in = new Scanner(System.in);
    int dim = in.nextInt();
    String s1 = in.next();
    String s2 = in.next();
    System.out.println(Math.abs(find(s1) - find(s2)) - 1);
  }
  public static long find(String s) {
    if (s.equals("0")) {
      return 0;
    }
    else if (s.equals("1")) {
      return 1;
    }
    else {
      if (s.charAt(0) == '0') {
        return find(s.substring(1));
      }
      else {
        return (long)(Math.pow(2, s.length())) - 1 - find(s.substring(1));
      }
    }
  }
}