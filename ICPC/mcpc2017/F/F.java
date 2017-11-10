import java.io.*;
import java.util.*;

public class F {
  public static void main(String [] args) {
    Scanner in = new Scanner(System.in);
    String a = in.next();
    String b = in.next();
    int first=0, last=0;
    for (int i = 0; i < a.length(); i++) {
      if (a.charAt(i) != b.charAt(i)) {
        first = i;
        break;
      }
    }
    for (int i = a.length()-1; i >= 0; i--) {
      if (a.charAt(i) != b.charAt(i)) {
        last = i;
        break;
      }
    }
    String sub = a.substring(first, last+1);
    String rev = new StringBuilder(sub).reverse().toString();
    if (!rev.equals(b.substring(first, last+1))) {
      System.out.println(0);
    } 
    else {
      int left = first-1;
      int right = last+1;
      int count = 1;
      while (left >= 0 && right < a.length()) {
        if (a.charAt(left--) == a.charAt(right++)) {
          count++;
        }
        else {
          break;
        }
      }
      System.out.println(count);
    }
  }
}