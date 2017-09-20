import java.io.*;
import java.util.*;

public class D {
  public static void main(String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(f.readLine());
    int[][] in = new int[n][3];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(f.readLine());
      in[i][0] = Integer.parseInt(st.nextToken());
      in[i][1] = Integer.parseInt(st.nextToken());
      in[i][2] = Integer.parseInt(st.nextToken());      
    }
    int[] count = new int[n];
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (overlap(in[i][1], in[i][2], in[j][1], in[j][2])) {
          if (greet(in[i][1], in[i][2], in[j][1], in[j][2], in[i][0], in[j][0])) {
              count[i]++;
              count[j]++;
          }
        }
      }
    }
    String s = "";
    for(int i : count) {
      s += i + " ";
    }
    System.out.println(s.trim());
  }

  public static boolean greet(int s1, int f1, int s2, int f2, int t1, int t2) {
    if (direction(s1, f1) == direction(s2, f2)) {
      if (t1 < t2) {
        return ((t2 - t1) * direction(s1, f1) + s1 == s2);
      }
      else {
        return ((t1 - t2) * direction(s2, f2) + s2 == s1);
      }
    }
    else {
      int st1 = t1;
      int et1 = Math.abs(f1 - s1) + st1;
      int st2 = t2;
      int et2 = Math.abs(f2 - s2) + st2;
      if (!overlap(st1, et1, st2, et2)) {
        return false;
      }
      int st = Math.max(st1, st2);
      int et = Math.min(et1, et2);
      int sp1 = direction(s1, f1) * (st - st1) + s1;
      int sp2 = direction(s2, f2) * (st - st2) + s2;
      int ep1 = direction(s1, f1) * (et - st1) + s1;
      int ep2 = direction(s2, f2) * (et - st2) + s2;
      return overlap(sp1, ep1, sp2, ep2);
    }
  }

  public static int direction(int s, int f) {
    int k = f - s;
    return k / Math.abs(k);
  }

  public static boolean overlap(int a, int b, int c, int d) {
    int s1 = Math.min(a, b);
    int e1 = Math.max(a, b);
    int s2 = Math.min(c, d);
    int e2 = Math.max(c, d);
    return !(e1 < s2 || s1 > e2);
  }
}