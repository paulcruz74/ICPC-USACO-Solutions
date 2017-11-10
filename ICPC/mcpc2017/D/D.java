import java.io.*;
import java.util.*;

public class D {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    Set<String> set = new HashSet<>();
    boolean works = true;
    while (in.hasNext()) {
      String s = in.next();
      if (set.contains(s)) {
        works = false;
        break;
      }
      else {
        set.add(s);
      }
    }
    if (works) {
      System.out.println("yes");
    }
    else {
      System.out.println("no");
    }
  }
}